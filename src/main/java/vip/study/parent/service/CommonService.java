package vip.study.parent.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.study.parent.api.model.TreeNode;

import java.util.*;

@Slf4j
@Service
public class CommonService {

    // -----------------------冒泡排序
    public void bubbleSort(int []a){
        int L = a.length;
        for (int i = 0; i < L - 1; i++) { // 总共需要比较【n-1】个元素
            Boolean isSwap = false;
            for (int j = 0; j < L - 1 - i; j++) { // 相邻两个元素对比
                if(a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    isSwap = true;
                }
            }
            if (!isSwap) break; //
        }
    }

    // -----------------------选择排序
    public void selectSort(int []a){
        for (int i = 0; i < a.length - 1; i++) {
            int k = i;
            for (int j = k + 1; j < a.length; j++) {
                if(a[k] > a[j]) k = j; //找到未排序子集的最小元素
            }
            swap(a, i, k); // 交换位置
        }
    }
    
    // -----------------------快速排序
    public void quickSort(int []a, int L, int R){
        if(L >= R) return;
        int q = binaryPatition(a, L, R); // 找基点
        log.info(" ----- 当前基点: {}, 排序结果： {}", q, Arrays.toString(a));
        quickSort(a, L, q - 1); // 基点左区域
        quickSort(a, q + 1, R); // 基点右区域
    }
    // 基点
    public int binaryPatition(int []a, int L, int R){
        int pv = a[L]; // 取【最左元素】为基点
        int j = R; // j【从右到左】找比基点小
        int i = L; // i【左到右】找比基数大
        while(i != j){
            // j方向
            while (a[j] > pv && i < j) j--;
            // i方向
            while (a[i] <= pv && i < j) i++;
            //小交换到左边，大交换到右边
            swap(a, i, j);
        }
        swap(a, L, j); // 基点元素交换到中间
        return j; // 返回新基点位置
    }
    // 交换
    public void swap(int []a, int i ,int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // -----------------------插入排序
    public void insertSort(int []a){

        for (int i = 1; i < a.length; i++) { // 未排序子集从1开始
            log.info(" ---- 当前排序结果： {}", Arrays.toString(a));
            int t = a[i]; // 待插入元素
            int j = i - 1; // 排序子集的上界
            while(j >= 0){
                if(t < a[j]) a[j + 1] = a[j]; // 待插入元素 < 排序子集元素
                else break; // 待插入元素 > 排序子集元素, 直接退出
                j--;
            }
            a[j + 1] = t; // 【j + 1】是插入的位置
        }
    }

    // -----------------------归并排序 -- 分而治之
    public void mergeSort(int[] a, int L, int R){
        if(L < R){
            int M = (L + R) / 2;  // 取中点不断分治
            mergeSort(a, L , M); // 左半边排序
            mergeSort(a, M + 1, R); // 右半边排序

            int[] b = new int[R - L + 1];
            merge(a, b, L, M, R);  // 最后合并子问题
        }
    }

    // 将左半边a[L, M] 和右半边a[M + 1, R] 合并到 b[L, R]
    public void merge(int[] a, int[] b, int L, int M, int R){
        int i = L; // 左半边下界
        int j = M + 1; // 右半边下界
        int k = 0; // b数组下界

        while (i <= M && j <= R){  // 同时从两边出发，每次取最小
            if(a[i] < a[j])
                b[k++] = a[i++];
            else
                b[k++] = a[j++];
        }
        // 存在一边先走完情况，分别再来一次
        while (i <= M)
            b[k++] = a[i++];

        while (j <= R)
            b[k++] = a[j++];

        // 拼接回到a[L, R]中，直到 a[0, n]为止
        for (int l = 0; l < k; l++) {
            a[L + l] = b[l];
        }

    }

    // ----------------------- 二叉树构建
    public TreeNode initBtree(int[] elements, int size){

        if(size < 1){
            return null;
        }
        TreeNode[] nodes = new TreeNode[size];
        //将int数据转换为TreeNode节点
        for (int i = 0; i < size; i++) {
            if (elements[i] == 0) {
                nodes[i] = null;
            } else {
                nodes[i]  = new TreeNode(elements[i]);
            }
        }
        Deque<TreeNode> q = new LinkedList<>();

        TreeNode node = new TreeNode();
        TreeNode head = nodes[0];
        q.addFirst(nodes[0]);
        int index = 1;

        while(index < size){
            node = q.removeFirst();//抛出队首元素
            q.addLast(nodes[index++]);//加到队尾
            node.left = q.peekLast();
            if(index >= size){
                break;
            }
            q.addLast(nodes[index++]);
            node.right = q.peekLast();
        }
        return head;
    }

    // -----------------------二叉树前序遍历
    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;
    }

    // 递归查询：1、确定参数和返回值 2、确定终止条件 3、确定单层递归逻辑
    public void preOrder(TreeNode cur, List<Integer> res){
        // 终止条件
        if(cur == null) return; // 当前节点是否为空
        //单层逻辑
        res.add(cur.val); // 中
        preOrder(cur.left, res); // 左
        preOrder(cur.right, res); // 右
    }

    // -----------------------二叉树中序遍历
    public List<Integer> middOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        middOrder(root, res);
        return res;
    }

    // 递归查询：1、确定参数和返回值 2、确定终止条件 3、确定单层递归逻辑
    public void middOrder(TreeNode cur, List<Integer> res){
        // 终止条件
        if(cur == null) return; // 当前节点是否为空
        //单层逻辑
        middOrder(cur.left, res); // 左
        res.add(cur.val); // 中
        middOrder(cur.right, res); // 右
    }

    // -----------------------二叉树后序遍历
    public List<Integer> lastOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        lastOrder(root, res);
        return res;
    }

    // 递归查询：1、确定参数和返回值 2、确定终止条件 3、确定单层递归逻辑
    public void lastOrder(TreeNode cur, List<Integer> res){
        // 终止条件
        if(cur == null) return; // 当前节点是否为空
        //单层逻辑
        lastOrder(cur.right, res); // 右
        res.add(cur.val); // 中
        lastOrder(cur.left, res); // 左
    }

    // ----------------------- 二叉树层次遍历
    public List<List<Integer>> res = new ArrayList<List<Integer>>(); // 定义一个动态 二维 Integer 列表 res

    public List<List<Integer>> levelOrder(TreeNode root) {
        res = new ArrayList<List<Integer>>();
        queueTravel(root);
        return res;
    }

    // 借助 队列 实现遍历：按层次自上而下，从左到右遍历
    public void queueTravel(TreeNode root){

        if(root == null) return; // 终止条件
        // 定义队列
        Deque<TreeNode> que = new LinkedList<>();
        que.offer(root); // 根节点入队

        while(!que.isEmpty()){ // 遍历层：自上而下
            // 定义存 每层元素Integer 的列表
            List<Integer> item = new LinkedList<>();
            int len = que.size(); // que.size是动态大小

            while(len -- > 0){ // 遍历每层的节点：从左到右
                TreeNode tempNode = que.poll(); // 出队
                item.add(tempNode.val);
                // 左节点
                if(tempNode.left != null) que.offer(tempNode.left);
                // 右节点
                if(tempNode.right != null) que.offer(tempNode.right);
            }
            res.add(item); // 存 每层的节点元素
        }
    }
}
