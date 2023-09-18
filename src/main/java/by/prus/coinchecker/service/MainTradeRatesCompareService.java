package by.prus.coinchecker.service;

import by.prus.coinchecker.entity.ByBitResponse;
import by.prus.coinchecker.entity.MultiResultCouple;
import by.prus.coinchecker.entity.OkxResponse;
import by.prus.coinchecker.entity.TradeCoupple;
import by.prus.coinchecker.service.requestservice.RequestServiceRestTemplate;
import by.prus.coinchecker.service.requestservice.RequestServiceWebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainTradeRatesCompareService {

    static String OKX_TICKERS_SPOT = "https://okx.com/api/v5/market/tickers?instType=SPOT";
    static String BYBIT_TICKERS_SPOT = "https://api.bybit.com/v5/market/tickers?category=spot";
    static final double MARKET_EXCHANGE_RATE = 0.003;

    TradePairCreationService tradePairCreationService = new TradePairCreationService();

    //rest template
    RequestServiceRestTemplate<OkxResponse> okxRequest = new RequestServiceRestTemplate<>();
    RequestServiceRestTemplate<ByBitResponse> byBitRequest = new RequestServiceRestTemplate<>();
    //web client
    RequestServiceWebClient<OkxResponse> okxRequestWCL = new RequestServiceWebClient<>();
    RequestServiceWebClient<ByBitResponse> bybitRequestWCL = new RequestServiceWebClient<>();

    public List<String> runTraidPlutformsCheck() {
        Set<TradeCoupple> okxCouples = tradePairCreationService.getOkxTradeCouples(okxRequest.doRequest(OKX_TICKERS_SPOT, null, OkxResponse.class));
        Set<TradeCoupple> byBitCouples = tradePairCreationService.getByBitTradeCouples(byBitRequest.doRequest(BYBIT_TICKERS_SPOT, null, ByBitResponse.class));

        List<String> resultList = new ArrayList<>();
        String template = "%-14s | %-10s | %-11s | %-7s";
        String header = String.format(template, "Curr1/Curr2", "OKX rate", "ByBit rate", ">5%");
        resultList.add(header);

        for (TradeCoupple oTc : okxCouples) {
            for (TradeCoupple bTc : byBitCouples) {
                if (oTc.getCurr1().equals(bTc.getCurr1()) && oTc.getCurr2().equals(bTc.getCurr2())) {
                    boolean is5persent = isDeviationGreaterThanPercentage(bTc.getPrice(), oTc.getPrice(), 10);
                    String currency = oTc.getCurr1() + "/" + oTc.getCurr2();
                    String result = String.format(template, currency, oTc.getPrice(), bTc.getPrice(), is5persent);
                    resultList.add(result);
                }
            }
        }
        return resultList;
    }

    public List<MultiResultCouple> getMultiResultCouple() {
        Set<TradeCoupple> okxCouples = tradePairCreationService.getOkxTradeCouples(okxRequest.doRequest(OKX_TICKERS_SPOT, null, OkxResponse.class));
        Set<TradeCoupple> byBitCouples = tradePairCreationService.getByBitTradeCouples(byBitRequest.doRequest(BYBIT_TICKERS_SPOT, null, ByBitResponse.class));

        List<MultiResultCouple> resultList = new ArrayList<>();

        for (TradeCoupple oTc : okxCouples) {
            for (TradeCoupple bTc : byBitCouples) {
                if (oTc.getCurr1().equals(bTc.getCurr1()) && oTc.getCurr2().equals(bTc.getCurr2())) {

                    double okxPrice = oTc.getPrice();
                    double bybitPrice = bTc.getPrice();

                    double buyPrice = okxPrice;
                    double sellPrice = bybitPrice;
                    boolean okxFirst = true;

                    if (okxPrice > bybitPrice) {
                        buyPrice = bybitPrice;
                        sellPrice = okxPrice;
                        okxFirst = false;
                    }

                    if(buyPrice<0.0001) continue;

                    double buy = (1000d / buyPrice) * (1d - MARKET_EXCHANGE_RATE);
                    double sell = (buy * sellPrice) * (1d - MARKET_EXCHANGE_RATE);
                    double sell1000 = 1000d - sell < 0 ? 0 : 1000d - sell;

                    MultiResultCouple mrc = MultiResultCouple.builder()
                            .curr1(oTc.getCurr1())
                            .curr2(oTc.getCurr2())
                            .oxkRate(oTc.getPrice())
                            .bybitRate(bTc.getPrice())
                            .round1000revenue(sell1000)
                            .isOkxFirst(okxFirst)
                            .build();
                    resultList.add(mrc);
                }
            }
        }
        return resultList;
    }

    private boolean isDeviationGreaterThanPercentage(double number1, double number2, double percentage) {
        double deviation = Math.abs(number1 - number2);
        double percentageDeviation = (deviation / Math.max(number1, number2)) * 100.0;
        return percentageDeviation > percentage;
    }
}
