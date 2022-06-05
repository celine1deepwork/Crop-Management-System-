
public class FruitNotFoundException extends Exception{
    String msg;
    
    public FruitNotFoundException() {
    }

    public FruitNotFoundException(String msg) {
        this.msg = msg;
    }   
}
