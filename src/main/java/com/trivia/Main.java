package com.trivia;

import com.trivia.ui.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) throws Exception{
        Application.launch(GUI.class, args);
    }
       /* final GUI triviaGUI = new GUI();
        final Main app = new Main(triviaGUI);
        app.init();
    }

    private Main(final GUI triviaGUI) throws Exception{
        this.triviaGUI = triviaGUI;
        triviaGUI.start(new Stage());
    }

    private void init(){
        triviaGUI.init();
    }*/
}
