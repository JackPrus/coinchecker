package by.prus.coinchecker;

import by.prus.coinchecker.service.MainTradeRatesCompareService;

import java.util.List;

public class MainTest {

    static String INDEX_COMPONENTS = "https://okx.com/api/v5/market/index-components";
    static String EXHANGE_RATE = "https://okx.com/api/v5/market/exchange-rate";
    static String OKX_TICKERS_SWAP = "https://okx.com/api/v5/market/tickers?instType=SWAP"; //есть не только swap но и спот торговля
    static String OKX_TICKERS_SPOT = "https://okx.com/api/v5/market/tickers?instType=SPOT";
    static String BYBIT_TICKERS_SPOT = "https://api-testnet.bybit.com/v5/market/tickers?category=spot";
    static String BYBIT_TICKERS_INWERSE = "https://api-testnet.bybit.com/v5/market/tickers?category=spot";


    static double okxRate = 0.002; //Это ставка биржи. (1+0.00185)* цену валюты = сумма по которой будешь покупать
    static double bybitRate = 0.002; //Это ставка биржи. (1+0.00185)* цену валюты = сумма по которой будешь покупать

    public static void main(String[] args) {
        MainTradeRatesCompareService runer = new MainTradeRatesCompareService();
        List<String> resultList = runer.runTraidPlutformsCheck();
        resultList.forEach(System.out::println);
    }

}
