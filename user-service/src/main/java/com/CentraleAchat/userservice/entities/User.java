package com.CentraleAchat.userservice.entities;

<<<<<<< Updated upstream
import lombok.Getter;
import lombok.Setter;
=======
import lombok.*;
>>>>>>> Stashed changes

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
<<<<<<< Updated upstream
import javax.validation.constraints.Size;

@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
@MappedSuperclass
@Getter
@Setter
public class User extends BaseEntity{
=======
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;


@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {

>>>>>>> Stashed changes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Firstname is mandatory")
    private String firstName;
<<<<<<< Updated upstream
    @NotEmpty(message = "LastName is mandatory")
    private String lastName;
    @Email
    private String email;
    @Size(min = 7, message = "Password must have a minimum of 7 characters")
    private String password;
    private Role role;
    @Lob
    @Column(columnDefinition = "BLOB")
    private String image;
    @NotEmpty(message = "Phone Number is mandatory")
    private int phoneNumber;
=======

    @NotEmpty(message = "LastName is mandatory")
    private String lastName;

    @Email(message = "Valid Email required")
    @NotEmpty(message = "Email is mandatory")
    private String email;

    @Size(min = 7, message = "Password must have a minimum of 7 characters")
    private String password;

    @NotEmpty
    @Enumerated(EnumType.STRING)
    private Role role;

    @Lob
    @Column(columnDefinition = "BLOB")
    private String image;

    @Size(min = 8, message = "Valid Phone number required")
    private int phoneNumber;
    @ManyToOne
    private Company company;

    private AcountStatus acountStatus = AcountStatus.ENABLED;

>>>>>>> Stashed changes
}
