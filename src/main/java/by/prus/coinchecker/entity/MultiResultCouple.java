package by.prus.coinchecker.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MultiResultCouple implements Comparable<MultiResultCouple> {
    private String curr1;
    private String curr2;
    private double oxkRate;
    private double bybitRate;
    private double round1000revenue;
    private boolean isOkxFirst;

    @Override
    public int compareTo(MultiResultCouple o) {
        if (o.getRound1000revenue()==this.getRound1000revenue()) return 0;
        return o.getRound1000revenue()>this.getRound1000revenue() ? 1 : -1;
    }
}
