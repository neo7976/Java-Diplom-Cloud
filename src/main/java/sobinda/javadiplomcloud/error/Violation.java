package sobinda.javadiplomcloud.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Violation {
    private final String fieldName;
    private final String message;


    @Override
    public String toString() {
        return "\nfieldName=" + fieldName + '\n' +
                "message='" + message +  "\n";
    }
}