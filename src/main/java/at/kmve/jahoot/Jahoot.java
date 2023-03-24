package at.kmve.jahoot;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Jahoot {
    private List<JQuestion> questions = new ArrayList<>();


    public Jahoot(Path questionsFilePath) throws IOException {
        questions = loadQuestionsFile(questionsFilePath);

        for (JQuestion q : questions)
            System.out.println(q);
    }


    public List<JQuestion> loadQuestionsFile(Path questionsFilePath) throws IOException, NumberFormatException {
        List<String> lines;
        List<JQuestion> q = new ArrayList<>();

        lines = Files.readAllLines(questionsFilePath, StandardCharsets.UTF_8);

        for (String line : lines) {
            if (line.isBlank())
                continue;

            String[] qData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            for (int i = 0; i < qData.length; i++) {
                qData[i] = qData[i].replaceAll("\"", "");
                qData[i] = qData[i].replaceAll("'", "\"");
            }

            if (qData.length <= 3)
                continue;

            String[] answers = new String[qData.length - 2];

            System.arraycopy(qData, 2, answers, 0, qData.length - 2);

            JQuestion question = new JQuestion(qData[0], Integer.parseInt(qData[1]), answers);
            q.add(question);
        }

        return q;
    }


    public static void main(String[] args) {
        Path path = Path.of(System.getProperty("user.dir"), "resources", "jahoots", "demo.jht");

        try {
            Jahoot jahoot = new Jahoot(path);
        } catch (Exception e) {
            System.out.println("Could not load Jahoot.");
            e.printStackTrace();
        }
    }
}
