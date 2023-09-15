package by.prus.coinchecker.controller;

import by.prus.coinchecker.service.WebParser;
import by.prus.coinchecker.service.WebParserSimple;
import by.prus.coinchecker.service.WebParserStrong;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {

    @GetMapping("/simpletest")
    public ResponseEntity<String> checkParseSimple() {
        String path1 = "https://ozon.by/product/namotka-dlya-tennisnoy-raketki-2-sht-dlya-turnika-udochki-overgrip-obmotka-na-tennisnuyu-raketku-860917545/?advert=d9JCIInDRbkmA1pzXmq7MNThNxGxOThqkpXZ6aCt_AuT07O0Nk24zQTglsG1XKJkBhhHwxiuA2iBe8ZmXgcV3Hc52QA6jSXQPe8BRsGawFGzkmdMALWhiulZ3mbnvc5uxgv9AE_yEwOdCb2-8YIAID9BVf2t8nZJ&avtc=1&avte=2&avts=1692109510&sh=0WxURV-btQ";
        WebParser simpleParser = new WebParserSimple();
        String text = simpleParser.parse(path1);
        return ResponseEntity.ok().body(text);
    }

    @GetMapping("/strongtest")
    public ResponseEntity<String> checkParseStrong() {
        String path1 = "https://www.ozon.by/";
        WebParser strongParser = new WebParserStrong();
        String text = strongParser.parse(path1);
        return ResponseEntity.ok().body(text);
    }
}
