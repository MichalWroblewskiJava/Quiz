public class Question {
    private String question;
    String[] answers;
    private  String categoryName;


    public Question(String question, String[] answers, String category) {
        this.question = question;
        this.answers = answers;
        this.categoryName = category;
    }

    String getCategoryName() {
        return categoryName;
    }



    String getQuestion() {
        return question;
    }


    String[] getAnswers() {
        return answers;
    }


}