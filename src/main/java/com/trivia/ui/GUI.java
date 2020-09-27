package com.trivia.ui;

import com.trivia.model.TriviaSearch;
import com.trivia.util.CreateAnsweringScene;
import com.trivia.util.HandleAPIRequests;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI extends Application{
    private HandleAPIRequests APIHandler = new HandleAPIRequests();

    private Button randomQButton = new Button("Give me a random question!");
    private Label welcomeLabel = new Label("Let's Play Some Trivia!");

    private HBox quickQHBox = new HBox();
    private BorderPane borderPane = new BorderPane();

    private Scene mainScene;

    public void start(Stage mainStage) throws Exception{

        configureUI();

        mainStage.setScene(mainScene);
        mainStage.setTitle("A Game of Trivia!");
        mainStage.show();
    }

    private void configureUI(){
        welcomeLabel.setFont(new Font("Arial", 24));
        randomQButton.addEventHandler(ActionEvent.ANY, randomQEvent);

        quickQHBox.setAlignment(Pos.TOP_CENTER);
        quickQHBox.getChildren().add(randomQButton);

        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        BorderPane.setMargin(welcomeLabel, new Insets(12,12,12,12));

        borderPane.setTop(welcomeLabel);
        borderPane.setCenter(quickQHBox);

        mainScene = new Scene(borderPane, 400, 400);
        //temporary comment
    }

    private final EventHandler<ActionEvent> randomQEvent = actionEvent -> {
        System.out.println("Fetching question from API.");
        TriviaSearch results = APIHandler.handleRandomRequest();

        CreateAnsweringScene nodeSwitcher = new CreateAnsweringScene(results.getQuestions().get(0));
        Node[] nodeList = nodeSwitcher.creator();

        BorderPane.setAlignment(nodeList[0], Pos.CENTER);
        BorderPane.setAlignment(nodeList[2], Pos.CENTER);
        borderPane.setTop(nodeList[0]);
        borderPane.setCenter(nodeList[1]);
        borderPane.setBottom(nodeList[2]);
    };

    public static void main(String[] args){
        launch(args);
    }
}