package com.joker.demo.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public static void main(String[] args){
        LRUCache cache=new LRUCache(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.systemOut();
        cache.put(3,3);
        cache.systemOut();
        cache.get(1);
        cache.systemOut();
        cache.put(4,3);
        cache.systemOut();

    }


    static Map cacheMap;
    static int size;//容量
    //保存链表的头节点和尾节点
    private static Node first;
    private static Node last;

    // 双向链表节点定义
    static class Node{
        int key;
        int value;
        Node prev;
        Node next;
    }

    LRUCache(int size){
        this.size=size;
        cacheMap = new HashMap<>(size);
    }

    static void put(int key,int value){
        Node node= (Node) cacheMap.get(key);
        if(node==null){
           node=new Node();
           node.key=key;
           node.value=value;
           if(cacheMap.size()>=size){
               removeLast();
           }
           addtoHead(node);
           cacheMap.put(key,node);
        }else {
            node.value=value;
            moveToHead(node);
        }

    }

    private static void moveToHead(Node node) {
        if(node==first){
            return;
        }else if(node==last){
            last.prev.next=null;
            last=node.prev;
        }else{
            node.next.prev=node.prev;
            node.prev.next=node.next;
        }
        node.prev=null;
        node.next=first;
        first.prev=node;
        first=node;
    }

    private static void addtoHead(Node node) {
        if(cacheMap.isEmpty()){
            first=node;
            last=node;
        }else{
            first.prev = node;
            node.next=first;
            first=node;
        }
    }


    private static void removeLast() {
        cacheMap.remove(last.key);
        Node prevNode=last.prev;
        if(prevNode!=null){
            prevNode.next=null;
            last=prevNode;
        }
    }

    static int get(int key){
       Node node= (Node) cacheMap.get(key);
       if(node==null){
           return -1;
       }
       moveToHead(node);
       return node.value;

    }

    @Override
    public String toString() {
        return cacheMap.keySet().toString();
    }


    public   void systemOut(){
        for (Node node = first; node!=null; node=node.next){
            System.out.print(node.key);
        }
        System.out.println("");
    }





}
