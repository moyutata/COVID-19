package jmu.lsk.service.impl;

import jmu.lsk.bean.ProCity;
import jmu.lsk.mapper.EcProvinceMapper;
import jmu.lsk.service.EcProvinceService;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("EcProvinceService")
public class EcProvinceServiceImpl implements EcProvinceService {
    @Autowired
    private EcProvinceMapper ecProvinceMapper;
    @Override
    public List<Map<String, Object>> getProvinceData() {
        String datetime = FastDateFormat.getInstance("yyyy-MM-dd").format(System.currentTimeMillis());
        List<Map<String, Object>> data=ecProvinceMapper.getProvinceData(datetime);
        return data;
    }
    @Override
    public List<ProCity> getAllProvinceData() {
        String datetime = FastDateFormat.getInstance("yyyy-MM-dd").format(System.currentTimeMillis());
        List<ProCity>  data=ecProvinceMapper.getAllProvinceData(datetime);
        return data;
    }
}
