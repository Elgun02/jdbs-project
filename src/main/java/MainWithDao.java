import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MainWithDao {
    public static void main(String[] args) throws SQLException {
        try (Connection connection =
                     DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                             "postgres", "ecuserver123");) {

            CarDAO carDAO = new CarDAO(connection);

            Car car = new Car(1, "BMW", "Germany");
//            carDAO.insertNewCar(car);
//            carDAO.deleteCar(1);
//            carDAO.updateCar(car);

            List<Car> cars = carDAO.getAllCars(1);
            for (Car obj : cars) {
                System.out.println(obj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
