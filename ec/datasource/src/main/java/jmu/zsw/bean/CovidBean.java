package jmu.zsw.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用来封装各个省市疫情数据的JavaBean
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CovidBean {
    private String provinceName;            //省份
    private String provinceShortName;       //省份简称
    private String cityName;                //城市名
    private Integer currentConfirmedCount;   //当前确诊
    private Integer confirmedCount;          //累计确诊
    private Integer suspectedCount;          //疑似病例
    private Integer suspectedCountIncr;
    private Integer curedCount;              //治愈人数
    private Integer deadCount;               //死亡人数
    private String comment;                 //简报
    private Integer locationId;              //位置id
    private Integer pid;                     //城市id
    private String statisticsData;          //每天数据
    private String cities;                  //下属城市
    private String datetime;                //爬取数据的日期
    private Integer provinceId;

}
