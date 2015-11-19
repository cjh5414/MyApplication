package me.dong.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by com on 2015-11-19.
 */
public class CalcActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc);

        double result=0.0;
        Intent inIntent = getIntent();

        switch(inIntent.getStringExtra("Calc")) {
            case "plus":
                result = inIntent.getDoubleExtra("Num1", 0) + inIntent.getDoubleExtra("Num2", 0);
                break;
            case "minus":
                result = inIntent.getDoubleExtra("Num1", 0) - inIntent.getDoubleExtra("Num2", 0);
                break;
            case "mul":
                result = inIntent.getDoubleExtra("Num1", 0) * inIntent.getDoubleExtra("Num2", 0);
                break;
            case "div":
                result = inIntent.getDoubleExtra("Num1", 0) / inIntent.getDoubleExtra("Num2", 0);
                break;
        }

        Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
        outIntent.putExtra("Result", result);

        setResult(RESULT_OK, outIntent);
        finish();
    }
}
