package attendance.domain;

import java.util.Arrays;

public enum Penalty {

    KICK(6, "제적"),
    TALK(3, "면담"),
    WARNING(2, "경고"),
    NON(0, "");

    private final int rate;
    private final String name;

    Penalty(int rate, String name) {
        this.rate = rate;
        this.name = name;
    }

    public static Penalty from(int late_count, int absence_count) {
        int total = (late_count / 3) + absence_count;
        return Arrays.stream(values())
                .filter(p -> p.rate <= total)
                .findFirst().orElse(NON);
    }

    public String getName() {
        return name;
    }
}
