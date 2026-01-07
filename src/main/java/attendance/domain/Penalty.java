package attendance.domain;

import java.util.Arrays;

public enum Penalty {

    KICK(6),
    TALK(3),
    WARNING(2),
    NON(0);

    private final int rate;

    Penalty(int rate) {
        this.rate = rate;
    }

    public static Penalty from(int late_count, int absence_count){
        int total = (late_count / 3) + absence_count;
        return Arrays.stream(values())
                .filter(p -> p.rate <= total)
                .findFirst().orElse(NON);
    }
}
