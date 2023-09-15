package by.prus.coinchecker;

import by.prus.coinchecker.entity.ByBitResponse;
import by.prus.coinchecker.entity.OkxResponse;
import by.prus.coinchecker.service.requestservice.RequestServiceRestTemplate;
import by.prus.coinchecker.service.requestservice.RequestServiceWebClient;

import java.util.Arrays;

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

        String[] ss = new String[2];
        ss[0]="4";
        ss[1]="sadf";

        String[] ff = new String[2];
        ff[0]="4";
        ff[1]="sadf";

        System.out.println(Arrays.equals(ss,ff));

//        RequestServiceRestTemplate<OkxResponse> requestOKX = new RequestServiceRestTemplate<>();
//        String resultOKX = requestOKX.doJsonRequest(OKX_TICKERS_SPOT, null);
//
//        RequestServiceWebClient<ByBitResponse> request = new RequestServiceWebClient<>();
//        String resultByBit = request.doJsonRequest(BYBIT_TICKERS_SPOT);

        System.out.println("done");


//        for(OkxSwapData sd : dataList){
//            if (sd.getInstId().contains("BTC-USDT")){
//                System.out.println(sd.getInstId());
//                System.out.println(sd.getLast());
//                double price = Double.parseDouble(sd.getLast());
//                double birgerateBuy = price*(1+ okxRate);
//                double birgerateSell = price*(1- okxRate);
//                System.out.println("Биржа покупает за "+birgerateBuy);
//                System.out.println("Биржа продаёт за " +birgerateSell);
//            }
//        }
//
//        System.out.println("________________");
//
//        OkxResponse spot = getTickersResult(OKX_TICKERS_SPOT);
//        List<OkxSwapData> spotList = spot.getData();
//        for(OkxSwapData sd : spotList){
//            if (sd.getInstId().contains("BTC-USDT")){
//                System.out.println(sd.getInstId());
//                System.out.println(sd.getLast());
//            }
//        }
    }

}
