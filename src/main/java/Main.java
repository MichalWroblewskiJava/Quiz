import org.apache.commons.lang3.math.NumberUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, AWTException {
        QuestionImport questions = new QuestionImport();
        List<Question> questionList = questions.questionImport();
        List<Question> questionToAsk = new ArrayList<>();
        File[] category = questions.getCategory();
        String[] categoryID = new String[category.length + 1];
        int catID = 0;
        for (File categoryName : category) {
            String categoryNameSub = categoryName.getName().substring(0, categoryName.getName().length() - 4);
            System.out.println(catID + 1 + " " + categoryNameSub);
            categoryID[catID] = categoryNameSub;
            catID++;
        }
        categoryID[catID] = "All";
        int catAll = catID + 1;
        System.out.println(catAll + " All");
        System.out.println("Please, pick catID");

        String catergoryPickString;
        int catergoryPick = 0;
        Scanner inputCategory = new Scanner(System.in);

        do {
            catergoryPickString = inputCategory.nextLine();
            if (NumberUtils.isDigits(catergoryPickString)) {
                catergoryPick = Integer.parseInt(catergoryPickString);
            } else {
                System.out.println("Input is not a number, pick category from 1 to " + catAll);
            }
            if (0 >= catergoryPick || catergoryPick > catAll) {
                System.out.println("Please, pick category from 1 to " + catAll);
            }
        } while (0 >= catergoryPick || catergoryPick > catAll);

        for (Question output : questionList) {
            int catLength = output.getCategoryName().length();
            String catSub = output.getCategoryName().substring(0, catLength);
            String catPick = categoryID[catergoryPick - 1];
            if (catSub.equalsIgnoreCase(catPick)) {
                questionToAsk.add(output);
            }
            if (catergoryPick == categoryID.length) {
                questionToAsk = questionList;
            }
        }
        Collections.shuffle(questionToAsk);
        int points = 0;
        for (int i = 0; i < 10; i++) {
            String[] answer = questionToAsk.get(i).answers;
            String correctAnswer = answer[0];
            List<String> consolenswer = Arrays.asList(answer);
            Collections.shuffle(consolenswer);
            int answerId = 1;
            System.out.println("Category: " + questionToAsk.get(i).getCategoryName());
            System.out.println(questionToAsk.get(i).getQuestion());
            String[] shuffleAnswer = new String[answer.length];
            for (String answerToConsole : consolenswer) {
                System.out.println(answerId + " " + answerToConsole);
                shuffleAnswer[answerId - 1] = answerToConsole;
                answerId++;
            }
            int userAnswerID;
            BufferedReader inputInt = new BufferedReader(new InputStreamReader(System.in));
            final Robot enterKey = new Robot();
            TimerTask task = new TimerTask() {
                public void run() {
                    enterKey.keyPress(KeyEvent.VK_ENTER);
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 30 * 1000);
            do {
                userAnswerID = Character.getNumericValue(inputInt.read());
                if (userAnswerID == -1) {
                    break;
                }
                if (0 >= userAnswerID || userAnswerID > answer.length) {
                    System.out.println("Please, pick answer from 1 to " + answer.length);
                    userAnswerID = Character.getNumericValue(inputInt.read());
                }
            } while (0 >= userAnswerID || userAnswerID > answer.length);
            timer.cancel();
            if (userAnswerID == -1) {
                System.out.println("You didn't give the answer. The correct answer is " + correctAnswer + "\n");
            } else {
                String userAsnwer = shuffleAnswer[userAnswerID - 1];
                if (correctAnswer.equalsIgnoreCase(userAsnwer)) {
                    points++;
                    System.out.println("Correct\n");
                } else {
                    System.out.println("Wrong. Correct answer is " + correctAnswer + "\n");
                }
            }
        }
        System.out.println("Your score is " + points + " points");
    }
}