package sd.hotelroomservice.validators;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sd.hotelroomservice.dto.UserDTO;
import sd.hotelroomservice.exception.*;
import sd.hotelroomservice.repository.UserRepository;

import javax.naming.InvalidNameException;

@Component
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    public void validate(UserDTO userDTO)
    {
        if(userRepository.findByUsername(userDTO.getUsername()) != null)
            throw new DuplicateUsernameException("User with username " + userDTO.getUsername() + " already exists!");
        if(userRepository.findByEmail(userDTO.getEMail()) != null)
            throw new DuplicateEmailException("E-mail" + userDTO.getEMail() + "already in use");
        if(userDTO.getRole() == null)
            throw new MissingUserRoleException();
        if(userDTO.getEMail() == null || userDTO.getEMail().isEmpty())
            throw new InvalidCredentialsException("Email can not be empty");
        if(!userDTO.getEMail().matches("^[A-Za-z0-9+_.-]+@[A-za-z]+\\.[A-za-z]+$"))
            throw new InvalidEmailException();
        if(userDTO.getUsername() == null || userDTO.getUsername().isEmpty() || userDTO.getUsername().length() < 3)
            throw  new InvalidUsernameException();
        if(userDTO.getFirstname() == null)
            throw new InvalidNameException("Invalid first name exception. First name can not be null.");
        if(userDTO.getLastname() == null)
            throw new InvalidNameException("Invalid last name exception. First name can not be null.");
    }

    @SneakyThrows
    public void validateUpdate(UserDTO userDTO)
    {
        if(userDTO.getRole() == null)
            throw new MissingUserRoleException();
        if(userDTO.getEMail() == null || userDTO.getEMail().isEmpty())
            throw new InvalidCredentialsException("Email can not be empty");
        if(!userDTO.getEMail().matches("^[A-Za-z0-9+_.-]+@[A-za-z]+\\.[A-za-z]+$"))
            throw new InvalidEmailException();
        if(userDTO.getUsername() == null || userDTO.getUsername().isEmpty() || userDTO.getUsername().length() < 3)
            throw  new InvalidUsernameException();
        if(userDTO.getFirstname() == null)
            throw new InvalidNameException("Invalid first name exception. First name can not be null.");
        if(userDTO.getLastname() == null)
            throw new InvalidNameException("Invalid last name exception. First name can not be null.");
    }
}
