package by.prus.coinchecker;

import by.prus.coinchecker.entity.MultiResultCouple;
import by.prus.coinchecker.service.MainTradeRatesCompareService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainTest {

    static String INDEX_COMPONENTS = "https://okx.com/api/v5/market/index-components";
    static String EXHANGE_RATE = "https://okx.com/api/v5/market/exchange-rate";
    static String OKX_TICKERS_SWAP = "https://okx.com/api/v5/market/tickers?instType=SWAP"; //есть не только swap но и спот торговля
    static String OKX_TICKERS_SPOT = "https://okx.com/api/v5/market/tickers?instType=SPOT";
    static String BYBIT_TICKERS_SPOT = "https://api.bybit.com/v5/market/tickers?category=spot";
    static String BYBIT_TICKERS_INWERSE = "https://api-testnet.bybit.com/v5/market/tickers?category=spot";


    static double okxRate = 0.002; //Это ставка биржи. (1+0.00185)* цену валюты = сумма по которой будешь покупать
    static double bybitRate = 0.002; //Это ставка биржи. (1+0.00185)* цену валюты = сумма по которой будешь покупать


    static double comission = 0.1; //0.1% первой валюты из пары при покупке/продаже на bybit/okx
    static double transferFeee = 2; //2 USDT

    public static void main(String[] args) throws InterruptedException {

//        for (int i=0; i<100; i++){
//            explore();
//            Thread.sleep(1000);
//        }

        double money = 1000d;
        for(int i=0; i<1000; i++){
            money = runTradeBot(money);
        }
    }

    public static void explore(){
        MainTradeRatesCompareService runer = new MainTradeRatesCompareService();
        List<MultiResultCouple> resultCouples = runer.getMultiResultCouple();
        Collections.sort(resultCouples);

        List<String> stringList = new ArrayList<>();
        String template = "%-8s |%-8s | %-10s | %-11s | %-7s";
        String header = String.format(template, "Curr1","Curr2","OKX rate", "ByBit rate","revenue");
        stringList.add(header);
        for(MultiResultCouple mrc : resultCouples){
            String line = String.format(template, mrc.getCurr1(), mrc.getCurr2(), mrc.getOxkRate(), mrc.getBybitRate(), mrc.getRound1000revenue());
            stringList.add(line);
        }

        System.out.println(new Date());
        for(int i=0; i<10; i++){
            System.out.println(stringList.get(i));
        }
    }


    public static double runTradeBot(double money) throws InterruptedException {
        MainTradeRatesCompareService runer = new MainTradeRatesCompareService();
        List<MultiResultCouple> resultCouples = runer.getMultiResultCouple();
        Collections.sort(resultCouples);

        MultiResultCouple buyCouple = resultCouples.stream()
                .filter(mrc -> mrc.getOxkRate() > 0.001 && mrc.getRound1000revenue() > 6)
                .findFirst()
                .orElse(null);
        //пробуем заново если не нашли комбинацию
        if (buyCouple==null){
            Thread.sleep(2500);
            return money;
        }

        double rateToBuy = buyCouple.isOkxFirst() ? buyCouple.getOxkRate() : buyCouple.getBybitRate();
        double newlyBuyedCurrency = money/rateToBuy;

        //2.5 minutes while transer of money
        Thread.sleep(1000);

        List<MultiResultCouple> newReslutCouple = runer.getMultiResultCouple();
        MultiResultCouple sellCouple = newReslutCouple.stream()
                .filter(mrc -> mrc.getCurr1().equals(buyCouple.getCurr1())&&mrc.getCurr2().equals(buyCouple.getCurr2()))
                .findFirst()
                .orElse(null);

        double rateToSell = buyCouple.isOkxFirst() ? sellCouple.getBybitRate() : sellCouple.getOxkRate();
        double resultMoney = newlyBuyedCurrency*rateToSell - 2d; //2 USDT for transerf


        String template = "%-8s |%-8s | %-10s | %-11s | %-10s | %-10s";
        String header = String.format(template, "Curr1","Curr2","BuyRate", "SellRate","Revenue","Result");
        String line = String.format(template, sellCouple.getCurr1(), sellCouple.getCurr2(), rateToBuy, rateToSell, resultMoney-money, resultMoney);
        System.out.println(new Date());
        System.out.println(header);
        System.out.println(line);
        System.out.println("______________________________________________-");
        return resultMoney;
    }

}
