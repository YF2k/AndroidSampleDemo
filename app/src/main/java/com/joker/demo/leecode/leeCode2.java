package com.joker.demo.leecode;

import java.util.HashMap;

public class leeCode2 {


    public ListNode rotateRight(ListNode head, int k) {
        ListNode p=head;
        ListNode temp=head;
        int length=1;
        while(p!=null){
            p=p.next;
            length+=1;
        }
        System.out.println("length:"+length);
        int position=length-k%length-1;
        for(int i=0;i<position;i++){
            temp=temp.next;

        }
        ListNode result=temp.next;
        temp.next.next=head;
        temp.next=null;
        return result;
    }


    public int kthToLast(ListNode head, int k) {
        ListNode p=head;
        HashMap<Integer,Integer> map=new HashMap();
//        if(head==null)  throw new Exception("空指针");
        for(int i=1;p!=null;i++){
            map.put(i,p.val);
            p=p.next;
        }
        return map.get(map.size()-k+1);
    }

    public int[][] transpose(int[][] A) {
        int max=A.length>A[0].length?A.length:A[0].length;
        int[][] array = new int[max][max];
        for(int i=0;i<A.length;i++){
            for(int j=0;j<A[i].length;j++){
                array[j][i]=A[i][j];
            }

        }

        return array;
    }



    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3=new ListNode(0);
        ListNode l4;
        l4=l3;
        int flag=0;
        for(;l1!=null||l2!=null;){
            //使用三元操作符--简化代码
            int a = 0,b=0;
            if(l1!=null){
                a=l1.val;
            }
            if(l2!=null){
                b=l2.val;
            }
            int x=a+b+flag;
            //使用x/10 简化代码
            flag=0;
            if(x>9){
                flag=1;
                x = x % 10;
            }
            l3.val=x;
            System.out.println("l3.value="+l3.val);
            if((l1!=null&&l1.next!=null)||(l2!=null&&l2.next!=null)){
                l3.next=new ListNode(0);
                l3=l3.next;

            }
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
            System.out.println("l3.next.value="+l3.val);

        }
        if (flag == 1) {
        l3.next = new ListNode(1);
        }
//        System.out.println("l3.value="+l3.val);
        return l4;

    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode l3=new ListNode(0);
        ListNode l4=l3;
        int carry=0;
        while(l1!=null||l2!=null){
            //使用三元操作符--简化代码
            int a = l1==null?0:l1.val;
            int b = l2==null?0:l2.val;
            int sum=a+b+carry;
            //使用x/10 简化代码
            carry = sum/10;
            l3.next=new ListNode(sum%10);
            System.out.println("l3.value="+l3.val);
            l3=l3.next;
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
            System.out.println("l3.next.value="+l3.val);

        }
        if (carry == 1) {
            l3.next = new ListNode(carry);
        }
        return l4.next;

    }



      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }
}
