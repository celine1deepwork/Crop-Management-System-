public class CapacityNotEnoughException extends Exception{

    String msg;
    
    public CapacityNotEnoughException() {
    }
    
    public CapacityNotEnoughException(String msg) {
        this.msg = msg;
    }    
    
}
