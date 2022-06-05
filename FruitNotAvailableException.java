
public class FruitNotAvailableException extends Exception{
    String msg;
    
    public FruitNotAvailableException() {
    }

    public FruitNotAvailableException(String msg) {
        this.msg = msg;
    }    
    
}
