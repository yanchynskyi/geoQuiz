package android.bignerdranch.com;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private ImageButton mTrueButton;
    private ImageButton mFalseButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private TextView mScoreTextView;
    private String action;
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final String ACTION_OCEANS = "info.android.action.oceans";
    private static final String ACTION_MOUNTAINS = "info.android.action.mountains";
    private static final String ACTION_COUNTRIES = "info.android.action.countries";

    private List<Question> mQuestionBankList = new ArrayList<>();

    private boolean mIsCheater;
    private int mCurrentIndex = 0;
    private int score = 0;

    private void fillQuestions(String action) {
        mImageView = (ImageView) findViewById(R.id.image_view);

            if (action.equals(ACTION_OCEANS)) {
                mImageView.setImageResource(R.drawable.ocean);

                mQuestionBankList.add(new Question(R.string.question_oceans, true));
                mQuestionBankList.add(new Question(R.string.question_mideast, false));
                mQuestionBankList.add(new Question(R.string.question_jellyfish, true));
                mQuestionBankList.add(new Question(R.string.question_lake, true));
                mQuestionBankList.add(new Question(R.string.question_internet, true));
            } else if (action.equals(ACTION_MOUNTAINS)) {
                mImageView.setImageResource(R.drawable.mountains);

                mQuestionBankList.add(new Question(R.string.qustion_everest, true));
                mQuestionBankList.add(new Question(R.string.question_conquered, false));
                mQuestionBankList.add(new Question(R.string.question_smallest, true));
                mQuestionBankList.add(new Question(R.string.question_deaths, false));
                mQuestionBankList.add(new Question(R.string.question_tmp, true));
            } else if (action.equals(ACTION_COUNTRIES)){
                mImageView.setImageResource(R.drawable.citiess);

                mQuestionBankList.add(new Question(R.string.question_australia, true));
                mQuestionBankList.add(new Question(R.string.question_asia, true));
                mQuestionBankList.add(new Question(R.string.question_italy, false));
                mQuestionBankList.add(new Question(R.string.question_switzerland, true));
                mQuestionBankList.add(new Question(R.string.question_romania, false));

            } else {

            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        ifSavedInstanceStateNull(savedInstanceState);

        //потрібно прийняти ключ і передати його в метод fillQuestions()
        Intent intent = getIntent();
        action = intent.getAction();
        if (action == null) {
            action = intent.getStringExtra("action");
        }
            fillQuestions(action);


        mQuestionTextView = (TextView) findViewById(R.id.question_textView);
        mScoreTextView = (TextView) findViewById(R.id.score_textView);

        mTrueButton = (ImageButton) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                if (mCurrentIndex < mQuestionBankList.size()) {     //! done
                    mCurrentIndex = mCurrentIndex + 1;
                }
                mIsCheater = false;
                updateQuestion();
                setButtonsEnabled(true);
            }
        });

        mFalseButton = (ImageButton) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                if (mCurrentIndex < mQuestionBankList.size()) {     //! done
                    mCurrentIndex = mCurrentIndex + 1;
                }
                mIsCheater = false;
                updateQuestion();
                setButtonsEnabled(true);
            }

        });

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBankList.get(mCurrentIndex).isAnswerTrue();
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });

        updateScore();
        updateQuestion();
    }

    private void ifSavedInstanceStateNull(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
    }

    private void updateQuestion() {
        if (mCurrentIndex < mQuestionBankList.size()) {                         //! done
            int question = mQuestionBankList.get(mCurrentIndex).getTextResId();     //! done
            mQuestionTextView.setText(question);
            }else {
            Intent intent = new Intent(MainActivity.this, StatisticActivity.class);
            intent.putExtra("score", score);
            intent.putExtra("countOfQuestions", mQuestionBankList.size());
            intent.putExtra("action", action);
            startActivity(intent);
            }
    }

    private void updateScore() {
            mScoreTextView.setText("Score: " + score);

    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBankList.get(mCurrentIndex).isAnswerTrue();     //! done
        int messageResId = 0;
        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            score++;
            updateScore();
            setButtonsEnabled(false);
        } else {
            messageResId = R.string.incorrect_toast;
            updateScore();
            setButtonsEnabled(false);

        }
        Toast toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    private void setButtonsEnabled(boolean enabled) {
        mTrueButton.setEnabled(enabled);
        mFalseButton.setEnabled(enabled);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

}
