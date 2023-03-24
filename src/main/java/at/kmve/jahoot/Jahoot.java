package at.kmve.jahoot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Jahoot {
    private List<JQuestion> questions;


    public Jahoot(Path questionsFilePath) throws IOException {
        loadQuestionsFile(questionsFilePath);
    }


    public List<JQuestion> getQuestions() {
        return questions;
    }


    public void loadQuestionsFile(Path questionsFilePath) throws IOException, NumberFormatException {
        List<String> lines;
        questions = new ArrayList<>();

        lines = Files.readAllLines(questionsFilePath, StandardCharsets.UTF_8);

        for (String line : lines) {
            if (line.isBlank())
                continue;

            String[] qData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            if (qData.length < 4)
                continue;

            for (int i = 0; i < qData.length; i++) {
                if (!(qData[i].startsWith("\"") || qData[i].endsWith("\"")))
                    continue;

                qData[i] = qData[i].substring(1, qData[i].length() - 1);
            }

            String[] answers = new String[qData.length - 2];

            System.arraycopy(qData, 2, answers, 0, qData.length - 2);

            JQuestion question = new JQuestion(qData[0], Integer.parseInt(qData[1]), answers);
            questions.add(question);
        }
    }


    public static void main(String[] args) {
        Path path = Path.of(System.getProperty("user.dir"), "resources", "jahoots", "demo.jht");

        try {
            Jahoot jahoot = new Jahoot(path);

            for (JQuestion q : jahoot.getQuestions())
                System.out.println(q);
        } catch (Exception e) {
            System.out.println("Could not load Jahoot.");
            e.printStackTrace();
        }
    }
}
