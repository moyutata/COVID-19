package jmu.lsk.breaker;

import jmu.lsk.bean.ProCity;
import jmu.lsk.client.CityClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityBreak implements CityClient {

    @Override
    public List<ProCity> getAllProvinceData() {
        return null;
    }
}
