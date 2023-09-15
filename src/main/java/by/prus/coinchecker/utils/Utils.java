package by.prus.coinchecker.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<CurC> curCList = new ArrayList<>(Arrays.asList(CurC.values()));

    public static List<String> parseByBitPair(String symbol) {
        List<String> result = new ArrayList<>(2);

        for (int i = 0; i < symbol.length(); i++) {
            String firstCurrency = symbol.substring(0, i + 1);
            String remaining = symbol.substring(i + 1);

            if (containsCur(firstCurrency) && containsCur(remaining)) {
                result.add(firstCurrency);
                result.add(remaining);
                return result;
            }else if(i == symbol.length()-1){
                writeProcess(symbol); //записываем в файл
            }
        }
        return result; // Возвращаем пустой массив, если не удалось разделить входную строку на пару криптовалют
    }

    public static List<String> parseOkxPair(String instId) {
        List<String> result = new ArrayList<>(2);
        String[]splited = instId.split("-");
        if(splited.length!=2){
            writeProcess(instId);
            return result; // Возвращаем пустой массив, если не удалось разделить входную строку на пару криптовалют
        }
        result.add(splited[0]);
        result.add(splited[1]);
        return result;
    }

    public static boolean containsCur(String curName){
        return curCList.stream().anyMatch(c -> c.getName().equals(curName));
    }

    public static void writeProcess(String line){
        URL resource = Utils.class.getClassLoader().getResource("newcurpairs.txt");
        try(BufferedWriter br = new BufferedWriter(new FileWriter(resource.getPath()))) {
            if(line!=null&&!line.isBlank()) br.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
