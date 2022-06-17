package sd.hotelroomservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class UserDTO {

    private String username;

    private String password;

    private String firstname;

    private String lastname;

    private String  role;

    private String eMail;

}
