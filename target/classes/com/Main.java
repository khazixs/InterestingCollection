package com;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Main {
    private static ToPo toPo;
    private static List<ItemProject> itemProjects;
    private static int FailedNum;
    private static int FailedSumWeight;
    private static List<ResultProject> resultProjects;
    private static Item errorItem;

    public static void main(String[] args) throws IOException {
        getSourceData();
        sortItems();
        for (int i = toPo.items.length - 1; i >= 0; i--) {
            errorItem = toPo.items[i];
            getPath2(toPo.items[i]);

        }
        saveResult2();
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

    public static void getPath2(Item item) {
        if (item.itemId.equals("G1153")){
            System.out.println("chucuo");
        }
        ResultProject resultProject = new ResultProject(item.itemId);
        Map<String, String> paths = new HashMap<>();
        Map<String, Integer> distances = new HashMap<>();
        paths.put(item.srcNode.nodeId, "");
        distances.put(item.srcNode.nodeId, 0);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(item.srcNode);
        Node v1;
        Node v2;
        Link last = null;
        Map<String, Object> res;
        int d1;
        String p1;
        while (!queue.isEmpty()) {
            v1 = queue.poll();
            String[] strings = paths.get(v1.nodeId).split(" ");
            last = toPo.links.get(strings[strings.length - 1]);
            while ((res = getNextPath2(v1, last, item.necessaryNodes)) != null) {
                Link a = (Link) res.get("Link");
                int temp = a.cost;
                v2 = a.getAnotherNode(v1);
                last = a;
                v2.visited.add(v1.nodeId);
                d1 = distances.get(v1.nodeId);
                p1 = paths.get(v1.nodeId);
                queue.offer(v2);
                if (!distances.containsKey(v2.nodeId)) {
                    distances.put(v2.nodeId, temp + d1);
                    paths.put(v2.nodeId, p1 + a.linkId);
                }
                if (d1 + temp < distances.get(v2.nodeId)) {
                    distances.replace(v2.nodeId, d1 + temp);
                    paths.replace(v2.nodeId, p1 + a.linkId);
                }
                if (v2 == item.dstNode) {
                    String[] param = paths.get(v2.nodeId).split(",");
                    int c = getCar(param, item.weight, a.cars.size());
                    if (c != -1) {
                        if (!item.dstNode.hasWorker.get(a.linkId)[c - 1]) {
                            if (item.dstNode.availableWorkerNumber > 0) {
                                item.dstNode.hasWorker.get(a.linkId)[c - 1] = true;
                                item.dstNode.availableWorkerNumber--;
                            } else {
                                System.out.println(item + ":终点处无拣货员可用");
                                resultProject.flag = false;
                                FailedNum++;
                                FailedSumWeight += item.weight;
                                resultProjects.add(resultProject);
                                clearNodes();
                                return;
                            }
                        }

                        if (!item.srcNode.hasWorker.get(param[0])[c - 1]) {
                            if (item.srcNode.availableWorkerNumber > 0) {
                                item.srcNode.hasWorker.get(param[0])[c - 1] = true;
                                item.srcNode.availableWorkerNumber--;
                            } else {
                                System.out.println(item + ":起点处无拣货员可用");
                                resultProject.flag = false;
                                FailedNum++;
                                FailedSumWeight += item.weight;
                                resultProjects.add(resultProject);
                                clearNodes();
                                return;
                            }
                        }
                        resultProject.path = paths.get(v2.nodeId);
                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < param.length; i++) {
                            toPo.links.get(param[i]).cars.get(c - 1).availableWeight -= item.weight;
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
                        System.out.println(item + "：最短路径上无可用不换乘车");
                        resultProject.flag = false;
                        FailedNum++;
                        FailedSumWeight += item.weight;
                        resultProjects.add(resultProject);
                        clearNodes();
                        return;
                    }

                }
                paths.replace(v2.nodeId, paths.get(v2.nodeId) + ",");
                if ((Boolean) res.get("necessary")) {
                    break;
                }
            }
        }
        System.out.println(item + "：规划失败");
        resultProject.flag = false;
        FailedNum++;
        FailedSumWeight += item.weight;
        resultProjects.add(resultProject);
        clearNodes();
    }

    private static int getCar(String[] path, double weight, int MAX_CARS) {
        int count = 0;
        int i;
        for (i = 0; i < MAX_CARS; i++) {
            count = 0;
            for (String s : path) {
                Car c;
                try {
                    c = toPo.links.get(s).cars.get(i);
                    if (compareTwoDouble(c.availableWeight, weight) < 0) {
                        break;
                    } else {
                        count++;
                    }
                } catch (Exception e) {
                    System.out.println("");
                }


            }
            if (count == path.length) {
                break;
            }
        }
        return count == path.length ? i + 1 : -1;
    }

    private static Map<String, Object> getNextPath2(Node v1, Link last, List<Node> necessaryNodes) {
        if (v1.nodeId.equals("Z75")){
            System.out.println();
        }
        Map<String, Object> res = new HashMap<>();
        if (!necessaryNodes.isEmpty()) {
            for (Link link : v1.relatedLink) {
                Node toNode = link.getAnotherNode(v1);
                if (necessaryNodes.contains(toNode)) {
                    res.put("Link", link);
                    res.put("necessary", true);
                    return res;
                }
            }
        }
        for (Link link : v1.relatedLink) {
            Node target = link.getAnotherNode(v1);
            if (link != last && (!target.visited.contains(v1.nodeId))) {
                res.put("Link", link);
                res.put("necessary", false);
                return res;
            }
        }
        return null;
    }


    /*获取数据源*/
    public static void getSourceData() {/*topoAndRequest9.txt   toPoInfo1.txt*/
        try {
            BufferedReader in = new BufferedReader(new FileReader("F:\\project\\InterestingCollection\\src\\main\\resources\\doc\\topoAndRequest9.txt"));
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
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
            itemProjects = new ArrayList<>();
            resultProjects = new ArrayList<>();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    /*规划路径*/
    /* String itemId;
    double weight;
    String linkId;
    String nowNode;
    String toNode;
    int lastCarId;
    int carId = -1;*/
    /*获取下一步路径，并且减拣货员*/
//    public static void getPath(Item item) {
//        ItemProject itemProject = new ItemProject(item.itemId);
//        if (item.dstNode.availableWorkerNumber < 1) {
//            FailedNum++;
//            FailedSumWeight += item.weight;
//            itemProject.flag = false;
//            itemProjects.add(itemProject);
//            if (item.necessaryNodes.isEmpty()){
//                System.out.println(item.itemId + "目的地没有拣货员");
//            }else{
//                System.out.println(item.itemId + "有必经节点");
//            }
//
//            return;
//        }
//        /*依次为nowNodeId,linkId,carId,costType*/
//        Map<String, String> pathInfos = new HashMap<>();
//        Map<String, Integer> distances = new HashMap<>();
//        pathInfos.put(item.srcNode.nodeId, "");
//        distances.put(item.srcNode.nodeId, 0);
//        Queue<Node> queue = new LinkedList<>();
//        queue.offer(item.srcNode);
//        Node v1;
//        Node v2;
//        Path paramPath = new Path(item.itemId, item.weight);
//        paramPath.carId = -1;
//        paramPath.toNode = item.srcNode.nodeId;
//        Path res;
//        while (!queue.isEmpty()) {
//            /*节点重放*/
//            v1 = queue.poll();
//            if (v1 != item.srcNode) {
//                String[] strings = pathInfos.get(v1.nodeId).split(" ");
////                System.out.println(pathInfos.get(v1.nodeId));
//                paramPath.costWorkerType = Integer.parseInt(strings[strings.length - 1]);
//                paramPath.carId = Integer.parseInt(strings[strings.length - 2]);
//                paramPath.linkId = strings[strings.length - 3];
//                paramPath.toNode = toPo.links.get(paramPath.linkId).getAnotherNode(toPo.nodes.get(strings[strings.length - 4])).nodeId;
//            }
//            String pathV1 = pathInfos.get(v1.nodeId);
//            int distanceV1 = distances.get(v1.nodeId);
//            /*找到可用节点，并记录用的车号*/
//            while ((res = getNextLink(paramPath, item.necessaryNodes)) != null) {
//                Link tempLink = toPo.links.get(res.linkId);
//                v2 = toPo.nodes.get(res.toNode);
//                v2.visited.add(v1.nodeId);
//                queue.offer(v2);
//                /*temp代表下一个路径的消耗cost*/
//                int temp = tempLink.cost;
//                if (!pathInfos.containsKey(v2.nodeId)) {
//                    distances.put(v2.nodeId, distanceV1 + temp);
//                    pathInfos.put(v2.nodeId, pathV1 + res.nowNode + " " + res.linkId + " " + res.carId + " " + res.costWorkerType + " ");
//                } else if ((temp + distanceV1 < distances.get(v2.nodeId))) {
//                    distances.replace(v2.nodeId, temp + distanceV1);
//                    pathInfos.replace(v2.nodeId, pathV1 + res.nowNode + " " + res.linkId + " " + res.carId + " " + res.costWorkerType + " ");
//                }
//                if (v2 == item.dstNode) {
//                    clearNodes();
//                    String[] info = pathInfos.get(item.dstNode.nodeId).split(" ");
//                    StringBuilder result = new StringBuilder();
//                    if (item.dstNode.availableWorkerNumber > 0) {
//                        item.dstNode.availableWorkerNumber--;
//                        int lastCardId = -1;
//                        for (int i = 0; i < info.length; i += 4) {
//                            if (i > 3) {
//                                lastCardId = Integer.parseInt(info[i - 2]);
//                            }
//                            result.append(info[i + 1]).append(" ").append(info[i + 2]).append(" ");
//                            reduceResource(info[i], info[i + 1], Integer.parseInt(info[i + 2]), Integer.parseInt(info[i + 3]), lastCardId, item.weight);
//                        }
//                        itemProject.flag = true;
//                        itemProject.paths = result.toString().split(" ");
//                    } else {
//                        FailedNum++;
//                        FailedSumWeight += item.weight;
//                        itemProject.flag = false;
//                        System.out.println(item.itemId + ":目的地没有拣货员失败");
//                    }
//                    itemProjects.add(itemProject);
//                    return;
//                }
//                if (res.re) {
//                    break;
//                }
//            }
//            if (!queue.contains(v1)) {
//                pathInfos.remove(v1.nodeId);
//            }
//        }
//        clearNodes();
//        FailedNum++;
//        FailedSumWeight += item.weight;
//        itemProject.flag = false;
//        System.out.println(item.itemId + ":规划失败");
//        itemProjects.add(itemProject);
//    }

    /*清理节点的访问记录*/
    private static void clearNodes() {
        for (Node n : toPo.nodes.values()) {
            n.visited = new ArrayList<>();
        }
    }

    /*路径可用，进行资源减少*/
    public static void reduceResource(String nowNode, String linkId, int carId, int costWorkerType, int lastCarId, double weight) {
//        System.out.println(lastCarId);
        toPo.links.get(linkId).cars.get(carId - 1).availableWeight -= weight;
        switch (costWorkerType) {
            case 0:
                break;
            case 1:
                toPo.nodes.get(nowNode).availableWorkerNumber -= 1;
                toPo.nodes.get(nowNode).hasWorker.get(linkId)[carId - 1] = true;
                break;
            case 10:
                toPo.nodes.get(nowNode).availableWorkerNumber -= 1;
                toPo.nodes.get(nowNode).hasWorker.get(linkId)[lastCarId - 1] = true;
                break;
            case 11:
                toPo.nodes.get(nowNode).availableWorkerNumber -= 2;
//                System.out.println(carId);
//                System.out.println(lastCarId);
                toPo.nodes.get(nowNode).hasWorker.get(linkId)[lastCarId - 1] = true;
                toPo.nodes.get(nowNode).hasWorker.get(linkId)[lastCarId - 1] = true;
                break;
        }
    }

    /*寻找下一个可用路径*/
    private static Path getNextLink(Path lastPath, List<Node> necessaryNodes) {
        Node nowNode = toPo.nodes.get(lastPath.toNode);
        int lastCarId = lastPath.carId;
        Link lastLink = toPo.links.get(lastPath.linkId);
        double weight = lastPath.weight;
        Link necessaryNext = null;
        /*下边判断是否在这一轮路径中存在必经节点*/
        if (!necessaryNodes.isEmpty()) {
            for (Link tempLink : nowNode.relatedLink) {
                if (necessaryNodes.contains(tempLink.getAnotherNode(nowNode))) {
                    necessaryNext = tempLink;
                }
            }
        }
        if (necessaryNext == null) {
            /*这一轮无必经节点*/
            for (int i = 0; i < nowNode.relatedLink.size(); i++) {
                Link tempLink = nowNode.relatedLink.get(i);
                Node toNode = tempLink.getAnotherNode(nowNode);
                if (tempLink != lastLink && !toNode.visited.contains(nowNode.nodeId)) {/*当前路径非以前的路径，且目的节点未被访问过*/
                    int carId = getUsableCar(lastCarId, tempLink, weight);
                    int res = pickWorker(carId, lastCarId, nowNode, tempLink);
                    if (res == -1) {
                        continue;
                    }
                    return fillPath(lastPath.itemId, res, lastCarId, weight, carId, tempLink, nowNode, toNode, false);
                }
            }
        } else {
            /*有必经节点，就只需要判断，必经节点是否有可用车*/
            Node toNode = necessaryNext.getAnotherNode(nowNode);
            int carId = getUsableCar(lastCarId, necessaryNext, weight);
            int res = pickWorker(carId, lastCarId, nowNode, necessaryNext);
            if (res == -1) {
                return null;
            } else {
                return fillPath(lastPath.itemId, res, lastCarId, weight, carId, necessaryNext, nowNode, toNode, true);
            }
        }
        return null;
    }

    /*按照已有信息判断应该如何减少和配置拣货员*/
    private static int pickWorker(int carId, int lastCarId, Node nowNode, Link tempLink) {
        if (carId == -1) {
            return -1;
        }
        int res = 0;
        /*只要是换车肯定新的车肯定需要拣货员*/
        if (!nowNode.hasWorker.get(tempLink.linkId)[carId - 1]) {
            if (nowNode.availableWorkerNumber > 0) {
                res += 1;
            } else {
                /*无法配置拣货员该路径失败*/
                return -1;
            }
        }
        /*接下来判断需不需要卸货员*/
        if (!(lastCarId == -1)) {
            /*需要卸货员*/
            if (!nowNode.hasWorker.get(tempLink.linkId)[lastCarId - 1]) {
                if (nowNode.availableWorkerNumber > 0) {
                    res += 10;
                } else {
                    /*无法配置拣货员该路径失败*/
                    return -1;
                }
            }
        }
        return res;
    }

    /*获取当前路径上可用的车号*/
    private static int getUsableCar(int lastCar, Link link, double weight) {
        /*先判断是否是头节点找下一轨道*/
        if (lastCar != -1 && compareTwoDouble(link.cars.get(lastCar - 1).availableWeight, weight) >= 0) {
            return lastCar;
        }
        for (int i = 0; i < link.cars.size(); i++) {
            if (compareTwoDouble(link.cars.get(i).availableWeight, weight) >= 0) {
                return i + 1;
            }
        }
        return -1;
    }

    /*比较两个double值*/
    private static int compareTwoDouble(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        /*返回1代表d1>d2，返回-1代表d1<d2，返回零表示相等*/
        return b1.compareTo(b2);
    }

    /*填充返回值对象*/
    private static Path fillPath(String itemId, int res, int lastCarId, double weight, int carId, Link link, Node nowNode, Node toNode, boolean re) {
        Path path = new Path(itemId, weight);
        path.costWorkerType = res;
        path.toNode = toNode.nodeId;
        path.nowNode = nowNode.nodeId;
        path.carId = carId;
        path.lastCarId = lastCarId;
        path.linkId = link.linkId;
        path.re = re;
        return path;
    }


    private static void saveResult() throws IOException {
        File F = new File("F:\\project\\InterestingCollection\\src\\main\\resources\\doc\\result.txt");
        FileWriter fw = new FileWriter(F);
        fw.write("");
        fw = new FileWriter(F, true);
        try {
            fw.write(FailedNum + "," + FailedSumWeight + "\n");
            for (ItemProject itemProject : itemProjects) {
                if (itemProject.flag) {
                    fw.write(itemProject.itemId + "\n");
                    for (int i = 0; i < itemProject.paths.length; i += 2) {
                        fw.write(itemProject.paths[i]);
                        if (i != itemProject.paths.length - 2) {
                            fw.write(",");
                        }
                    }
                    fw.write("\n");
                    for (int j = 1; j < itemProject.paths.length; j += 2) {
                        fw.write(itemProject.paths[j]);
                        if (j != itemProject.paths.length - 1) {
                            fw.write(",");
                        }
                    }
                    fw.write("\n");
                } else {
                    fw.write(itemProject.itemId + "\n");
                    fw.write("null" + "\n");
                    fw.write("null" + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

            e.getMessage();
        } finally {
            fw.flush();
            fw.close();
        }
    }

    public static void printResults() {
        System.out.print(FailedNum + "," + FailedSumWeight + "\n");
        for (ItemProject itemProject : itemProjects) {
            if (itemProject.flag) {
                System.out.print(itemProject.itemId + "\n");
                for (int i = 0; i < itemProject.paths.length; i += 2) {
                    System.out.print(itemProject.paths[i]);
                    if (i != itemProject.paths.length - 2) {
                        System.out.print(",");
                    }
                }
                System.out.print("\n");
                for (int j = 1; j < itemProject.paths.length; j += 2) {
                    System.out.print(itemProject.paths[j]);
                    if (j != itemProject.paths.length - 1) {
                        System.out.print(",");
                    }
                }
                System.out.print("\n");
            } else {
                System.out.print(itemProject.itemId + "\n");
                System.out.println("null");
                System.out.println("null");
            }
        }
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

class Path {
    String itemId;
    double weight;
    String linkId;
    String nowNode;
    String toNode;
    int lastCarId;
    int carId = -1;
    boolean re;
    /*11,10,01,00*/
    int costWorkerType;

    public Path(String itemId, double weight) {
        this.itemId = itemId;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Path{" +
                "itemId='" + itemId + '\'' +
                ", weight=" + weight +
                ", linkId='" + linkId + '\'' +
                ", nowNode='" + nowNode + '\'' +
                ", toNode='" + toNode + '\'' +
                ", lastCarId=" + lastCarId +
                ", carId=" + carId +
                ", re=" + re +
                ", costWorkerType=" + costWorkerType +
                '}';
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

    public Car(String linkId, int carId, double maxWeight) {
        this.linkId = linkId;
        this.carId = carId;
        this.maxWeight = maxWeight;
        this.availableWeight = maxWeight;
        this.used = false;
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

class ItemProject {
    String itemId;
    String[] paths;
    boolean flag;

    public ItemProject(String itemId) {
        this.itemId = itemId;
    }
}


