package com.trivia.ui;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class GUI extends Application{
    private Button randomQButton = new Button("Give me a random question!");
    private Button random10QButton = new Button("Give me 10!");
    private HBox quickQHBox = new HBox();
    private BorderPane borderPane = new BorderPane();

    public void start(Stage mainStage) throws Exception{

        Label welcomeLabel = new Label("Let's Play Some Trivia!");
        welcomeLabel.setFont(new Font("Arial", 24));

        randomQButton.addEventHandler(ActionEvent.ANY, randomEvent);
        random10QButton.addEventHandler(ActionEvent.ANY, random10Event);

        quickQHBox.setSpacing(10);
        HBox.setMargin(randomQButton, new Insets(20, 20, 20, 20));
        HBox.setMargin(random10QButton, new Insets(20, 20, 20, 20));
        quickQHBox.getChildren().addAll(randomQButton, random10QButton);

        borderPane.setTop(welcomeLabel);
        borderPane.setCenter(quickQHBox);

        Scene mainScene = new Scene(borderPane, 400, 400);

        mainStage.setScene(mainScene);
        mainStage.setTitle("A Game of Trivia!");
        mainStage.show();
    }

   EventHandler<ActionEvent> randomEvent = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){

            Label questionLabel = new Label();
            TextField answerField = new TextField();

            String request = "https://opentdb.com/api.php?amount=1";
            try {
                URL triviaRequest = new URL(request);
                URLConnection uRequest = triviaRequest.openConnection();
                uRequest.connect();

                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) uRequest.getContent()));
                JsonObject rootObj = root.getAsJsonObject();

                JsonArray results = (JsonArray) rootObj.get("results");
                JsonObject resultObj = (JsonObject) results.get(0);

                questionLabel.setWrapText(true);
                questionLabel.setText(resultObj.get("question").getAsString());

                borderPane.setTop(questionLabel);

            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("You clicked on random question button!");
        }
    };

    EventHandler<ActionEvent> random10Event = new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent e){
            System.out.println("You clicked on 10 random questions button!");
        }
    };

    public static void main(String[] args){
        launch(args);
    }
}
