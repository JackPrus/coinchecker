package by.prus.coinchecker.entity;

import lombok.Data;

@Data
public class OkxSwapData {
    private String instType; // Instrument type                                | SWAP
    private String instId;   // Instrument ID                                  | BTC-USDT-SWAP
    private String last;     // Last traded price                              | 26342.5
    private String lastSz;   // Last traded size                               | 2
    private String askPx;    // Best ask price                                 | 26343
    private String askSz;    // Best ask size                                  | 1
    private String bidPx;    // Best bid price                                 | 26342.9
    private String bidSz;    // Best bid size                                  | 582
    private String open24h;  // Open price in the past 24 hours                | 25174.9
    private String high24h;  // Highest price in the past 24 hours             | 26600
    private String low24h;   // Lowest price in the past 24 hours              | 24881
    private String volCcy24h;// 24h trading volume, with a unit of currency.   | 232449.98
    private String vol24h;   // 24h trading volume, with a unit of contract.   | 23244998
    private String ts;       // Ticker data generation time, Unix timestamp format in milliseconds, e.g. 1597026383085  | 1694536677505
    private String sodUtc0;  // Open price in the UTC 0                        | 25166.2
    private String sodUtc8;  // Open price in the UTC 8                        | 26207.9

    /**
     * EXAMPLE
     * {
     *       "instType": "SWAP",
     *       "instId": "BTC-USDT-SWAP",
     *       "last": "26342.5",
     *       "lastSz": "2",
     *       "askPx": "26343",
     *       "askSz": "1",
     *       "bidPx": "26342.9",
     *       "bidSz": "582",
     *       "open24h": "25174.9",
     *       "high24h": "26600",
     *       "low24h": "24881",
     *       "volCcy24h": "232449.98",
     *       "vol24h": "23244998",
     *       "ts": "1694536677505",
     *       "sodUtc0": "25166.2",
     *       "sodUtc8": "26207.9"
     *     }
     */
}
