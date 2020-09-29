package com.trivia.util;

import com.google.gson.Gson;
import com.trivia.model.TriviaSearch;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class HandleAPIRequests {

    /*
    Request parameters (for custom games):
        # of questions: 1 - 25
        type of category: 24 in total, +1 for any
        difficulty: easy, medium, hard, any
        type: true/false, multiple type, any
        timed: true/false
     */

    private final Gson gson = new Gson();

    public TriviaSearch handleRandomRequest(){

        String requestURL = "https://opentdb.com/api.php?amount=10";

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
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void handleCustomRequests(ArrayList<String> req, boolean timed){
    }

}
