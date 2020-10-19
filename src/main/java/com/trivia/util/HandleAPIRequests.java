package com.trivia.util;

import com.google.gson.Gson;
import com.trivia.model.TriviaSearch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HandleAPIRequests {

    /*
    Request parameters (for custom games):
        # of questions: 1 - 25
        type of category: 24 in total, +1 for any
        difficulty: easy, medium, hard, any
        type: true/false, multiple type, any
     */

    private final Gson gson = new Gson();
    private String requestURL;

    public TriviaSearch handleRandomRequest() {
        requestURL = "https://opentdb.com/api.php?amount=10";
        return handleRequest();
    }

    public TriviaSearch handleCustomRequests(ArrayList<String> req) {

        List<String> categoryOptions = Arrays.asList(
                "Any", "General Knowledge", "Books", "Film", "Music", "Musicals & Theatres",
                "Television", "Video Games", "Board Games", "Science & Nature", "Computer Science",
                "Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Art",
                "Celebrities", "Animals", "Vehicles", "Comics", "Gadgets", "Anime & Manga",
                "Cartoons & Animations"
        );

        requestURL = "https://opentdb.com/api.php?";

        requestURL = requestURL + "amount=" + req.get(0);

        if (!req.get(1).equals("Any")) {
            String categoryNum = Integer.toString(8 + categoryOptions.indexOf(req.get(1)));
            requestURL = requestURL + "&category=" + categoryNum;
        }

        if (!req.get(2).equals("Any")) {
            requestURL = requestURL + "&difficulty=" + req.get(2).toLowerCase();
        }

        if (!req.get(3).equals("Any")) {
            if (req.get(3).equals("Multiple Choice")) {
                requestURL = requestURL + "&type=multiple";
            } else if (req.get(3).equals("True/False")) {
                requestURL = requestURL + "&type=boolean";
            }
        }

        return handleRequest();
    }

    public TriviaSearch handleRequest() {

        try {
            URL triviaRequest = new URL(requestURL);

            HttpsURLConnection connection = (HttpsURLConnection) triviaRequest.openConnection();
            connection.connect();

            InputStream input = connection.getInputStream();
            int responseCode = connection.getResponseCode();

            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(inputReader);
            TriviaSearch results = gson.fromJson(reader, TriviaSearch.class);

            return results;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
