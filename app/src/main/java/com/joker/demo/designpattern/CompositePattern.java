package com.joker.demo.designpattern;

import java.util.ArrayList;
import java.util.List;

//组合模式（Composite Pattern）也称为部分整体模式 (Part Whole Pattern) ，
// 属于结构性设计模式，组合模式比较简单，它将一组相似的对象看做一个对象处理，并根据一个树状结构来组合对象，
// 然后提供一个统一的方法去访问相应的对象，以此忽略掉对象与对象集合之间的差别。
//参考 https://www.cnblogs.com/mingmingcome/p/11215497.html
public class CompositePattern {

    public static void main(String[] args){
        Company headquarters=new ConcreteComp("公司总部");
        headquarters.addOrg(new FinanceDepartment("财务部"));
        headquarters.addOrg(new HumanResourceDepartment("人力资源部"));

        Company gd = new ConcreteComp("广东分公司");
        gd.addOrg(new FinanceDepartment("财务部"));
        gd.addOrg(new HumanResourceDepartment("人力资源部"));

        Company sz = new ConcreteComp("深圳分公司");
        sz.addOrg(new FinanceDepartment("财务部"));
        sz.addOrg(new HumanResourceDepartment("人力资源部"));

        gd.addOrg(sz);

        Company sh = new ConcreteComp("上海分公司");
        sh.addOrg(new FinanceDepartment("财务部"));
        sh.addOrg(new HumanResourceDepartment("人力资源部"));

        headquarters.addOrg(gd);
        headquarters.addOrg(sh);

        headquarters.showOrg(1);

        Company test = new FinanceDepartment("测试财务部");
        test.addOrg(new HumanResourceDepartment("测试人力资源部"));
        test.addOrg(sh);

        test.showOrg(1);

    }

    //公司组织或部门
    interface Company{
        void addOrg(Company company);
        void removeOrg(Company company);
        void showOrg(int depth);
        default void print(int depth){
            while(depth-->0){
                System.out.print("-");
            }
        }
    }

    // 总部或分公司
    static class ConcreteComp implements Company{
        String name="";
        List<Company> companies=new ArrayList<>();

        public ConcreteComp(String name) {
            this.name = name;
        }

        @Override
        public void addOrg(Company company) {
            companies.add(company);
        }

        @Override
        public void removeOrg(Company company) {
            companies.remove(company);
        }

        @Override
        public void showOrg(int depth) {
            print(depth);
            System.out.println(this.name);
            for (Company c : companies) {
                c.showOrg(depth + 2);
            }
        }
    }

    //财务部
    static class FinanceDepartment implements Company{
        String name="";

        public FinanceDepartment(String name) {
            this.name = name;
        }

        @Override
        public void addOrg(Company company) {

        }

        @Override
        public void removeOrg(Company company) {

        }

        @Override
        public void showOrg(int depth) {
            print(depth);
            System.out.println(this.name);
        }
    }

    //人力资源部
    static class HumanResourceDepartment implements Company{
        private String name = "";
        HumanResourceDepartment(String name) {
            this.name = name;
        }

        @Override
        public void addOrg(Company company) {

        }

        @Override
        public void removeOrg(Company company) {

        }

        @Override
        public void showOrg(int depth) {
            print(depth);
            System.out.println(this.name);
        }
    }
}

