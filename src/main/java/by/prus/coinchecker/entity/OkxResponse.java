package by.prus.coinchecker.entity;

import lombok.Data;

import java.util.List;

@Data
public class OkxResponse {
    private String code;
    private String msg;
    private List<OkxSwapData> data;
}

