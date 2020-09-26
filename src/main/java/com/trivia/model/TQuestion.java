package com.trivia.model;

public class TQuestion {
    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;

    public String getCategory(){return category;}

    public String getType(){return type;}

    public String getDifficulty(){return difficulty;}

    public String getQuestion(){return question;}

    public String getCorrect_answer(){return correct_answer;}

    public String toString(){
        return category + " " + type + " " + difficulty + " " +
                question + " " + correct_answer;
    }
}
