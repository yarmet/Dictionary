package com.components.requestModels;


import lombok.Data;
import java.util.List;

/**
 * Created by ruslan on 23.11.16.
 */

@Data
public class ResponseData {

    public static ResponseData OK = new ResponseData("ok");
    public static ResponseData ERROR = new ResponseData("error");

    private String status;
    private List<String> description;

    private ResponseData(String status) {
        this.status = status;
    }

}
