import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Car {

    private int id = 0;
    private String name;
    private String country;
}
