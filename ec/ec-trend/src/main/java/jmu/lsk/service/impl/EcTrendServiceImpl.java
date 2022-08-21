package jmu.lsk.service.impl;

import jmu.lsk.mapper.EcTrendMapper;
import jmu.lsk.service.EcTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service("EcTrendService")
public class EcTrendServiceImpl implements EcTrendService {
    @Autowired
    private EcTrendMapper ecTrendMapper;
    @Override
    public List<Map<String, Object>> getNationTimeData() {
        List<Map<String, Object>>  data= ecTrendMapper.getNationTimeData();
        return data;
    }
}
