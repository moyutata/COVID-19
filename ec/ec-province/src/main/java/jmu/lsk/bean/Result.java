package jmu.lsk.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Object data;
    private Integer code;
    private String message;

    public static Result success(Object data){
        Result result = new Result();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    public static Result fail(Object data){
        Result result = new Result();
        result.setCode(500);
        result.setMessage("fail");
        result.setData(data);
        return result;
    }
}