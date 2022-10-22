import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Invoice {

    private int code;

    private double value;

    private Date date;

    private Client client;
}
