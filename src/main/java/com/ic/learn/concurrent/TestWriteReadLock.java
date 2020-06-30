package com.ic.learn.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestWriteReadLock {
    public static Map<String, Object> map = new HashMap<>();
    public static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    public static Lock r = rwl.readLock();
    public static Lock w = rwl.writeLock();

    public static Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public static void put(String key, Object value) {
        w.lock();
        try {
            map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public static void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
