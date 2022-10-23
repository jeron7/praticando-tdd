import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Client {

    private static final List<State> STATES_FROM_SOUTH = Arrays.asList(State.SC, State.RS,  State.PR);

    private String name;

    private Date inclusionDate;

    private State state;

    private List<Invoice> invoices;
    public List<Invoice> filterInvoices() {
        if (invoices.isEmpty())
            return Collections.EMPTY_LIST;

        List<Invoice> result = invoices.stream().collect(Collectors.toList());

        for (Invoice invoice : invoices) {
            boolean isValueLesserThan2000 = invoice.getValue() < 2000;

            boolean isValueBetween2000and2500 = invoice.getValue() >= 2000 && invoice.getValue() < 2500;
            boolean isDateEqualOrLesserThanLastMonth = invoice.getDate().compareTo(DateUtils.getLastMonthFromToday()) <= 0;

            boolean isValueBetween2500and3000 = invoice.getValue() >= 2500 && invoice.getValue() <= 3000;
            Date twoMonthsAgoFromToday = DateUtils.getNMonthsAgoFromDate(2, new Date());
            boolean isDateLesserOrEqualThanTwoMonthsAgo = this.getInclusionDate().compareTo(twoMonthsAgoFromToday) <= 0;

            boolean isValueGreaterThan4000 = invoice.getValue() > 4000;
            boolean isClientStateFromSouth = STATES_FROM_SOUTH.contains(this.getState());

            if (isValueLesserThan2000
                    || isValueBetween2000and2500 && isDateEqualOrLesserThanLastMonth
                    || isValueBetween2500and3000 && isDateLesserOrEqualThanTwoMonthsAgo
                    || isValueGreaterThan4000 && isClientStateFromSouth
            ) {
                result.remove(invoice);
            }
        }

        return result;
    }
}
