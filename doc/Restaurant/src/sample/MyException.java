package sample;

/**
 * Created by Master Faster on 09.12.2016.
 */
public class MyException extends Exception {

    public MyException(){
        System.out.println("New Exception was thrown");
    }

    public MyException(String msg){
        super(msg);
    }
}
