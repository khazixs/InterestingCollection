package com.ic.learn.algorithm.exercise;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

public class Game {
    private static ToPo toPo;
    private static int FailedNum;
    private static double FailedSumWeight;
    private static List<ResultProject> resultProjects;
    private static Map<String, ResultProject> itemSD;/*key为srcNodeId+desSrcNodeId*/


    public static void main(String[] args) throws IOException {
        getSourceData();
        sortItems();
        for (int i = toPo.items.length - 1; i >= 0; i--) {
            getPath2(toPo.items[i]);
        }
//        saveResult2();
//        ppp();
        printResult2();
    }

    public static void ppp() {
        Map<String, Item> map = new HashMap<>();
        for (int i = 0; i < toPo.items.length; i++) {
            map.put(toPo.items[i].itemId, toPo.items[i]);
        }
        for (ResultProject r : resultProjects) {
            boolean sign = false;
            if (r.flag) {
                String[] strings = r.path.split(",");
                for (String s : strings) {
                    if (toPo.links.get(s).srcNode.nodeId.equals("Z290") || toPo.links.get(s).desNode.nodeId.equals("Z290")) {
                        sign = true;
                    }
                }
                if (sign) {
                    System.out.println(r.itemId);
                    System.out.println(r.path);
                    Node srcNode = map.get(r.itemId).srcNode;
                    for (String s : strings) {
                        System.out.print(srcNode.nodeId);
                        System.out.print(",");
                        srcNode = toPo.links.get(s).getAnotherNode(srcNode);
                    }
                    System.out.print(srcNode.nodeId);
                    System.out.print("\n");
                    System.out.println(r.car);
                    System.out.println();
                }
            }
        }
    }

    public static void saveResult2() throws IOException {
        File F = new File("F:\\project\\InterestingCollection\\src\\main\\resources\\doc\\result.txt");
        FileWriter fw = new FileWriter(F);
        fw.write("");
        fw = new FileWriter(F, true);
        fw.write(FailedNum + "," + FailedSumWeight + "\n");
        for (ResultProject r : resultProjects) {
            if (r.flag) {
                fw.write(r.itemId + "\n");
                fw.write(r.path + "\n");
                fw.write(r.car + "\n");
            } else {
                fw.write(r.itemId + "\n");
                fw.write("null" + "\n");
                fw.write("null" + "\n");
            }
        }
        fw.flush();
        fw.close();
    }

    public static void printResult2() {
        System.out.print(FailedNum + "," + FailedSumWeight + "\n");
        for (ResultProject r : resultProjects) {
            if (r.flag) {
                System.out.print(r.itemId + "\n");
                System.out.print(r.path + "\n");
                System.out.print(r.car + "\n");
            } else {
                System.out.print(r.itemId + "\n");
                System.out.print("null" + "\n");
                System.out.print("null" + "\n");
            }
        }
    }

    public static void getPath2(Item item) {
        ResultProject resultProject = new ResultProject(item.itemId);
        Map<String, String> paths = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();
        paths.put(item.srcNode.nodeId, "");
        distances.put(item.srcNode.nodeId, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(item.srcNode);
        Node v1;
        Node v2;
        Map<String, Object> res;
        int d1;
        String p1;
        while (!queue.isEmpty()) {
            v1 = queue.poll();
            while ((res = getNextPath2(v1, item.necessaryNodes)) != null) {
                Link a = (Link) res.get("Link");
                int temp = a.cost;
                v2 = a.getAnotherNode(v1);
                v2.visited.add(v1.nodeId);
                v1.visited.add(v2.nodeId);
                d1 = distances.get(v1.nodeId);
                p1 = paths.get(v1.nodeId);
                queue.offer(v2);
                if (!distances.containsKey(v2.nodeId)) {
                    distances.put(v2.nodeId, temp + d1);
                    String b = p1 + a.linkId + ",";
                    paths.put(v2.nodeId, b);
                }
                if (d1 + temp < distances.get(v2.nodeId)) {
                    distances.replace(v2.nodeId, d1 + temp);
                    String b = p1 + a.linkId + ",";
                    paths.replace(v2.nodeId, b);
                }
                if (v2 == item.dstNode) {
                    if (!checkNecessary(item, paths.get(v2.nodeId))) {
//                        System.out.println("没有经过必要节点");
                        resultProject.flag = false;
                        FailedNum++;
                        FailedSumWeight += item.weight;
                        resultProjects.add(resultProject);
                        clearNodes();
                        return;
                    }
                    String[] param = paths.get(v2.nodeId).split(",");
                    int c = getCar(item, param, item.weight, a.cars.size());
                    int type = 0;
                    if (c != -1) {
                        if (!item.srcNode.hasWorker.get(param[0])[c - 1]) {
                            if (item.srcNode.availableWorkerNumber > 0) {
                                type += 1;
                            } else {
                                resultProject.flag = false;
                                FailedNum++;
                                FailedSumWeight += item.weight;
                                resultProjects.add(resultProject);
                                clearNodes();
                                return;
                            }
                        }
                        if (!item.dstNode.hasWorker.get(a.linkId)[c - 1]) {
                            if (item.dstNode.availableWorkerNumber > 0) {
                                type += 10;
                            } else {
                                resultProject.flag = false;
                                FailedNum++;
                                FailedSumWeight += item.weight;
                                resultProjects.add(resultProject);
                                clearNodes();
                                return;
                            }
                        }
                        switch (type) {
                            case 1:

                                item.srcNode.hasWorker.get(param[0])[c - 1] = true;
                                item.srcNode.availableWorkerNumber--;
                                break;
                            case 10:
                                item.dstNode.hasWorker.get(a.linkId)[c - 1] = true;
                                item.dstNode.availableWorkerNumber--;
                                break;
                            case 11:
                                item.srcNode.hasWorker.get(param[0])[c - 1] = true;
                                item.srcNode.availableWorkerNumber--;
                                item.dstNode.hasWorker.get(a.linkId)[c - 1] = true;
                                item.dstNode.availableWorkerNumber--;
                                break;
                        }


                        resultProject.path = paths.get(v2.nodeId).substring(0, paths.get(v2.nodeId).length() - 1);
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < param.length; i++) {
                            toPo.links.get(param[i]).cars.get(c - 1).availableWeight -= item.weight;
                            toPo.links.get(param[i]).cars.get(c - 1).used = true;
                            builder.append(c);
                            if (i != param.length - 1) {
                                builder.append(",");
                            }
                        }
                        resultProject.flag = true;
                        resultProject.car = builder.toString();
                        resultProjects.add(resultProject);
                        clearNodes();
                        return;
                    } else {
//                        System.out.println(item.itemId + "：最短路径上无可用不换乘车");
                        resultProject.flag = false;
                        FailedNum++;
                        FailedSumWeight += item.weight;
                        resultProjects.add(resultProject);
                        clearNodes();
                        return;
                    }
                }
                if ((Boolean) res.get("necessary")) {
                    break;
                }
            }
        }
//        System.out.println(item + "：规划失败");
        resultProject.flag = false;
        FailedNum++;
        FailedSumWeight += item.weight;
        resultProjects.add(resultProject);
        clearNodes();
    }

    private static int getCar(Item item, String[] path, double weight, int MAX_CARS) {
        int count = 0;
        int i;
        int ban = -1;
        String search = item.srcNode.nodeId + item.dstNode;
        if (itemSD.containsKey(search)) {
            ResultProject resultProject = itemSD.get(search);
            int car = resultProject.car.charAt(0);
            for (String s : path) {
                Car c = toPo.links.get(s).cars.get(car - 1);
                if (compareTwoDouble(c.availableWeight, weight) < 0) {
                    break;
                } else {
                    count++;
                }
            }
            if (count == path.length) {
                return car;
            } else {
                ban = car;
            }

        } else {
            for (i = 0; i < MAX_CARS; i++) {
                count = 0;
                if (i == ban - 1) {
                    continue;
                }
                for (String s : path) {
                    Car c = toPo.links.get(s).cars.get(i);
                    if (c.used) {
                        break;
                    }
                    if (compareTwoDouble(c.availableWeight, weight) < 0) {
                        break;
                    } else {
                        count++;
                    }
                }
                if (count == path.length) {
                    break;
                }
            }
            if (count == path.length) {
                return i + 1;
            } else {
                return -1;
            }
        }
        return -1;
    }


    private static Map<String, Object> getNextPath2(Node v1, List<Node> necessaryNodes) {
        Map<String, Object> res = new HashMap<>();
        if (!necessaryNodes.isEmpty()) {
            for (Link link : v1.relatedLink) {
                Node toNode = link.getAnotherNode(v1);
                if (necessaryNodes.contains(toNode) && !toNode.visited.contains(v1.nodeId)) {
                    res.put("Link", link);
                    res.put("necessary", true);
                    return res;
                }
            }
        }
        for (Link link : v1.relatedLink) {
            Node target = link.getAnotherNode(v1);
            if (!target.visited.contains(v1.nodeId)) {
                res.put("Link", link);
                res.put("necessary", false);
                return res;
            }
        }
        return null;
    }


    /*获取数据源*/
    public static void getSourceData() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String s = in.readLine();
            String[] dataSize = s.split(",");//依次是站点数，轨道数，列车数，列车容量
            int nodeNum = Integer.parseInt(dataSize[0]);
            int linkNum = Integer.parseInt(dataSize[1]);
            int MAX_CarNum = Integer.parseInt(dataSize[2]);
            double MAX_CarWeight = Double.parseDouble(dataSize[3]);
            Map<String, Node> nodes = new HashMap<>();
            Map<String, Link> links = new HashMap<>();
            for (int i = 0; i < nodeNum; i++) {
                s = in.readLine();
                /*读取站点信息*/

                String[] data = s.split(",");
                nodes.put(data[0], new Node(data[0], Integer.parseInt(data[1])));
            }

            for (int i = 0; i < linkNum; i++) {
                s = in.readLine();
                /*读取轨道信息*/

                String[] data = s.split(",");
                Link tempLink = new Link(data[0], MAX_CarNum, MAX_CarWeight, nodes.get(data[1]), nodes.get(data[2]));
                links.put(data[0], tempLink);
                nodes.get(data[1]).relatedLink.add(tempLink);
                nodes.get(data[1]).hasWorker.put(tempLink.linkId, new boolean[MAX_CarNum]);
                nodes.get(data[2]).relatedLink.add(tempLink);
                nodes.get(data[2]).hasWorker.put(tempLink.linkId, new boolean[MAX_CarNum]);
            }

            int itemNum = Integer.parseInt(in.readLine());
            Item[] items = new Item[itemNum];
            for (int i = 0; i < itemNum; i++) {
                s = in.readLine();
                /*读取货物信息*/

                String[] data = s.split(",");
                Item tempItem = new Item(data[0], nodes.get(data[1]), nodes.get(data[2]), Double.parseDouble(data[3]));
                for (int j = 4; j < data.length; j++) {
                    if (data[j].equals("null")) {
                        break;
                    } else {
                        tempItem.necessaryNodes.add(nodes.get(data[j]));
                    }
                }
                items[i] = tempItem;
            }
            toPo = new ToPo(links, nodes, items);
            resultProjects = new ArrayList<>();
            itemSD = new HashMap<>();

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*比较两个double值*/
    private static int compareTwoDouble(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        /*返回1代表d1>d2，返回-1代表d1<d2，返回零表示相等*/
        return b1.compareTo(b2);
    }

    /*采用快排对货物按照重量进行排序*//*将货物按照重量从小到大排序*/
    private static void sortItems() {
        int length;
        if (toPo.items == null || (length = toPo.items.length) == 0 || length == 1) {
            return;
        }
        quickSort(toPo.items, 0, length - 1);
    }

    /*快排函数*/
    private static void quickSort(Item[] items, int left, int right) {
        if (left > right) {
            return;
        }
        // base中存放基准数
        Item base = items[left];
        int i = left, j = right;
        while (i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (items[j].weight >= base.weight && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while (items[i].weight <= base.weight && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                Item tmp = items[i];
                items[i] = items[j];
                items[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        items[left] = items[i];
        items[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        quickSort(items, left, i - 1);
        quickSort(items, i + 1, right);
    }

    /*清理节点的访问记录*/
    private static void clearNodes() {
        for (Node n : toPo.nodes.values()) {
            n.visited = new ArrayList<>();
        }
    }

    private static boolean checkNecessary(Item item, String path) {
        for (Node n : item.necessaryNodes) {
            for (Link link : n.relatedLink) {
                Pattern p = Pattern.compile(link.linkId);
                if (!p.matcher(path).find()) {
                    return false;
                }
            }
        }

        return true;
    }
}

class ToPo {
    Map<String, Link> links;
    Map<String, Node> nodes;
    Item[] items;

    public ToPo(Map<String, Link> links, Map<String, Node> nodes, Item[] items) {
        this.links = links;
        this.nodes = nodes;
        this.items = items;
    }
}

class ResultProject {
    String itemId;
    String path;
    String car;
    boolean flag;

    public ResultProject(String itemId) {
        this.itemId = itemId;
        this.flag = false;
    }
}

class Node {
    String nodeId;
    List<Link> relatedLink;
    int totalWorkerNumber;
    int availableWorkerNumber;
    List<String> visited;
    Map<String, boolean[]> hasWorker;

    public Node(String nodeId, int totalWorkerNumber) {
        this.nodeId = nodeId;
        this.totalWorkerNumber = totalWorkerNumber;
        this.availableWorkerNumber = totalWorkerNumber;
        this.relatedLink = new ArrayList<>();
        this.hasWorker = new HashMap<>();
        this.visited = new ArrayList<>();
    }
}

class Link {
    String linkId;
    Node srcNode;
    Node desNode;
    List<Car> cars;
    int cost = 1;

    public Node getAnotherNode(Node node) {
        if (node == srcNode) {
            return desNode;
        } else if (node == desNode) {
            return srcNode;
        }
        return null;
    }

    public Link(String linkId, int carNum, double carWeight, Node srcNode, Node desNode) {
        this.linkId = linkId;
        this.srcNode = srcNode;
        this.desNode = desNode;
        this.cars = Arrays.asList(new Car[carNum]);
        for (int i = 0; i < carNum; i++) {
            cars.set(i, new Car(linkId, i + 1, carWeight));
        }
    }
}

class Car {
    String linkId;
    int carId;
    double maxWeight;
    double availableWeight;
    boolean used;
    List<String> items;

    public Car(String linkId, int carId, double maxWeight) {
        this.linkId = linkId;
        this.carId = carId;
        this.maxWeight = maxWeight;
        this.availableWeight = maxWeight;
        this.used = false;
        this.items = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Car{" +
                "linkId='" + linkId + '\'' +
                ", carId=" + carId +
                ", maxWeight=" + maxWeight +
                ", availableWeight=" + availableWeight +
                '}';
    }
}

class Item {
    String itemId;
    Node srcNode;
    Node dstNode;
    double weight;
    List<Node> necessaryNodes;

    public Item(String itemId, Node srcNode, Node dstNode, double weight) {
        this.itemId = itemId;
        this.srcNode = srcNode;
        this.dstNode = dstNode;
        this.weight = weight;
        this.necessaryNodes = new ArrayList<>();
    }
}

