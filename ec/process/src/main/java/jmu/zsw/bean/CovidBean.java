package jmu.zsw.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CovidBean {
    private String provinceName;            //省份名称
    private String provinceShortName;       //省份短名
    private String cityName;
    private Integer currentConfirmedCount;   //当前确诊人数
    private Integer confirmedCount;          //累计确诊人数
    private Integer suspectedCount;          //疑似病例人数
    private Integer curedCount;              //治愈人数
    private Integer deadCount;               //死亡人数
    private String comment;                 //简报
    private Integer locationId;              //位置id
    private Integer pid;
    private String statisticsData;          //每一天的统计数据
    private String cities;                  //下属城市
    private String datetime;
    private Integer currentConfirmedIncr;    //当前确诊增长
    private Integer confirmedIncr;           //累计确诊增长
    private Integer curedIncr;               //治愈增长
    private Integer deadIncr;                //死亡增长

}
