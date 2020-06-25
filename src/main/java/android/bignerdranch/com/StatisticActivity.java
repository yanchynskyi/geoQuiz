package android.bignerdranch.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StatisticActivity extends AppCompatActivity {
    private TextView mScoreTextView;
    private TextView mKnowledgeLevelTextView;
    private Button mMenuButton;
    private ImageButton mRefreshImageButton;
    private int score;
    private int countOfQuestions;
    private static final String ACTION_OCEANS = "info.android.action.oceans";
    private static final String ACTION_MOUNTAINS = "info.android.action.mountains";
    private static final String ACTION_COUNTRIES = "info.android.action.countries";

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

        mRefreshImageButton = (ImageButton) findViewById(R.id.refresh_icon_imageButton);
        mRefreshImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGet = new Intent();
                String action = getIntent().getExtras().get("action").toString();
                Intent intent = new Intent(StatisticActivity.this, MainActivity.class);
                intent.putExtra("action", action);
                startActivity(intent);
            }
        });

        mMenuButton = (Button) findViewById(R.id.backToMenu_button);
        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }


    //потрібно реалізувати метод якиц буде вираховувати коефіцієнт правильних відповідей
    private int getLevelOfKnowledge(int score) {
        int textId = 0;
        switch (score) {
            case 0: textId = R.string.bad_statistic_text;
                break;
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
