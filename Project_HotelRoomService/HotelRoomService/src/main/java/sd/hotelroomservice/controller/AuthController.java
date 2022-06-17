package sd.hotelroomservice.controller;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sd.hotelroomservice.dto.UserDTO;
import sd.hotelroomservice.entity.ApiResponse;
import sd.hotelroomservice.service.CustomUserDetailsService;
import sd.hotelroomservice.service.UserService;
import sd.hotelroomservice.util.JwtUtil;

@RestController
@Log4j2
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

//    @GetMapping("/salut1")
//    public ResponseEntity<ApiResponse> salut()
//    {
//        return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "CEAUUU")
//                .build();
//    }

    /**
     *
     * @param userDTO user details to be authenticated
     * @return 200 OK - for valid credentials
     * @return 400 BAD REQUEST - for invalid credentials
     */
    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> login(@RequestBody UserDTO userDTO)  {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "AuthController/authenticate" );
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(),userDTO.getPassword()));
        }catch(BadCredentialsException ex)
        {
//            throw new Exception("Incorrect username or password", ex);
            logger.error("Incorrect username or password");
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .build();
        }

        final UserDetails userDetails = customUserDetailsService.loadUserByUsername(userDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        headers.set("Authorization","Bearer " +jwt);

        logger.info("Successfully logged in user" + userDTO.getUsername());

        return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(),"Successfully logged in user" + userDTO.getUsername())
                .addHttpHeader(headers)
                .build();
    }

    /**
     *
     * @param userDTO user details to be registered
     * @return 200 OK - for valid credentials
     * @return 400 BAD REQUEST - for invalid credentials, such as  : wrong e-mail, short password etc
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDTO userDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "AuthController/register");
        try{

            userService.register(userDTO);
            logger.info("Successfully registered user " + userDTO.getUsername());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully added user    " + userDTO.getUsername() + "!")
                    .addHttpHeader(headers)
                    .build();
        }catch(Exception ex)
        {   logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .addHttpHeader(headers)
                    .build();
        }
    }


}
