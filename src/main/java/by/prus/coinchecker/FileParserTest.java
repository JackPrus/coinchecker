package by.prus.coinchecker;

import by.prus.coinchecker.entity.OkxResponse;
import by.prus.coinchecker.entity.OkxSwapData;
import by.prus.coinchecker.service.requestservice.RequestServiceRestTemplate;
import org.apache.catalina.connector.InputBuffer;

import java.io.*;
import java.util.*;

public class FileParserTest {
    public static void main(String[] args) {

        try(BufferedWriter br = new BufferedWriter(new FileWriter("D:\\IT\\!!Spring\\coinchecker\\src\\main\\java\\by\\prus\\coinchecker\\enumms.txt"));) {
            StringBuilder sb = new StringBuilder();
            RequestServiceRestTemplate<OkxResponse> requestOKX = new RequestServiceRestTemplate<>();
            OkxResponse okxResponse = requestOKX.doRequest("https://okx.com/api/v5/market/tickers?instType=SPOT", null,OkxResponse.class);
            List<OkxSwapData> data = okxResponse.getData();
            HashSet<String>currencySet = new HashSet<>();
            for (OkxSwapData d : data){
                String [] arr = d.getInstId().split("-");
                currencySet.add(arr[0]);
                currencySet.add(arr[1]);
            }

            List<String> sortedList = new ArrayList<>(currencySet);
            Collections.sort(sortedList);

            for(String l : sortedList){
                //USDT("USDT"),
                String line = l+"(\""+l+"\"),\n";
                sb.append(line);
            }
            br.write(sb.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
