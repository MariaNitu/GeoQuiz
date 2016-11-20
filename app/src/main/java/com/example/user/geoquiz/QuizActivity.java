package com.example.user.geoquiz;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;
    private static final String key_index="index";
    private Button mCheatButton;
    private static final String EXTRA_ANSWER_IS_TRUE="com.example.user.geoquiz.answer-is-true";



    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_africa, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true),
    };
    private int mCurrentIndex = 0;

    private boolean mIsCheater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN,false);
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
        int messageResId = 0;
        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                    .show();
        }}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d("MainActivity","onCreate state");


        if (savedInstanceState!=null)
        {
            mCurrentIndex=savedInstanceState.getInt(key_index,0);
        }
        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);

        mCheatButton=(Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(QuizActivity.this,CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);

                startActivity(i);

            }
        });

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(QuizActivity.this,
//                        R.string.incorrect_toast,
//                        Toast.LENGTH_SHORT).show();

                checkAnswer(true);


            }
        });
        mFalseButton = (Button)findViewById(R.id.false_button);

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(QuizActivity.this,
//                        R.string.correct_toast,
//                        Toast.LENGTH_SHORT).show();
                checkAnswer(false);


            }
        });

        mNextButton = (Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();

            }
        });
        updateQuestion();


    }


    @Override
    public void onStart(){
        super.onStart();
        Log.d("MainActivity","onStartState");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("MainActivity","onResumeState");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d("MainActivity","onStopState");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("MainActivity","onDestroyState");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i("MainActivity", "onSaveInstanceState");
        savedInstanceState.putInt(key_index, mCurrentIndex);

    }
}
