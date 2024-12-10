import java.io.Serializable;

public class QuizRequest implements Serializable {
    private String requestType;

    public QuizRequest(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }
}
