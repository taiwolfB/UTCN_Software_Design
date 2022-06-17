package tailup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tailup.dto.LoginForm;
import tailup.dto.UserDTO;
import tailup.entity.ApiResponse;
import tailup.service.UserService;

//import javax.validation.Valid;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/hello")
    public String hello()
    {
        return "Hello";
    }

//    @PostMapping("/login")
//    public void login(@RequestBody LoginForm loginForm)
//    {
//        this.userService.login(loginForm);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<ApiResponse> register(@Valid @RequestBody UserDTO userDTO) throws Exception
//    {
//        userService.register(userDTO);
//        ApiResponse apiResponse = new ApiResponse(HttpStatus.OK.value(), "Successfully created user with username" + userDTO.getUsername());
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }
//

}
