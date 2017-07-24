package com.example.android.emsense3.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.android.emsense3.R;
import com.example.android.emsense3.data.ItemsContract.LibraryEntry;
import com.example.android.emsense3.data.ItemsDbHelper;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class SpecificObjectActivity extends AppCompatActivity {

    private ImageView bannerImage;
    private ImageButton btnStep, btnInfo, btnVideo;
    private ItemsDbHelper mDbHelper = new ItemsDbHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_specific_object);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Thunder Laser Systems Mini");
        setSupportActionBar(toolbar);

        bannerImage = (ImageView) findViewById(R.id.specific_object_banner);


        btnStep = (ImageButton) findViewById(R.id.button_step);
        btnInfo = (ImageButton) findViewById(R.id.button_info);
        btnVideo = (ImageButton) findViewById(R.id.button_video);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTutorialStepActivity();
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchYoutubeActivity();
            }
        });

        //Database Stuff

        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();


        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
//                LibraryEntry._ID,
                LibraryEntry.COLUMN_BANNER_ID


        };

        // Filter results WHERE "title" = 'My Title'
        String selection = LibraryEntry.COLUMN_MODEL + " = ?";

        Intent intent = getIntent();
        String object = intent.getStringExtra(EXTRA_MESSAGE);


        String[] selectionArgs = {object};

        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                LibraryEntry.COLUMN_MODEL + " ASC";

        Cursor cursor = dbRead.query(
                LibraryEntry.TABLE_NAME,                  // The table to query
                projection,                               // The columns to return
                selection,                                     // The columns for the WHERE clause
                selectionArgs,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        cursor.moveToNext();
        int bannerIdColumnIndex = cursor.getColumnIndex(LibraryEntry.COLUMN_BANNER_ID);
        int bannerId = cursor.getInt(bannerIdColumnIndex);

        setBannerImage(bannerId);
        cursor.close();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void launchTutorialStepActivity() {
        startActivity(new Intent(SpecificObjectActivity.this, TutorialStepActivity.class));

    }

    private void launchYoutubeActivity() {
        startActivity(new Intent(SpecificObjectActivity.this, YouTubeActivity.class));

    }

    public void setBannerImage(int bannerId) {
        bannerImage.setImageResource(bannerId);
    }

}
