package sd.hotelroomservice.service;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sd.hotelroomservice.dto.UserDTO;
import sd.hotelroomservice.entity.enums.Role;
import sd.hotelroomservice.entity.User;
import sd.hotelroomservice.mappers.UserMapper;
import sd.hotelroomservice.repository.UserRepository;
import sd.hotelroomservice.validators.UserValidator;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private UserValidator userValidator;

   @Autowired
   private PasswordEncoder userPasswordEncoder;

   Logger logger = LoggerFactory.getLogger(UserService.class);


   @SneakyThrows
   public void register(UserDTO userDTO) {

      try {
         userValidator.validate(userDTO);

      }catch(Exception ex) {
         logger.error(ex.getMessage());
         ex.printStackTrace();
         throw ex;
      }
      userDTO.setPassword(userPasswordEncoder.encode(userDTO.getPassword()));
      userRepository.save(UserMapper.toEntity(userDTO));
   }

   public List<UserDTO> findAllUsers()
   {
      return userRepository.findAll().stream().map(UserMapper::toDTO).collect(Collectors.toList());
   }


   public UserDTO getByUsername(String username)
   {
      return UserMapper.toDTO(userRepository.findByUsername(username));
   }


   @SneakyThrows
   public void save(UserDTO userDTO) {
      try {
         userValidator.validate(userDTO);
         userDTO.setPassword(userPasswordEncoder.encode(userDTO.getPassword()));
         userRepository.save(UserMapper.toEntity(userDTO));
      } catch (Exception ex) {
         logger.error(ex.getMessage());
         throw ex;
      }
   }



   @SneakyThrows
   public void deleteByUsername(String username)
   {
      User user = userRepository.findByUsername(username);
      if(user == null)
         throw new Exception("User with specified username does not exist!");
      else
         userRepository.delete(user);

   }

   @SneakyThrows
   public void updateByUsername(UserDTO userDTO, String username)
   {
      try{
         User user = userRepository.findByUsername(username);
         userValidator.validateUpdate(userDTO);
         user.setEmail(userDTO.getEMail());
         user.setFirstname(userDTO.getFirstname());
         user.setLastname(userDTO.getLastname());
         user.setRole(Role.valueOf(userDTO.getRole()));
         user.setUsername(userDTO.getUsername());
         userRepository.save(user);
      }catch(Exception ex)
      {
         logger.error(ex.getMessage());
         throw ex;
      }
   }


}
