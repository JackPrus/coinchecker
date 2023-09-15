package by.prus.coinchecker.service.webparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebParserSimple implements WebParser {
    @Override
    public String parse(String webPath) {
        try {
            Document doc = Jsoup.connect(webPath).get();
            return doc.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
