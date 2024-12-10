package Product.BTL_DBMS.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
public class CovertJsonToScriptMySQL {

    public static void main(String[] args) {
        // Tạo ObjectMapper để đọc dữ liệu JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Đọc tệp JSON vào một danh sách các đối tượng
            File jsonFile = new File("src/main/resources/static/10k_records_sample.json");
            List<Map<String, Object>> jsonDataList = objectMapper.readValue(jsonFile, List.class); // Đọc JSON dưới dạng danh sách

            // Tạo lệnh SQL để tạo bảng
            String createTableSQL = "CREATE TABLE IF NOT EXISTS `User` (\n" +
                    "    `Index` INT PRIMARY KEY,\n" +
                    "    `UserId` VARCHAR(255),\n" +
                    "    `FirstName` VARCHAR(255),\n" +
                    "    `LastName` VARCHAR(255),\n" +
                    "    `Sex` VARCHAR(255),\n" +
                    "    `Email` VARCHAR(255),\n" +
                    "    `Phone` VARCHAR(255),\n" +
                    "    `DateOfBirth` VARCHAR(255),\n" +
                    "    `JobTitle` VARCHAR(255)\n" +
                    ");\n";

            // Mở tệp SQL để ghi
            FileWriter fileWriter = new FileWriter("src/main/resources/static/output.sql");
            fileWriter.write(createTableSQL);

            // Bắt đầu câu lệnh INSERT
            fileWriter.write("INSERT INTO `User` (`Index`, `UserId`, `FirstName`, `LastName`, `Sex`, `Email`, `Phone`, `DateOfBirth`, `JobTitle`) VALUES\n");

            // Lặp qua tất cả các đối tượng và xây dựng câu lệnh INSERT
            for (int i = 0; i < jsonDataList.size(); i++) {
                Map<String, Object> jsonData = jsonDataList.get(i);

                // Tạo câu lệnh INSERT cho mỗi đối tượng
                String insertSQL = String.format(" ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                        jsonData.get("Index"),
                        jsonData.get("User Id"),
                        jsonData.get("First Name"),
                        jsonData.get("Last Name"),
                        jsonData.get("Sex"),
                        jsonData.get("Email"),
                        jsonData.get("Phone"),
                        jsonData.get("Date of birth"),
                        jsonData.get("Job Title")
                );

                // Nếu không phải bản ghi cuối cùng, thêm dấu phẩy sau mỗi câu lệnh INSERT
                if (i < jsonDataList.size() - 1) {
                    insertSQL += ",";
                } else {
                    insertSQL += ";";  // Đối với bản ghi cuối cùng, thêm dấu chấm phẩy
                }

                fileWriter.write(insertSQL + "\n");  // Ghi câu lệnh INSERT vào tệp
            }

            // Đóng tệp sau khi ghi
            fileWriter.close();

            System.out.println("SQL lệnh đã được lưu vào tệp output.sql");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
