package by.prus.coinchecker.service;

public interface WebParser {

    /**
     * @param webPath for example https://www.juventus.com/en/tickets/standard/first-team-men/
     * @return received html page
     */
    public String parse(String webPath);
}
