package com.ic.learn.others;

import com.ic.userManagement.entity.User;
import org.apache.lucene.util.RamUsageEstimator;

import java.util.HashMap;
import java.util.Map;

public class CalculateObjectSize {
    public static void main(String[] args) {
        Map<String,String> i = new HashMap<>();
        System.out.println(RamUsageEstimator.shallowSizeOf(i));
        System.out.println(RamUsageEstimator.shallowSizeOf(new User()));
    }
}
