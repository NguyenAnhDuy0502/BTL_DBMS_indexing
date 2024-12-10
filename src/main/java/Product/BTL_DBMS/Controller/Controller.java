package Product.BTL_DBMS.Controller;

import Product.BTL_DBMS.Modal.User;
import Product.BTL_DBMS.Service.MySQL;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/all")
    public List<User> getAll() {
        return MySQL.query("SELECT * FROM `User`;");
    }

    @GetMapping("/search")
    public List<User> search(
            @RequestParam(value = "FirstName", required = false) String firstName,
            @RequestParam(value = "LastName", required = false) String lastName,
            @RequestParam(value = "Sex", required = false) String sex,
            @RequestParam(value = "Email", required = false) String email,
            @RequestParam(value = "Phone", required = false) String phone,
            @RequestParam(value = "JobTitle", required = false) String jobTitle
    ) {
        // Do điều kiện thứ 2 mới bắt đầu thêm AND cho nên dùng 1=1 để khỏi cần kiểm tra có phải đang là điều kiện >=2 không
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM User WHERE 1=1");
        List<Object> parameters = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        if(firstName == null && lastName == null && sex == null && email == null && phone == null && jobTitle == null)
            return getAll();

        if (firstName != null && !firstName.isEmpty())
            queryBuilder.append(" AND MATCH(`FirstName`) AGAINST('" + firstName + "')");
        if (lastName != null && !lastName.isEmpty())
            queryBuilder.append(" AND MATCH(`LastName`) AGAINST('" + lastName + "')");
        if (sex != null && !sex.isEmpty())
            queryBuilder.append(" AND MATCH(`Sex`) AGAINST('" + sex + "')");
        if (email != null && !email.isEmpty())
            queryBuilder.append(" AND MATCH(`Email`) AGAINST('" + email + "')");
        if (phone != null && !phone.isEmpty())
            queryBuilder.append(" AND MATCH(`Phone`) AGAINST('" + phone + "')");
        if (jobTitle != null && !jobTitle.isEmpty())
            queryBuilder.append(" AND MATCH(`JobTitle`) AGAINST('" + jobTitle + "')");

        queryBuilder.append(";");
        String query = queryBuilder.toString();
        return MySQL.query(query);

    }
}





