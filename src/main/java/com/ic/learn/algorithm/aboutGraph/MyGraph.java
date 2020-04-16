package com.ic.learn.algorithm.aboutGraph;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class MyGraph {
    private Vertex[] vertexesList;/*顶点数组*/
    private int[][] adjacencyMatrix;/*邻接矩阵*/
    private int vertexesNum;/*当前顶点数*/
    private Stack<Integer> graphStack;
    private ArrayBlockingQueue<Integer> graphQueue;

    public MyGraph() {
        /*定义最大顶点数*/
        int MAX_VERTEXES = 20;
        vertexesList = new Vertex[MAX_VERTEXES];
        adjacencyMatrix = new int[MAX_VERTEXES][MAX_VERTEXES];
        vertexesNum = 0;
        for (int i = 0; i < MAX_VERTEXES; i++) {
            for (int j = 0; j < MAX_VERTEXES; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
        graphStack = new Stack<>();
        graphQueue = new ArrayBlockingQueue<Integer>(1000);
    }

    /*为图添加顶点*/
    public void addVertex(Character label) {
        vertexesList[vertexesNum] = new Vertex(label);
        vertexesNum++;
    }

    /*添加边,即将邻接矩阵中对应位置的值置一*/
    public void addEdge(int start, int end) {
        adjacencyMatrix[start][end] = 1;
        adjacencyMatrix[end][start] = 1;
    }

    /*打印数组中v位置的顶点名*/
    public void displayVertex(int v) {
        System.out.print(vertexesList[v].label + " ");
    }

    /*获取和v邻接的未访问顶点*/
    public int getAdjacencyUnvisitedVertex(int v) {
        for (int i = 0; i < vertexesNum; i++) {
            if (adjacencyMatrix[v][i] == 1 && !vertexesList[i].wasVisited) {
                return i;
            }
        }
        return -1;
    }

    /*深度优先搜索*/
    public void depthFirstSearch() {
        vertexesList[0].wasVisited = true;
        displayVertex(0);
        graphStack.push(0);

        while (!graphStack.empty()) {
            int v = getAdjacencyUnvisitedVertex(graphStack.peek());
            if (v == -1) {
                graphStack.pop();/*回溯*/
            } else {
                vertexesList[v].wasVisited = true;
                displayVertex(v);
                graphStack.push(v);
            }
        }
        for (int i = 0; i < vertexesNum; i++) {
            /*重置*/
            vertexesList[i].wasVisited = false;
        }
        System.out.println();
    }

    /*广度优先搜索*/
    public void breadthFirstSearch() {
        vertexesList[0].wasVisited = true;
        displayVertex(0);
        graphQueue.add(0);
        int v2;

        while (!graphQueue.isEmpty()) {
            int v1 = graphQueue.poll();/*重放*/

            while ((v2 = getAdjacencyUnvisitedVertex(v1)) != -1) {
                vertexesList[v2].wasVisited = true;
                displayVertex(v2);
                graphQueue.add(v2);
            }
        }

        for (int j = 0; j < vertexesNum; j++) {
            vertexesList[j].wasVisited = false;
        }
    }

    public void dfs(){
        if (vertexesNum==0){
            return;
        }
        graphStack.add(0);
        vertexesList[0].wasVisited = true;
        displayVertex(0);

        while (!graphStack.empty()){
            int v = getAdjacencyUnvisitedVertex(graphStack.peek());
            if (v!=-1&&!vertexesList[v].wasVisited){
                graphStack.add(v);
                displayVertex(v);
                vertexesList[v].wasVisited = true;
            }else{
                graphStack.pop();
            }
        }
        for (int i = 0;i<vertexesNum;i++){
            vertexesList[i].wasVisited = false;
        }
        System.out.println();
    }
    public void bfs(){
        if (vertexesNum == 0){
            return;
        }
        graphQueue.add(0);
        vertexesList[0].wasVisited = true;
        displayVertex(0);
        int v2;
        while(!graphQueue.isEmpty()){
            int v = graphQueue.poll();
            while((v2 = getAdjacencyUnvisitedVertex(v))!=-1&&!vertexesList[v2].wasVisited){
                graphQueue.add(v2);
                displayVertex(v2);
                vertexesList[v2].wasVisited = true;
            }
        }

        for (int i = 0;i<vertexesNum;i++){
            vertexesList[i].wasVisited = false;
        }
    }

    public static void main(String[] args) {
        MyGraph myGraph = new MyGraph();
        myGraph.addVertex('A');
        myGraph.addVertex('B');
        myGraph.addVertex('C');
        myGraph.addVertex('D');
        myGraph.addVertex('E');
        myGraph.addVertex('F');
        myGraph.addVertex('G');

        myGraph.addEdge(0, 1);
        myGraph.addEdge(1, 2);
        myGraph.addEdge(0, 3);
        myGraph.addEdge(2, 3);
        myGraph.addEdge(1, 4);
        myGraph.addEdge(3, 4);
        myGraph.addEdge(4, 5);
        myGraph.addEdge(5, 6);
        myGraph.addEdge(3, 6);
        System.out.println(myGraph.vertexesList[0]);
        System.out.println("深度优先遍历:");
//        myGraph.depthFirstSearch();
        myGraph.dfs();
        System.out.println("广度优先遍历:");
//        myGraph.breadthFirstSearch();
        myGraph.bfs();

    }

}

class Vertex {
    public char label;// 名字
    public boolean wasVisited;

    public Vertex(char lab) {
        label = lab;
        wasVisited = false;
    }
}