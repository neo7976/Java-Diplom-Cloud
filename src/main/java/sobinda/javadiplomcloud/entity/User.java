package sobinda.javadiplomcloud.entity;

import lombok.*;
import sobinda.javadiplomcloud.model.Role;

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

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CloudFile> cloudFileList;

}
