package jmu.lsk.mapper;

import jmu.lsk.bean.City;
import jmu.lsk.bean.ProCity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EcCityMapper {
    @Select("select locationId,cityName as location,pId,currentConfirmedCount,confirmedCount,curedCount,deadCount from city where datetime=#{datetime} order by currentConfirmedCount desc")
    List<ProCity> getAllCityData(String datetime);
    @Select("select `datetime`,`provinceShortName`,`cityName` , `currentConfirmedCount`, `confirmedCount`  , `curedCount` , `deadCount`  from city where TO_DAYS(NOW()) - TO_DAYS(datetime) <= #{num} and cityName like concat ('%',#{cityName},'%') order by datetime desc ")
    List<City> searchCityData(String cityName,String num);
}
