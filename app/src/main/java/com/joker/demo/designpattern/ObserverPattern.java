package com.joker.demo.designpattern;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ObserverPattern {


    public static void main(String[] args) {
//        UpdateProgress updateProgress = new UpdateProgress();
//        UpdateProgress2 updateProgress = new UpdateProgress2();
        Observer observer = new ObserverImpl();
        UpdateProgress3 updateProgress = new UpdateProgress3();
        new ObserverImpl2(updateProgress);
//        updateProgress.attach(observer);
        updateProgress.progressChange("1...");
        updateProgress.progressChange("51...");
        updateProgress.progressChange("100...");

    }


    //抽象 观察者
    interface Observer {
        public void update(String state);

        public void update(Observable observable);
    }

    //具体观察者
    static class ObserverImpl implements Observer {

        //推方式的观察察者模式实例：
        @Override
        public void update(String progress) {
            System.out.println("更新进度---" + progress);
        }

        //拉方式的观察察者模式实例：
        @Override
        public void update(Observable observable) {
            if (observable instanceof UpdateProgress2) {
                System.out.println("更新进度---" + ((UpdateProgress2) observable).getState());
            } else {
                System.out.println("出错了");
            }

        }


    }

    //抽象 被观察者
    static abstract class Observable {
        List<Observer> observerList = new ArrayList<>();


        public void attach(Observer observer) {
            observerList.add(observer);
        }

        public void detach(Observer observer) {
            observerList.remove(observer);
        }

        public void notifyObservers(String state) {
            for (Observer observer : observerList) {
                observer.update(state);
            }

        }

        public void notifyObservers() {
            for (Observer observer : observerList) {
                observer.update(this);
            }

        }
    }


    //具体 被观察者
    static class UpdateProgress extends Observable {


        public void progressChange(String progress) {
            notifyObservers(progress);
        }

    }


    //拉方式
    static class UpdateProgress2 extends Observable {
        String state;

        public String getState() {
            return state;
        }

        public void progressChange(String progress) {
            state = progress;
            notifyObservers();
        }

    }

    //java util
    //观察者
    static class ObserverImpl2 implements java.util.Observer {

        public ObserverImpl2(java.util.Observable obs) {
            obs.addObserver(this);
        }

        @Override
        public void update(java.util.Observable o, Object arg) {
            if (o instanceof UpdateProgress3) {
                System.out.println("更新进度---" + ((UpdateProgress3) o).getState());
            } else {
                System.out.println("出错了");
            }
        }
    }

    //拉方式
    static class UpdateProgress3 extends java.util.Observable {
        String state;

        public String getState() {
            return state;
        }

        public void progressChange(String progress) {
            state = progress;
            this.setChanged();
            notifyObservers();
        }

    }
}
