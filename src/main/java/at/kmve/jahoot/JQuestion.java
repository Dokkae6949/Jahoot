package at.kmve.jahoot;

import java.util.Arrays;

public class JQuestion {
    private String question;

    private int solution;
    private String[] answers;


    public JQuestion(String question, int solution, String[] answers) {
        this.question = question;
        this.solution = solution;
        this.answers = answers;
    }


    @Override
    public String toString() {
        return "JQuestion{" +
                "question='" + question + '\'' +
                ", solution=" + solution +
                ", answers=" + Arrays.toString(answers) +
                '}';
    }


    public String getQuestion() {
        return question;
    }

    public int getSolution() {
        return solution;
    }

    public String[] getAnswers() {
        return answers;
    }
}
