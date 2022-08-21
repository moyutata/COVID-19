package jmu.lsk.service.impl;

import jmu.lsk.bean.foreign;
import jmu.lsk.mapper.EcForeignDataMapper;
import jmu.lsk.service.EcForeignService;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("EcForeignService")
public class EcForeignServiceImpl implements EcForeignService {
    @Autowired
    private EcForeignDataMapper ecForeignDataMapper;
    @Override
    public List<foreign> getForeignData() {
        String datetime = FastDateFormat.getInstance("yyyy-MM-dd").format(System.currentTimeMillis());
        List<foreign> data=ecForeignDataMapper.getForeignData(datetime);
        return data;
    }
}
