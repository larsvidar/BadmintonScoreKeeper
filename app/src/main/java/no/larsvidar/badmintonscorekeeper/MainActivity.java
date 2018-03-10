package no.larsvidar.badmintonscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /********** VARIABLES **********/
    //Score variables
    int teamAPoints = 0;
    int teamBPoints = 0;
    int teamAGames = 0;
    int teamBGames = 0;
    //View variables
    Button teamAPointButton;
    Button teamBPointButton;
    Button teamAGameButton;
    Button teamBGameButton;
    TextView teamAPointsView;
    TextView teamAGamesView;
    TextView teamBPointsView;
    TextView teamBGamesView;


    /********** METHODS **********/

    /**
     * Method for updating scores and sets.
     */
    private void updateScore() {
        //Updating Team A's score.
        teamAPointsView.setText(String.valueOf(teamAPoints));
        //Updating Team A's set.
        teamAGamesView.setText(String.valueOf(teamAGames));
        //Updating Team B's score.
        teamBPointsView.setText(String.valueOf(teamBPoints));
        //Updating Team B's score.
        teamBGamesView.setText(String.valueOf(teamBGames));
    }

    /**
     * Method for setting scores to zero.
     */
    private void setScoreToZero() {
        teamAPoints = 0;
        teamBPoints = 0;
    }

    /**
     * Method for setting buttons to their default state
     */
    private void setButtonsToDefault() {
        enableButton(teamAPointButton);
        enableButton(teamBPointButton);
        disableButton(teamAGameButton);
        disableButton(teamBGameButton);
    }

    private void disableButton(Button button) {
        button.setEnabled(false);
        button.setBackgroundColor(getResources().getColor(R.color.main_button_disabled_color));
        button.setTextColor(getResources().getColor(R.color.disabled_button_text));
    }

    private void enableButton(Button button) {
        button.setEnabled(true);
        button.setBackgroundColor(getResources().getColor(R.color.main_button_color));
        button.setTextColor(getResources().getColor(R.color.light_text_color));
    }


    /********** BUTTON EVENTS **********/

    /**
     * Method for increasing score for Team A
     */
    public void setScoreA(View view) {
        teamAPoints++;
        if (teamAPoints >= 21 && teamAPoints > teamBPoints + 1) {
            disableButton(teamAPointButton);
            enableButton(teamAGameButton);
        } else {
            enableButton(teamAPointButton);
            disableButton(teamAGameButton);
        }
        updateScore();
    }

    /**
     * Method for increasing score for Team B
     */
    public void setScoreB(View view) {
        teamBPoints++;
        if (teamBPoints >= 21 && teamBPoints > teamAPoints + 1) {
            disableButton(teamBPointButton);
            enableButton(teamBGameButton);
        } else {
            enableButton(teamBPointButton);
            disableButton(teamBGameButton);
        }
        updateScore();
    }

    /**
     * Method for incresing sets for Team A
     */
    public void setSetA(View view) {
        teamAGames++;
        setScoreToZero();
        setButtonsToDefault();
        updateScore();
    }

    /**
     * Method for increasing sets for Team B
     */
    public void setSetB(View view) {
        teamBGames++;
        setScoreToZero();
        setButtonsToDefault();
        updateScore();
    }

    /**
     * Method for resetting all scores.
     */
    public void reset(View view) {
        teamAGames = 0;
        teamBGames = 0;
        setScoreToZero();
        setButtonsToDefault();
        updateScore();
    }

    /**
     * onCreate function
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*** App starts here! ***/
        // Setting view variables.
        teamAPointButton = findViewById(R.id.point_a_button);
        teamBPointButton = findViewById(R.id.point_b_button);
        teamAGameButton = findViewById(R.id.game_a_button);
        teamBGameButton = findViewById(R.id.game_b_button);
        teamAPointsView = findViewById(R.id.team_a_score);
        teamAGamesView = findViewById(R.id.team_a_set);
        teamBPointsView = findViewById(R.id.team_b_score);
        teamBGamesView = findViewById(R.id.team_b_set);

        //set buttons to default states
        setButtonsToDefault();
    }
}
