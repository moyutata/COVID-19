package jmu.lsk.mapper;

import jmu.lsk.bean.Nation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface EcNationMapper {
    @Select("select `datetime`, `currentConfirmedCount`,`currentConfirmedIncr` ,`confirmedCount`,`confirmedIncr`, `curedCount`,`curedIncr`,`deadCount` ,`deadIncr` from country where datetime = #{datetime}")
    Nation getNationalData(String datetime);
}
