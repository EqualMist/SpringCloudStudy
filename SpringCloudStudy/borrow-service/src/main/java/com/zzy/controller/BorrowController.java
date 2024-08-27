package com.zzy.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.zzy.entity.BorrowDetail;
import com.zzy.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
public class BorrowController {

    @Resource
    BorrowService service;

    @RequestMapping("/borrow/{uid}")
    BorrowDetail findUserBorrows(@PathVariable("uid") int uid){
        BorrowDetail borrowDetail =service.getUserBorrowDetailByUid(uid);
        return borrowDetail;
    }

    @RequestMapping("/borrow2/{uid}")
    BorrowDetail findUserBorrows2(@PathVariable("uid") int uid){
        BorrowDetail borrowDetail =service.getUserBorrowDetailByUid(uid);
        return borrowDetail;
    }

    @RequestMapping("/blocked")
    JSONObject Blocked(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",403);
        jsonObject.put("success", false);
        jsonObject.put("msg","您的请求频率过快，请稍后再试");
        return jsonObject;
    }

    @RequestMapping("/test")
    @SentinelResource(value = "test",
            fallback = "except",    //fallback指定出现异常时的替代方案
            blockHandler = "blockMethod",
            exceptionsToIgnore = IOException.class)  //忽略那些异常，也就是说这些异常出现时不使用替代方案
    String test(){
        return"HelloWorld！";
    }

    //替代方法必须和原方法返回值和参数一致，最后可以添加一个Throwable作为参数接受异常
    String except(Throwable t){
        return t.getMessage();
    }

    String blockMethod(BlockException e) {
        return "blocked";
    }

    @RequestMapping("/testParam")
    @SentinelResource("testParam")   //注意这里需要添加@SentinelResource才可以，用户资源名称就使用这里定义的资源名称
    String findUserBorrows2(@RequestParam(value = "a", required = false) String a,
                            @RequestParam(value = "b", required = false) String b,
                            @RequestParam(value = "c",required = false) String c) {
        return "请求成功！a = "+a+", b = "+b+", c = "+c;
    }
}
