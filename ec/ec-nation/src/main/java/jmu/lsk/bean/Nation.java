package jmu.lsk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Nation {
    private int currentConfirmedCount;
    private int currentConfirmedIncr;
    private int confirmedCount;
    private int confirmedIncr;
    private int curedCount;
    private int curedIncr;
    private int deadCount;
    private int deadIncr;
    private String datetime;
}
