package techtown.org.blescanner.HTTP_REST.Mobicare_Rest_Object;

public class Logout_Response {
    boolean result;
    String extra;
    long systemTime;
    int error;
    String data;
    String message;

    public boolean getResult() {
        return result;
    }

    public String getExtra() {
        return extra;
    }

    public long getSystemTime() {
        return systemTime;
    }

    public int getError() {
        return error;
    }

    public String getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String string(){
        String body = "";
        body = "\nresult : "+getResult()+"\nExtra : "+getExtra()+"\nSystemTime : "+getSystemTime()+"\nError : "+getError()+"\nData : "+getData()+"\nMessage : "+getMessage();
        return body;
    }
}
