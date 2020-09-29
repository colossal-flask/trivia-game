package com.trivia.model;

import java.util.HashMap;

public class AnswerRecord {

   HashMap<TQuestion, String> questions;
   int score;

   public AnswerRecord(){
       this.questions = new HashMap<>();
       this.score = 0;
   }

   public HashMap<TQuestion, String> getQuestions(){ return questions;}

   public int getScore(){ return score;}

   public void addQuestion(TQuestion nQuestion, boolean correct){
       String str;
       if (correct){
           str = "";
           score++;
       } else {
           str = nQuestion.getCorrect_answer();
       }

       questions.put(nQuestion, str);
   }
}
