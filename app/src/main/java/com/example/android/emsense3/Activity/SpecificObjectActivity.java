package com.example.android.emsense3.Activity;

//Receive an intent with EXTRA_MESSAGE containing the object name clicked in the ObjectsActivity
//Query the database for the rows with the object name
//Create the layout dynamically based on the object clicked, with its specific image and description



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
import android.widget.TextView;

import com.example.android.emsense3.R;
import com.example.android.emsense3.data.ItemsContract.LibraryEntry;
import com.example.android.emsense3.data.ItemsDbHelper;

import static android.provider.AlarmClock.EXTRA_MESSAGE;


public class SpecificObjectActivity extends AppCompatActivity {

    private ImageView bannerImageView;
    private TextView descriptionTextView, titleTextView;
    private ImageButton btnStep, btnInfo, btnVideo;
    private ItemsDbHelper mDbHelper = new ItemsDbHelper(this);
    private String objectName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_specific_object);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        bannerImageView = (ImageView) findViewById(R.id.specific_object_banner);

        descriptionTextView = (TextView) findViewById(R.id.specific_object_description);
        titleTextView = (TextView) findViewById(R.id.specific_object_itemName);

        btnStep = (ImageButton) findViewById(R.id.button_step);
        btnInfo = (ImageButton) findViewById(R.id.button_info);
        btnVideo = (ImageButton) findViewById(R.id.button_video);

        Intent intent = getIntent();
        objectName = intent.getStringExtra(EXTRA_MESSAGE);

        toolbar.setTitle(objectName);
        setSupportActionBar(toolbar);

        Cursor cursor = createCursor();

        cursor.moveToNext();
        int bannerIdColumnIndex = cursor.getColumnIndex(LibraryEntry.COLUMN_BANNER_ID);
        int bannerId = cursor.getInt(bannerIdColumnIndex);
        setBannerImage(bannerId);

        int descriptionColumnIndex = cursor.getColumnIndex(LibraryEntry.COLUMN_DESCRIPTION);
        String description = cursor.getString(descriptionColumnIndex);
        descriptionTextView.setText(description);

        int serialNumberColumnIndex = cursor.getColumnIndex(LibraryEntry.COLUMN_SERIAL_NUMBER);
        final String serialNumber = cursor.getString(serialNumberColumnIndex);

        int youtubeIdColumnIndex = cursor.getColumnIndex(LibraryEntry.COLUMN_YOUTUBE_ID);
        final String youtubeId = cursor.getString(youtubeIdColumnIndex);





        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchTutorialStepActivity(serialNumber);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchYoutubeActivity(youtubeId);
            }
        });


        titleTextView.setText(objectName);


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

    private void launchTutorialStepActivity(String serialNumber) {
        Intent intent = new Intent(this, TutorialStepActivity.class);
        intent.putExtra(EXTRA_MESSAGE, serialNumber);
        startActivity(intent);

    }

    private void launchYoutubeActivity(String youtubeId) {
        Intent intent = new Intent(this, YouTubeActivity.class);
        intent.putExtra(EXTRA_MESSAGE, youtubeId);
        startActivity(new Intent(SpecificObjectActivity.this, YouTubeActivity.class));

    }

    private void setBannerImage(int bannerId) {
        bannerImageView.setImageResource(bannerId);
    }

    private Cursor createCursor() {
        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();

        String[] projection = {
//                LibraryEntry._ID,
                LibraryEntry.COLUMN_BANNER_ID,
                LibraryEntry.COLUMN_MODEL,
                LibraryEntry.COLUMN_DESCRIPTION,
                LibraryEntry.COLUMN_SERIAL_NUMBER,
                LibraryEntry.COLUMN_YOUTUBE_ID,

        };

        // Filter results WHERE "title" = 'My Title'
        String selection = LibraryEntry.COLUMN_MODEL + " = ?";


        String[] selectionArgs = {objectName};

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

        return cursor;
    }


}
