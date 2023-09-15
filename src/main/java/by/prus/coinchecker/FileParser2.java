package by.prus.coinchecker;

import by.prus.coinchecker.entity.ByBitResponse;
import by.prus.coinchecker.entity.ByBitSwapData;
import by.prus.coinchecker.service.requestservice.RequestServiceWebClient;
import by.prus.coinchecker.utils.CurC;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileParser2 {
    public static void main(String[] args) {

        try(BufferedWriter br = new BufferedWriter(new FileWriter("D:\\IT\\!!Spring\\coinchecker\\src\\main\\java\\by\\prus\\coinchecker\\enumms2.txt"));) {
            StringBuilder sb = new StringBuilder();
            RequestServiceWebClient<ByBitResponse> request = new RequestServiceWebClient<>();
            ByBitResponse byBitResponse = request.doRequest("https://api-testnet.bybit.com/v5/market/tickers?category=spot",ByBitResponse.class);
            List<ByBitSwapData> swapData = byBitResponse.getResult().getList();

            HashSet<String>pairs = new HashSet<>();

            List<CurC> curCList = new ArrayList<>(Arrays.asList(CurC.values()));
            for (int i=0; i<curCList.size()-1; i++){
                for(int j=i+1; j<curCList.size(); j++){
                    String i1 = curCList.get(i).getName();
                    String j2 = curCList.get(j).getName();
                    pairs.add(i1+j2);
                    pairs.add(j2+i1);
                }
            }

            Iterator<ByBitSwapData> iterator = swapData.iterator();
            while (iterator.hasNext()) {
                ByBitSwapData bit = iterator.next();
                for(String pair : pairs){
                    if(pair.equals(bit.getSymbol())){
                        iterator.remove();
                    }
                }
            }

            List<String> resultList = new ArrayList<>();
            swapData.forEach(f-> resultList.add(f.getSymbol()));
            Collections.sort(resultList);
            resultList.forEach(s -> sb.append(s).append("\n"));

            br.write(sb.toString());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
