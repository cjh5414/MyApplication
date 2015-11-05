package com.example.com.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2, dlgEdit1, dlgEdit2;
    Button btn;
    View dialogView, toastView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText)findViewById(R.id.edit1);
        edit2 = (EditText)findViewById(R.id.edit2);
        btn = (Button)findViewById(R.id.btn1);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.dialog1, null);
                dlgEdit1 = (EditText)dialogView.findViewById(R.id.dlgEdit1);
                dlgEdit2 = (EditText)dialogView.findViewById(R.id.dlgEdit2);

                dlgEdit1.setText(edit1.getText().toString());
                dlgEdit2.setText(edit2.getText().toString());

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");

                dlg.setIcon(R.drawable.icon);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        edit1.setText(dlgEdit1.getText());
                        edit2.setText(dlgEdit2.getText());
                        //((ViewGroup)dialogView.getParent()).removeView(dialogView);
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
                        int xOffset = (int)(Math.random()*dm.widthPixels);
                        int yOffset = (int)(Math.random()*dm.heightPixels);
                        Toast toast = new Toast(MainActivity.this);
                        toastView = View.inflate(MainActivity.this, R.layout.toast1, null);
                        toast.setView(toastView);

                        toast.setGravity(Gravity.TOP| Gravity.LEFT,xOffset,yOffset);

                        toast.show();
                    }
                });
                dlg.show();
            }
        });
    }
}
