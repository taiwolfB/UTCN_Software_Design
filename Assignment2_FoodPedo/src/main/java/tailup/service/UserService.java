package tailup.service;

import org.springframework.stereotype.Service;
import tailup.dto.LoginForm;
import tailup.dto.UserDTO;
import tailup.entity.User;
import tailup.exception.DuplicateUsernameException;
import tailup.exception.InvalidEmailException;
import tailup.exception.InvalidUsernameException;
import tailup.exception.MissingUserTypeException;
import tailup.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void login(LoginForm loginForm)
    {
        User user = userRepository.findByUsername(loginForm.getUsername());

    }


    public void register(UserDTO userDTO) throws  Exception
    {
        validateUser(userDTO);
        userRepository.save(convertFromDto(userDTO));
    }

    public void validateUser(UserDTO userDTO) throws  Exception
    {
        if(userRepository.findByUsername(userDTO.getUsername()) != null)
            throw new DuplicateUsernameException("Username with username " + userDTO.getUsername() + " already exists!");
        if(userDTO.getUserType() == null)
            throw new MissingUserTypeException();
        if(!userDTO.getEMail().matches("^[A-za-z]+[\\s-]?[A-za-z]*[\\s-]?[A-za-z]*[\\s-]?[A-za-z]*[\\s-]?[A-za-z]*$"))
            throw new InvalidEmailException();
        if(userDTO.getUsername() == null || userDTO.getUsername().isEmpty() || userDTO.getUsername().length() < 3)
            throw  new InvalidUsernameException();
    }

    public UserDTO convertToDto(User user)
    {
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .eMail(user.getEMail())
                .userType(user.getUserType())
                .build();
    }

    public User convertFromDto(UserDTO userDTO)
    {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEMail(userDTO.getEMail());
        user.setUserType(userDTO.getUserType());
        return user;
    }
}
