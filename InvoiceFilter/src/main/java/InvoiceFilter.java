import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceFilter {
    public static List<Invoice> doFilter(List<Invoice> invoices) {
        if (invoices.isEmpty())
            return Collections.EMPTY_LIST;

        List<Invoice> result = invoices.stream().collect(Collectors.toList());

        for (Invoice invoice : invoices) {
            boolean isValueLesserThan2000 = invoice.getValue() < 2000;
            boolean isValueBetween2000and2500 = invoice.getValue() >= 2000 && invoice.getValue() < 2500;
            boolean isDateEqualOrLesserThanLastMonth = invoice.getDate().compareTo(getLastMonthFromToday()) <= 0;
            if (isValueLesserThan2000
                    || isValueBetween2000and2500 && isDateEqualOrLesserThanLastMonth
            ) {
                result.remove(invoice);
            }
        }

        return result;
    }

    protected static Date getLastMonthFromDate(Date date) {
        LocalDateTime lastMonthDateTime = LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault())
                .minusMonths(1);
        return Date.from(lastMonthDateTime.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    protected static Date getLastMonthFromToday() {
        Date today = new Date();
        return getLastMonthFromDate(today);
    }
}
