package sd.hotelroomservice.dto;

import lombok.Getter;
import lombok.Setter;
import sd.hotelroomservice.entity.enums.JobStatus;

import java.time.LocalDate;

@Getter
@Setter
public class JobDto {

    private String jobType;

    private JobStatus jobStatus;

    private LocalDate startDate;

    private LocalDate endDate;

}
