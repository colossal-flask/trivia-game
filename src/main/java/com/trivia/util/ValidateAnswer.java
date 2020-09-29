package com.trivia.util;

import com.trivia.model.TQuestion;

public class ValidateAnswer {

    private final TQuestion question;
    private final String answer;

    public ValidateAnswer(TQuestion question, String answer){
        this.question = question;
        this.answer = answer;
    }

    public Boolean validate(){
        return question.getCorrect_answer().equals(answer);
    }

}
