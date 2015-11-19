package me.dong.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calcBtn = (Button)findViewById(R.id.btnCalc);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText edtNum1 = (EditText)findViewById(R.id.edtText1);
                EditText edtNum2 = (EditText)findViewById(R.id.edtText2);
                RadioGroup rGroup = (RadioGroup)findViewById(R.id.rGroup);
                String calc;

                Intent intent = new Intent(getApplicationContext(), CalcActivity.class);
                try {
                    intent.putExtra("Num1", Double.parseDouble(edtNum1.getText().toString()));
                    intent.putExtra("Num2", Double.parseDouble(edtNum2.getText().toString()));
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "숫자를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                switch(rGroup.getCheckedRadioButtonId()) {
                    case R.id.btnPlus:
                        calc = "plus";
                        break;
                    case R.id.btnMinus:
                        calc = "minus";
                        break;
                    case R.id.btnMul:
                        calc = "mul";
                        break;
                    case R.id.btnDiv:
                        if(Double.parseDouble(edtNum2.getText().toString())==0.0) {
                            Toast.makeText(getApplicationContext(), "0으로 나눌 수 없습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        calc = "div";
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "연산을 선택하세요.", Toast.LENGTH_SHORT).show();
                        return;
                }
                intent.putExtra("Calc", calc);

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            double result = data.getDoubleExtra("Result", 0);
            Toast.makeText(getApplicationContext(), "결과 : " + result, Toast.LENGTH_SHORT).show();
        }
    }
}


