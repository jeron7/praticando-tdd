import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Client {

    private String name;

    private Date inclusionDate;

    private State state;
}
