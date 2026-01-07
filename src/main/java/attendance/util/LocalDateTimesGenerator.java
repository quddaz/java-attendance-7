package attendance.util;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;

public class LocalDateTimesGenerator implements DateTimesGenerator{
    @Override
    public LocalDateTime generator() {
        return DateTimes.now();
    }
}
