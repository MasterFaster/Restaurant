package sample.Exceptions;

/**
 * Created by Master Faster on 08.01.2017.
 * thrown, when there is not enough Meals in meanu to take order
 */
public class NoDataException extends Exception{

    public NoDataException(){
        super();
    }

    public NoDataException(String message){
        super(message);
    }
}
