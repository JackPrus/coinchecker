package by.prus.coinchecker.service.webparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class WebParserStrong implements WebParser {

    private EdgeOptions options;
    private WebDriver driver;

    {
        options = new EdgeOptions();
        //options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new EdgeDriver(options);
    }

    @Override
    public String parse(String webPath) {
        driver.get(webPath);
        Document doc = Jsoup.parse(driver.getPageSource());
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        String result = doc.text();
        driver.quit();
        return result;
    }

    private String parseSiteData(Document doc) {
        //some parsing
        return doc.text();
    }
}
