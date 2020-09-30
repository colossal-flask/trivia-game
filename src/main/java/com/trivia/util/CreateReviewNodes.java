package com.trivia.util;

import com.trivia.model.AnswerRecord;
import com.trivia.model.TQuestion;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateReviewNodes {

    private AnswerRecord answers;

    public CreateReviewNodes(AnswerRecord answers){
        this.answers = answers;
    }

    public List<VBox> creator(){

        List<VBox> boxList = new ArrayList<>();
        HashMap<TQuestion, String> ans = answers.getQuestions();

        int i = 1;

        for(Map.Entry<TQuestion, String> entry : ans.entrySet()){
            String questionTitle = entry.getKey().getQuestion();
            questionTitle = HtmlUtils.htmlUnescape(questionTitle);
            String questionType = entry.getKey().getType();
            String correctAnswer = entry.getKey().getCorrect_answer();
            List<String> wrongAnswers = entry.getKey().getIncorrect_answers();

            boolean userCorrect;
            userCorrect = entry.getValue().equals("");

            VBox vbox = new VBox();
            vbox.setPadding(new Insets(20));
            vbox.setSpacing(30);

            Label questionLabel = new Label("Question " + i + ":\n" +
                    questionTitle);

            questionLabel.setWrapText(true);
            questionLabel.setPrefWidth(300);
            questionLabel.setPrefHeight(100);
            questionLabel.setFont(new Font("Arial", 18));

            vbox.getChildren().add(questionLabel);

            if (questionType.equals("Boolean")){
                Label T = new Label("True");
                Label F = new Label("False");

                T.setFont(new Font("Arial", 18));
                F.setFont(new Font("Arial", 18));

                if (correctAnswer.equals("True")){
                    T.setTextFill(Color.GREEN);
                    F.setTextFill(Color.RED);
                }
                else {
                    T.setTextFill(Color.RED);
                    F.setTextFill(Color.GREEN);
                }

                vbox.getChildren().addAll(T, F);
            }
            else {
                Label correct = new Label(correctAnswer);
                Label incorrect1 = new Label(wrongAnswers.get(0));
                Label incorrect2 = new Label(wrongAnswers.get(1));
                Label incorrect3 = new Label(wrongAnswers.get(2));

                correct.setFont(new Font("Arial", 18));
                incorrect1.setFont(new Font("Arial", 18));
                incorrect2.setFont(new Font("Arial", 18));
                incorrect3.setFont(new Font("Arial", 18));

                correct.setTextFill(Color.GREEN);
                incorrect1.setTextFill(Color.RED);
                incorrect2.setTextFill(Color.RED);
                incorrect3.setTextFill(Color.RED);

                vbox.getChildren().addAll(correct, incorrect1, incorrect2, incorrect3);
            }
            boxList.add(vbox);
        }
        return boxList;
    }
}
