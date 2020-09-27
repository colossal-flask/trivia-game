package com.trivia.util;

import com.trivia.model.TQuestion;
import com.trivia.model.TriviaSearch;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.List;

public class CreateAnsweringScene {

    private TriviaSearch questionList;

    public CreateAnsweringScene(TriviaSearch search){
        this.questionList = search;
    }

    public Node[] creator(){
        Node[] nodeList = new Node[3];

        VBox vbox = new VBox();
        VBox labelVBox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(20);
        labelVBox.setPadding(new Insets(10));

        Button submitButton = new Button("Submit");
        submitButton.setFont(new Font("Arial", 24));

        List<TQuestion> questions = questionList.getQuestions();

        String questionNumStr = "Question 1:";
        String questionStr = questions.get(0).getQuestion();

        Label questionNum = new Label(questionNumStr);
        Label questionTitle = new Label(questionStr);

        questionTitle.setWrapText(true);
        questionTitle.setPrefWidth(300);
        questionTitle.setPrefHeight(100);
        questionTitle.setFont(new Font("Arial", 18));
        questionNum.setFont(new Font("Arial", 18));

        if (questions.get(0).getType().equals("boolean")){
            RadioButton T = new RadioButton("True");
            RadioButton F = new RadioButton("False");

            T.setFont(new Font("Arial", 18));
            F.setFont(new Font("Arial", 18));

            vbox.getChildren().addAll(T, F);
        }

        labelVBox.getChildren().addAll(questionNum, questionTitle);

        nodeList[0] = labelVBox;
        nodeList[1] = vbox;
        nodeList[2] = submitButton;

        return nodeList;
    }

}
