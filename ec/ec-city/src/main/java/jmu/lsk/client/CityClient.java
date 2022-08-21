package jmu.lsk.client;

import jmu.lsk.bean.ProCity;
import jmu.lsk.breaker.CityBreak;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "ECPROVINCE" ,fallback = CityBreak.class)
public interface CityClient {
    @RequestMapping("getAllProvinceData")
     List<ProCity> getAllProvinceData();
}
