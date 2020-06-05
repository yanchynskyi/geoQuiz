package android.bignerdranch.com;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticActivity extends AppCompatActivity {
    private TextView mScoreTextView;
    private TextView mKnowledgeLevelTextView;
    private int score;
    private int countOfQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        Bundle arguments = getIntent().getExtras();
        score = Integer.parseInt(arguments.get("score").toString());
        countOfQuestions = Integer.parseInt(arguments.get("countOfQuestions").toString());
        String scoreFinal = score + "/" + countOfQuestions;

        mScoreTextView = (TextView) findViewById(R.id.final_score_textView);
        mScoreTextView.setText(scoreFinal);

        mKnowledgeLevelTextView = (TextView) findViewById(R.id.knowledge_text_textView);
        mKnowledgeLevelTextView.setText(getLevelOfKnowledge(score));
    }

    private int getLevelOfKnowledge(int score) {
        int textId = 0;
        switch (score) {
            case 1: textId = R.string.bad_statistic_text;
                break;
            case 2: textId = R.string.bad_statistic_text;
                break;
            case 3: textId = R.string.soso_statistic_text;
                break;
            case 4: textId = R.string.soso_statistic_text;
                break;
            case 5: textId = R.string.soso_statistic_text;
                break;
            case 6: textId = R.string.good_statistic_text;
                break;
            case 7: textId = R.string.good_statistic_text;
                break;
        }
        return textId;
    }
}
