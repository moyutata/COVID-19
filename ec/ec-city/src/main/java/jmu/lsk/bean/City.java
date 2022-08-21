package jmu.lsk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
    private String cityName;
    private int currentConfirmedCount;
    private int confirmedCount;
    private int curedCount;
    private int deadCount;
    private int locationId;
    private int suspectedCount;
    private int pId;
    private String provinceShortName;
    private String datetime;
}
