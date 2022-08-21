package jmu.lsk.controller;


import jmu.lsk.bean.Result;
import jmu.lsk.bean.foreign;
import jmu.lsk.service.EcForeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ForeignCountController {
    @Autowired
    private EcForeignService ecForeignService;

    @RequestMapping("getForeignData")
    public Result getCovidImportData(){
        List<foreign> data = ecForeignService.getForeignData();
        return  Result.success(data);
    }
}
