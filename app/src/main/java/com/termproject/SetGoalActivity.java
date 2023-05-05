package com.termproject;

import static java.lang.Float.parseFloat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetGoalActivity extends AppCompatActivity {

    private AuthenticatedUserManager mAuthManager = AuthenticatedUserManager.getInstance();
    private WeightsDatabase mWeightsDatabase;
    private Toolbar mToolbar;
    private EditText mNewGoalInput;
    private Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        // singleton
        mWeightsDatabase = WeightsDatabase.getInstance(getApplicationContext());

        // initialize toolbar
        mToolbar = findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);

        // initialize edit text
        mNewGoalInput = findViewById(R.id.newGoalDecimal);
        float currentGoal = mWeightsDatabase.getGoal(mAuthManager.getUser().getUsername());
        if (currentGoal != 0.0){
            mNewGoalInput.setText(String.valueOf(currentGoal));
        }

        // initialize button
        mSubmitButton = findViewById(R.id.submitGoalBtn);
        mSubmitButton.setOnClickListener(v -> handleSubmitButton());
    }

    /**
     * OnClick Listener for the Submit button.
     **/
    private void handleSubmitButton(){
        // acquire weight value from input
        String inputValue = String.valueOf(mNewGoalInput.getText());
        if (inputValue.equals("")){
            // warn user that they must enter a value
            String text = getString(R.string.empty_input);
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            float newGoal = parseFloat(inputValue);

            // call database set
            mWeightsDatabase.setGoal(newGoal, mAuthManager.getUser().getUsername());

            // redirect to the profile screen
            Intent intent = new Intent(this, ProfileActivity.class);
            finish();
            SetGoalActivity.this.startActivity(intent);
        }
    }
}