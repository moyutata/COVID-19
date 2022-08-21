package jmu.lsk.controller;


import jmu.lsk.bean.Result;
import jmu.lsk.service.EcTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class TrendController {
    @Autowired
    private EcTrendService ecTrendService;

    @RequestMapping("getNationTimeData")
    public Result getNationTimeData(){
        List<Map<String, Object>> data =  ecTrendService.getNationTimeData();
        return Result.success(data);
    }
}
