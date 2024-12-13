package edu.utsa.cs3443.quizfreaks.model;

public class Question {
    /**
     * this is the edu.utsa.cs3443.quizfreaks.model.Question class- for question objects
     * @author Raymond Harmon
     */



private String question;
private String category;
private int difficulty;
private String answer;

    /**
     * The constructor for the class
     * @param question- the wording of the question being asked
     * @param cat- the category the question is in
     * @param diff- the difficulty of the question
     * @param ans - the answer to the question
     */
    public Question(String question,String ans, String cat, int diff) {
        this.question = question;
        this.answer=ans;
        this.category=cat;
        this.difficulty=diff;
    }

    /**
     * returns the wording of the question
     * @return the question string
     */
    public String getQuestion() {
        return question;
    }

    /**
     * sets the wording of the question
     * @param question- a String that holds the question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * returns the questions category
     * @return the category the question belongs to
     */
    public String getCategory() {
        return category;
    }

    /**
     * sets the question category
     * @param category- input parameter for category the question belongs to
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * returns the difficulty of the question
     * @return the difficulty level of the question
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * sets the difficulty of the question
     * @param difficulty input parameter for the level of difficulty
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * returns the difficulty of the question
     * @return the difficulty level of the question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * sets the answer of the question
     * @param answer input parameter for the questions answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
