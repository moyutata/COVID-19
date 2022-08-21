package jmu.lsk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProCity {
    private String locationId;
    private String location;
    private int currentConfirmedCount;
    private int confirmedCount;
    private int curedCount;
    private int deadCount;
    private int pId;
}
