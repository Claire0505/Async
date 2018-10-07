package com.claire.async;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * 設計 AsyncTask -- 與 UI 元件互通
 * AsyncTask<傳入值型態，更新進度型態，結果型態>
 * (可傳入的型態，如 Integer,String,Boolean...,若不需要傳入值可使用 Void)
 * 將工作寫在 doInBackground方法中，使用的參數是陣列語法，在編譯時會轉為陣列(與AsyncTask的傳入值配合)，
 * 若是字串時，Params[0]即代表陣列的第一個字串值
 * 產生類別並呼叫 execute()
 *
 * 與 UI thread 互通的方法，一個 AsyncTask的執行流程有四個階段，四個執行方法分別是
 * onPreExecute - 之前，在背景工作之前會自動執行的方法
 * doInBackground (除了這個方法之外，另外三個方法能夠與 UI Thread 或 UI元件互動，需覆寫這些方法)
 *
 * onProgressUpdate - 過程，在下載過程中可以手動呼叫的方法，當在 doInBackground 中呼叫
 * [publishProgress方法] 會自動執行onProgressUpdate方法內的程式碼。
 *
 * onPostExecute - 之後，在背景工作執行完成後會自動執行的方法，在方法內撰寫工作完成後必要的程式碼。
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go1(View v){
        new Job1Task().execute();
    }

    public void go2(View v){
        new Job2Task().execute(3);
    }

    public void go3(View v){
        new Job3Task().execute(6);
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

    //需求3，在畫面上按上GO3按鈕，與需求2相同，但需每秒更新秒數到TextView
    class Job3Task extends AsyncTask<Integer, Integer, Void>{
        private TextView info;

        public Job3Task() {
            info = findViewById(R.id.info);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            //使用for迴圈，由傳入值遞增到6
            for (int i = 0; i < integers[0]; i++){
                //呼叫publishProgress方法，會自動呼叫onProgressUpdate方法，此方法負責每秒更新TextView文字
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            info.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            info.setText("Go3 Done");
        }

    }

}
