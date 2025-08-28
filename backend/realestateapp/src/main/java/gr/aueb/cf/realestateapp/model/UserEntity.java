package gr.aueb.cf.realestateapp.model;

import gr.aueb.cf.realestateapp.core.enums.ContactHours;
import gr.aueb.cf.realestateapp.core.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity extends BaseAbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "contact_hours")
    private ContactHours contactHours;

    @Enumerated(EnumType.STRING)
    private RoleEnum role = RoleEnum.USER;

    private String password;
}
