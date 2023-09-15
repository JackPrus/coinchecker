package by.prus.coinchecker.service;

import by.prus.coinchecker.entity.ByBitResponse;
import by.prus.coinchecker.entity.OkxResponse;
import by.prus.coinchecker.entity.TradeCoupple;
import by.prus.coinchecker.service.requestservice.RequestServiceRestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainTradeRatesCompareService {

    static String OKX_TICKERS_SPOT = "https://okx.com/api/v5/market/tickers?instType=SPOT";
    static String BYBIT_TICKERS_SPOT = "https://api-testnet.bybit.com/v5/market/tickers?category=spot";

    TradePairCreationService tradePairCreationService = new TradePairCreationService();
    RequestServiceRestTemplate<OkxResponse>okxRequest = new RequestServiceRestTemplate<>();
    RequestServiceRestTemplate<ByBitResponse>byBitRequest = new RequestServiceRestTemplate<>();

    public List<String> runTraidPlutformsCheck(){
        Set<TradeCoupple> okxCouples = tradePairCreationService.getOkxTradeCouples(okxRequest.doRequest(OKX_TICKERS_SPOT, null, OkxResponse.class));
        Set<TradeCoupple> byBitCouples = tradePairCreationService.getByBitTradeCouples(byBitRequest.doRequest(BYBIT_TICKERS_SPOT, null, ByBitResponse.class));

        List<String>resultList = new ArrayList<>();
        String template = "%-14s | %-10s | %-11s | %-7s";
        String header = String.format(template, "Curr1/Curr2","OKX rate", "ByBit rate",">5%");
        resultList.add(header);

        for(TradeCoupple oTc : okxCouples){
            for (TradeCoupple bTc : byBitCouples){
                if(oTc.getCurr1().equals(bTc.getCurr1())&&oTc.getCurr2().equals(bTc.getCurr2())){
                    boolean is5persent = isDeviationGreaterThanPercentage(bTc.getPrice(), oTc.getPrice(), 20);
                    String currency = oTc.getCurr1()+"/"+oTc.getCurr2();
                    String result = String.format(template, currency,oTc.getPrice(),bTc.getPrice(),is5persent);
                    resultList.add(result);
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
