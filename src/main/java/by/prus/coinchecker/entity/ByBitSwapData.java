package by.prus.coinchecker.entity;

import lombok.Data;

@Data
public class ByBitSwapData {
    private String symbol;
    private String bid1Price;
    private String bid1Size;
    private String ask1Price;
    private String ask1Size;
    private String lastPrice;
    private String prevPrice24h;
    private String price24hPcnt;
    private String highPrice24h;
    private String lowPrice24h;
    private String turnover24h;
    private String volume24h;
    private String usdIndexPrice;
}
