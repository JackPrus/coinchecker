package by.prus.coinchecker.entity;

import lombok.Data;

@Data
public class TradeCupple {
    private String curr1;
    private String curr2;
    private double price; //curr2 per one curr1
}
