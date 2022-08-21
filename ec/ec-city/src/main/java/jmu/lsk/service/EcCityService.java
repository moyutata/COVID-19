package jmu.lsk.service;

import jmu.lsk.bean.City;
import jmu.lsk.bean.ProCity;

import java.util.List;
public interface EcCityService {
    List<City> searchCityData(String cityName,String num);
    List<ProCity> getAllCityData();
}
