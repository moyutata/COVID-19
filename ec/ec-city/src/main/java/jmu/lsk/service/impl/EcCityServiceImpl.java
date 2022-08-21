package jmu.lsk.service.impl;
import jmu.lsk.bean.City;
import jmu.lsk.bean.ProCity;
import jmu.lsk.mapper.EcCityMapper;
import jmu.lsk.service.EcCityService;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("EcCityService")
public class EcCityServiceImpl implements EcCityService {
    @Autowired
    private EcCityMapper ecCityMapper;
    @Override
    public List<City> searchCityData(String cityName,String num) {
        List<City> data = ecCityMapper.searchCityData(cityName,num);
        return data;
    }

    @Override
    public List<ProCity> getAllCityData() {
        String datetime = FastDateFormat.getInstance("yyyy-MM-dd").format(System.currentTimeMillis());
        List<ProCity>  data= ecCityMapper.getAllCityData(datetime);
        return data;
    }

}
