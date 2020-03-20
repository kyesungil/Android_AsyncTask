package com.example.asynctask_example_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView PercentText;
    private EditText PercentEdit;
    private Button startbtn,cancelbtn;
    private int value;
    private MyAsyncTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.percent_ProgressBar);
        PercentText = findViewById(R.id.percent_text);
        PercentEdit = findViewById(R.id.percent_edit);

        cancelbtn = findViewById(R.id.cancel_btn);
        startbtn = findViewById(R.id.start_btn);

        //progressbar 취소 버튼
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onCancelled() 호출
                task.cancel(true);
            }
        });

        //progressbar 시작 버튼
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new MyAsyncTask();
                // execute(k) : k는 doInBackground로 넘겨줌
                task.execute(Integer.parseInt(PercentEdit.getText().toString()));
            }
        });
    }
    // AsyncTask<doInBackground 매개변수,onProgressUpdate 매개변수,onPostExecute 매개변수>
    private class MyAsyncTask extends AsyncTask<Integer,Integer,Integer> {

        // AsyncTask 초기화 작업
        @Override
        protected void onPreExecute() {
            value = 0;
            progressBar.setProgress(0);
        }

        // Work Thread
        @Override
        protected Integer doInBackground(Integer... integers) {
            while(isCancelled() == false) {
                value++;
                if (value > integers[0])
                    break;
                else {
                    // publishProgress() : MainThread 접근해서 UI 작업하기 위한 메소드
                    publishProgress(value);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
            return value;
        }

        // UI 작업
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
            PercentText.setText("Percent : " + values[0].toString());
        }

        // MyAsyncTask 작업(dolnBackground)이 종료되면 호출
        @Override
        protected void onPostExecute(Integer integer) {
            progressBar.setProgress(0);
            PercentText.setText("완료");
        }

        // MyAsyncTask 작업이 취소되면 호출
        @Override
        protected void onCancelled() {
            progressBar.setProgress(0);
            PercentText.setText("강제 종료 했습니다.");
        }
    }
}
