package com.ic.learn.algorithm.exercise;

import org.junit.Test;
import sun.reflect.generics.tree.Tree;

import java.util.*;

/*重新规划机票*/
public class FindItinerary {
    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    List<String> itinerary = new LinkedList<String>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }

    @Test
    public void test(){
       List<List<String>> tickets = new ArrayList<>();
       String[][] data = new String[][]{{"JFK","KUL"},{"JFK","NRT"},{"NRT","JFK"}};
        for (String[] datum : data) {
            List<String> list = new ArrayList<>();
            Collections.addAll(list, datum);
            tickets.add(list);
        }
        List<String> a = findItinerary(tickets);
        System.out.println(a.toString());
    }
}
