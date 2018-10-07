package com.claire.async;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //需求1，在畫面上按上GO1按鈕，工作5秒鐘，結束後在TextView中顯示[Done]
    class Job1Task extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        //5秒結束後需改變TextView中的文字，因此得再覆寫onPostExecute方法
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = findViewById(R.id.info);
            info.setText("Go1 DONE");
        }
    }

    //需求2，在畫面上按上GO2按鈕，工作 n 秒鐘，結束後在TextView中顯示[Done]
    class Job2Task extends AsyncTask<Integer, Void, Void>{ //傳入值宣告為Integer，代表整數

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(integers[0]*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = findViewById(R.id.info);
            info.setText("Go2 Done");
        }
    }

    public void go1(View v){
        new Job1Task().execute();
    }

    public void go2(View v){
        new Job2Task().execute(3);
    }

    public void go3(View v){

    }
}
