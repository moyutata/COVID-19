package jmu.lsk.mapper;

import jmu.lsk.bean.foreign;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface EcForeignDataMapper {
    @Select("select provinceShortName as `name`,confirmedCount as `value` from abroad where datetime = #{datetime} order by `value` desc")
    List<foreign> getForeignData(String datetime);
}
