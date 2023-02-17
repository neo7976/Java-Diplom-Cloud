package sobinda.javadiplomcloud.entity;

import jdk.jfr.Name;
import lombok.*;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;
import sobinda.javadiplomcloud.model.Authorities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(length = 25, nullable = false, unique = true)
    private String login;

    @NotBlank
    @Column(length = 15, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private Authorities role;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private Set<Authorities> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CloudFile> cloudFileList;

}
