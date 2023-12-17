import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private final Connection connection;

    List<Car> cars = new ArrayList<>();

    public CarDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Car> getAllCars(int page) { // page - это страница, например первая страница это начало списка
        try {
            int value = (page - 1) * 10;
            Statement statement = connection.createStatement();
            String GET_ALL_CARS = "SELECT * FROM car OFFSET " + value + " LIMIT 10"; // В каждой странице по 10 объектов

            ResultSet resultSet = statement.executeQuery(GET_ALL_CARS);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                Car car = new Car(id, name, country);
                cars.add(car);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }

    public void insertNewCar(Car car) {
        try {
            Statement statement = connection.createStatement();
            int id = car.getId();
            String name = car.getName();
            String country = car.getCountry();
            String INSERT_INTO = "INSERT INTO car VALUES (" + id + " ,'" + name + "', '" +  country + "');";
            statement.execute(INSERT_INTO);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(int carID) {
        try {
            Statement statement = connection.createStatement();
            String DELETE_CAR = "DELETE FROM car WHERE id = " + carID;
            statement.executeUpdate(DELETE_CAR);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCar(Car car) {
        try {
            Statement statement = connection.createStatement();

            int id = car.getId();
            String name = car.getName();
            String country = car.getCountry();

            String UPDATE_CAR = "UPDATE car SET name = '" + name + "', country = '" + country + "' WHERE id = " + id;
            statement.executeUpdate(UPDATE_CAR);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
