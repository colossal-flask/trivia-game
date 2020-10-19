package com.trivia.util;

import com.trivia.model.TQuestion;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.springframework.web.util.HtmlUtils;

import java.util.ArrayList;
import java.util.Random;

import java.util.List;

public class CreateAnsweringScene {

    private final Random rand;
    private final List<TQuestion> qList;

    public CreateAnsweringScene(List<TQuestion> qList){
        this.qList = qList;
        rand = new Random();
    }

    public List<VBox> creator(){

        List<VBox> boxList = new ArrayList<>();

        int i = 1;

        for (TQuestion question : qList){
            VBox vbox = new VBox();
            vbox.setPadding(new Insets(20));
            vbox.setSpacing(25);

            String questionStr = question.getQuestion();
            questionStr = HtmlUtils.htmlUnescape(questionStr);
            Label questionLabel = new Label("Question " + i + ":\n" + questionStr);
            questionLabel.setAlignment(Pos.TOP_CENTER);
            questionLabel.setWrapText(true);
            questionLabel.setPrefWidth(300);
            questionLabel.setPrefHeight(100);
            questionLabel.setFont(new Font("Arial", 18));

            vbox.getChildren().add(questionLabel);

            if(question.getType().equals("boolean")){
                RadioButton T = new RadioButton("True");
                RadioButton F = new RadioButton("False");

                T.setFont(new Font("Arial", 18));
                F.setFont(new Font("Arial", 18));

                final ToggleGroup group = new ToggleGroup();

                T.setToggleGroup(group);
                F.setToggleGroup(group);

                T.setSelected(true);

                vbox.getChildren().addAll(T, F);
            }
            else {
                int correctAns = rand.nextInt(4);
                RadioButton A1 = new RadioButton();
                RadioButton A2 = new RadioButton();
                RadioButton A3 = new RadioButton();
                RadioButton A4 = new RadioButton();

                String correct = HtmlUtils.htmlUnescape(question.getCorrect_answer());
                String incorrect1 = HtmlUtils.htmlUnescape(question.getIncorrect_answers().get(0));
                String incorrect2 = HtmlUtils.htmlUnescape(question.getIncorrect_answers().get(1));
                String incorrect3 = HtmlUtils.htmlUnescape(question.getIncorrect_answers().get(2));

                switch (correctAns){
                    case 0:
                        A1 = new RadioButton(correct);
                        A2 = new RadioButton(incorrect1);
                        A3 = new RadioButton(incorrect2);
                        A4 = new RadioButton(incorrect3);
                        break;
                    case 1:
                        A1 = new RadioButton(incorrect1);
                        A2 = new RadioButton(correct);
                        A3 = new RadioButton(incorrect2);
                        A4 = new RadioButton(incorrect3);
                        break;
                    case 2:
                        A1 = new RadioButton(incorrect1);
                        A2 = new RadioButton(incorrect2);
                        A3 = new RadioButton(correct);
                        A4 = new RadioButton(incorrect3);
                        break;
                    case 3:
                        A1 = new RadioButton(incorrect1);
                        A2 = new RadioButton(incorrect2);
                        A3 = new RadioButton(incorrect3);
                        A4 = new RadioButton(correct);
                        break;
                }

                A1.setFont(new Font("Arial", 18));
                A2.setFont(new Font("Arial", 18));
                A3.setFont(new Font("Arial", 18));
                A4.setFont(new Font("Arial", 18));

                final ToggleGroup group = new ToggleGroup();
                A1.setToggleGroup(group);
                A2.setToggleGroup(group);
                A3.setToggleGroup(group);
                A4.setToggleGroup(group);

                A1.setAlignment(Pos.TOP_CENTER);
                A2.setAlignment(Pos.TOP_CENTER);
                A3.setAlignment(Pos.TOP_CENTER);
                A4.setAlignment(Pos.TOP_CENTER);

                A1.setSelected(true);

                vbox.getChildren().addAll(A1, A2, A3, A4);
            }
            i++;
            boxList.add(vbox);
        }
        return boxList;
    }
}
