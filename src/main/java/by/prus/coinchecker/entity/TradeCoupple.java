package by.prus.coinchecker.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeCoupple {
    private String curr1;
    private String curr2;
    private double price; //curr2 per one curr1
}
