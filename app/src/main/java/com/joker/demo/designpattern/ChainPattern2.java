package com.joker.demo.designpattern;

/**
 * author: Bairong
 * date: 2020/5/10 16:11
 */
//责任链模式
public class ChainPattern2 {
    public static void  main(String[] args){
        Leader teamLeader=new TeamLeader("TeamLeader",3);
        Leader departmentdirector = new Departmentdirector("Departmentdirector", 7);
        Leader generalManager = new GeneralManager("GeneralManager", Integer.MAX_VALUE);

        teamLeader.setSuccesor(departmentdirector);
        departmentdirector.setSuccesor(generalManager);

        // 请假三天
        teamLeader.handleRequest(3);
        // 请假六天
        teamLeader.handleRequest(6);
        // 请假100天
        teamLeader.handleRequest(100);

    }


    static interface IHandler{
        //处理请求
        void handleRequest(int dayNums);
        //设置下一个执行者
        void setSuccesor(IHandler handler);

        //获取下一个执行者
        IHandler getNextSuccesor();
    }

    static class Leader implements IHandler{
        String mName;
        int mCouldHandleNum;
        IHandler mNextHandler;

        public Leader(String name,int couldHandleNum) {
            mName=name;
            mCouldHandleNum=couldHandleNum;
        }

        @Override
        public void handleRequest(int dayNums) {
            if(dayNums<=mCouldHandleNum){
                System.out.println(mName + " 同意了你的申请， dayNums = " + dayNums);
            }else{
                if(null!=getNextSuccesor()){
                    getNextSuccesor().handleRequest(dayNums);
                }else{
                    System.out.println(mName + " 拒绝了你的申请 dayNums = " + dayNums);
                }
            }

        }

        @Override
        public void setSuccesor(IHandler handler) {
            mNextHandler=handler;

        }

        @Override
        public IHandler getNextSuccesor() {
            return mNextHandler;
        }
    }

    public static class TeamLeader extends Leader {

        public TeamLeader(String name, int couldHandlerNum) {
            super(name, couldHandlerNum);
        }


    }

    public static class Departmentdirector extends Leader{

        public Departmentdirector(String name, int couldHandlerNum) {
            super(name, couldHandlerNum);
        }

    }

    public static class GeneralManager extends Leader{

        public GeneralManager(String name, int couldHandlerNum) {
            super(name, couldHandlerNum);
        }

    }


}
