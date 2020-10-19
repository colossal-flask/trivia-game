package com.trivia.model;

import java.util.LinkedHashMap;

public class AnswerRecord {

   LinkedHashMap<TQuestion, String> questions;
   int score;
   int right;
   int wrong;

   public AnswerRecord(){
       this.questions = new LinkedHashMap<>();
       this.score = 0;
       this.right = 0;
       this.wrong = 0;
   }

   public LinkedHashMap<TQuestion, String> getQuestions(){ return questions;}

   public int getScore(){ return score;}

   public int getRight(){ return right;}

   public int getWrong(){ return wrong;}

   public void addQuestion(TQuestion nQuestion, boolean correct, String userAnswer){
       String str;
       if (correct){
           str = "";
           score++;
           right++;
       } else {
           str = userAnswer;
           wrong++;
       }

       questions.put(nQuestion, str);
   }
}
