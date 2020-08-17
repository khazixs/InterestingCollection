package com.ic.learn.others;

public class WatchItCarefully {

}
package com.IceCoLa.service.impl;/*
 * @program: BlockchainDemandResponse_Server
 * @author: IceCoLa
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.IceCoLa.pojo.Transaction;
import com.IceCoLa.pojo.TransactionLog;
import com.IceCoLa.service.MatchTxService;
import com.IceCoLa.service.TransactionLogService;
import com.IceCoLa.service.TransactionService;
import com.IceCoLa.utils.CryptoUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("matchTxService")
// TODO: 2019/12/6 需要在调取订单信息的短时间内禁止修改订单
public class MatchTxServiceImpl implements MatchTxService {
    @Resource
    private TransactionService transactionService;
    @Resource
    private TransactionLogService transactionLogService;
    private static final int powerGirdBuyPrice = 45;
    private static final int powerGirdSellPrice = 60;
    private static final String powerGirdHash = "asdasdasda123123124123131321";

    @Scheduled(cron = "0 0 0/1 * * ？")/*每一个小时执行一次*/
    @Override
    public void matchAllTx() throws Exception {
        List<Transaction> publishingTransactions = transactionService.getTransactionsByOrderStatus("publishing");
        List<Transaction> transactionsB = new ArrayList<>();//用来存储可以撮合的买方订单对象
        List<Transaction> transactionsS = new ArrayList<>();//用来存储可以撮合的卖方订单对象
        List<TransactionLog> transactionLogs = new ArrayList<>();//存储记录已完成交易的对象
        int totalB = 0;
        int totalS = 0;
        for (Transaction tx : publishingTransactions) {
            if (tx.getType().equals("B") && tx.getPriceLimit() >= powerGirdBuyPrice) {
                transactionsB.add(tx);
                totalB = totalB + tx.getEnergy();
            } else if (tx.getType().equals("S") && tx.getPriceLimit() <= powerGirdSellPrice) {
                transactionsS.add(tx);
                totalS = totalS + tx.getEnergy();
            }
        }
        //需要保证的是卖电价格要低于电网卖电价格，电网收购价格要低于用户买电价格，保证比较少的一端一定能够消纳才和电网结算
        //如果供大于求，则以买家为着眼点进行撮合
        //如果供小于求，则以卖家为着眼点进行撮合

        if (totalB < totalS) {
            //这种情况下对列表价格限制以从高到低进行排序，先对能出价高的买电用户进行撮合
            mergeSort(transactionsS);
            mergeSort(transactionsB);
            int indexB = transactionsB.size() - 1;
            int indexS = transactionsS.size() - 1;
            while (transactionsB.size() != 0) {
                if (transactionsB.get(indexB).getPriceLimit() < transactionsS.get(indexS).getPriceLimit() && indexS >= 1) {
                    indexS--;
                } else {
                    //找到价格最接近的两个订单进行交易并生成交易成功记录
                    TransactionLog transactionLog = new TransactionLog();
                    transactionLog.setBuyerHash(transactionsB.get(indexB).getAccountHash());
                    transactionLog.setSellerHash(transactionsS.get(indexS).getAccountHash());
                    transactionLog.setDealPrice((transactionsS.get(indexS).getPriceLimit() + transactionsB.get(indexB).getPriceLimit()) / 2);
                    transactionLog.setSettlementInfo("");
                    if (transactionsB.get(indexB).getEnergy() > transactionsS.get(indexS).getEnergy()) {
                        transactionLog.setDealEnergyValue(transactionsS.get(indexS).getEnergy());
                        /*从列表中移除已经完成的订单并更改状态及数据库，此处需要移除完成的卖家订单*/
                        //1.首先修改买家订单
                        transactionsB.get(indexB).setEnergy(transactionsB.get(indexB).getEnergy() - transactionsS.get(indexS).getEnergy());
                        //2.修改卖家订单
                        transactionsS.get(indexS).setOrderStatus("succeeded");
                        transactionService.changeTransaction(transactionsS.get(indexS));
                        //3.移除卖家订单，并重置索引值
                        transactionsS.remove(indexS);
                        indexS = transactionsS.size() - 1;
                    } else {
                        transactionLog.setDealEnergyValue(transactionsB.get(indexB).getEnergy());
                        /*从列表中移除已经完成的订单并更改状态及数据库，此处需要移除完成的买家订单*/
                        //1.首先修改卖家订单
                        transactionsS.get(indexS).setEnergy(transactionsS.get(indexS).getEnergy() - transactionsB.get(indexB).getEnergy());
                        //2.修改买家订单
                        transactionsB.get(indexB).setOrderStatus("succeeded");
                        transactionService.changeTransaction(transactionsB.get(indexB));
                        //3.移除买家订单，并重置索引值
                        transactionsB.remove(indexB);
                        indexB = indexB - 1;
                        indexS = transactionsS.size() - 1;
                    }
                    transactionLog.setTransactionLogCode(CryptoUtil.generateHash(transactionLog));
                    int count = transactionLogService.saveTransactionLog(transactionLog);
//                    transactionLogService.createBlockTransactionLog(transactionLog);
                    if (count != 1) {
                        throw new IllegalArgumentException("日志保存失败");
                    }

                }
            }
            //todo
            for (Transaction txS : transactionsS) {
                if (txS.isSettlement()) {
                    txS.setOrderStatus("succeeded");
                    TransactionLog transactionLog = new TransactionLog();
                    transactionLog.setSettlementInfo("powerGirdSettlement");
                    transactionLog.setDealPrice(powerGirdBuyPrice);
                    transactionLog.setSellerHash(txS.getAccountHash());
                    transactionLog.setDealEnergyValue(txS.getEnergy());
                    transactionLog.setBuyerHash(powerGirdHash);
                    transactionLog.setTransactionLogCode(CryptoUtil.generateHash(transactionLog));
                    transactionService.changeTransaction(txS);
                    int count = transactionLogService.saveTransactionLog(transactionLog);
//                    transactionLogService.createBlockTransactionLog(transactionLog);
                    if (count != 1) {
                        throw new IllegalArgumentException("日志保存失败");
                    }
                } else {
                    txS.setOrderStatus("timeOut");
                    transactionService.changeTransaction(txS);
                }
            }


        } else {
            //这种情况下对列表价格限制以从低到高进行排序，先对能售价低的卖电用户进行撮合
            mergeSort(transactionsS);
            mergeSort(transactionsB);
            int indexB = 0;
            int indexS = 0;
            while (transactionsS.size() != 0) {
                if (transactionsB.get(indexB).getPriceLimit() < transactionsS.get(indexS).getPriceLimit() && indexB < transactionsB.size()) {
                    indexB++;
                } else {
                    //找到价格最接近的两个订单进行交易并生成交易成功记录
                    TransactionLog transactionLog = new TransactionLog();
                    transactionLog.setBuyerHash(transactionsB.get(indexB).getAccountHash());
                    transactionLog.setSellerHash(transactionsS.get(indexS).getAccountHash());
                    transactionLog.setDealPrice((transactionsS.get(indexS).getPriceLimit() + transactionsB.get(indexB).getPriceLimit()) / 2);
                    transactionLog.setSettlementInfo("");
                    if (transactionsB.get(indexB).getEnergy() > transactionsS.get(indexS).getEnergy()) {
                        transactionLog.setDealEnergyValue(transactionsS.get(indexS).getEnergy());
                        /*从列表中移除已经完成的订单并更改状态及数据库，此处需要移除完成的卖家订单*/
                        //1.首先修改买家订单
                        transactionsB.get(indexB).setEnergy(transactionsB.get(indexB).getEnergy() - transactionsS.get(indexS).getEnergy());
                        //2.修改卖家订单
                        transactionsS.get(indexS).setOrderStatus("succeeded");
                        transactionService.changeTransaction(transactionsS.get(indexS));
                        //3.移除卖家订单，并重置索引值
                        transactionsS.remove(indexS);
                        indexB = 0;
                    } else {
                        transactionLog.setDealEnergyValue(transactionsB.get(indexB).getEnergy());
                        /*从列表中移除已经完成的订单并更改状态及数据库，此处需要移除完成的买家订单*/
                        //1.首先修改卖家订单
                        transactionsS.get(indexS).setEnergy(transactionsS.get(indexS).getEnergy() - transactionsB.get(indexB).getEnergy());
                        //2.修改买家订单
                        transactionsB.get(indexB).setOrderStatus("succeeded");
                        transactionService.changeTransaction(transactionsB.get(indexB));
                        //3.移除买家订单，并重置索引值
                        transactionsB.remove(indexB);
                        indexB = 0;
                        indexS++;
                    }
                    transactionLog.setTransactionLogCode(CryptoUtil.generateHash(transactionLog));
                    int count = transactionLogService.saveTransactionLog(transactionLog);
//                    transactionLogService.createBlockTransactionLog(transactionLog);
                    if (count != 1) {
                        throw new IllegalArgumentException("日志保存失败");
                    }
                }
            }
            for (Transaction txB : transactionsB) {
                if (txB.isSettlement()) {
                    txB.setOrderStatus("succeeded");
                    TransactionLog transactionLog = new TransactionLog();
                    transactionLog.setSellerHash(powerGirdHash);
                    transactionLog.setBuyerHash(txB.getAccountHash());
                    transactionLog.setSettlementInfo("powerGirdSettlement");
                    transactionLog.setDealPrice(powerGirdSellPrice);
                    transactionLog.setDealEnergyValue(txB.getEnergy());
                    transactionLog.setTransactionLogCode(CryptoUtil.generateHash(transactionLog));
                    transactionService.changeTransaction(txB);
                    int count = transactionLogService.saveTransactionLog(transactionLog);
//                    transactionLogService.createBlockTransactionLog(transactionLog);
                    if (count != 1) {
                        throw new IllegalArgumentException("日志保存失败");
                    }
                } else {
                    txB.setOrderStatus("timeOut");
                    transactionService.changeTransaction(txB);
                }
            }
        }
    }

    /*下面是归并排序，将订单对象按照priceLimit升序排列*/
    public void mergeSort(List<Transaction> transactions) {
        sort(transactions, 0, transactions.size() - 1);
    }

    public void sort(List<Transaction> transactions, int left, int right) {
        if (left > right)
            return;
        //找出中间索引
        int center = (left + right) / 2;
        //对左边数组进行递归
        sort(transactions, left, center);
        //对右边数组进行递归
        sort(transactions, center + 1, right);
        //合并
        merge(transactions, left, center, right);
    }

    public void merge(List<Transaction> transactions, int left, int center, int right) {
        //临时数组
        List<Transaction> tempTransactions = new ArrayList<>(transactions);
        //右数组第一个元素索引
        int mid = center + 1;
        //third记录临时数组的索引
        int third = left;
        //缓存左数组第一个元素的索引
        int temp = left;
        while (left <= center && mid <= right) {
            //从两个数组中取出最小的放入临时数组
            if (transactions.get(left).getPriceLimit() <= transactions.get(mid).getPriceLimit()) {
                //后加加是先使用后加加
                tempTransactions.set(third++, transactions.get(left++));
            } else {
                tempTransactions.set(third++, transactions.get(mid++));
            }
        }
        //将剩余的部分一次放入临时数组（实际上两个while只会执行一个）
        while (mid <= right) {
            tempTransactions.set(third++, transactions.get(mid++));
        }
        while (left < center) {
            tempTransactions.set(third++, transactions.get(left++));
        }
        //将临时数组中的内容拷贝到原数组中去
        while (temp <= right) {
            transactions.set(temp, tempTransactions.get(temp++));
        }
    }
}





package com.IceCoLa.service.impl;

import com.IceCoLa.contract.*;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.itao.bdrs.contract.*;
import com.IceCoLa.service.ContractFactoryService;
import org.fisco.bcos.web3j.tx.Contract;

import javax.annotation.PostConstruct;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.Properties;


/**
 * @author hetao
 * @date 2019-11-27
 * @description
 */
@Service("contractFactoryService")
public class ContractFactoryServiceImpl implements ContractFactoryService {
    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;

    private static BigInteger gasPrice = new BigInteger("30000000");
    private static BigInteger gasLimit = new BigInteger("30000000");
    private DemandSideResponseTable demandSideResponseTable;
    private DevicesTable devicesTable;
    private DirectTransfer directTransfer;
    private EnergyTransactionLog energyTransactionLog;
    private EnergyTransactionTable energyTransactionTable;
    private UserTable userTable;

    //
    @PostConstruct
    public void init(){
        try {
            Properties prop = loadAddrProperties();
            loadContract(prop);
            saveAddrProperties(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Properties loadAddrProperties() throws IOException {
        Properties properties = new Properties();
        Resource resource = new ClassPathResource("contract.properties");
        properties.load(resource.getInputStream());
        return properties;
    }

    private void saveAddrProperties(Properties prop) throws IOException {
        Resource resource = new ClassPathResource("contract.properties");
        prop.store(new FileOutputStream(resource.getFile()),"smart contract address");
    }

    private void loadContract(Properties prop) throws Exception {
        Class<?> clazz = ContractFactoryServiceImpl.class;
        Field[] fields=clazz.getDeclaredFields();
        for (Field field : fields) {
            Class<?> fieldClazz = field.getType();
            if (!Contract.class.isAssignableFrom(fieldClazz)){
                continue;
            }
            field.setAccessible(true);
            String key = field.getName();
            StaticGasProvider provider = new StaticGasProvider(gasPrice, gasLimit);
            Object val;
            if (!StringUtils.isEmpty(prop.getProperty(key))){
                Method load = fieldClazz.getMethod("load", String.class, Web3j.class, Credentials.class, ContractGasProvider.class);
                val = load.invoke(null, prop.getProperty(key), web3j, credentials, provider);
            }else {
                Method deploy = fieldClazz.getMethod("deploy", Web3j.class, Credentials.class, ContractGasProvider.class);
                RemoteCall<?> call = (RemoteCall<?>) deploy.invoke(null, web3j, credentials, provider);
                val = call.send();
                Method create=null;
                try {
                    create = fieldClazz.getMethod("create");
                }catch (NoSuchMethodException ex){ }
                if (create!=null){
                    RemoteCall<?> createCall= (RemoteCall<?>) create.invoke(val);
                    createCall.send();
                }
                prop.setProperty(key, ((Contract)val).getContractAddress());
            }
            field.set(this, val);
        }
    }


    @Override
    public DemandSideResponseTable getDemandSideResponseTable() {
        return demandSideResponseTable;
    }

    @Override
    public DevicesTable getDevicesTable() {
        return devicesTable;
    }

    @Override
    public DirectTransfer getDirectTransfer() {
        return directTransfer;
    }

    @Override
    public EnergyTransactionLog getEnergyTransactionLog() {
        return energyTransactionLog;
    }

    @Override
    public EnergyTransactionTable getEnergyTransactionTable() {
        return energyTransactionTable;
    }

    @Override
    public UserTable getUserTable() {
        return userTable;
    }
}
