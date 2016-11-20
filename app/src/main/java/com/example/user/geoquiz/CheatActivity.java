package com.example.user.geoquiz;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private boolean answerIsTrue;
    private Button showAnswerButton;
    private TextView answerTextView;
    public static final String EXTRA_ANSWER_SHOWN =
            "com.bignerdranch.android.geoquiz.answer_shown";

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answerIsTrue=getIntent().getBooleanExtra("com.example.user.geoquiz.answer-is-true",true);

        showAnswerButton=(Button)findViewById(R.id.showAnswerButton);
        answerTextView=(TextView)findViewById(R.id.answerTextView);
        setAnswerShownResult(false);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answerIsTrue==true)
                {
                    answerTextView.setText(R.string.true_button);
                }
                else
                {
                    answerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // outState.put
    }
}
