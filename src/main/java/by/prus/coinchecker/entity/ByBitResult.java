package by.prus.coinchecker.entity;

import lombok.Data;

import java.util.List;

@Data
public class ByBitResult {
    private String category;
    private String time;
    private List<ByBitSwapData> list;
}
