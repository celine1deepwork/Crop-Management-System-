
public class SupplierHasNotEnoughMoneyException extends Exception{
    String msg;
    
    public SupplierHasNotEnoughMoneyException() {
    }

    public SupplierHasNotEnoughMoneyException(String msg) {
        this.msg = msg;
    }   
    
}
