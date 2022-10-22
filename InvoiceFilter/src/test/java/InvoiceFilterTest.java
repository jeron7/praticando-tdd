import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Test InvoiceFilter class")
public class InvoiceFilterTest {

    static final List<Invoice> EMPTY_LIST = Collections.emptyList();

    @Test
    @DisplayName("When invoices list is empty, should return empty list")
    void filterEmptyList() {
        List<Invoice> emptyList = Collections.emptyList();

        assertThat(InvoiceFilter.doFilter(emptyList))
                .isEqualTo(EMPTY_LIST);
    }

    @Nested
    @DisplayName("When invoices list have a invoice with value less than 2000")
    class WhenHaveInvoiceWithValueLessThan2000 {

        @Test
        @DisplayName("Should return empty")
        void filterListWithOneInvoice() {
            Invoice invoice = new Invoice(1, 2000.0, new Date(), new Client());
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(EMPTY_LIST);
        }
    }
}
