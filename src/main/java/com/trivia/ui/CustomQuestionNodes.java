package com.trivia.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CustomQuestionNodes {

    private ComboBox questionNum;
    private ComboBox category;
    private ComboBox difficulty;
    private ComboBox type;

    public CustomQuestionNodes(){

        ObservableList<String> numberOptions = FXCollections.observableArrayList(
                "1", "5", "10", "15", "20", "25"
        );

        ObservableList<String> categoryOptions = FXCollections.observableArrayList(
                "Any", "General Knowledge", "Books", "Film", "Music", "Musicals & Theatres",
                "Television", "Video Games", "Board Games", "Science & Nature", "Computer Science",
                "Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Art",
                "Celebrities", "Animals", "Vehicles", "Comics", "Gadgets", "Anime & Manga",
                "Cartoons & Animations"
        );

        ObservableList<String> difficultyOptions = FXCollections.observableArrayList(
                "Any", "Easy", "Medium", "Hard"
        );

        ObservableList<String> typeOptions = FXCollections.observableArrayList(
                "Any", "True/False", "Multiple Choice"
        );

        this.category = new ComboBox(categoryOptions);
        this.difficulty = new ComboBox(difficultyOptions);
        this.type = new ComboBox(typeOptions);

        this.questionNum = new ComboBox(numberOptions);
    }

    public Node[] generate(){
        Node[] nodeList = new Node[3];

        Label titleLabel = new Label("Customize Your Questions");
        titleLabel.setFont(new Font("Arial", 20));
        titleLabel.setAlignment(Pos.CENTER);

        questionNum.setMaxWidth(200);
        category.setMaxWidth(200);
        difficulty.setMaxWidth(200);
        type.setMaxWidth(200);

        VBox questionNumBox = new VBox();
        VBox categoryBox = new VBox();
        VBox difficultyBox = new VBox();
        VBox typeBox = new VBox();

        Label questionNumLabel = new Label("Number of Questions: ");
        Label categoryLabel = new Label("Category: ");
        Label difficultyLabel = new Label("Difficulty: ");
        Label typeLabel = new Label("Question Type: ");

        questionNumLabel.setFont(new Font("Arial", 18));
        categoryLabel.setFont(new Font("Arial", 18));
        difficultyLabel.setFont(new Font("Arial", 18));
        typeLabel.setFont(new Font("Arial", 18));

        questionNumBox.setAlignment(Pos.CENTER);
        categoryBox.setAlignment(Pos.CENTER);
        difficultyBox.setAlignment(Pos.CENTER);
        typeBox.setAlignment(Pos.CENTER);

        questionNumBox.getChildren().addAll(questionNumLabel, new Label(" "), questionNum);
        categoryBox.getChildren().addAll(categoryLabel, new Label(" "), category);
        difficultyBox.getChildren().addAll(difficultyLabel, new Label(" "), difficulty);
        typeBox.getChildren().addAll(typeLabel, new Label(" "), type);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        vbox.setSpacing(40);
        vbox.getChildren().addAll(questionNumBox, categoryBox, difficultyBox, typeBox);

        Button submitButton = new Button("Submit");
        submitButton.setFont(new Font("Arial", 20));
        submitButton.setAlignment(Pos.BOTTOM_CENTER);

        nodeList[0] = titleLabel;
        nodeList[1] = vbox;
        nodeList[2] = submitButton;

        return nodeList;
    }

}
