package Product.BTL_DBMS.Modal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    int index;
    String userId;
    String firstName;
    String lastName;
    String sex;
    String email;
    String phone;
    String dateOfBirth;
    String jobTitle;

    public User(int index, String userId, String firstName, String lastName, String sex, String email, String phone, String dateOfBirth, String jobTitle) {
        this.index = index;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "index=" + index +
                ", userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}

