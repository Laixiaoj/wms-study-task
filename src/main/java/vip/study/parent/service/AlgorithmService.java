package vip.study.parent.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import vip.study.parent.api.model.Result;
import vip.study.parent.api.model.TreeNode;
import vip.study.parent.common.OrderSortEnum;
import vip.study.parent.common.SortEnum;

import java.util.*;


@Slf4j // lombok 注释
@Service // 配合 @Autowired
public class AlgorithmService {

    @Autowired
    CommonService commonService;

    public Result sortAlgorithm(int[] nums, String[] types) {
        Map<String, String> mapRes = new HashMap<>();
        for (String type : types) {
            if(SortEnum.byDesc(type) == 1){
                commonService.selectSort(nums);
            }
            else if(SortEnum.byDesc(type) == 2){
                commonService.insertSort(nums);
            }
            else if(SortEnum.byDesc(type) == 3){
                commonService.bubbleSort(nums);
                mapRes.put(type, Arrays.toString(nums));
            }
            else if(SortEnum.byDesc(type) == 4){
                commonService.quickSort(nums, 0, nums.length - 1);
            }
            else{
                commonService.mergeSort(nums, 0, nums.length - 1);
            }
            mapRes.put(type, Arrays.toString(nums));
        }
        Result result = new Result(200, "success");
        result.setResponse(mapRes);
        log.info("AlgorithmController response: {}", result);
        return result;
    }

    public Result binaryTreeOrder(int[] nums, String[] types) {

        TreeNode root = commonService.initBtree(nums, nums.length);
        log.info("二叉树构造: {}", root);
        Map<String, String> treeRes = new HashMap<>();
        treeRes.put("待排序的树", Arrays.toString(nums));
        for (String type : types) {
            if(OrderSortEnum.byDesc(type) == 1){
                List<Integer> res = commonService.preOrderTraversal(root);
                log.info("{}", res.toString());
                treeRes.put(type, res.toString());
            }
            else if(OrderSortEnum.byDesc(type) == 2){
                List<Integer> res = commonService.middOrderTraversal(root);
                treeRes.put(type, res.toString());
            }
            else if(OrderSortEnum.byDesc(type) == 3){
                List<Integer> res = commonService.lastOrderTraversal(root);
                treeRes.put(type, res.toString());
            }
            else if(OrderSortEnum.byDesc(type) == 4){
                List<List<Integer>> finalRes = commonService.levelOrder(root);
                treeRes.put(type, finalRes.toString());
            }
        }
        Result result = new Result(200, "success");
        result.setResponse(treeRes);
        log.info("AlgorithmController.binaryTreeOrder response: {}", result);
        return result;
    }



}
