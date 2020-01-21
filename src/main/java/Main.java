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
        List<String> categoryList = questions.getCategoryList();
        categoryList.add("ALL");

        int catID = 0;
        for (String categoryName : categoryList) {
            System.out.println(catID + 1 + " " + categoryName);
            catID++;
        }
        int catAll = categoryList.size();
        System.out.println("Please, pick catID");

        int catergoryPick = categoryPick(catAll);
        List<Question> questionToAsk = creatQuestionToAskList(questionList, categoryList, catergoryPick);
        Collections.shuffle(questionToAsk);
        question(questionToAsk);
    }

    private static int categoryPick(int catAll) {
        int categoryPick = 0;
        Scanner inputCategory = new Scanner(System.in);
        String catergoryPickString;
        do {
            catergoryPickString = inputCategory.nextLine();
            if (NumberUtils.isDigits(catergoryPickString)) {
                categoryPick = Integer.parseInt(catergoryPickString);
                if (0 >= categoryPick || categoryPick > catAll) {
                    System.out.println("Please, pick category from 1 to " + catAll);
                }
            } else {
                System.out.println("Input is not a number, pick category from 1 to " + catAll);
            }
        } while (0 >= categoryPick || categoryPick > catAll);
        return categoryPick;
    }

    private static List<Question> creatQuestionToAskList(List<Question> questionList, List<String> categoryList, int catergoryPick) {
        List<Question> questionToAsk = new ArrayList<>();
        for (Question output : questionList) {
            String catSub = output.getCategoryName();
            String catPick = categoryList.get(catergoryPick - 1);
            if (catSub.equalsIgnoreCase(catPick)) {
                questionToAsk.add(output);
            }
            if (catergoryPick == categoryList.size()) {
                questionToAsk = questionList;
            }
        }
        return questionToAsk;
    }

    private static void question(List<Question> questionToAsk) throws IOException, AWTException {
        int points = 0;
        for (int i = 0; i < 10; i++) {
            String[] answer = questionToAsk.get(i).answers;
            String correctAnswer = answer[0];
            List<String> consolAnswer = Arrays.asList(answer);
            Collections.shuffle(consolAnswer);
            int answerId = 1;
            System.out.println("Category: " + questionToAsk.get(i).getCategoryName());
            System.out.println(questionToAsk.get(i).getQuestion());
            String[] shuffleAnswer = new String[answer.length];
            for (String answerToConsole : consolAnswer) {
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