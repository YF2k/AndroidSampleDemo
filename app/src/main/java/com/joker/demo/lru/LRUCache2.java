package com.joker.demo.lru;

import java.util.LinkedHashMap;


public class LRUCache2<K,V> extends LinkedHashMap<K,V> {

    //首先设定最大缓存空间 MAX_ENTRIES 为 3；
    private static final int MAX_ENTRIES = 3;

    LRUCache2(){
        super(MAX_ENTRIES,0.75f,true);
    }

    //最后覆盖removeEldestEntry(）方法实现，在节点多于 MAX_ENTRIES 就会将最近最少使用的数据移除。
    //因为这个函数默认返回false，不重写的话缓存爆了的时候无法删除最近最久未使用的节点

    @Override
    protected boolean removeEldestEntry(Entry eldest) {
        //在容量超过最大允许节点数的时候返回true，使得在afterNodeInsertion函数中能执行removeNode()
        return size()>MAX_ENTRIES;
    }


    public static void main(String[] args) {
        LRUCache2<Integer, Integer> cache = new LRUCache2<>();
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1);
        cache.put(4, 4);
        System.out.println(cache.keySet());
    }
}

