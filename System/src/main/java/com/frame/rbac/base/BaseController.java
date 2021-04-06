package com.frame.rbac.base;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.response.ResponseMessage;

import java.util.List;

public class BaseController<S extends ServiceImpl,T extends Object> {
    @Autowired
    private S service;

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody List<T> data){
        service.saveOrUpdateBatch(data);
        return ResponseMessage.success();
    }

    @PostMapping("/delete")
    public ResponseMessage deleteByIds(@RequestBody List<Integer> ids){
        service.removeByIds(ids);
        return ResponseMessage.success();
    }

    @PostMapping("/list")
    public ResponseMessage listById(){
        return ResponseMessage.success(service.list());
    }
}
