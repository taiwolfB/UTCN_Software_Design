package sd.hotelroomservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.hotelroomservice.dto.ReservationDTO;
import sd.hotelroomservice.entity.ApiResponse;
import sd.hotelroomservice.service.ReservationService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ReservationService reservationService;


    /**
     *
     * @param reservationDTO details of the reservation to be added to the database
     * @return 200 OK if the reservation details were valid
     * @return 400 BAD REQUEST if the reservation details were invalid
     */
    @PostMapping("/reservations/add")
    public ResponseEntity<ApiResponse> addReservation(@RequestBody ReservationDTO reservationDTO)
    {


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "CustomerController/reservations/add");
        try{

            reservationService.addReservation(reservationDTO);
            logger.info("Successfully added reservation for user " + reservationDTO.getUserId());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully added reservation for user " + reservationDTO.getUserId())
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
     * @param username of the user who desires to see his reservations
     * @return 200 OK and a list of reservations if the user has ongoing reservations
     * @return 200 OK and a list of empty reservations if the user does not have ongoing reservations
     * @return 400 BAD REQUEST if  there is any database error
     */
    @GetMapping("/{username}/reservations/findAll")
    public ResponseEntity<ApiResponse> findReservationsForUser(@PathVariable String username)
    {


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "CustomerController/reservations/findAll");
        try{

            List<ReservationDTO> reservations = reservationService.findReservationsForUsername(username);
            logger.info("Successfully retrieved reservations for user " + username);
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully retrieved reservations for user " + username)
                    .addHttpHeader(httpHeaders)
                    .addData(reservations)
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
     * @param id of the reservation to be deleted
     * @param username of the owner of the reservation
     * @return 200 OK if the reservation was deleted successfully
     * @return 400 BAD REQUEST if there was any error in the database while deleting
     */
    @DeleteMapping("/{username}/reservations/{id}/delete")
    public ResponseEntity<ApiResponse> deleteReservationById(@PathVariable String id, @PathVariable String username)
    {


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "CustomerController/reservations/deleteReservationByid");
        try{

            reservationService.deleteReservationByIdAndUsername(id, username);
            logger.info("Successfully deleted reservation with id " + id);
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully deleted reservation with id " + id)
                    .addHttpHeader(httpHeaders)
                    .build();
        }catch(Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .build();
        }
    }

}
