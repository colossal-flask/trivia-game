package com.trivia.ui;

import com.trivia.model.AnswerRecord;
import com.trivia.model.TQuestion;
import com.trivia.model.TriviaSearch;
import com.trivia.util.CreateAnsweringScene;
import com.trivia.util.CreateReviewNodes;
import com.trivia.util.HandleAPIRequests;
import com.trivia.util.ValidateAnswer;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class GUI extends Application{
    private HandleAPIRequests APIHandler = new HandleAPIRequests();

    private Button randomQButton = new Button("10 Random Questions!");
    private Button customQButton = new Button("Configure Questions");
    private Label welcomeLabel = new Label("Let's Play Some Trivia!");

    private HBox quickQHBox = new HBox();
    private BorderPane borderPane = new BorderPane();

    private Scene mainScene;

    private AnswerRecord score;

    private Button submitButton;
    private Button restartButton;
    private Button reviewButton;
    private Button nextReviewButton;

    public void start(Stage mainStage) throws Exception{

        configureUI();

        mainStage.setScene(mainScene);
        mainStage.setTitle("A Game of Trivia!");
        mainStage.getIcons().add(new Image("question.png"));
        mainStage.show();
    }

    private void configureUI(){
        welcomeLabel.setFont(new Font("Arial", 24));
        randomQButton.addEventHandler(ActionEvent.ANY, randomQEvent);
        randomQButton.setFont(new Font("Arial", 20));

        customQButton.setFont(new Font("Arial", 20));
        customQButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CustomQuestionNodes customHandler = new CustomQuestionNodes();
                Node[] nodes = customHandler.generate();

                BorderPane.setAlignment(nodes[0], Pos.TOP_CENTER);
                BorderPane.setAlignment(nodes[1], Pos.CENTER);
                BorderPane.setAlignment(nodes[2], Pos.BOTTOM_CENTER);

                borderPane.setTop(nodes[0]);
                borderPane.setCenter(nodes[1]);
                borderPane.setBottom(nodes[2]);
            }
        });
        customQButton.setAlignment(Pos.BOTTOM_CENTER);

        quickQHBox.setAlignment(Pos.TOP_CENTER);
        quickQHBox.getChildren().add(randomQButton);

        BorderPane.setAlignment(welcomeLabel, Pos.CENTER);
        BorderPane.setMargin(welcomeLabel, new Insets(12,12,12,12));
        BorderPane.setAlignment(customQButton, Pos.CENTER);

        CustomQuestionNodes customHandler = new CustomQuestionNodes();
        Node[] nodes = customHandler.generate();

        borderPane.setTop(welcomeLabel);
        borderPane.setCenter(quickQHBox);
        borderPane.setBottom(customQButton);

        mainScene = new Scene(borderPane, 400, 500);
    }

    private final EventHandler<ActionEvent> randomQEvent = actionEvent -> {
        System.out.println("Fetching question from API.");
        TriviaSearch results = APIHandler.handleRandomRequest();
        score = new AnswerRecord();
        List<TQuestion> qList = results.getQuestions();
        CreateAnsweringScene questionGiver = new CreateAnsweringScene(qList);

        submitButton = new Button("Submit");
        submitButton.setFont(new Font("Arial", 24));

        List<VBox> VBoxList = questionGiver.creator();
        borderPane.getChildren().remove(quickQHBox);
        BorderPane.setAlignment(submitButton, Pos.BOTTOM_CENTER);
        borderPane.setBottom(submitButton);

        submitButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String answer = "";

                TQuestion question = qList.get(0);
                List<Node> radioSubList;
                if (question.getType().equals("boolean")) {
                    radioSubList = VBoxList.get(0).getChildren().subList(1, 3);
                }
                else {
                    radioSubList = VBoxList.get(0).getChildren().subList(1, 5);
                }

                for (Node radio : radioSubList) {
                    RadioButton rad = (RadioButton) radio;
                    if (rad.isSelected()) {
                        System.out.println(rad.getText());
                        answer = rad.getText();
                    }
                }

                ValidateAnswer validator = new ValidateAnswer(question, answer);
                score.addQuestion(question, validator.validate(), answer);
                VBoxList.remove(0);
                qList.remove(0);

                iterateQuestions(VBoxList);
            }
        });

        iterateQuestions(VBoxList);
    };

    public void iterateQuestions(List<VBox> vList){
        if (vList.size() == 0){
            System.out.println("Done.");
            gameFinished();
        }
        else {
            borderPane.setTop(vList.get(0));
        }
    }

    /*public void iterateQuestions(List<TQuestion> qList){
        if(qList.size() == 0){System.out.println("Done. " + score.getScore()); gameFinished();}
        else {
            TQuestion question = qList.get(0);
            qList.remove(0);

            CreateAnsweringScene nodeSwitcher = new CreateAnsweringScene(question);
            Node[] nodeList = nodeSwitcher.creator();

            VBox vbox = (VBox) nodeList[1];
            submitButton = (Button) nodeList[2];

            submitButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String answer = "";

                    for (Node radio : vbox.getChildren()){
                        RadioButton rad = (RadioButton) radio;
                        if (rad.isSelected()){
                            System.out.println(rad.getText());
                            answer = rad.getText();
                        }
                    }

                    ValidateAnswer validator = new ValidateAnswer(question, answer);
                    score.addQuestion(question, validator.validate());

                    iterateQuestions(qList);
                }
            });

            BorderPane.setAlignment(nodeList[0], Pos.CENTER);
            BorderPane.setAlignment(nodeList[2], Pos.CENTER);
            borderPane.setTop(nodeList[0]);
            borderPane.setCenter(nodeList[1]);
            borderPane.setBottom(submitButton);
        }
    }*/

    public void gameFinished(){
        restartButton = new Button("Play Again!");
        reviewButton = new Button("View Answers");

        Label endLabel = new Label("Game Over!");
        Label scoreLabel = new Label(score.getRight() + " correct, " +
                score.getWrong() + " wrong");

        endLabel.setFont(new Font("Arial", 24));
        scoreLabel.setFont(new Font("Arial", 18));

        restartButton.setFont(new Font("Arial", 24));
        reviewButton.setFont(new Font("Arial", 24));

        VBox endVBox = new VBox();
        endVBox.setPadding(new Insets(20));
        endVBox.setSpacing(20);
        endVBox.getChildren().addAll(endLabel, scoreLabel);
        endVBox.setAlignment(Pos.CENTER);

        BorderPane.setAlignment(restartButton, Pos.CENTER);
        BorderPane.setAlignment(reviewButton, Pos.BOTTOM_CENTER);

        restartButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                restartGame();
            }
        });

        reviewButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                reviewAnswers();
            }
        });

        borderPane.setTop(endVBox);
        borderPane.setCenter(restartButton);
        borderPane.setBottom(reviewButton);
    }

    public void restartGame(){
        borderPane.setTop(welcomeLabel);
        borderPane.setCenter(quickQHBox);
        borderPane.getChildren().remove(reviewButton);
    }

    public void reviewAnswers(){
        nextReviewButton = new Button("Next Question");
        nextReviewButton.setFont(new Font("Arial", 18));

        borderPane.getChildren().remove(reviewButton);
        borderPane.getChildren().remove(restartButton);
        BorderPane.setAlignment(nextReviewButton, Pos.BOTTOM_CENTER);
        borderPane.setBottom(nextReviewButton);

        CreateReviewNodes reviewer = new CreateReviewNodes(score);
        List<VBox> boxList = reviewer.creator();

        nextReviewButton.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boxList.remove(0);
                reviewIterator(boxList);
            }
        });

        borderPane.setTop(boxList.get(0));
    }

    public void reviewIterator(List<VBox> aList){
        if (aList.size() == 0){
            System.out.println("Done.");
            borderPane.getChildren().remove(nextReviewButton);
            restartGame();
        }
        else if (aList.size() == 1){
            nextReviewButton.setText("Play Again?");
            borderPane.setTop(aList.get(0));
        }
        else {
            borderPane.setTop(aList.get(0));
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}