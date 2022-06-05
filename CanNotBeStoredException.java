
public class CanNotBeStoredException extends Exception{

    String msg;
            
    CanNotBeStoredException() {
    }
    
    CanNotBeStoredException(String msg) {
        this.msg = msg;
    }
}
