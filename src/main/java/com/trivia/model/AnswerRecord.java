package com.trivia.model;

import java.util.HashMap;

public class AnswerRecord {

   HashMap<TQuestion, String> questions;
   int score;
   int right;
   int wrong;

   public AnswerRecord(){
       this.questions = new HashMap<>();
       this.score = 0;
       this.right = 0;
       this.wrong = 0;
   }

   public HashMap<TQuestion, String> getQuestions(){ return questions;}

   public int getScore(){ return score;}

   public int getRight(){ return right;}

   public int getWrong(){ return wrong;}

   public void addQuestion(TQuestion nQuestion, boolean correct){
       String str;
       if (correct){
           str = "";
           score++;
           right++;
       } else {
           str = nQuestion.getCorrect_answer();
           wrong++;
       }

       questions.put(nQuestion, str);
   }
}
