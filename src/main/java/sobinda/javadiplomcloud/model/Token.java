package sobinda.javadiplomcloud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Token {
    @JsonProperty("auth-token")
    private String token;

}
