package jmu.lsk.mapper;


import jmu.lsk.bean.ProCity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface EcProvinceMapper {
    @Select("select provinceShortName as name ,confirmedCount as value from province where datetime = #{datetime}")
    List<Map<String, Object>> getProvinceData(String datetime);
    @Select("select provinceShortName as location, pId,locationId,currentConfirmedCount,confirmedCount,curedCount,deadCount from province where datetime=#{datetime} order by currentConfirmedCount desc")
    List<ProCity> getAllProvinceData(String datetime);
}
