package sd.hotelroomservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.hotelroomservice.dto.ReservationDTO;
import sd.hotelroomservice.dto.RoomDTO;
import sd.hotelroomservice.entity.ApiResponse;
import sd.hotelroomservice.service.ReservationService;
import sd.hotelroomservice.service.RoomService;

import java.util.List;

@RestController()
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationService reservationService;

    Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     *
     * @return 200 OK - no database error
     * @return 400 BAD REQUEST - error in the database
     */
    @GetMapping("/rooms/all")
    public ResponseEntity<ApiResponse> getAllRooms(@RequestHeader("Authorization") String jwt)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", jwt);
        try{
            List<RoomDTO> rooms = roomService.findAllRooms();
            logger.info("Successfully retrieved all rooms");
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully retrieved all rooms")
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
     * @param reservationDTO contains the startDate and endDate used to find the rooms available between those dates
     * @return 200 OK and a list of rooms if there are available rooms
     * @return 200 OK and a list of empty rooms if there are no available rooms
     * @return 400 BAD REQUEST if the dates are passed in wrong in the parameter
     */
    @PostMapping("/rooms/allByDate")
    public ResponseEntity<ApiResponse> getAllRoomsByDate(@RequestBody ReservationDTO reservationDTO)
    {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Responded", "CommonController/rooms/allByDate");
        try{
            List<RoomDTO> rooms = roomService.findAllAvailableRoomsByDate(reservationDTO.getStartDate(), reservationDTO.getEndDate());
            logger.info("Successfully retrieved all rooms");
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully retrieved all rooms")
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
}
