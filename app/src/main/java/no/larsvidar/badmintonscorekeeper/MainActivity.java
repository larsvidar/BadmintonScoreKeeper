package no.larsvidar.badmintonscorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /********** VARIABLES **********/
    //Score variables
    private int teamAPoints = 0;    //Points for Team A
    private int teamBPoints = 0;    //Points for Team B
    private int teamAGames = 0;     //Games for Team A
    private int teamBGames = 0;     //Games for Team B
    //View elements
    private Button teamAPointButton;    //Button for giving points to Team A
    private Button teamBPointButton;    //Button for giving points to Team B
    private Button teamAGameButton;     //Button for giving games to Team A
    private Button teamBGameButton;     //Button for giving games to Team B
    private TextView teamAPointsView;   //TextView for showing Team A's points
    private TextView teamAGamesView;    //TextView for showing Team A's games
    private TextView teamBPointsView;   //TextView for showing Team B's points
    private TextView teamBGamesView;    //TextView for showing Team B's games
    private TextView gameWonMessage;    //TextView for showing Game Won message
    private TextView matchWonMessage;   //TextView for showing Match Won message


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
        //Enabling point-buttons.
        enableButton(teamAPointButton);
        enableButton(teamBPointButton);
        //Disabling game-buttons.
        disableButton(teamAGameButton);
        disableButton(teamBGameButton);
    }

    /**
     * Method for disabling buttons.
     *
     * @param button to be disabled.
     */
    private void disableButton(Button button) {
        button.setEnabled(false);
        button.setBackgroundColor(getResources().getColor(R.color.main_button_disabled_color));
        button.setTextColor(getResources().getColor(R.color.disabled_button_text));
    }

    /**
     * Method for enabling buttons.
     *
     * @param button to be enabled.
     */
    private void enableButton(Button button) {
        button.setEnabled(true);
        button.setBackgroundColor(getResources().getColor(R.color.main_button_color));
        button.setTextColor(getResources().getColor(R.color.light_text_color));
    }

    /**
     * Method for showing Game Won message.
     *
     * @param team that won the game.
     */
    private void showGameWinner(String team) {
        //Removing point-buttons while message is showing.
        teamAPointButton.setVisibility(View.GONE);
        teamBPointButton.setVisibility(View.GONE);
        //Checking which team won.
        if (team.equals("A")) {
            gameWonMessage.setText(R.string.team_a_won_game);
        } else {
            gameWonMessage.setText(R.string.team_b_won_game);
        }
        //Making the message visible.
        gameWonMessage.setVisibility(View.VISIBLE);
    }

    /**
     * Method for displaying Match Won message.
     *
     * @param team that won the match.
     */
    private void showMatchWinner(String team) {
        //Hiding all buttons.
        teamAPointButton.setVisibility(View.GONE);
        teamBPointButton.setVisibility(View.GONE);
        teamAGameButton.setVisibility(View.GONE);
        teamBGameButton.setVisibility(View.GONE);
        //Checking which team won.
        if (team.equals("A")) {
            matchWonMessage.setText(R.string.team_a_won_match);
        } else {
            matchWonMessage.setText(R.string.team_b_won_match);
        }
        //Making the message visible
        matchWonMessage.setVisibility(View.VISIBLE);
    }

    /**
     * Method for hiding game or match winning message.
     *
     * @param message to be hidden.
     */
    private void hideWinner(TextView message) {
        //Making all buttons visible again.
        teamAPointButton.setVisibility(View.VISIBLE);
        teamBPointButton.setVisibility(View.VISIBLE);
        teamAGameButton.setVisibility(View.VISIBLE);
        teamBGameButton.setVisibility(View.VISIBLE);
        //Hiding whatever message was showing.
        message.setVisibility(View.GONE);
    }

    /**
     * Method for saving state when screen is rotated.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("savedTeamAPoints", teamAPoints);
        outState.putInt("savedTeamBPoints", teamBPoints);
        outState.putInt("savedTeamAGames", teamAGames);
        outState.putInt("savedTeamBGames", teamBGames);

        super.onSaveInstanceState(outState);
    }


    /********** BUTTON EVENTS **********/

    /**
     * Method for increasing score for Team A
     */
    public void setScoreA(View view) {
        //Adding points to team A
        teamAPoints++;
        //Checking weather Team A has 21 points or higher with a two point lead.
        if (teamAPoints >= 21 && teamAPoints > teamBPoints + 1) {
            disableButton(teamAPointButton);
            enableButton(teamAGameButton);
            showGameWinner("A");
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
        //Adding points to team B.
        teamBPoints++;
        //Checking weather Team B has 21 points or higher with a two point lead.
        if (teamBPoints >= 21 && teamBPoints > teamAPoints + 1) {
            disableButton(teamBPointButton);
            enableButton(teamBGameButton);
            showGameWinner("B");
        } else {
            enableButton(teamBPointButton);
            disableButton(teamBGameButton);
        }
        updateScore();
    }

    /**
     * Method for increasing sets for Team A
     */
    public void setGameA(View view) {
        //Adding a game to Team A.
        teamAGames++;
        //Resetting points to zero for both teams.
        setScoreToZero();
        setButtonsToDefault();
        hideWinner(gameWonMessage);
        updateScore();
        //Checking weather Team A has enough games to win the match.
        if (teamAGames > 1) {
            showMatchWinner("A");
        }
    }

    /**
     * Method for increasing sets for Team B
     */
    public void setGameB(View view) {
        //Adding a game to team B.
        teamBGames++;
        //Resetting points to zero for both teams.
        setScoreToZero();
        setButtonsToDefault();
        hideWinner(gameWonMessage);
        updateScore();
        //Checking weather Team B has enough games to win the match.
        if (teamBGames > 1) {
            showMatchWinner("B");
        }
    }

    /**
     * Method for resetting all scores.
     */
    public void reset(View view) {
        teamAGames = 0;
        teamBGames = 0;
        setScoreToZero();
        setButtonsToDefault();
        hideWinner(matchWonMessage);
        hideWinner(gameWonMessage);
        updateScore();
    }


    /**
     * onCreate method
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
        gameWonMessage = findViewById(R.id.game_won_message);
        matchWonMessage = findViewById(R.id.match_won_message);

        //set buttons to default states
        setButtonsToDefault();

        //Get saved state after screen rotation
        if (savedInstanceState != null) {
            //Getting teams games - 1.
            teamAGames = savedInstanceState.getInt("savedTeamAGames") - 1;
            teamBGames = savedInstanceState.getInt("savedTeamBGames") - 1;
            //Giving teams one game.
            teamAGameButton.performClick();
            teamBGameButton.performClick();
            //Getting teams points - 1.
            teamAPoints = savedInstanceState.getInt("savedTeamAPoints") - 1;
            teamBPoints = savedInstanceState.getInt("savedTeamBPoints") - 1;
            //Giving each team one point.
            teamAPointButton.performClick();
            teamBPointButton.performClick();
        }
    }
}
