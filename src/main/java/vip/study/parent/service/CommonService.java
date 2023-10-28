package vip.study.parent.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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
}
