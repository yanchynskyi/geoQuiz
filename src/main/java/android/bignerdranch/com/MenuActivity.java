package android.bignerdranch.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    private ImageButton mOceansButton;
    private ImageButton mMountainsButton;
    private ImageButton mCountriesButton;
    private static final String ACTION_OCEANS = "info.android.action.oceans";
    private static final String ACTION_MOUNTAINS = "info.android.action.mountains";
    private static final String ACTION_COUNTRIES = "info.android.action.countries";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mOceansButton = (ImageButton) findViewById(R.id.oceans_button);
        mOceansButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_OCEANS);
                startActivity(intent);

            }
        });

        mMountainsButton = (ImageButton) findViewById(R.id.mountains_button);
        mMountainsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_MOUNTAINS);
                startActivity(intent);
            }
        });

        mCountriesButton = (ImageButton) findViewById(R.id.countries_id);
        mCountriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ACTION_COUNTRIES);
                startActivity(intent);
            }
        });
    }
}
