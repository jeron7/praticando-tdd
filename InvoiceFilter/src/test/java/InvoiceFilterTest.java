import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Test InvoiceFilter class")
public class InvoiceFilterTest {

    final Client VALID_CLIENT = new Client("client", new Date(), State.AC);

    @Test
    @DisplayName("When invoices list is empty, should return empty list")
    void filterEmptyList() {
        List<Invoice> emptyList = Collections.emptyList();

        assertThat(InvoiceFilter.doFilter(emptyList))
                .isEqualTo(Collections.EMPTY_LIST);
    }

    @Nested
    @DisplayName("When invoices list have a invoice with value less than 2000")
    class WhenHaveInvoiceWithValueLesserThan2000 {

        @Test
        @DisplayName("Should return empty")
        void filterListWithOneInvoice() {
            Invoice invoice = new Invoice(1, 100.0, new Date(), VALID_CLIENT);
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(Collections.EMPTY_LIST);
        }

        @Test
        @DisplayName("Should return not filtered invoices")
        void filterListWithVariousInvoices() {
            Invoice invoice1 = new Invoice(1, 0.0, new Date(), VALID_CLIENT);
            Invoice invoice2 = new Invoice(2, 1999.9, new Date(), VALID_CLIENT);
            Invoice notFilteredInvoice = new Invoice(3, 2000.0, new Date(), VALID_CLIENT);

            List<Invoice> oneInvoice = Arrays.asList(invoice1, invoice2, notFilteredInvoice);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(Collections.singletonList(notFilteredInvoice));
        }
    }

    @Nested
    @DisplayName("When invoices list have a invoice with value between 2000 and 2500")
    class WhenHaveInvoiceWithValueBetween2000And2500 {
        @Test
        @DisplayName("And invoice date is from last month, should return empty")
        void filterListWithOneInvoice() {
            Date lastMonth = InvoiceFilter.getLastMonthFromToday();

            Invoice invoice = new Invoice(1, 2000.0, lastMonth, VALID_CLIENT);
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(Collections.EMPTY_LIST);
        }

        @Test
        @DisplayName("And have a invoice date is from last year, should not filtered invoices")
        void filterListWithMoreThanOneInvoice() {
            LocalDateTime localDateTime = LocalDateTime.parse("2021/10/29 12:00:00", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            Date lastYearDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

            Invoice invoice1 = new Invoice(1, 2000.0, lastYearDate, VALID_CLIENT);
            Invoice notFiltered1 = new Invoice(2, 2000.0, new Date(), VALID_CLIENT);
            Invoice notFiltered2 = new Invoice(3, 2500.0, new Date(), VALID_CLIENT);
            List<Invoice> oneInvoice = Arrays.asList(invoice1, notFiltered1, notFiltered2);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(Arrays.asList(notFiltered1, notFiltered2));
        }
    }

    @Nested
    @DisplayName("When invoices list have a invoice with value between 2500 and 3000")
    class WhenHaveInvoiceWithValueBetween2500And3000 {
        @Test
        @DisplayName("And invoice client date inclusion is from last month, should return the invoice in the list")
        void filterListWithOneInvoice() {
            Date lastMonth = InvoiceFilter.getLastMonthFromToday();

            Invoice invoice = new Invoice(1, 2500.0, lastMonth, new Client("client", lastMonth, State.AC));
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(oneInvoice);
        }

        @Test
        @DisplayName("And invoice client date inclusion is from two month ago, should empty list")
        void filterListWithOneInvalidInvoice() {
            Date twoMonthsAgo = InvoiceFilter.getNMonthsAgoFromDate(2, new Date());

            Invoice invoice = new Invoice(1, 2999.0, twoMonthsAgo, new Client("client", twoMonthsAgo, State.AC));
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(Collections.EMPTY_LIST);
        }

        @Test
        @DisplayName("And invoices client date inclusion is from two month ago, should empty list")
        void filterListWithTwoInvalidInvoices() {
            Date twoMonthsAgo = InvoiceFilter.getNMonthsAgoFromDate(2, new Date());
            Client invalidClient = new Client("client", twoMonthsAgo, State.AC);

            Invoice invoice1 = new Invoice(1, 2500.0, twoMonthsAgo, invalidClient);
            Invoice invoice2 = new Invoice(2, 2999.0, twoMonthsAgo, invalidClient);
            List<Invoice> oneInvoice = Arrays.asList(invoice1, invoice2);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(Collections.EMPTY_LIST);
        }
    }

    @Nested
    @DisplayName("When invoices list have a invoice with value greater than 4000")
    class WhenHaveInvoiceWithValueGreaterThan4000 {
        @Test
        @DisplayName("And invoice client date inclusion is from last month, should return the invoice in the list")
        void filterListWithOneInvoice() {
            Date lastMonth = InvoiceFilter.getLastMonthFromToday();

            Invoice invoice = new Invoice(1, 4000.1, lastMonth, new Client("client", lastMonth, State.AC));
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(oneInvoice);
        }

        @Test
        @DisplayName("And invoice client date inclusion is from two month ago, should empty list")
        void filterListWithOneInvalidInvoice() {
            Date twoMonthsAgo = InvoiceFilter.getNMonthsAgoFromDate(2, new Date());

            Invoice invoice = new Invoice(1, 5000, twoMonthsAgo, new Client("client", twoMonthsAgo, State.SC));
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            assertThat(InvoiceFilter.doFilter(oneInvoice))
                    .isEqualTo(Collections.EMPTY_LIST);
        }
    }
}
