import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Test invoice filter class")
public class InvoiceFilterTest {

    @Test
    @DisplayName("When invoices list is empty, should return empty list")
    void filterEmptyList() {
        List<Invoice> emptyList = Collections.emptyList();

        assertThat(InvoiceFilter.doFilter(emptyList))
                .isEqualTo(emptyList);
    }
}
