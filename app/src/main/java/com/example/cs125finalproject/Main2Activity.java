package com.example.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    /**
     * Buttons to the answers.
     */
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    /**
     * The question TextView.
     */
    private TextView question;
    /**
     * The playerScore TextView.
     */
    private TextView playerScore;
    /**
     * This is an internal counter (not visible to player)
     * reflects the question number the user is currently on.
     */
    private int questionNumb;
    /**
     * Current name and score of player for the player history UI.
     */
    public static String currentNameScore;
    /**
     * Previous current name and score of player for the player history UI.
     */
    public static String previousCurrentNameScore;
    /**
     * Number of times the player has played the quiz. Starts at -1 but updates to 0 on first create, which continues to update.
     */
    public static int numberTimesPlayed = 1;
    /**
     * Player stores player info: name, score.
     */
    public static Player newPlayer;
    /**
     * The score counter that updates as questions are answered is visible to player.
     */
    private int activeScore;
    private JsonObject QandA;
    private String[] questionsArray;
    private String[] correctAnswersArray;
    private String[][] incorrectAnswersArray;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);
        question = findViewById(R.id.question);
        playerScore = findViewById(R.id.playerScore);
        questionNumb = 1;
        activeScore = 0;
        Bundle bundle = getIntent().getExtras();
        newPlayer = new Player(bundle.getString("newPlayerName"),
         0, bundle.getInt("chosenTopic"));
        String display = newPlayer.getName() + " " + 0;
        playerScore.setText(display);
        numberTimesPlayed++;
        questionsArray = new String[10];
        correctAnswersArray = new String[10];
        incorrectAnswersArray = new String[10][3];
        webResponse();
    }
    /** gets the questions from the url and returns it as a Json Object. */
    private void webResponse() {
        final RequestQueue queue = Volley.newRequestQueue(this);
        String generalUrl = "";
        switch (newPlayer.getTopic()) {
            case 1:
                generalUrl = "https://opentdb.com/api.php?amount=10&category=9&type=multiple";
                break;
            case 2:
                generalUrl = "https://opentdb.com/api.php?amount=10&category=18&type=multiple";
                break;
            case 3:
                generalUrl = "https://opentdb.com/api.php?amount=10&category=11&type=multiple";
                break;
            case 4:
                generalUrl = "https://opentdb.com/api.php?amount=10&category=20&type=multiple";
                break;
            case 5:
                generalUrl = "https://opentdb.com/api.php?amount=10&category=23&type=multiple";
                break;
            default:
                break;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.GET, generalUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        QandA = new JsonParser().parse(response).getAsJsonObject();
                        putQandA();
                        updateQandA();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                question.setText("response failed");
            }
        });
        queue.add(stringRequest);
    }
    public void putQandA() {
        JsonArray responseJArray = QandA.get("results").getAsJsonArray();
        int count = 0;
        for (JsonElement part : responseJArray) {
            JsonObject partObject = (JsonObject) part;
            questionsArray[count] = partObject.get("question").getAsString();
            correctAnswersArray[count] = partObject.get("correct_answer").getAsString();
            JsonArray incorrectJArray = partObject.get("incorrect_answers").getAsJsonArray();
            int count2 = 0;
            for (JsonElement incorrect : incorrectJArray) {
                String incorrectString = incorrect.getAsString();
                incorrectAnswersArray[count][count2] = incorrectString;
                count2++;
            }
            count++;
        }
    }
    /**
     * Generates the random questions.
     */
    public void generateQandA() {
        if (questionNumb == 11) {
            if (numberTimesPlayed == 0) {
                previousCurrentNameScore = "";
            } else {
                //important that previousCurrentScore is modified before currentScore.
                //This is so the previousCurrentScore is updated with the last CurrentScore before CurrentScore
                // is modified to reflect the score now.
                previousCurrentNameScore = currentNameScore;
            }
            newPlayer.setPoints(activeScore);
            currentNameScore = newPlayer.getName() + " " + newPlayer.getPoints();
            Intent intent = new Intent(this, ScoreHistory.class);
            startActivity(intent);
            finish();
        } else {
            question.setText(StringEscapeUtils.unescapeHtml4(questionsArray[questionNumb - 1]));
            Random randomNumb = new Random();
            int high = 4;
            result = randomNumb.nextInt(high);
            switch (result) {
                case 0:
                    answer1.setText(StringEscapeUtils.unescapeHtml4(correctAnswersArray[questionNumb - 1]));
                    answer2.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][0]));
                    answer3.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][1]));
                    answer4.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][2]));
                    break;
                case 1:
                    answer2.setText(StringEscapeUtils.unescapeHtml4(correctAnswersArray[questionNumb - 1]));
                    answer1.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][0]));
                    answer3.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][1]));
                    answer4.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][2]));
                    break;
                case 2:
                    answer3.setText(StringEscapeUtils.unescapeHtml4(correctAnswersArray[questionNumb - 1]));
                    answer2.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][0]));
                    answer1.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][1]));
                    answer4.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][2]));
                    break;
                case 3:
                    answer4.setText(StringEscapeUtils.unescapeHtml4(correctAnswersArray[questionNumb - 1]));
                    answer2.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][0]));
                    answer3.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][1]));
                    answer1.setText(StringEscapeUtils.unescapeHtml4(incorrectAnswersArray[questionNumb - 1][2]));
                    break;
                default:
                    break;
            }
        }
    }
    /**
     * Update questions and answers when an answer is clicked.
     * If the number of questions answered is 10 (after user clicked 10 answers), then the app will go to players page.
     * currentScore will be set to the score of this session.
     * previousCurrentScore is the score of last session and is "0" if there was no session previously.
     */
    public void updateQandA() {
        //the correctAnswer is based on the question and answer. Code doesn't reflect that and instead has int 2 for testing purposes.
        generateQandA();
        final int correctAnswer = result;
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                if (correctAnswer == 0) {
                    updatePlayerScore(true);
                } else {
                    updatePlayerScore(false);
                }
                updateQandA();
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                if (correctAnswer == 1) {
                    updatePlayerScore(true);
                } else {
                    updatePlayerScore(false);
                }
                updateQandA();
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                if (correctAnswer == 2) {
                    updatePlayerScore(true);
                } else {
                    updatePlayerScore(false);
                }
                updateQandA();
            }
        });
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // Change the label's text
                if (correctAnswer == 3) {
                    updatePlayerScore(true);
                } else {
                    updatePlayerScore(false);
                }
                updateQandA();
            }
        });
    }
    /**
     * Updates the score part of UI after the answer is clicked by user.
     */
    public void updatePlayerScore(Boolean correct) {
        questionNumb++;
        if (correct) {
            activeScore++;
            String display = newPlayer.getName() + " " + (activeScore);
            playerScore.setText(display);
        } else {
            activeScore--;
            String display = newPlayer.getName() + " " + (activeScore);
            playerScore.setText(display);
        }
    }

}
