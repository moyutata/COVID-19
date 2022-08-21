package jmu.lsk.service;

import jmu.lsk.bean.ProCity;

import java.util.List;
import java.util.Map;

public interface EcProvinceService {
    List<Map<String, Object>> getProvinceData();
    List<ProCity> getAllProvinceData();
}
