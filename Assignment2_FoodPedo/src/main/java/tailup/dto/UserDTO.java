package tailup.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import tailup.enums.UserType;


@Getter
@Setter
@Builder
public class UserDTO {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private UserType userType;
    private String eMail;
}
