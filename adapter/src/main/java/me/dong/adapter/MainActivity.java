package me.dong.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 포스터");

        final GridView gv = (GridView) findViewById(R.id.gridView);
        MyAdapter adapter = new MyAdapter(this);
        gv.setAdapter(adapter);
    }

    public class MyAdapter extends BaseAdapter {
        Integer[] posterID = { R.drawable.poster1, R.drawable.poster2, R.drawable.poster3, R.drawable.poster4, R.drawable.poster4,
                R.drawable.poster5, R.drawable.poster6, R.drawable.poster7, R.drawable.poster8, R.drawable.poster9,
                R.drawable.poster10, R.drawable.poster4, R.drawable.poster4, R.drawable.poster4, R.drawable.poster4,
                R.drawable.poster4, R.drawable.poster4, R.drawable.poster4, R.drawable.poster4, R.drawable.poster4,
                R.drawable.poster4, R.drawable.poster4, R.drawable.poster4, R.drawable.poster4, R.drawable.poster4,
                R.drawable.poster4, R.drawable.poster4, R.drawable.poster4, R.drawable.poster4, R.drawable.poster4 };
        String[] posterName = { "신세계", "괴물", "달처럼", "감시자들", "신세계",
                "파파로티", "완득이", "본레거시", "하울링", "수퍼맨리턴즈",
                "치타", "신세계", "신세계", "신세계", "신세계",
                "신세계", "신세계", "신세계", "신세계", "신세계",
                "신세계", "신세계", "신세계", "신세계", "신세계",
                "신세계", "신세계", "신세계", "신세계", "신세계" };
        Context context;
        public MyAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return posterID.length;
        }
        public Object getItem(int arg0) {
            return null;
        }
        public long getItemId(int arg0) {
            return 0;
        }
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            if(convertView==null) {
                convertView = getLayoutInflater().inflate(R.layout.poster, null);
            }
            ImageView imgView = (ImageView)convertView.findViewById(R.id.imageView);
            TextView textView= (TextView)convertView.findViewById(R.id.textView);

            convertView.setLayoutParams(new GridView.LayoutParams(200, 300));
            imgView.setImageResource(posterID[pos]);
            textView.setText(posterName[pos]);
            convertView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    View dialogView = getLayoutInflater().inflate(R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterID[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setIcon(R.drawable.icon);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return convertView;
        }
    }
}




