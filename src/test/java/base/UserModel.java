package base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    private String email;
    private String password;
    private String name;

    public UserModel(String email, String password, String name) {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}
