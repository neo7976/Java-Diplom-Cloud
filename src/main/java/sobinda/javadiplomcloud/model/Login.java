package sobinda.javadiplomcloud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Data
@AllArgsConstructor
@ToString
public class Login {
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return Objects.equals(login, login1.login) && Objects.equals(password, login1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
