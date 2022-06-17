package sd.hotelroomservice.controller;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.hotelroomservice.dto.RoomDTO;
import sd.hotelroomservice.entity.ApiResponse;
import sd.hotelroomservice.service.ReservationService;
import sd.hotelroomservice.service.RoomService;

import java.util.List;

@Log4j2
@RestController("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationService reservationService;

    Logger logger = LoggerFactory.getLogger(RoomController.class);

    @GetMapping("/income")
    public ResponseEntity<ApiResponse> getHotelIncome(@RequestHeader("Authorization") String jwt)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", jwt);

        try{
            double totalPrice = reservationService.findIncomeForAllReservations();
            logger.info("Successfully retrieved hotel income");
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.OK.value(), "Successfully retrieved hotel income")
                    .addData(totalPrice)
                    .addHttpHeader(httpHeaders)
                    .build();
        }catch(Exception ex)
        {
            logger.error(ex.getMessage());
            return new ApiResponse.ApiResponseBuilder<>(HttpStatus.BAD_REQUEST.value(), ex.getMessage())
                    .build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllRooms(@RequestHeader("Authorization") String jwt)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", jwt);
        try{
            List<RoomDTO> rooms = roomService.findAllRooms();
            logger.info("Succcessfully retrieved all rooms");
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
