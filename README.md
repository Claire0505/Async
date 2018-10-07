# Async
耗時工作處理- AsyncTask 類別( android 實作這樣學)
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
