package com.trivia.util;

import com.trivia.model.AnswerRecord;
import com.trivia.model.TQuestion;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.springframework.web.util.HtmlUtils;

import java.util.*;

public class CreateReviewNodes {

    private AnswerRecord answers;

    public CreateReviewNodes(AnswerRecord answers){
        this.answers = answers;
    }

    public List<VBox> creator(){

        List<VBox> boxList = new ArrayList<>();
        LinkedHashMap<TQuestion, String> ans = answers.getQuestions();

        int i = 1;

        for(Map.Entry<TQuestion, String> entry : ans.entrySet()){
            String questionTitle = entry.getKey().getQuestion();
            questionTitle = HtmlUtils.htmlUnescape(questionTitle);
            String questionType = entry.getKey().getType();
            String correctAnswer = entry.getKey().getCorrect_answer();
            String userAnswer = entry.getValue();
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

            BackgroundFill correctFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY);
            BackgroundFill incorrectFill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY);

            if (questionType.equals("boolean")){
                Label T = new Label("True");
                Label F = new Label("False");

                T.setFont(new Font("Arial", 18));
                F.setFont(new Font("Arial", 18));

                if (correctAnswer.equals("True")){
                    T.setTextFill(Color.GREEN);
                    F.setTextFill(Color.RED);
                    if (userCorrect){
                        T.setTextFill(Color.WHITE);
                        T.setBackground(new Background(correctFill));
                    }
                    else {
                        F.setTextFill(Color.WHITE);
                        F.setBackground(new Background(incorrectFill));
                    }
                }
                else {
                    T.setTextFill(Color.RED);
                    F.setTextFill(Color.GREEN);
                    if (userCorrect){
                        F.setBackground(new Background(correctFill));
                        F.setTextFill(Color.WHITE);
                    }
                    else {
                        T.setBackground(new Background(incorrectFill));
                        T.setTextFill(Color.WHITE);
                    }
                }

                vbox.getChildren().addAll(T, F);
            }
            else if (questionType.equals("multiple")) {
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

                List<Label> incorrectLabels = new ArrayList<>();
                incorrectLabels.add(incorrect1);
                incorrectLabels.add(incorrect2);
                incorrectLabels.add(incorrect3);

                if (userCorrect){
                    correct.setBackground(new Background(correctFill));
                    correct.setTextFill(Color.WHITE);
                }
                else {
                    for (Label label : incorrectLabels){
                        if (label.getText().equals(userAnswer)){
                            label.setBackground(new Background(incorrectFill));
                            label.setTextFill(Color.WHITE);
                        }
                    }
                }

                vbox.getChildren().addAll(correct, incorrect1, incorrect2, incorrect3);
            }
            boxList.add(vbox);
            i++;
        }
        return boxList;
    }
}
