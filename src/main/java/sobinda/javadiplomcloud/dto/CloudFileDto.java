package sobinda.javadiplomcloud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CloudFileDto {

    @JsonProperty("filename")
    private String fileName;
    private Long size;
    private Instant date;
    private UUID key;
    private byte[] resource;

    public CloudFileDto(String fileName) {
        this.fileName = fileName;
    }
}
