package sd.hotelroomservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.hotelroomservice.dto.UserDTO;
import sd.hotelroomservice.entity.ApiResponse;
import sd.hotelroomservice.service.CustomUserDetailsService;
import sd.hotelroomservice.service.UserService;
import sd.hotelroomservice.util.JwtUtil;

import java.util.List;

@RestController("/manager")
public class StaffController {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    private final Logger logger = LoggerFactory.getLogger(StaffController.class);

//    private ResponseEntity<ApiResponse> checkJWT(UserDTO userDTO,String jwt)
//    {
//
//        try{
//            final UserDetails userDetails = customUserDetailsService.loadUserByUsername(userDTO.getUsername());
//            if(jwtUtil.validateToken(jwt,userDetails))
//                httpHeaders.add("Authorization", jwt);
//            else
//                throw new Exception("Invalid JWT");
//        }catch(Exception ex)
//        {
//            logger.error(ex.getMessage());
//            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.UNAUTHORIZED.value(), ex.getMessage())
//                    .build();
//        }
//    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse> addNewStaff(@RequestBody UserDTO userDTO, @RequestHeader("Authorization") String jwt)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", jwt);
        try
        {
            userService.save(userDTO);
            logger.info("Sucessfully saved user " + userDTO.getUsername() + "!");
            List<UserDTO> allUsers = userService.findAllUsers();
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(),
                    "Sucessfull saved user " + userDTO.getUsername() + "!")
                    .addHttpHeader(httpHeaders)
                    .addData(allUsers)
                    .build();

        }
        catch(Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .build();
        }
    }

//    @PutMapping("/edit")
//    public ResponseEntity<ApiResponse>  updateUser(@RequestBody UserDTO userDTO, @RequestHeader("Authorization")String jwt)
//    {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", jwt);
//        try
//        {
//            userService.editStaff(userDTO);
//            logger.info("Successfully updated user " + userDTO +"!");
//            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully updated user " + userDTO + "!")
//                    .addHttpHeader(httpHeaders)
//                    .addData(userService.findAllUsers())
//                    .build();
//        }catch(Exception ex)
//        {
//            logger.error(ex.getMessage());
//            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
//                    .build();
//        }
//    }

}
