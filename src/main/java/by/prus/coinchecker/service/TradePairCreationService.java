package by.prus.coinchecker.service;

import by.prus.coinchecker.entity.*;
import by.prus.coinchecker.utils.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TradePairCreationService {
    public Set<TradeCoupple> getOkxTradeCouples(OkxResponse okxResponse){
        Set<TradeCoupple> tradeCoupples = new HashSet<>();
        for (OkxSwapData osd : okxResponse.getData()){
            List<String>pairName = Utils.parseOkxPair(osd.getInstId());
            if(pairName.isEmpty()) continue;
            String priceStr = osd.getLast().replace(",", ".");
            if(3>Double.parseDouble(osd.getVolCcy24h())) {
                priceStr = osd.getOpen24h();
                if (priceStr==null) continue;
                priceStr = priceStr.replace(",", ".");
            };
            //округление до 4 знаков
            BigDecimal price = new BigDecimal(priceStr);
            double roundedPrice = price.setScale(8, RoundingMode.HALF_UP).doubleValue();

            TradeCoupple tc = TradeCoupple.builder()
                    .curr1(pairName.get(0))
                    .curr2(pairName.get(1))
                    .price(roundedPrice)
                    .build();

            tradeCoupples.add(tc);
        }
        return tradeCoupples;
    }

    public Set<TradeCoupple> getByBitTradeCouples(ByBitResponse byBitResponse){
        Set<TradeCoupple> tradeCoupples = new HashSet<>();
        for (ByBitSwapData bsd : byBitResponse.getResult().getList()){
            List<String>pairName = Utils.parseByBitPair(bsd.getSymbol());
            if(pairName.isEmpty()) continue;
            String priceStr = bsd.getLastPrice().replace(",", ".");
            if(3>Double.parseDouble(bsd.getVolume24h())) {
                priceStr = bsd.getUsdIndexPrice();
                if (priceStr==null) continue;
                priceStr = priceStr.replace(",", ".");
            };

            //округление до 2 знаков

            BigDecimal price = new BigDecimal(priceStr);
            double roundedPrice = price.setScale(8, RoundingMode.HALF_UP).doubleValue();

            TradeCoupple tc = TradeCoupple.builder()
                    .curr1(pairName.get(0))
                    .curr2(pairName.get(1))
                    .price(roundedPrice)
                    .build();
            tradeCoupples.add(tc);
        }
        return tradeCoupples;
    }
}
