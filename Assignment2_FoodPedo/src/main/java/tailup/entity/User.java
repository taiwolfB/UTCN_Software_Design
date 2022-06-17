package tailup.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import tailup.enums.UserType;

import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;

@Table(name = "user")
@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Column(name = "username", nullable = false, unique = true)
    //@NotBlank(message = "Username already exists or it is empty!")
    private String username;

    @Column(name = "password", nullable = false)
  //  @NotBlank(message = "Password can not be empty!")
    private String password;

    @Column(name = "firstname")
  //  @NotBlank(message = "Firstname can not be empty!")
    private String firstname;

    @Column(name = "lastname")
   // @NotBlank(message = "Lastname can not be empty!")
    private String lastname;

    @Enumerated(EnumType.STRING)
  //  @NotNull(message = "Please select an user type!")
    private UserType userType;

    @Column(nullable = false, unique = true)
   // @NotBlank(message = "Email can not be empty!")
    private String eMail;

   // private List<Order> customerOrders;

   // private Restaurant restaurantOwner;


}
