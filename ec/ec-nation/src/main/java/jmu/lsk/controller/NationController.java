package jmu.lsk.controller;

import jmu.lsk.bean.Nation;
import jmu.lsk.bean.Result;
import jmu.lsk.service.EcNationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NationController {
    @Autowired
    private EcNationService ecNationService;

    /**
     * 接收前端请求返回全国疫情汇总数据
     */
    @RequestMapping("getNationalData")
    public Result getNationalData(){
        Nation data = ecNationService.getNationalData();
        Result result = Result.success(data);
        return result;
    }
}
