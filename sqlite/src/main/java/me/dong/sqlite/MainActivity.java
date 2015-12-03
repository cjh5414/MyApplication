package me.dong.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    EditText edtName, edtNumber, edtNameResult, edtNumberResult;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("가수 그룹 관리 DB");

        edtName = (EditText)findViewById(R.id.edtText1);
        edtNumber = (EditText)findViewById(R.id.edtText2);
        edtNameResult = (EditText)findViewById(R.id.edtText3);
        edtNumberResult = (EditText)findViewById(R.id.edtText4);
        myHelper = new myDBHelper(this);
    }

    public void onClick(View view) {
        Cursor cursor;
        String strNames = "그룹 이름" + "\r\n" + "-------" + "\r\n";
        String strNumbers = "인원" + "\r\n" + "-------" + "\r\n";
        switch(view.getId()) {
            case R.id.btnInit:
                myHelper.onUpgrade(sqlDB, 1, 2);
                break;
            case R.id.btnInsert:
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ('" + edtName.getText().toString() + "', " + edtNumber.getText().toString() + ");");
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();
                onClick(findViewById(R.id.btnSearch));
                break;
            case R.id.btnUpdate:
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("UPDATE groupTBL SET gNumber = "+edtNumber.getText().toString()+" WHERE gName='"+edtName.getText().toString()+"';");
                sqlDB.close();
                onClick(findViewById(R.id.btnSearch));
                break;
            case R.id.btnDelete:
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM groupTBL WHERE gName = '"+edtName.getText().toString()+"';");
                sqlDB.close();
                onClick(findViewById(R.id.btnSearch));
                break;
            case R.id.btnSearch:
                sqlDB = myHelper.getWritableDatabase();
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);
                while (cursor.moveToNext()) {
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }
                edtNameResult.setText(strNames);
                edtNumberResult.setText(strNumbers);

                cursor.close();
                sqlDB.close();
                break;
        }
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        // db 파일이 새로 생김
        // 테이블 스키마를 만든다.
        // 디폴트로 레코드 들어가있어야 하면 insert도 가능
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL(gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        // 새로운 버전으로 업그레이드
        // 고쳐줘야 할 내용을 쓴다.
        // 버전이 많아지면 upgrade 내용도 많아진다. 1->4, 2->4, 3->4 ..
        // 이 경우에는 다 날려버리고 다시 만들지만 데이터가 사라지므로 좋지 않다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
    }
}
