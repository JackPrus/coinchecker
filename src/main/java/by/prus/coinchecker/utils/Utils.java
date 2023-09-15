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

    public static String[] parseByBitPair(String symbol) {
        String[] result = new String[2];

        for (int i = 0; i < symbol.length(); i++) {
            String firstCurrency = symbol.substring(0, i + 1);
            String remaining = symbol.substring(i + 1);

            if (containsCur(firstCurrency) && containsCur(remaining)) {
                result[0]=firstCurrency;
                result[1]=remaining;
                return result;
            }else if(i == symbol.length()-1){
                writeProcess(symbol); //записываем в файл
            }
        }
        return result; // Возвращаем пустой список, если не удалось разделить входную строку на пару криптовалют
    }

    public static List<String> parseOkxPair(String instId) {
        List<String> result = new ArrayList<>();
        String[]pair = instId.split("-");
        if(pair.length!=2){
            writeProcess(instId);
            return result;
        }

        result.add(pair[0]);
        result.add(pair[1]);

        return result; // Возвращаем пустой список, если не удалось разделить входную строку на пару криптовалют
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
