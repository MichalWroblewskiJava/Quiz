import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class QuestionImport {
    private List<Question> questionList = new ArrayList<Question>();
    private File folder = new File("src\\main\\resources");
    private File[] pliki = folder.listFiles();

    File[] getCategory() {
        return this.pliki;
    }

    List<Question> questionImport() throws FileNotFoundException {
        for (File output : pliki) {
            int categoryLength = output.getName().length();
            String category = output.getName().substring(0, categoryLength - 4);
            Scanner in = new Scanner(output);
            while (in.hasNextLine()) {
                String question = in.nextLine();
                int arrayLength = Integer.parseInt(in.nextLine());
                String[] answers = new String[arrayLength];
                for (int i = 0; i < answers.length; i++) {
                    answers[i] = in.nextLine();
                }
                questionList.add(new Question(question, answers, category));
            }
        }
        return questionList;
    }
}
