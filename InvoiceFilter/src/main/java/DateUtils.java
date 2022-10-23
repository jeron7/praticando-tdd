import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date getNMonthsAgoFromDate(int n, Date date) {
        LocalDateTime lastMonthDateTime = LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault())
                .minusMonths(n);
        return Date.from(lastMonthDateTime.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static Date getLastMonthFromDate(Date date) {
        return getNMonthsAgoFromDate(1, date);
    }

    public static Date getLastMonthFromToday() {
        Date today = new Date();
        return getLastMonthFromDate(today);
    }
}
