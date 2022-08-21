package jmu.lsk.controller;

import com.alibaba.fastjson.JSON;
import jmu.lsk.bean.City;
import jmu.lsk.bean.ProCity;
import jmu.lsk.bean.Result;
import jmu.lsk.client.CityClient;
import jmu.lsk.service.EcCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CityController {
    @Autowired
    private EcCityService ecCityService;
    @Qualifier("jmu.lsk.client.CityClient")
    @Autowired
    private CityClient cityClient;
    @RequestMapping("getAllCityData")
    public String  getAllCityData() {
        List<ProCity> cities = ecCityService.getAllCityData();
        List<ProCity> provinces=cityClient.getAllProvinceData();
        int count = cities.size()+provinces.size();
        cities.addAll(provinces);
        Result result = Result.success(cities,count);
        return JSON.toJSONString(result);
    }
    @RequestMapping("searchCityData")
    public Result searchCityData(String cityName,String num){
        List<City> data =ecCityService.searchCityData(cityName,num);
        Result result = Result.success(data,0);
        return result;
    }
}
