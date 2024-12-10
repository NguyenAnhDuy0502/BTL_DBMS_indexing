package Product.BTL_DBMS.Service;

import Product.BTL_DBMS.Modal.User;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    // Thông tin cơ sở dữ liệu
    private static final String URL = "jdbc:mysql://localhost:3309/BTL_DBMS_index"; // Thay "your_database" bằng tên DB của bạn
    private static final String USER = "root"; // Tên người dùng MySQL
    private static final String PASSWORD = "123"; // Mật khẩu MySQL

    public static Connection connect() {
        Connection connection = null;
        try {
            // Kết nối tới cơ sở dữ liệu
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static List<User> query(String query) {
        List<User> userList = new ArrayList<>();
        try(Connection connection = connect();) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getInt("Index"),
                        resultSet.getString("UserId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Sex"),
                        resultSet.getString("Email"),
                        resultSet.getString("Phone"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getString("JobTitle"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
