package dev.vuphatdat.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "user_email")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name="user_email", nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telephone")
    private Long telephone;

    @Column(name = "creat_at")
    private LocalDate createAt;

    @Column(name = "modifided_at")
    private LocalDate modifiedAt;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_Id"))
    private List<Role> roles = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private OrderDetail orderDetail;
}
