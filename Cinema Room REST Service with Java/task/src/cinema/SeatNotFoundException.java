package cinema;

//@ResponseStatus(code = HttpStatus.BAD_REQUEST) not needed with ExceptionHandler in RestController class
public class SeatNotFoundException extends RuntimeException{

    public SeatNotFoundException(String message){
        super(message);
    }
}
