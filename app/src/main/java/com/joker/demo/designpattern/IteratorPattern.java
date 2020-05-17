package com.joker.demo.designpattern;

import java.util.ArrayList;
import java.util.List;

public class IteratorPattern {
    public static void main(String[] args){

        Aggregate<String> aggregate=new ConcreteAggregate<String>();
        aggregate.add("1");
        aggregate.add("2");
        aggregate.add("3");

        //拿到容器迭代器，使用迭代器遍历
        Iterator<String> iterator=aggregate.iterator();
        while (iterator.hasNext()){
            System.out.println("Aggregate:"+iterator.next());
        }

    }

    //迭代器接口
    public interface Iterator<T>{
        //是否还有下一个元素
        boolean hasNext();

        //返回当前位置的元素并将位置移动到下一位
        T next();

    }

    //具体迭代器
    static class ConcreteIterator<T> implements Iterator<T>{

        /**
         * 模拟用一个 List
         */
        List<T> mList=new ArrayList<>();
        int cursor=0;

        public ConcreteIterator(List<T> list) {
            this.mList = list;
        }

        @Override
        public boolean hasNext() {
            return cursor!=mList.size();
        }

        @Override
        public T next() {
            T obj=null;
            if(this.hasNext()){
                obj=this.mList.get(cursor++);
            }
            return obj;
        }
    }


    //容器接口
    interface Aggregate<T>{
        void add(T t);
        void remove(T t);
        /**
         * 获取容器的迭代器
         * @return
         */
        Iterator<T> iterator();
    }

    //具体容器
    static class ConcreteAggregate<T> implements Aggregate<T>{
        /**
         * 模拟具体容器
         */
        private List<T> mLists = new ArrayList<>();

        @Override
        public void add(T t) {
            mLists.add(t);
        }

        @Override
        public void remove(T t) {
            mLists.remove(t);
        }

        @Override
        public Iterator<T> iterator() {

            return  new ConcreteIterator<T>(mLists);
        }
    }
}
