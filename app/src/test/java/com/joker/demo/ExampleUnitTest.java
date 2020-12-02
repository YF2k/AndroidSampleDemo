package com.joker.demo;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.joker.demo.utils.BairongSignature;

import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    String userHome = System.getProperty("user.home");
    String url = userHome + "/360jiagu";//apk包位置

    private String myFile = userHome + "/channel_data.txt";
    private String outFile = userHome + "/channel_out.txt";
    private String appVersion = "3.16.0";

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testByte() {
//        int a = 129;
//        String s = "128";
//        byte b = (byte) a;
//        byte d = Byte.parseByte(s);
//        System.out.println(b);
//        System.out.println(d);

        HashMap<Integer, Integer> map = new HashMap<>();
        System.out.println(map.get(null));
        map.put(288, 1);
        map.put(288, 2);
        map.put(2, 2);
        System.out.println(map);
    }

    @Test
    public void select() {
        String str = null;
        List channels = new ArrayList<String>();
        File file = new File(myFile);
        if (file.exists()) {
            try {
                InputStreamReader fileInputStream = new InputStreamReader(new FileInputStream(file));
                BufferedReader bis = new BufferedReader(fileInputStream);
                while ((str = bis.readLine()) != null) {

                    String channel = str.split(" ")[1];
                    channels.add(channel);
//                    System.out.println(channel);

                }

                fileInputStream.close();
                bis.close();
                for (int i = 0; i <= channels.size() - 2; i++) {
                    for (int j = i + 1; j <= channels.size() - 1; j++) {
                        if (channels.get(i).equals(channels.get(j))) {
                            System.out.println(channels.get(i));
                        }
                    }

                }

            } catch (Exception e) {

            }

        }
    }

    @Test
    public void iteratorRemove() {
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(1);
        if (nums.contains(1)) {
            nums.remove(Integer.valueOf(1));
        }
        if (nums.contains(1)) {
            nums.remove(Integer.valueOf(1));
        }
        Iterator<Integer> iterator = nums.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            System.out.println(num);
            if (num == 2) {
                iterator.remove();
                System.out.println(num);
                break;
            }
        }

        for (Integer i : nums) {
            System.out.println(i);
        }
    }


    @Test
    public void sha1Encode() {
        String str = "lsi1111111111111111djf";
        try {
            System.out.println(str);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(str.getBytes());
            System.out.println("sha1加密串：" + messageDigest.digest());
            System.out.println("sha1加密串16进制：" + getFormattedText(messageDigest.digest()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    @Test
    public void testHashMap() {
        HashMap<String, HashMap<String, String>> map = new HashMap<>();
        HashMap<String, String> data = new HashMap<>();
        data.put("li", "haha");
        data.put("xiang", "haha");
        HashMap<String, String> data1 = new HashMap<>();
        data1.put("li1", "haha");
        data1.put("xiang1", "haha");
        map.put("data", data);
        map.put("data1", data1);
        System.out.println(map.toString());

        int x = 11 / 10;
        System.out.println("s:" + x);

        int n = 5, k = 9;

        k = (k / n) * n + k % n;

    }


    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || (head != null && head.next == null)) return head;
        int length = 1;
        ListNode old_tail = head;
        while (old_tail.next != null) {
            length += 1;
            old_tail = old_tail.next;
        }
        old_tail.next = head;
//        if(k%length==0) return head;
        int position = length - k % length;
        ListNode new_tail = head;
        //漏洞：当new_tail和old_tail指向同一位置时，new_tail.next便会=null;
        for (int i = 1; i < position; i++) {
            new_tail = new_tail.next;
        }
        ListNode new_head = new_tail.next;
        new_tail.next = null;
//        old_tail.next=head;

        return new_head;
    }


    public Node2 flatten(Node2 head) {
        Node2 p = head;
        while (p != null) {
            if (p.child != null) {
                nodeHandle(p);
            } else {
                p = p.next;
            }
        }

        return null;
    }

    public void nodeHandle(Node2 node) {
        if (node.child != null) {
            nodeHandle(node.child);

        } else {
            node = node.next;

        }
    }


    class Node2 {
        public int val;
        public Node2 prev;
        public Node2 next;
        public Node2 child;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    //剑指 Offer 35. 复杂链表的复制
    public Node copyRandomList(Node head) {
        //新老结点映射关系
        HashMap<Node, Node> map = new HashMap<>();
        Node oldCur = head;
        while (oldCur != null) {
            map.put(oldCur, new Node(oldCur.val));
            oldCur = oldCur.next;
        }

        oldCur = head;
        while (oldCur != null) {
            map.get(oldCur).next = map.get(oldCur.next);
            map.get(oldCur).random = map.get(oldCur.random);
            oldCur = oldCur.next;
        }

        return map.get(head);
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    //螺旋矩阵
    @Test
    public void spiralOrder() {
        int[][] matrix = {{2, 5, 8}, {4, 0, -1}};
        ;
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0 || matrix == null) return;
        int n = matrix.length;
        int m = matrix[0].length;
        int count = (Math.min(n, m) + 1) / 2;

        for (int i = 0; i < count; i++) {
            for (int j = i; j < m - i; j++) {
                list.add(matrix[i][j]);
                System.out.println("top:" + matrix[i][j]);
            }
            for (int j = i + 1; j < n - i; j++) {//2,2
                list.add(matrix[j][(m - 1) - i]);
                System.out.println("right:" + matrix[j][m - i - 1]);
            }
            for (int j = m - i; (j > i + 1) && (i != n - 1 - i); j--) {//3 2
                list.add(matrix[n - i - 1][j - 1 - 1]);
                System.out.println("bottom:" + matrix[n - i - 1][j - 1 - 1]);
            }
            for (int j = n - i; (j > i + 2) && (i != m - 1 - i); j--) {
                list.add(matrix[j - 1 - 1][i]);
                System.out.println("left:" + matrix[j - 1 - 1][i]);
            }
        }

        return;
    }


    //59. 螺旋矩阵 II
    public int[][] generateMatrix(int n) {
        int[][] nums = new int[n][n];
        int count = (n + 1) / 2;
        int num = 0;
        for (int i = 0; i < count; i++) {
            for (int top = i; top < n - i; top++) {
                nums[i][top] = ++num;
            }

            for (int right = i + 1; right < n - i; right++) {
                nums[right][n - 1 - i] = ++num;
            }

            for (int bottom = n - 1 - i; bottom > i && i != n - i - 1; bottom--) {
                nums[n - 1 - i][bottom - 1] = ++num;
            }

            for (int left = n - 1 - i; left > i + 1 && i != n - i - 1; left--) {
                nums[left - 1][i] = ++num;
            }

        }
        return nums;
    }


    class Solution {
        public int[][] generateMatrix(int n) {
            int l = 0, r = n - 1, t = 0, b = n - 1;
            int[][] mat = new int[n][n];
            int num = 1, tar = n * n;
            while (num <= tar) {
                for (int i = l; i <= r; i++) mat[t][i] = num++; // left to right.
                t++;
                for (int i = t; i <= b; i++) mat[i][r] = num++; // top to bottom.
                r--;
                for (int i = r; i >= l; i--) mat[b][i] = num++; // right to left.
                b--;
                for (int i = b; i >= t; i--) mat[i][l] = num++; // bottom to top.
                l++;
            }
            return mat;
        }
    }


    public int[][] generateMatrix2(int n) {
        //设定边界
        int l = 0, t = 0, r = n - 1, b = n - 1;
        int[][] nums = new int[n][n];
        int count = n * n;
        int num = 1;
        while (num <= count) {
            for (int j = l; j <= r; j++) {//l -> r
                nums[t][j] = num++;
            }
            t++;
            for (int j = t; j <= b; j++) {//t -> b
                nums[j][r] = num++;
            }
            r--;
            for (int j = r; j >= l; j--) {//r -> l
                nums[b][j] = num++;
            }
            b--;
            for (int j = b; j >= t; j--) {//b -> t
                nums[j][l] = num++;
            }
            l++;
        }
        return nums;
    }


    @Test
    public void spiralOrder1() {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return;
        int l = 0, t = 0, r = matrix[0].length - 1, b = matrix.length - 1;
        int count = matrix.length * matrix[0].length;
        while (count >= 1) {
            for (int i = l; i <= r && count >= 1; i++) {//l->r
                list.add(matrix[t][i]);
                count--;
                System.out.println("t:" + matrix[t][i]);
            }
            t++;
            for (int i = t; i <= b && count >= 1; i++) {//t->b
                list.add(matrix[i][r]);
                count--;
                System.out.println("r:" + matrix[i][r]);
            }
            r--;
            for (int i = r; i >= l && count >= 1; i--) {//r->l
                list.add(matrix[b][i]);
                count--;
                System.out.println("b:" + matrix[b][i]);
            }
            b--;
            for (int i = b; i >= t && count >= 1; i--) {//b->t
                list.add(matrix[i][l]);
                count--;
                System.out.println("l:" + matrix[i][l]);
            }
            l++;

        }

        System.out.println(list.toString());
//        return list;
    }


    //20. 有效的括号
    @Test
    public void isValid() {
        String s = "]";
        if (s.length() == 0) {
            System.out.println("true");
//            return true;
        }
        LinkedList<Character> stack = new LinkedList<>();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == ')' && null != stack.peek() && '(' == stack.peek()) {
                stack.pop();
                continue;
            }
            if (array[i] == ']' && null != stack.peek() && '[' == stack.peek()) {
                stack.pop();
                continue;
            }
            if (array[i] == '}' && null != stack.peek() && '{' == stack.peek()) {
                stack.pop();
                continue;
            }
            stack.push(array[i]);
        }


        if (stack.isEmpty()) {
            System.out.println("true");
//            return true;
        } else {
            System.out.println("false");
//            return false;
        }


    }


    //962. 最大宽度坡
    public int maxWidthRamp(int[] A) {
        int max = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] <= A[j]) {
                    max = j - i > max ? j - i : max;
                }
            }
        }
        return max;
    }


    @Test
    public void maxWidthRamp2() {
        int[] A = {6, 0, 8, 2, 1, 5};
        int res = 0;
        //单调栈
        LinkedList<Integer> stack = new LinkedList<>();
        //构造单调递增栈（递增or递减 是按出栈顺序定义的）【坡底一定在这里（反证法）】
        for (int i = 0; i < A.length; i++) {
            if (stack.isEmpty() || A[i] < A[stack.peek()]) {
                stack.push(i);
            }
        }

        for (int i = A.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A[i] >= A[stack.peek()]) {
                int cur = stack.pop();
                res = Math.max(res, i - cur);
            }
        }

        System.out.println("res:" + res);
//        return res;
    }


    @Test
    public void longestWPI() {
        int[] hours = {9, 9, 6, 0, 6, 6, 9};
        int[] newHours = new int[hours.length];
        for (int i = 0; i < hours.length; i++) {
            newHours[i] = hours[i] > 8 ? 1 : -1;
        }
        //前缀和---转变成坡度最宽问题（max(j-i)&&A[j]-A[i]>0）
        int[] parems = new int[hours.length + 1];
        for (int i = 1; i < hours.length + 1; i++) {
            parems[i] = parems[i - 1] + newHours[i - 1];
        }
        //单调栈
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < hours.length + 1; i++) {
            if (stack.isEmpty() || parems[stack.peek()] > parems[i]) {
                stack.push(i);
            }
        }
        System.out.println("单调栈：" + stack.toString());
        int res = 0;
        for (int i = hours.length; i >= 0 && !stack.isEmpty(); i--) {
            while (!stack.isEmpty() && parems[i] - parems[stack.peek()] > 0) {
                int cur = stack.pop();
                res = Math.max(res, i - cur);
            }
        }

        System.out.println("res:" + res);
//        return res;

    }

    @Test
    public void testStackToArray() {
        LinkedList<Integer> stack = new LinkedList();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(5);
        stack.push(4);
        Object[] array = stack.toArray();
        //array:4 5 3 2 1
        System.out.println(array);
    }


    //56. 合并区间
    @Test
    public void merge() {

        int[][] intervals = {{362, 367}, {314, 315}, {133, 138}, {434, 443}, {202, 203}, {144, 145}, {229, 235}, {205, 212}, {314, 323}, {128, 129}, {413, 414}, {342, 345}, {43, 49}, {333, 342}, {173, 178}, {386, 391}, {131, 133}, {157, 163}, {187, 190}, {186, 186}, {17, 19}, {63, 69}, {70, 79}, {386, 391}, {98, 102}, {236, 239}, {195, 195}, {338, 338}, {169, 170}, {151, 153}, {409, 416}, {377, 377}, {90, 96}, {156, 165}, {182, 186}, {371, 372}, {228, 233}, {297, 306}, {56, 61}, {184, 190}, {401, 403}, {221, 228}, {203, 212}, {39, 43}, {83, 84}, {66, 68}, {80, 83}, {32, 32}, {182, 182}, {300, 306}, {235, 238}, {267, 272}, {458, 464}, {114, 120}, {452, 452}, {372, 375}, {275, 280}, {302, 302}, {5, 9}, {54, 62}, {237, 237}, {432, 439}, {415, 421}, {340, 347}, {356, 358}, {165, 168}, {15, 17}, {259, 265}, {201, 204}, {192, 197}, {376, 383}, {210, 211}, {362, 367}, {481, 488}, {59, 64}, {307, 315}, {155, 164}, {465, 467}, {55, 60}, {20, 24}, {297, 304}, {207, 210}, {322, 328}, {139, 142}, {192, 195}, {28, 36}, {100, 108}, {71, 76}, {103, 105}, {34, 38}, {439, 441}, {162, 168}, {433, 433}, {368, 369}, {137, 137}, {105, 112}, {278, 280}, {452, 452}, {131, 132}, {475, 480}, {126, 129}, {95, 104}, {93, 99}, {394, 403}, {70, 78}};
        if (intervals == null || intervals.length == 0) return;
        List<int[]> list = new ArrayList<>();
        //起点排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        list.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > list.get(list.size() - 1)[1]) {
                //添加新区间
                list.add(intervals[i]);
            } else {
                //合并
                int[] temp = new int[]{0, 0};
                int max = Math.max(list.get(list.size() - 1)[1], intervals[i][1]);
                temp[0] = list.get(list.size() - 1)[0];
                temp[1] = max;
                list.remove(list.size() - 1);
                list.add(temp);
            }
        }

        int[][] array = list.toArray(new int[0][]);
        System.out.println("sdfsdf");
//        return list.toArray(new int[0][]);
    }


    @Test
    public void merge2() {
        int[][] intervals = {{362, 367}, {314, 315}, {133, 138}, {434, 443}, {202, 203}, {144, 145}, {229, 235}, {205, 212}, {314, 323}, {128, 129}, {413, 414}, {342, 345}, {43, 49}, {333, 342}, {173, 178}, {386, 391}, {131, 133}, {157, 163}, {187, 190}, {186, 186}, {17, 19}, {63, 69}, {70, 79}, {386, 391}, {98, 102}, {236, 239}, {195, 195}, {338, 338}, {169, 170}, {151, 153}, {409, 416}, {377, 377}, {90, 96}, {156, 165}, {182, 186}, {371, 372}, {228, 233}, {297, 306}, {56, 61}, {184, 190}, {401, 403}, {221, 228}, {203, 212}, {39, 43}, {83, 84}, {66, 68}, {80, 83}, {32, 32}, {182, 182}, {300, 306}, {235, 238}, {267, 272}, {458, 464}, {114, 120}, {452, 452}, {372, 375}, {275, 280}, {302, 302}, {5, 9}, {54, 62}, {237, 237}, {432, 439}, {415, 421}, {340, 347}, {356, 358}, {165, 168}, {15, 17}, {259, 265}, {201, 204}, {192, 197}, {376, 383}, {210, 211}, {362, 367}, {481, 488}, {59, 64}, {307, 315}, {155, 164}, {465, 467}, {55, 60}, {20, 24}, {297, 304}, {207, 210}, {322, 328}, {139, 142}, {192, 195}, {28, 36}, {100, 108}, {71, 76}, {103, 105}, {34, 38}, {439, 441}, {162, 168}, {433, 433}, {368, 369}, {137, 137}, {105, 112}, {278, 280}, {452, 452}, {131, 132}, {475, 480}, {126, 129}, {95, 104}, {93, 99}, {394, 403}, {70, 78}};
        List<int[]> res = new ArrayList<>();
        if (intervals.length == 0 || intervals == null) return;
        // 对起点终点进行排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int i = 0;
        while (i < intervals.length) {
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 如果有重叠，循环判断哪个起点满足条件
            while (i < intervals.length - 1 && intervals[i + 1][0] <= right) {
                i++;
                right = Math.max(right, intervals[i][1]);
            }
            // 将现在的区间放进res里面
            res.add(new int[]{left, right});
            // 接着判断下一个区间
            i++;
        }
//        return res.toArray(new int[0][]);
    }


    //21. 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        //构造哑结点
        ListNode headL1Node = new ListNode(0);
        ListNode result = headL1Node;
        //l2 标志节点
        ListNode l2Head = l2;
        headL1Node.next = l1;
        while (l2 != null) {
            while (headL1Node.next != null && l2.val >= headL1Node.next.val) {
                headL1Node = headL1Node.next;
            }
            l2Head = l2.next;
            l2.next = headL1Node.next;
            headL1Node.next = l2;
            l2 = l2Head;
        }
        return result.next;
    }


    //递归  q:n的阶乘 n*(n-1)*....*1
    /*模型一： 在递去的过程中解决问题 
function recursion(大规模){
    if (end_condition){      // 明确的递归终止条件
        end;   // 简单情景
    }else{            // 在将问题转换为子问题的每一步，解决该步中剩余部分的问题
        solve;                // 递去
        recursion(小规模);     // 递到最深处后，不断地归来
    }
}

模型二： 在归来的过程中解决问题

function recursion(大规模){
    if (end_condition){      // 明确的递归终止条件
        end;   // 简单情景
    }else{            // 先将问题全部描述展开，再由尽头“返回”依次解决每步中剩余部分的问题
        recursion(小规模);     // 递去
        solve;                // 归来
    }
}
    * */
    @Test
    public void diguiTest() {
        int n = 5;
        System.out.println("result:" + digui(n));
        System.out.println("result2:" + noDigui(n));
    }


    //递归
    public int digui(int n) {
        int result = -1;
        if (n == 1) { //递归终止条件
            return 1;  //简单场景下的终止操作
        }
        result = n * digui(n - 1);   //相同重复逻辑，缩小规模
        return result;
    }

    //非递归，循环
    public int noDigui(int n) {
        int result = 1;
        while (n != 0) {
            result = n * result;
            n--;
        }
        return result;
    }

    //斐波那契数列
    @Test
    public void fbnqNumTest() {
        System.out.println("result:" + fbnqNum(7));
    }

    public int fbnqNum(int n) {
        if (n == 2 || n == 1) {
            return 1;
        }
        return fbnqNum(n - 1) + fbnqNum(n - 2);
    }

    //汉诺塔
    @Test
    public void hanNuoTaTest() {
        int nDisks = 5;
        moveDish(nDisks, 'A', 'B', 'C');
    }

    public void moveDish(int level, char from, char inter, char to) {

        if (level == 1) { // 递归终止条件
            System.out.println("从" + from + " 移动盘子" + level + " 号到" + to);
        } else {
            // 递归调用：将level-1个盘子从from移到inter(不是一次性移动，每次只能移动一个盘子,其中to用于周转)
            moveDish(level - 1, from, to, inter); // 递归调用，缩小问题的规模
            // 将第level个盘子从A座移到C座
            System.out.println("从" + from + " 移动盘子" + level + " 号到" + to);
            // 递归调用：将level-1个盘子从inter移到to,from 用于周转
            moveDish(level - 1, inter, from, to); // 递归调用，缩小问题的规模
        }
    }


    @Test
    public void hannuota() {
        int num = 5;
        hannuota1(num, 'A', 'B', 'C');
    }

    public void hannuota1(int n, char A, char B, char C) {
        if (n == 1) {//结束条件
            System.out.println("从" + A + " 移动盘子" + n + " 号到" + C);
        } else {
            hannuota1(n - 1, A, C, B);
            System.out.println("从" + A + " 移动盘子" + n + " 号到" + C);
            hannuota1(n - 1, B, A, C);
        }
    }


    //445. 两数相加 II
    @Test
    public void addTwoNumbers() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
//        if(l1==null) return l2;
//        if(l2==null) return  l1;
        LinkedList<ListNode> stack1 = new LinkedList<>();
        LinkedList<ListNode> stack2 = new LinkedList<>();

        int num1, num2, result, current;
        int flag = 0;
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }

        ListNode ans = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || flag > 0) {
            num1 = stack1.isEmpty() ? 0 : stack1.pop().val;
            num2 = stack2.isEmpty() ? 0 : stack2.pop().val;
            current = num1 + num2 + flag;
            result = current % 10;
            flag = current / 10;
            System.out.println("result:" + result);
            System.out.println("flag:" + flag);
            ListNode node = new ListNode(result);
            node.next = ans;
            ans = node;
        }

//        return ans;
    }


    //24. 两两交换链表中的节点
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode l2 = head.next;
        ListNode result = l2;
        ListNode temp;
        while (head.next != null) {
            temp = l2.next;
            l2.next = head;
            if (temp != null && temp.next != null) {
                head.next = temp.next;
                head = temp;
                l2 = temp.next;
            } else {
                head.next = temp;
                break;
            }
        }
        return result;
    }


    //24. 两两交换链表中的节点  递归
    public ListNode swapPairs1(ListNode head) {
        return digui(head, head.next);
    }

    public ListNode digui(ListNode l1, ListNode l2) {
        //结束条件
        if (l1 == null || l2 == null) {
            return l1;
        }
        //交换
        ListNode temp = l2.next;
        l2.next = l1;
        //减小规模
        if (temp == null) {
            l1.next = temp;
            return l2;
        }
        l1.next = digui(temp, temp.next);
        return l2;
    }


    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        //结束条件
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        //减小规模
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists3(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists3(l1, l2.next);
            return l2;
        }


    }

    //94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        traversalDigui(root, list);
        return list;

    }

    //递归
    public void traversalDigui(TreeNode root, List<Integer> list) {
        //结束条件
        if (root == null) {
            return;
        }
        //件小规模
        traversalDigui(root.left, list);
        list.add(root.val);
        traversalDigui(root.right, list);
    }

    //非递归
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            System.out.println(root.val);
            list.add(root.val);
            root = root.right;
        }
        return list;

    }

    //f非递归前序
    public List<Integer> inorderTraversal3(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList();
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode n1 = stack.pop();
            if (n1.right != null) {
                stack.push(n1.right);
            }
            if (n1.left != null) {
                stack.push(n1.left);
            }
            System.out.println(root.val);
            list.add(root.val);
        }
        return list;

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    //103. 二叉树的锯齿形层次遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        zigzagLevelOrderDigui(root, list);
        return list;
    }

    public void zigzagLevelOrderDigui(TreeNode root, List list) {
        //结束条件

    }


    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        // add the root element with a delimiter to kick off the BFS loop
        LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
        node_queue.addLast(root);
        node_queue.addLast(null);

        LinkedList<Integer> level_list = new LinkedList<Integer>();
        boolean is_order_left = true;
        while (node_queue.size() > 0) {
            TreeNode curr_node = node_queue.pollFirst();
            if (curr_node != null) {
                if (is_order_left)
                    level_list.addLast(curr_node.val);
                else
                    level_list.addFirst(curr_node.val);

                if (curr_node.left != null)
                    node_queue.addLast(curr_node.left);
                if (curr_node.right != null)
                    node_queue.addLast(curr_node.right);

            } else {
                // we finish the scan of one level
                results.add(level_list);
                level_list = new LinkedList<Integer>();
                // prepare for the next level
                if (node_queue.size() > 0)
                    node_queue.addLast(null);
                is_order_left = !is_order_left;
            }
        }
        return results;
    }

    public List<List<Integer>> zigzagLevelOrder0(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;//层数
        while (!queue.isEmpty()) {
            List<Integer> tmp = new LinkedList<>();//层结果
            int cnt = queue.size();
            System.out.println("cnt:" + cnt);
            for (int i = 0; i < cnt; i++) {
                TreeNode node = queue.poll();
                System.out.println("node:" + node.val);
                // System.out.println(node.val);
                if (depth % 2 == 0) tmp.add(node.val);
                else tmp.add(0, node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(tmp);
            depth++;
        }
        return res;
    }


    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();//结果
        helper(res, root, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, TreeNode root, int depth) {
        if (root == null) return;
        System.out.println("res.size:" + res.size());
        if (res.size() == depth) res.add(new LinkedList<>());
        if (depth % 2 == 0) res.get(depth).add(root.val);
        else res.get(depth).add(0, root.val);
        helper(res, root.left, depth + 1);
        helper(res, root.right, depth + 1);
    }


    //树的层次遍历
    public void levelTraverse(TreeNode root) {
        if (root == null) return;
        LinkedList<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + "  ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }


    public List<List<Integer>> zigzagLevelOrder5(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        int deep = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> lawList = new ArrayList<>();//层结果
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode node = queue.poll();
                if (deep % 2 == 0) {
                    lawList.add(node.val);
                } else {
                    lawList.add(0, node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(lawList);
            deep++;
        }
        return list;

    }


    //树的层次遍历
    public void levelTraverse1(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.val);
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    @Test
    public void test90() {
        String test = "/home////foo/";
        String[] array = test.split("/");
        System.out.println(array.toString());
    }

    //71. 简化路径
    public String simplifyPath(String path) {

        LinkedList<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!item.isEmpty() && !item.equals(".")) {
                stack.push(item);
            }


        }
        String res = "";
        for (String item : stack) {
            res = "/" + item + res;
        }
        return res.equals("") ? "/" : res;
    }


    @Test
    public void test45() {
        String path = "/a//b////c/d//././/..";
        Deque<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!item.isEmpty() && !item.equals(".")) {
                stack.push(item);
                System.out.println("item:" + item);
            }
        }
        String res = "";
        for (String d : stack) {
            res = "/" + d + res;
            System.out.println("d:" + d + ":res:" + res);
        }


    }


    //501. 二叉搜索树中的众数
    public int[] findMode(TreeNode root) {
        HashMap<Integer, Integer> result = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        treeTraserval(root, result);
        int max = 0;
        for (Map.Entry entry : result.entrySet()) {

            if (max <= (int) entry.getValue()) {
                max = (int) entry.getValue();
                list.add((Integer) entry.getKey());
            }
            System.out.println("k:" + entry.getKey() + ":v:" + entry.getValue());

        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public void treeTraserval(TreeNode root, HashMap result) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int key;
        while (!queue.isEmpty()) {
            int value = 0;
            root = queue.poll();
            key = root.val;
            if (result.get(root.val) != null) {
                value = (int) result.get(root.val);
            }
            result.put(key, ++value);

            if (root.left != null) {
                queue.offer(root.left);
            }
            if (root.right != null) {
                queue.offer(root.right);
            }
        }


    }

    int maxCount = 0;
    int currentValue = 0;
    int currentCount = 0;

    //501. 二叉搜索树中的众数
    public int[] findMode1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        findModeDigui(root, list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //中序遍历--中序遍历会从小到大排序
    public void findModeDigui(TreeNode root, List list) {
        if (root == null) {
            return;
        }
        findModeDigui(root.left, list);
        System.out.println(root.val);
        if (currentValue == root.val) {
            currentCount++;
        } else {
            currentCount = 1;
            currentValue = root.val;
        }
        if (currentCount == maxCount) {
            list.add(currentValue);
        } else if (currentCount > maxCount) {
            list.clear();
            list.add(currentValue);
            maxCount = currentCount;
        }
        findModeDigui(root.right, list);
    }


    //冒泡排序
    @Test
    public void maopao() {
        int temp;
        int[] num = {2, 5, 9, 6, 1, 0, 4};
        for (int i = 0; i < num.length - 1; i++) {
            for (int j = 0; j < num.length - i - 1; j++) {
                if (num[j] > num[j + 1]) {
                    temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                }
            }
        }
        System.out.println(num);
    }

    @Test
    public void testQuickSort() {
        int[] nums = {4, 5, 9, 2, 6, 0, 1, 7, 5, 4};

        quickSort(nums, 0, nums.length - 1);
        System.out.println("sdfsdfsdfdsf");
    }


    //快速排序--分治法，二分法
    public void quickSort(int[] nums, int l, int r) {
        if (l < r) {
            int start = l, end = r;
            int b = nums[l];//基准值;
            while (start < end) {
                //从右到左，直到找出小于b的数
                while (start < end && nums[end] >= b) {
                    end--;
                }
                if (start < end) {
                    nums[start] = nums[end];
                    start++;
                }
                //从左向右，直到找出大于等于b的数
                while (start < end && nums[start] < b) {
                    start++;
                }
                if (start < end) {
                    nums[end] = nums[start];
                    end--;
                }
            }
            nums[start] = b;
            quickSort(nums, l, start - 1);
            quickSort(nums, start + 1, r);

        }

    }

    //75. 颜色分类
    @Test
    public void sortColors() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        quickSort1(nums, 0, nums.length - 1);
        System.out.println("sdfsdfs");
    }

    public void quickSort1(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l, j = r, b = nums[i];
        while (i < j) {
            //从右往左
            while (i < j && nums[j] >= b) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }

            //从左往右
            while (i < j && nums[i] < b) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = b;
        quickSort1(nums, l, i - 1);
        quickSort1(nums, i + 1, r);
    }

    //56. 合并区间
    public int[][] merge(int[][] intervals) {
        List<int[]> array = new ArrayList<>();
        int[] ints = new int[2];
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1] > intervals[i + 1][0]) {
                ints[1] = Math.max(intervals[i][1], intervals[i + 1][1]);
                ints[0] = intervals[i][0];
            } else {
                ints[1] = intervals[i][1];
                ints[0] = intervals[i][0];
            }

        }

        return null;
    }

    class Node1 {
        int val;
        Node1 next;

        public Node1(int val) {
            this.val = val;
        }
    }


    //约瑟夫问题
    @Test
    public void yueSefu() {
        int n = 10, k = 2, m = 3;
        List<Integer> array = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            array.add(i);
        }
        for (int i = 1; i < n; i++) {//次数
            int start = k - 1;
            int outIndex = (start + m - 1) % array.size();
            System.out.println(array.get(outIndex) + "出局");
            array.remove(outIndex);
            k = outIndex + 1;

        }
        System.out.println(array.get(0));
    }


    @Test
    public void testJosephProblem() {
        int lastNumber = josephProblem(10, 2, 3);
        System.out.println(lastNumber);
    }

    public int josephProblem(int numberN, int startNumberK, int numberM) {
        //定义一个链表，让链表里面存1...1000个数
        List<Integer> josephList = new ArrayList<Integer>();
        for (int i = 1; i <= numberN; i++) {
            josephList.add(i);
        }
        int start = startNumberK;
        int length = josephList.size();
        for (int i = 1; i < length; i++) {
            start = (start + numberM) % josephList.size();
            System.out.println(josephList.get(start) + "出局");
            josephList.remove(start);
        }
        return josephList.get(0);
    }


    int count = 0;

    //剑指offer：二叉树路径求和
    /*@Test
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> lawResult = new ArrayList<>();//层结果
        traxerFirst(root, result, lawResult, sum, count);
        return result;
    }*/

    //先序遍历
   /* public void traxerFirst(TreeNode root, List result, List lawResult, int sum, int count) {
        if (root == null) {
            if (count == sum) {
                result.add(lawResult);
                System.out.println(lawResult);
                lawResult.clear();
            }
            return;
        }

        System.out.println(root.val);
        lawResult.add(root.val);
        count += root.val;
        traxerFirst(root.left, result, lawResult, sum, count);
        traxerFirst(root.right, result, lawResult, sum, count);
    }*/


    //剑指 Offer 34. 二叉树中和为某一值的路径
    //错误
    class Solution1 {


        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> lawResult = new ArrayList<>();//层结果
            traxerFirst(root, result, lawResult, sum, 0);
            return result;
        }

        //先序遍历
        public void traxerFirst(TreeNode root, List result, List lawResult, int sum, int count) {
            if (root == null) {
                if (count == sum) {
                    System.out.println(lawResult);
                    result.add(lawResult);
                    //error：result中添加的对象会因为这个clear()操作清空
                    lawResult.clear();
                }

                return;
            }

            System.out.println(root.val);
            lawResult.add(root.val);
            count += root.val;
            System.out.println("count:" + count);
            traxerFirst(root.left, result, lawResult, sum, count);
            traxerFirst(root.right, result, lawResult, sum, count);
        }
    }

    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();

    void recur(TreeNode root, int tar) {
        new LinkedList<>();
        if (root == null) return;
        path.add(root.val);
        tar -= root.val;
        if (tar == 0 && root.left == null && root.right == null)
            res.add(new LinkedList(path));
        recur(root.left, tar);
        recur(root.right, tar);
        path.removeLast();
    }

    //剑指 Offer 34. 二叉树中和为某一值的路径
    List<List<Integer>> result = new LinkedList<>();
    LinkedList<Integer> path1 = new LinkedList<>();

    public List<List<Integer>> pathSum1(TreeNode root, int sum) {
        treeTraveralFirst(root, sum);
        return result;
    }

    public void treeTraveralFirst(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path1.add(root.val);
        System.out.println(root.val);
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null) {
            result.add(new LinkedList<>(path1));
        }
        treeTraveralFirst(root.left, sum);
        treeTraveralFirst(root.right, sum);
        path1.removeLast();
    }


    //98. 验证二叉搜索树---中序遍历---有序数列
    long pre = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        //结束条件
        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left)) {
            return false;
        }
        System.out.println(root.val);
        if (pre >= root.val) {
            return false;
        }
        pre = root.val;

        return isValidBST(root.right);

    }

    //5. 最长回文子串----动态规划
    @Test
    public void longestPalindrome() {
        String s = "ibvjkmpyzsifuxcabqqpahjdeuzaybqsrsmbfplxycsafogotliyvhxjtkrbzqxlyfwujzhkdafhebvsdhkkdbhlhmaoxmbkqiwiusngkbdhlvxdyvnjrzvxmukvdfobzlmvnbnilnsyrgoygfdzjlymhprcpxsnxpcafctikxxybcusgjwmfklkffehbvlhvxfiddznwumxosomfbgxoruoqrhezgsgidgcfzbtdftjxeahriirqgxbhicoxavquhbkaomrroghdnfkknyigsluqebaqrtcwgmlnvmxoagisdmsokeznjsnwpxygjjptvyjjkbmkxvlivinmpnpxgmmorkasebngirckqcawgevljplkkgextudqaodwqmfljljhrujoerycoojwwgtklypicgkyaboqjfivbeqdlonxeidgxsyzugkntoevwfuxovazcyayvwbcqswzhytlmtmrtwpikgacnpkbwgfmpavzyjoxughwhvlsxsgttbcyrlkaarngeoaldsdtjncivhcfsaohmdhgbwkuemcembmlwbwquxfaiukoqvzmgoeppieztdacvwngbkcxknbytvztodbfnjhbtwpjlzuajnlzfmmujhcggpdcwdquutdiubgcvnxvgspmfumeqrofewynizvynavjzkbpkuxxvkjujectdyfwygnfsukvzflcuxxzvxzravzznpxttduajhbsyiywpqunnarabcroljwcbdydagachbobkcvudkoddldaucwruobfylfhyvjuynjrosxczgjwudpxaqwnboxgxybnngxxhibesiaxkicinikzzmonftqkcudlzfzutplbycejmkpxcygsafzkgudy";
        int max = 1;
        //最大子串i，j
        int l = 0, r = 0;

        int len = s.length();
        boolean[][] num = new boolean[len][len];
        char[] chars = s.toCharArray();
        //初始化
        for (int i = 0; i < len; i++) {
            num[i][i] = true;
        }
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    num[i][j] = false;
                } else {
                    //chars[i]==chars[j]
                    if (j - i < 3) {
                        num[i][j] = true;
                    } else {
                        num[i][j] = num[i + 1][j - 1];
                    }
                }
//                System.out.println("i,j=" + i + "," + j + ":" + num[i][j]);
                //记录最大子串
                if (num[i][j] && j - i + 1 >= max) {
                    l = i;
                    r = j;
                    max = j - i + 1;
                }
//                System.out.println("max=" + max);

            }
        }
//        System.out.println("l,r=" + l + "," + r);
        System.out.println("结果：" + s.substring(l, r + 1));

    }

    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int[] num2s = new int[nums.length];
        num2s[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (num2s[i - 1] >= 0) {
                num2s[i] = num2s[i - 1] + nums[i];
            } else {
                num2s[i] = nums[i];
            }
            if (num2s[i] >= max) {
                max = num2s[i];
            }
        }
        return max;
    }


    @Test
    public void isLongPressedName() {
        String name = "alex";
        String typed = "alexxr";
        int i = 0, j = 0;
        while (j < typed.length()) {
            System.out.println("i=" + i + ":j=" + j);
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j >= 1 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
//                return false;
                System.out.println("FALSE");
            }
        }

        if (i < name.length()) {
            System.out.println("FALSE");
            return;
        }
//        return true;
        System.out.println("TRUE");
    }


    //206. 反转链表

    ListNode reverseHead = new ListNode(2);

    public ListNode reverseList(ListNode head) {

        reverse(head, reverseHead);
        System.out.println(reverseHead.val);
        return reverseHead;
    }

    //引用类型 传的是地址， 基本类型传的是值
    public void reverse(ListNode curr, ListNode reverseHead) {

        //结束条件
        if (curr.next == null) {
            reverseHead = curr;
            System.out.println(reverseHead.val);
            return;
        }
        System.out.println("+++++");
        reverse(curr.next, reverseHead);
        System.out.println("======");
        curr.next.next = curr;
        curr.next = null;
        System.out.println(curr.val);
        //  System.out.println(reverseHead.val);
    }

    //381. O(1) 时间插入、删除和获取随机元素 - 允许重复
    class RandomizedCollection {
        HashMap<Integer, Integer> map;
        int key;

        /**
         * Initialize your data structure here.
         */
        public RandomizedCollection() {
            map = new HashMap<>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (map.containsValue(val)) {
                map.put(++key, val);
                return false;
            } else {
                map.put(++key, val);
                return true;
            }
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained the specified element.
         */
        public boolean remove(int val) {
            Collection coll = map.values();
            if (coll.contains(val)) {
                coll.remove(val);
                return true;
            }
            return false;
        }

        /**
         * Get a random element from the collection.
         */
        public int getRandom() {
            int index = (int) (Math.random() * key + 1);
            return map.get(index);
        }
    }

    //三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int target = -nums[i];
            for (int second = i + 1, third = nums.length - 1; second < third; ) {
                if (nums[second] == nums[second - 1]) {
                    continue;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList();
                    list.add(second);
                    list.add(third);
                    ans.add(list);
                    second++;
                    third--;
                } else if (nums[second] + nums[third] < target) {
                    second++;
                } else {
                    third--;
                }
            }

        }
        return ans;
    }


    //生成数据完整性sign
    public static final String RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJaBnB2fqV4lTKbvw0Sd2536EMSA2F2uv2sIxHIQcjwgkhHCjZDzDUn9MZyHRxNH2RtjMCCvbPQh7CSu+7SRi07UT7Fpl0Jh461cBThIxfK8U1nnxEgKlNdwYGxVHhHM8GAZNM0AmVk8VBhUtEj9OELkVVH97tRVfmjHCwRIj/9ZAgMBAAECgYAcQQKcsQ9rhBcKs7H1nKjQ1FP3f7SeiaKXplKykxHO5dJmER7gWjBhdm2s09xs6yz/rjQnvqb2gbPCAzNvZ28+N+O1evQ0PY0hannanoz+D73SUTfesUbG1uSLo5GPZAT/mW/eWhKBgoo5yoyMD7pWLCAdnye6y4bRacqIZo63MQJBAN7l2hgSLSR3nqtibqx5KwZMJL7XzwC1rdSZTd6bL+xoHP02zromD6H6TukFwdJ6FuWNeQDsr+hNLa0x4oqZQc0CQQCs25HOX7/evQA8lZ04A4YBUE4A8qJBi0M1illI2sFM9E4LlfU+67uGgWXxTaIw1NAeXHE7/JZtiIV6BggFhRe9AkEAohfO+VBGic1/kqy7RSu8cRDwa+Ruwdpc9k0iBq8eM7Im2rF/tnk1RxrLRcQNBm4ItpiiFV0KM0nk3J15XEdFRQJBAJ5iqh0hGtvq7haVOHOkttrpTDAOIqJQCos6c0kQOGJc0E5JX2gB89fxJQmPiveXaAMJzS+b5/IBT9xZPmxgYTkCQFkpbN8fjF75yjZM3E9TUG2QOEg20V8NFJ9I3H0v7SO2GjS+fPZ3QuOZQAuqSOeNz+aPaFF86EjJpYAyC1Z//zw=";
    //rsa加密公钥
    public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRssROo9nwQ2fuoOAQqCwBouAngmwsU4D10LWsIuPcfeMD7P70clnNF2aJRr89gnLwDOhp+2OVACRPeynW5Bb6rizBNPp/wxpUKKN2awOZKb7V2Boz36yg2rmU3rCGSuC7oZ/N0pdqunmWE3/+byzpS/3NFdApfAh22QlCdH7a+QIDAQAB";

    @Test
    public void testencrypt() {
        String input = "{name:hec}";
        String ans = rsaEncrypt(input);
        rsaDecrypt(ans);
    }

    //rsa加密
    public static String rsaEncrypt(String jsonStr) {
        JSONObject dataObj = new JSONObject();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            int len = jsonObject.length();
            if (len > 0) {
                Iterator<String> it = jsonObject.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = jsonObject.optString(key);
                    String rsaValue = BairongSignature.encryptRSA(value, RSA_PUBLIC_KEY);
                    dataObj.put(key, rsaValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("加密串：" + dataObj.toString());
        return dataObj.toString();

    }

    //rsa解密
    public static void rsaDecrypt(String jsonStr) {
        JSONObject dataObj = new JSONObject();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            int len = jsonObject.length();
            if (len > 0) {
                Iterator<String> it = jsonObject.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = jsonObject.optString(key);
                    String rsaValue = BairongSignature.decryptRSA(value, RSA_PRIVATE_KEY);
                    dataObj.put(key, rsaValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("解密串：" + dataObj.toString());

    }


    //514,自由之路
    Map<Character, List<Integer>> mp;
    int[][] dp;

    public int findRotateSteps(String ring, String key) {
        int rl = ring.length();
        int kl = key.length();
        if (kl == 0) return 0;
        char[] r = ring.toCharArray();
        char[] k = key.toCharArray();
        mp = new HashMap<>();
        for (int i = 0; i < rl; i++) {//保存字符出现的位置
            if (mp.containsKey(r[i])) {
                mp.get(r[i]).add(i);
            } else {
                List<Integer> next = new ArrayList<>();
                next.add(i);
                mp.put(r[i], next);
            }
        }
        dp = new int[kl][rl];
        List<Integer> next2 = mp.get(k[0]);
        Iterator<Integer> it2 = next2.iterator();
        while (it2.hasNext()) {
            int c = it2.next();
            int m = Math.min(c, rl - c);//找到每个位置
            dp[0][c] = m + 1;
        }

        //第二步
        for (int i = 1; i < kl; i++) {

            List<Integer> next = mp.get(k[i]);
            Iterator<Integer> it = next.iterator();
            while (it.hasNext()) {
                int c = it.next();//找到本次的所有位置
                int min = Integer.MAX_VALUE;
                List<Integer> next1 = mp.get(k[i - 1]);
                Iterator<Integer> it1 = next1.iterator();
                while (it1.hasNext()) {
                    int d = it1.next();//找到上个字符所有的位置来计算
                    int m = Math.min(rl - c + d, rl - d + c);//顺时针转
                    m = Math.min(m, Math.abs(c - d));//Math.abs(c-d):逆时针转
                    min = Math.min(min, dp[i - 1][d] + m + 1);

                }
                dp[i][c] = min;
            }
        }
        int ans = Integer.MAX_VALUE;
        List<Integer> next = mp.get(k[kl - 1]);
        Iterator<Integer> it = next.iterator();
        while (it.hasNext()) {
            ans = Math.min(ans, dp[kl - 1][it.next()]);
        }
        return ans;


    }


    //链表是否有环&确定环入口的位置
   /* @Test
    public boolean hasCycle(ListNode head){
        ListNode qickPoint = head;
        ListNode slowPoint = head;
        while(qickPoint!=null&&qickPoint.next!=null){
            slowPoint = slowPoint.next;
            qickPoint = qickPoint.next.next;
            // 注意判空， 如果值一致， 那么确定有环退出
            if (qickPoint != null && qickPoint.val == slowPoint.val) {
                return true;
            }
        }
        return false;
    }*/


    //延迟1s后监听
    static  class SearchEditText extends EditText{
        private  static final  long TIM =1000L;
        ITextListener mListener;
        String mStartText;

        Runnable mAction = new Runnable() {
            @Override
            public void run() {
                // 判断最终和开始前是否一致
//                if (!StringUtils.equals(mStartText, getText().toString())) {
//                    mStartText = getText().toString();// 更新 mStartText
//                    mListener.onTextChanged(mStartText);
//                }
            }
        };

        public SearchEditText(Context context) {
            super(context);
        }

        @Override
        protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
            super.onTextChanged(text, start, lengthBefore, lengthAfter);
            removeCallbacks(mAction);
            postDelayed(mAction,TIM);
        }

        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            removeCallbacks(mAction);
        }

        public void setTextChangedListener(ITextListener listener){
            mListener = listener;
        }

        private interface ITextListener{
            void onTextChanged(String text);
        }
    }


    @Test
    public void minDeletions() {
        int test = 1<<2;
        System.out.println("test:"+test);
        String s ="ssssss";
        Integer[] chars = new Integer[26];
        int ans =0;
        char[] words = s.toCharArray();
        for(int i=0;i<s.length();i++){
            if(chars[words[i]-'a']==null){
                chars[words[i]-'a']=1;
            }else{
                chars[words[i]-'a']++;
            }

        }

        chars=new Integer[]{-2147483646,2147483646};
        Arrays.sort(chars,new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                if(o1==null){
                    return 1;
                }
                if(o2==null){
                    return -1;
                }
                return o2-o1;
            }
        });


        for(int l=0;l<chars.length-1;l++){
            int r=l+1;
            while(chars[l]!=null&&chars[r]!=null&&chars[l]==chars[r]&&chars[l]!=0){
                chars[r]--;
                ans++;
                r++;
            }

        }

        System.out.println(ans);
//        return ans;
    }

    public  void  reverseOrder(Integer[] arr){
        int temp;//定义一个临时变量
        for(int i=0;i<arr.length-1;i++){//冒泡趟数
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j+1]<arr[j]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}






