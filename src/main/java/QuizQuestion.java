import java.io.Serializable;

public class QuizQuestion implements Serializable {
    private String questionText;
    private String[] options;
    private String correctAnswer;

    public QuizQuestion(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
