package com.multiplevideos.app;

import android.app.Activity;
import android.content.CursorLoader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.VideoView;


public class MainActivity extends Activity {

    private VideoView video1;
    private VideoView video2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video1 = (VideoView)findViewById(R.id.video_1);
        video2 = (VideoView)findViewById(R.id.video_1);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        video1.setVideoPath(getVideoFilePath(0));
        video2.setVideoPath(getVideoFilePath(1));

        video1.start();
        video2.start();
    }

    private String getVideoFilePath(int i) {
        String[] projection = { "*" };
        Cursor cursor = new CursorLoader(this,
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, null, // Return
                // all
                // rows
                null, null).loadInBackground();

        cursor.moveToFirst();
        cursor.moveToPosition(i);

        String str = cursor.getString(cursor
                .getColumnIndex(MediaStore.Video.Media.DATA));

        return str;
    }
}
