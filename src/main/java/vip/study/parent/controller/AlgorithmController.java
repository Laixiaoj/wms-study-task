package vip.study.parent.controller;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vip.study.parent.api.model.Result;
import vip.study.parent.api.model.TreeNode;
import vip.study.parent.service.AlgorithmService;
import java.util.Arrays;



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
    private static final String ORDERTYPE = "前序遍历,中序遍历,后序遍历,层次遍历";

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
        Result result = algorithmService.sortAlgorithm(list, types);
        return result;
    }

    @ApiOperation(value = "二叉树遍历使用", notes = "@vincent.lai")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "list", paramType = "query", dataType = "int[]", defaultValue = NUMS),
            @ApiImplicitParam(name = "types", dataType = "String", paramType = "query", allowableValues = ORDERTYPE, allowMultiple = true)
    })
    @RequestMapping(value = "/binaryTreeOrder", method = RequestMethod.GET)
    public Result binaryTreeOrder(int[] list, String[] types){
        log.info("AlgorithmController.binaryTreeOrder request: {}, {}", Arrays.toString(list), types);
        Result result = algorithmService.binaryTreeOrder(list, types);
        return result;
    }

}
