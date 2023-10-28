package vip.study.parent.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.study.parent.api.model.Result;
import vip.study.parent.common.SortEnum;
import vip.study.parent.service.AlgorithmService;
import vip.study.parent.service.CommonService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
* @author: vincent.lai
* @: 2023.10.28
* */
@Slf4j // lombok 注释
@Api(value = "算法测试模块")
@RestController
public class AlgorithmController {

    private static final String NUMS = "5,3,7,1,6,9,8,2,4";
    private static final String SORTTYPE = "选择排序,插入排序,冒泡排序,快速排序,归并排序";

    @Autowired // 配合 @Service
    AlgorithmService algorithmService;


    @ApiOperation(value = "排序使用", notes = "@vincent.lai")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "list", paramType = "query", dataType = "int[]", defaultValue = NUMS),
            @ApiImplicitParam(name = "types", dataType = "String", paramType = "query", allowableValues = SORTTYPE, allowMultiple = true)
            })
    @RequestMapping(value = "/sortAlgorithm", method = RequestMethod.GET)
    public Result sortAlgorithm(int[] list, String[] types){
        log.info("AlgorithmController.sortAlgorithm request: {}, {}", Arrays.toString(list), types);
        Result result = algorithmService.insertSort(list, types);
        return result;
    }

}
