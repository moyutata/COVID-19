package jmu.lsk.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface EcTrendMapper {
    @Select("select dateId,confirmedIncr as 新增确诊,confirmedCount as 累计确诊,suspectedCount as 疑似病例,curedCount as 累计治愈,deadCount as 累计死亡 from countryTrend " +
            "order by id desc limit 7")
    List<Map<String, Object>> getNationTimeData();
}
