

package com.example.android.emsense3.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.emsense3.Fragments.HomeFragment;
import com.example.android.emsense3.Fragments.ProfileFragment;
import com.example.android.emsense3.Fragments.SettingsFragment;
import com.example.android.emsense3.R;
import com.example.android.emsense3.data.ItemsContract.LibraryDatabaseEntry;
import com.example.android.emsense3.data.ItemsContract.LibraryEntry;
import com.example.android.emsense3.data.ItemsDbHelper;


//https://stackoverflow.com/questions/38011736/android-circle-profile-picture
//https://stackoverflow.com/questions/22105775/imageview-in-circular-through-xml

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Welcome!");
        toolbar.setTitleMarginStart(280);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.fragment_container);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container1) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                Log.v(TAG, "I'm at 56");
                return;

            }

            // Create a new Fragment to be placed in the activity layout
            HomeFragment firstFragment = new HomeFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment_container1, firstFragment, firstFragment.getTag()).commit();
            Log.v(TAG, "I'm at 71");
        }


    }


    //    Modify back button to close nav drawer
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.fragment_container);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // Create new fragment and transaction
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment newFragment = new HomeFragment();
            transaction.replace(R.id.fragment_container1, newFragment, newFragment.getTag());
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_profile) {
            Fragment newFragment = new ProfileFragment();
            transaction.replace(R.id.fragment_container1, newFragment, newFragment.getTag());
            transaction.addToBackStack(null);
            transaction.commit();


        } else if (id == R.id.nav_library) {
            Intent i = new Intent(getApplicationContext(), LibraryActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_settings) {
            Fragment newFragment = new SettingsFragment();
            transaction.replace(R.id.fragment_container1, newFragment, newFragment.getTag());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_privacy_policy) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.fragment_container);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void detect(View view) {
        final Context context = view.getContext();
        Log.v(TAG, "Button pressed.");
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
        helpBuilder.setTitle("ITEMS");
        helpBuilder.setMessage("Enter serial number:");
        final EditText input = new EditText(this);
        input.setSingleLine();
        input.setText("");
        helpBuilder.setView(input);
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String serialNumber = input.getText().toString();

                        updateLibrary(serialNumber);
                        Toast.makeText(context, "Items added", Toast.LENGTH_LONG).show();

                    }
                });

        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();


    }

    public void updateLibrary(String serialNumber) {
//        Hardcoded way to prevent error (for now)
        if (serialNumber.equals("deleteAll")) {
            ItemsDbHelper mDbHelper = new ItemsDbHelper(this);
            SQLiteDatabase dbWrite = mDbHelper.getWritableDatabase();
            dbWrite.delete(LibraryEntry.TABLE_NAME, null, null);
            return;
        } else if (!serialNumber.equals("1A") &&
                !serialNumber.equals("1B") &&
                !serialNumber.equals("1C") &&
                !serialNumber.equals("2A") &&
                !serialNumber.equals("2B") &&
                !serialNumber.equals("3A")) {
            return;
        }


        ItemsDbHelper mDbHelper = new ItemsDbHelper(this);
        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
//                LibraryDatabaseEntry._ID,
                LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER,
                LibraryDatabaseEntry.COLUMN_ITEMS,
                LibraryDatabaseEntry.COLUMN_MODEL,
                LibraryDatabaseEntry.COLUMN_SPECIFICATIONS
        };
        // Filter results WHERE "title" = 'My Title'
        String selection = LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER + " = ?";
        String[] selectionArgs = {serialNumber};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                LibraryDatabaseEntry.COLUMN_ITEMS + " ASC";

//      Cursor object with one row of all the details from the database respective to the serial number
        Cursor cursor = dbRead.query(
                LibraryDatabaseEntry.TABLE_NAME,                  // The table to query
                projection,                               // The columns to return
                selection,                                     // The columns for the WHERE clause
                selectionArgs,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        cursor.moveToFirst();


        ContentValues values = new ContentValues();
        values.put(LibraryEntry.COLUMN_SERIAL_NUMBER, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER)));
        values.put(LibraryEntry.COLUMN_MODEL, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_MODEL)));
        values.put(LibraryEntry.COLUMN_ITEMS, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_ITEMS)));
        values.put(LibraryEntry.COLUMN_SPECIFICATIONS, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS)));

        SQLiteDatabase dbWrite = mDbHelper.getWritableDatabase();
//        Insert the new row, returning the primary key value of the new row
        long newRowId = dbWrite.replace(LibraryEntry.TABLE_NAME, null, values);
    }

    public void deleteObjectFromLibrary(String serialNumber) {
        ItemsDbHelper mDbHelper = new ItemsDbHelper(this);
        SQLiteDatabase dbWrite = mDbHelper.getWritableDatabase();
        String selection = LibraryEntry.COLUMN_SERIAL_NUMBER + " LIKE ?";
        String[] selectionArgs = {serialNumber};
        dbWrite.delete(LibraryEntry.TABLE_NAME, selection, selectionArgs);

    }
}
