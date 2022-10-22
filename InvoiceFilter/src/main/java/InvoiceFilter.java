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
            boolean isValueBetween2500and3000 = invoice.getValue() >= 2500 && invoice.getValue() <= 3000;
            Date twoMonthsAgoFromToday = getNMonthsAgoFromDate(2, new Date());
            boolean isDateLesserOrEqualThanTwoMonthsAgo = invoice.getClient().getInclusionDate().compareTo(twoMonthsAgoFromToday) <= 0;
            if (isValueLesserThan2000
                    || isValueBetween2000and2500 && isDateEqualOrLesserThanLastMonth
                    || isValueBetween2500and3000 && isDateLesserOrEqualThanTwoMonthsAgo
            ) {
                result.remove(invoice);
            }
        }

        return result;
    }

    protected static Date getNMonthsAgoFromDate(int n, Date date) {
        LocalDateTime lastMonthDateTime = LocalDateTime
                .ofInstant(date.toInstant(), ZoneId.systemDefault())
                .minusMonths(n);
        return Date.from(lastMonthDateTime.atZone(ZoneId.systemDefault())
                .toInstant());
    }

    protected static Date getLastMonthFromDate(Date date) {
        return getNMonthsAgoFromDate(1, date);
    }

    protected static Date getLastMonthFromToday() {
        Date today = new Date();
        return getLastMonthFromDate(today);
    }
}
