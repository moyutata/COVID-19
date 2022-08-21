package jmu.lsk.service.Impl;

import jmu.lsk.bean.Nation;
import jmu.lsk.mapper.EcNationMapper;
import jmu.lsk.service.EcNationService;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("EcNationServiceImpl")
public class EcNationServiceImpl implements EcNationService {
    @Autowired
    private EcNationMapper ecNationMapper;
    @Override
    public Nation getNationalData() {
        String datetime = FastDateFormat.getInstance("yyyy-MM-dd").format(System.currentTimeMillis());
        Nation data =ecNationMapper.getNationalData(datetime);
        return data;
    }
}
