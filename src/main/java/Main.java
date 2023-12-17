import java.sql.*;

public class Main {
    public static void main(String[] args) {

        try (Connection connection =
                     DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                             "postgres", "ecuserver123");) {
            Statement statement = connection.createStatement();
//        statement.execute("CREATE TABLE car(id int, name varchar(32), country varchar(32))");
            ResultSet resultSet = statement.executeQuery("SELECT * from car");

            System.out.println("connected");

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String country = resultSet.getString("country");
                Car car = new Car(id, name, country);
                System.out.println(car);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
