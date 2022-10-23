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

@DisplayName("Test Client class")
public class ClientTest {

    @Test
    @DisplayName("When invoices list is empty, should return empty list")
    void filterEmptyList() {
        List<Invoice> emptyList = Collections.emptyList();
        Client client = new Client("client", new Date(), State.AC, Collections.emptyList());

        assertThat(client.filterInvoices())
                .isEqualTo(Collections.EMPTY_LIST);
    }

    @Nested
    @DisplayName("When invoices list have a invoice with value less than 2000")
    class WhenHaveInvoiceWithValueLesserThan2000 {

        @Test
        @DisplayName("Should return empty")
        void filterListWithOneInvoice() {
            Invoice invoice = new Invoice(1, 100.0, new Date());
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            Client client = new Client("name", new Date(), State.AC, oneInvoice);
            assertThat(client.filterInvoices())
                    .isEqualTo(Collections.EMPTY_LIST);
        }

        @Test
        @DisplayName("Should return not filtered invoices")
        void filterListWithVariousInvoices() {
            Invoice invoice1 = new Invoice(1, 0.0, new Date());
            Invoice invoice2 = new Invoice(2, 1999.9, new Date());
            Invoice notFilteredInvoice = new Invoice(3, 2000.0, new Date());

            List<Invoice> invoices = Arrays.asList(invoice1, invoice2, notFilteredInvoice);
            Client client = new Client("name", new Date(), State.AC, invoices);

            assertThat(client.filterInvoices())
                    .isEqualTo(Collections.singletonList(notFilteredInvoice));
        }
    }

    @Nested
    @DisplayName("When invoices list have a invoice with value between 2000 and 2500")
    class WhenHaveInvoiceWithValueBetween2000And2500 {
        @Test
        @DisplayName("And invoice date is from last month, should return empty")
        void filterListWithOneInvoice() {
            Date lastMonth = DateUtils.getLastMonthFromToday();

            Invoice invoice = new Invoice(1, 2000.0, lastMonth);
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            Client client = new Client("name", new Date(), State.AC, oneInvoice);

            assertThat(client.filterInvoices())
                    .isEqualTo(Collections.EMPTY_LIST);
        }

        @Test
        @DisplayName("And have a invoice date is from last year, should not filtered invoices")
        void filterListWithMoreThanOneInvoice() {
            LocalDateTime localDateTime = LocalDateTime.parse("2021/10/29 12:00:00", DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
            Date lastYearDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

            Invoice invoice1 = new Invoice(1, 2000.0, lastYearDate);
            Invoice notFiltered1 = new Invoice(2, 2000.0, new Date());
            Invoice notFiltered2 = new Invoice(3, 2500.0, new Date());
            List<Invoice> invoices = Arrays.asList(invoice1, notFiltered1, notFiltered2);

            Client client = new Client("name", new Date(), State.AC, invoices);

            assertThat(client.filterInvoices())
                    .isEqualTo(Arrays.asList(notFiltered1, notFiltered2));
        }
    }

    @Nested
    @DisplayName("When invoices list have a invoice with value between 2500 and 3000")
    class WhenHaveInvoiceWithValueBetween2500And3000 {
        @Test
        @DisplayName("And invoice client date inclusion is from last month, should return the invoice in the list")
        void filterListWithOneInvoice() {
            Date lastMonth = DateUtils.getLastMonthFromToday();

            Invoice invoice = new Invoice(1, 2500.0, lastMonth);
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            Client client = new Client("name", lastMonth, State.AC, oneInvoice);

            assertThat(client.filterInvoices())
                    .isEqualTo(oneInvoice);
        }

        @Test
        @DisplayName("And invoice client date inclusion is from two month ago, should empty list")
        void filterListWithOneInvalidInvoice() {
            Date twoMonthsAgo = DateUtils.getNMonthsAgoFromDate(2, new Date());

            Invoice invoice = new Invoice(1, 2999.0, twoMonthsAgo);
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            Client client = new Client("name", twoMonthsAgo, State.AC, oneInvoice);

            assertThat(client.filterInvoices())
                    .isEqualTo(Collections.EMPTY_LIST);
        }

        @Test
        @DisplayName("And invoices client date inclusion is from two month ago, should empty list")
        void filterListWithTwoInvalidInvoices() {
            Date twoMonthsAgo = DateUtils.getNMonthsAgoFromDate(2, new Date());

            Invoice invoice1 = new Invoice(1, 2500.0, twoMonthsAgo);
            Invoice invoice2 = new Invoice(2, 2999.0, twoMonthsAgo);
            List<Invoice> invoices = Arrays.asList(invoice1, invoice2);
            Client invalidClient = new Client("client", twoMonthsAgo, State.AC, invoices);

            assertThat(invalidClient.filterInvoices())
                    .isEqualTo(Collections.EMPTY_LIST);
        }
    }

    @Nested
    @DisplayName("When invoices list have a invoice with value greater than 4000")
    class WhenHaveInvoiceWithValueGreaterThan4000 {
        @Test
        @DisplayName("And invoice client date inclusion is from last month, should return the invoice in the list")
        void filterListWithOneInvoice() {
            Date lastMonth = DateUtils.getLastMonthFromToday();

            Invoice invoice = new Invoice(1, 4000.1, lastMonth);
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            Client client = new Client("client", lastMonth, State.AC, oneInvoice);

            assertThat(client.filterInvoices())
                    .isEqualTo(oneInvoice);
        }

        @Test
        @DisplayName("And invoice client date inclusion is from two month ago, should return empty list")
        void filterListWithOneInvalidInvoice() {
            Date twoMonthsAgo = DateUtils.getNMonthsAgoFromDate(2, new Date());

            Invoice invoice = new Invoice(1, 5000, twoMonthsAgo);
            List<Invoice> oneInvoice = Collections.singletonList(invoice);

            Client client = new Client("client", twoMonthsAgo, State.SC, oneInvoice);

            assertThat(client.filterInvoices())
                    .isEqualTo(Collections.EMPTY_LIST);
        }
    }
}
