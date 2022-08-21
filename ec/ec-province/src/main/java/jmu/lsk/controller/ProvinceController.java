package jmu.lsk.controller;


import jmu.lsk.bean.ProCity;
import jmu.lsk.bean.Result;

import jmu.lsk.service.EcProvinceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProvinceController {
    @Autowired
    private EcProvinceService ecProvinceService;

    @RequestMapping("getProvinceData")
    public Result getProvinceData(){
        List<Map<String, Object>>data =  ecProvinceService.getProvinceData();
        return Result.success(data);
    }
    @RequestMapping("getAllProvinceData")
    public List<ProCity> getAllProvinceData(){
        List<ProCity>data =  ecProvinceService.getAllProvinceData();
        return data;
    }
}
