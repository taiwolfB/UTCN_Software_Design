package sd.hotelroomservice.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sd.hotelroomservice.dto.UserDTO;
import sd.hotelroomservice.entity.enums.Role;
import sd.hotelroomservice.entity.User;

@Component
public class UserMapper {



    public static UserDTO toDTO(User user)
    {
        return  UserDTO
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .role(user.getRole().toString())
                .eMail(user.getEmail())
                .build();
    }

    public static User toEntity(UserDTO userDTO)
    {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setRole(Role.valueOf(userDTO.getRole()));
        user.setEmail(userDTO.getEMail());
        return user;
    }
}

