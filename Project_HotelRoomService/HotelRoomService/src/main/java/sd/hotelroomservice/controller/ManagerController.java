package sd.hotelroomservice.controller;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.hotelroomservice.dto.RoomDTO;
import sd.hotelroomservice.dto.UserDTO;
import sd.hotelroomservice.entity.ApiResponse;
import sd.hotelroomservice.service.RoomService;
import sd.hotelroomservice.service.UserService;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/manager")
@Log4j2
public class ManagerController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(ManagerController.class);


    /**
     *
     * @param jwt -
     * @param roomDTO - details of the rooms to be saved
     * @return 200 OK if the details were correct and a list of all the rooms in the hotel
     * @return 400 BAD REQUEST if the details were not INTEGERS
     */
    @PostMapping("/rooms/save")
    public ResponseEntity<ApiResponse> addNewRoom(@RequestHeader("Authorization") String jwt, @RequestBody RoomDTO roomDTO)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", jwt);

        try{
            roomService.save(roomDTO);
            logger.info("Successfully added new room!");
            List<RoomDTO> rooms = roomService.findAllRooms();
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully added new room!")
                    .addHttpHeader(httpHeaders)
                    .addData(rooms)
                    .build();
        }catch(Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .build();
        }
    }

    /**
     *
     * @param jwt
     * @param roomDTO - contains the parameters to be updated in the database
     * @return 200 OK if the parameters were valid and the update was successfully and also returns a list of all the rooms
     * @return 400 OK if the parameters were not valid or an error raised while updating the room
     */
    @PutMapping("/rooms/edit")
    public ResponseEntity<ApiResponse> editRoom(@RequestHeader("Authorization") String jwt, @RequestBody RoomDTO roomDTO)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", jwt);

        try{
            RoomDTO updatedRoom = roomService.editRoom(roomDTO);
            logger.info("Successfully edited room" + roomDTO.getId() + " with new details!");
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(),"Successfully edited room" + roomDTO.getId() + " with new details!")
                    .addData(updatedRoom)
                    .addHttpHeader(httpHeaders)
                    .build();
        }catch(Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .build();
        }
    }

    /**
     *
     * @return 200 OK and a list of users
     * @return 400 BAD REQUEST if an error raised in the database while fetching
     */
    @GetMapping("/users/all")
    public ResponseEntity<ApiResponse> getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "ManagerController/users");
        try {
            logger.info("Successfully retrieved all users!");
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully retrieved all users")
                    .addHttpHeader(headers)
                    .addData(userService.findAllUsers())
                    .build();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .addHttpHeader(headers)
                    .build();
        }

    }

    /**
     *
     * @param userDTO - details to be updated on the user
     * @return 200 OK and a list of all the users in the system if the details were valid
     * @return 400 BAD REQUEST if there were any invalid parameters or an error raised in the database while updating
     */
    @PostMapping("/users/save")
    public ResponseEntity<ApiResponse> register(@RequestBody UserDTO userDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "AuthController/users/save");
        try{

            userService.register(userDTO);
            logger.info("Successfully created new  user " + userDTO.getUsername());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully added user    " + userDTO.getUsername() + "!")
                    .addHttpHeader(headers)
                    .addData(userService.findAllUsers())
                    .build();
        }catch(Exception ex)
        {   logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .addHttpHeader(headers)
                    .build();
        }
    }

    /**
     *
     * @param username of the user to be deleted
     * @return 200 OK and a list of all the remaining users in the system if the deletion succeeded
     * @return 400 BAD REQUEST if the username is not valid or an error raised in the database while deleting
     */
    @DeleteMapping("/users/{username}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String username)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "AuthController/users/delete");

        try{

            userService.deleteByUsername(username);
            logger.info("Successfully deleted user " + username);
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully deleted user    " + username + "!")
                    .addHttpHeader(headers)
                    .addData(userService.findAllUsers())
                    .build();
        }catch(Exception ex)
        {   logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .addHttpHeader(headers)
                    .build();
        }
    }

    /**
     *
     * @param username of the user to be updated
     * @param userDTO - details to be updated for the user
     * @return 200 OK and a list of all the users in system if the details were valid
     * @return 400 BAD REQUEST if the details were not valid or an error raised in the database while updating the user
     */
    @PutMapping("/users/{username}/update")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable String username, @RequestBody UserDTO userDTO)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded", "AuthController/users/update");

        try{

            userService.updateByUsername(userDTO, username);
            logger.info("Successfully updated user " + username);
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully updated user " + username + " !")
                    .addHttpHeader(headers)
                    .addData(userService.findAllUsers())
                    .build();
        }catch(Exception ex)
        {   logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .addHttpHeader(headers)
                    .build();
        }
    }

}
