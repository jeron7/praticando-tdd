import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InvoiceFilter {
    public static List<Invoice> doFilter(List<Invoice> invoices) {
        if (invoices.isEmpty())
            return invoices;

        Predicate<Invoice> byValueGreaterThan200 = invoice -> invoice.getValue() >= 2000;

        List<Invoice> result = invoices.stream()
                .filter(byValueGreaterThan200)
                .collect(Collectors.toList());

        return result;
    }
}
