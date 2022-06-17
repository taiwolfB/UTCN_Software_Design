package sd.hotelroomservice.exception;

public class MissingUserRoleException extends  Exception{

    public MissingUserRoleException()
    {
        super("Please select an user type!");
    }
}
