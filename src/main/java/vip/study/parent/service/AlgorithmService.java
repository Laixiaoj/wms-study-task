package vip.study.parent.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vip.study.parent.api.model.Result;
import vip.study.parent.common.SortEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Slf4j // lombok 注释
@Service // 配合 @Autowired
public class AlgorithmService {

    @Autowired
    CommonService commonService;

    public Result quickSort(int[] nums){
        Result result = new Result(200, "success");
        commonService.quickSort(nums, 0, nums.length - 1);
        result.setResponse(Arrays.toString(nums));
        return result;

    }

    public Result insertSort(int[] nums, String[] types) {
        int[] tempList = nums;
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




}
