package com.termproject;

import static java.lang.Float.parseFloat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddWeightActivity extends AppCompatActivity {

    private final AuthenticatedUserManager mAuthManager = AuthenticatedUserManager.getInstance();
    private final NotificationsManager mNotificationsManager = NotificationsManager.getInstance();
    private WeightsDatabase mWeightsDatabase;
    private Toolbar mToolbar;
    private DatePicker mDatePicker;
    private EditText mWeightInput;
    private Button mSubmitButton;
    private Button mDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weight);

        // initialize date picker
        mDatePicker = findViewById(R.id.datePicker);

        // initialize edit text
        mWeightInput = findViewById(R.id.newWeightDecimal);

        // load date and weight from extras
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        if (intent.hasExtra("date")){
            // parse dates
            String[] str = extras.getString("date").split("-");
            int year = Integer.parseInt(str[0]);
            int month = Integer.parseInt(str[1]);
            int day = Integer.parseInt(str[2]);
            // set date in picker
            mDatePicker.init(year, month-1, day, null);
        }
        if (intent.hasExtra("weight")) {
            // set weight value text
            mWeightInput.setText(String.valueOf(extras.getFloat("weight")));
        }

        // singleton
        mWeightsDatabase = WeightsDatabase.getInstance(getApplicationContext());

        // initialize toolbar
        mToolbar = findViewById(R.id.toolBar);
        setSupportActionBar(mToolbar);

        // initialize buttons
        mSubmitButton = findViewById(R.id.submitWeightBtn);
        mSubmitButton.setOnClickListener(v -> handleSubmitButton());
        mDeleteButton = findViewById(R.id.deleteWeightBtn);
        mDeleteButton.setOnClickListener(v -> handleDeleteButton());
    }

    /**
     * OnClick Listener for the Login button
     *
     **/
    private void handleSubmitButton(){
        // https://stackoverflow.com/questions/8409043/getdate-from-datepicker-android

        Entry entry = new Entry();

        // compile date picker values to string
        int day  = mDatePicker.getDayOfMonth();
        int month= mDatePicker.getMonth();
        int year = mDatePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd");
        String formattedDate = sdf.format(calendar.getTime());

        // acquire weight value from input
        String inputValue = String.valueOf(mWeightInput.getText());
        if (inputValue.equals("")){
            // warn user that they must enter a value
            String text = getString(R.string.empty_input);
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
            toast.show();
        }
        else{
            float weight = parseFloat(inputValue);

            // build entry
            entry.setDate(formattedDate);
            entry.setWeight(weight);
            entry.setUsername(mAuthManager.getUser().getUsername());

            // call database add
            mWeightsDatabase.addEntry(entry);

            // check if user has hit their goal
            if (weight < mWeightsDatabase.getGoal(mAuthManager.getUser().getUsername())){
                // check notifications enabled
                if (mNotificationsManager.getNotificationPreference()){
                    String text = getString(R.string.congrats);
                    Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            // launch the home activity to view changes
            Intent intent = new Intent(this, HomeActivity.class);
            finish();
            AddWeightActivity.this.startActivity(intent);
        }
    }

    /**
     * OnClick Listener for the Create button
     **/
    private void handleDeleteButton(){
        Entry entry = new Entry();

        // compile spinner values to string
        int day  = mDatePicker.getDayOfMonth();
        int month= mDatePicker.getMonth();
        int year = mDatePicker.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd");
        String formattedDate = sdf.format(calendar.getTime());

        // get weight from input
        float weight = parseFloat(String.valueOf(mWeightInput.getText()));

        // build entry
        entry.setDate(formattedDate);
        entry.setWeight(weight);
        entry.setUsername(mAuthManager.getUser().getUsername());

        // call database delete
        mWeightsDatabase.removeEntry(entry);

        // redirect to the home screen to view changes + kill current activity
        Intent intent = new Intent(this, HomeActivity.class);
        finish();
        AddWeightActivity.this.startActivity(intent);
    }
}