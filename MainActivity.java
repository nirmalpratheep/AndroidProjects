package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
String mName=""; //This holds the name
int mScore =0; // This holds the total score
int mTotalQuestions = 4; // This holds the total questions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* This resets all the answers and Name field of the Quiz app */
    public void reset(View view) {
        //Reset Question 1
       RadioGroup rg=(RadioGroup)findViewById(R.id.qgroup);
        rg.clearCheck();
        //Reset Question 2
        CheckBox cb1 = (CheckBox)findViewById(R.id.q2_ans1);
        cb1.setChecked(false);
        CheckBox cb2 = (CheckBox)findViewById(R.id.q2_ans2);
        cb2.setChecked(false);
        CheckBox cb3 = (CheckBox)findViewById(R.id.q2_ans3);
        cb3.setChecked(false);
        CheckBox cb4 = (CheckBox)findViewById(R.id.q2_ans4);
        cb4.setChecked(false);
        //Clearing Name
        EditText text = (EditText)findViewById(R.id.edittext);
        text.setText("");
        //Clearing Question 3
        text = (EditText)findViewById(R.id.q3_ans);
        text.setText("");
        //Clearing Question 4
        rg=(RadioGroup)findViewById(R.id.qgroup2);
        rg.clearCheck();
        //Clearing summary message
        displayMessage("");
    }

/* This gets the namefield from the input */
public void getNameField() {
    EditText text = (EditText)findViewById(R.id.edittext);
    mName = text.getText().toString().trim();
}

public void getScore() {
    //Validate answer for Question 1
    RadioGroup rg1 = (RadioGroup) findViewById(R.id.qgroup);
    if (rg1.getCheckedRadioButtonId() == R.id.q1_ans2) {
        RadioButton rd1 = (RadioButton) findViewById(R.id.q1_ans2);
        boolean checked = ((RadioButton) rd1).isChecked();
        if (checked)
            mScore++;
    }
    //Validate answer for Question 2
    CheckBox cb1 = (CheckBox)findViewById(R.id.q2_ans1);
    CheckBox cb2 = (CheckBox)findViewById(R.id.q2_ans2);
    CheckBox cb3 = (CheckBox)findViewById(R.id.q2_ans3);
    CheckBox cb4 = (CheckBox)findViewById(R.id.q2_ans4);
    if(cb1.isChecked() && cb2.isChecked() && !cb3.isChecked() && cb4.isChecked())
        mScore++;

    //Validate answer for Question 3
    EditText text = (EditText)findViewById(R.id.q3_ans);
    String value = text.getText().toString().trim();
    if(Integer.parseInt(value)==3)
        mScore++;

    //Validate answer for Question 4
    rg1 = (RadioGroup) findViewById(R.id.qgroup2);
    if (rg1.getCheckedRadioButtonId() == R.id.q4_ans1) {
        RadioButton rd1 = (RadioButton) findViewById(R.id.q4_ans1);
        boolean checked = ((RadioButton) rd1).isChecked();
        if (checked)
            mScore++;
    }
    Log.i("Activity","Res "+mScore);
}

    /* This submits all the answers and checks against the correct value and produces
     * the score */
    public void submit(View view) {
        mScore=0;
        getNameField();
        getScore();
        String res = summaryResult();
        displayMessage(res);
    }

    /* Forms the summary result in a string */
    public String summaryResult(){
        String result ="Thank you ";
        result +=mName;
        result +=" for participating in the Quiz.";
        result +=" Your score : ";
        result +=mScore;
        result +=" out of ";
        result +=mTotalQuestions;
        result +=" total questions.";
        return result;
    }

    /*    Displays the string message      */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.result);
        orderSummaryTextView.setText(message);
    }

}
