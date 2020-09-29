package com.trivia.model;

import java.util.List;

public class TQuestion {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private List<String>  incorrect_answers;

    public String getCategory(){return category;}

    public String getType(){return type;}

    public String getDifficulty(){return difficulty;}

    public String getQuestion(){return question;}

    public String getCorrect_answer(){return correct_answer;}

    public List<String> getIncorrect_answers(){return incorrect_answers;}

    public String toString(){
        return category + " " + type + " " + difficulty + " " +
                question + " " + correct_answer + " " + incorrect_answers;
    }
}
