package no.larsvidar.badmintonscorekeeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    /***** VARIABLES *****/
    int teamAScore = 0;
    int teamBScore = 0;
    int teamASet = 0;
    int teamBSet = 0;

    /***** METHODS *****/
    //Method for updating scores and sets.
    private void updateScore() {
        //Updating Team A's score.
        TextView teamAScoreView = findViewById(R.id.team_a_score);
        teamAScoreView.setText(String.valueOf(teamAScore));
        //Updating Team A's set.
        TextView teamASetView = findViewById(R.id.team_a_set);
        teamASetView.setText(String.valueOf(teamASet));
        //Updating Team B's score.
        TextView teamBScoreView = findViewById(R.id.team_b_score);
        teamBScoreView.setText(String.valueOf(teamBScore));
        //Updating Team B's score.
        TextView teamBSetView = findViewById(R.id.team_b_set);
        teamBSetView.setText(String.valueOf(teamBSet));
    }


    /***** BUTTON EVENTS *****/
    public void setScoreA(View view) {
        TextView teamAScoreButton = findViewById(R.id.score_a_button);
        TextView teamASetButton = findViewById(R.id.set_a_button);
        teamAScore++;
        if (teamAScore >= 21 && teamAScore > teamBScore + 1) {
            teamAScoreButton.setEnabled(false);
            teamASetButton.setEnabled(true);
        } else {
            teamAScoreButton.setEnabled(true);
            teamASetButton.setEnabled(false);
        }
        updateScore();
    }

    public void setScoreB(View view) {
        TextView teamBScoreButton = findViewById(R.id.score_b_button);
        TextView teamBSetButton = findViewById(R.id.set_b_button);
        teamBScore++;
        if (teamBScore >= 21 && teamBScore > teamAScore + 1) {
            teamBScoreButton.setEnabled(false);
            teamBSetButton.setEnabled(true);
        } else {
            teamBScoreButton.setEnabled(true);
            teamBSetButton.setEnabled(false);
        }
        updateScore();
    }

    public void setSetA(View view) {
        teamASet++;
        setScoreToZero();
        setButtonsToDefault();
        updateScore();
    }

    public void setSetB(View view) {
        teamBSet++;
        setScoreToZero();
        setButtonsToDefault();
        updateScore();
    }

    public void reset(View view) {
        teamASet = 0;
        teamBSet = 0;
        setScoreToZero();
        setButtonsToDefault();
        updateScore();
    }

    private void setScoreToZero() {
        teamAScore = 0;
        teamBScore = 0;
    }

    private void setButtonsToDefault() {
        TextView teamAScoreButton = findViewById(R.id.score_a_button);
        TextView teamBScoreButton = findViewById(R.id.score_b_button);
        TextView teamASetButton = findViewById(R.id.set_a_button);
        TextView teamBSetButton = findViewById(R.id.set_b_button);
        teamAScoreButton.setEnabled(true);
        teamBScoreButton.setEnabled(true);
        teamASetButton.setEnabled(false);
        teamBSetButton.setEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*** App starts here! ***/
        //Disable Set set buttons.
        TextView teamASetButton = findViewById(R.id.set_a_button);
        teamASetButton.setEnabled(false);
        TextView teamBSetButton = findViewById(R.id.set_b_button);
        teamBSetButton.setEnabled(false);
    }
}
