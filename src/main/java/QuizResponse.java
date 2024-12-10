import java.io.Serializable;
import java.util.List;

public class QuizResponse implements Serializable {
    private List<QuizQuestion> questions;
    private int score;
    private String feedback;

    public QuizResponse(List<QuizQuestion> questions) {
        this.questions = questions;
    }

    public QuizResponse(int score, String feedback) {
        this.score = score;
        this.feedback = feedback;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public int getScore() {
        return score;
    }

    public String getFeedback() {
        return feedback;
    }
}
