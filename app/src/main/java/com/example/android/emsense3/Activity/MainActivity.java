

package com.example.android.emsense3.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.WifiManager;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.android.emsense3.Fragments.HomeFragment;
import com.example.android.emsense3.Fragments.ProfileFragment;
import com.example.android.emsense3.Fragments.SettingsFragment;
import com.example.android.emsense3.R;
import com.example.android.emsense3.data.ItemsContract.LibraryDatabaseEntry;
import com.example.android.emsense3.data.ItemsContract.LibraryEntry;
import com.example.android.emsense3.data.ItemsDbHelper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.List;


//https://stackoverflow.com/questions/38011736/android-circle-profile-picture
//https://stackoverflow.com/questions/22105775/imageview-in-circular-through-xml

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final int portNum = 55555;
    private static final String TAG = "MyActivity";
    public static boolean sdrFail = false;
    public static boolean terminateFlag;
    public static Bundle bundle = new Bundle();
    private static boolean authorised = true;
    ServerSocket serverSocket;
    private Cursor cursor;
    private ItemsDbHelper mDbHelper = new ItemsDbHelper(this);

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
            getSupportActionBar().setTitle("Profile");
            Fragment newFragment = new ProfileFragment();
            transaction.replace(R.id.fragment_container1, newFragment, newFragment.getTag());
            transaction.addToBackStack(null);
            transaction.commit();


        } else if (id == R.id.nav_library) {
            Intent i = new Intent(getApplicationContext(), LibraryActivity.class);
            startActivity(i);

        } else if (id == R.id.nav_settings) {
            getSupportActionBar().setTitle("Settings");
            Fragment newFragment = new SettingsFragment();
            transaction.replace(R.id.fragment_container1, newFragment, newFragment.getTag());
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (id == R.id.nav_about_us) {
            getSupportActionBar().setTitle("About us");

        } else if (id == R.id.nav_privacy_policy) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.fragment_container);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void detect(final View view) {
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
        final Context context = view.getContext();


        view.startAnimation(animScale);

        final AlertDialog.Builder helpBuilder2 = new AlertDialog.Builder(this);


        final String serialNumber = getDeviceId(view);

        if (serialNumber.equals("deleteAll")) {
            SQLiteDatabase dbWrite = mDbHelper.getWritableDatabase();
            dbWrite.delete(LibraryEntry.TABLE_NAME, null, null);
            return;
        } else if (!serialNumber.equals("1A") &&
                !serialNumber.equals("1B") &&
                !serialNumber.equals("1C") &&
                !serialNumber.equals("1D") &&
                !serialNumber.equals("2A") &&
                !serialNumber.equals("2B")) {
            return;
        }


        cursor = createCursor(serialNumber);

        cursor.moveToFirst();

        //Change "something" to intent

        String objectName = cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_ITEMS));
        String modelName = cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_MODEL));

        String title = objectName + " detected";
        String msg1 = "Model: " + modelName;
        String msg2 = "Save this object?";

        helpBuilder2.setTitle(title)
                .setMessage(msg1 + "\n" + "\n" + msg2)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Save the object

                        updateLibrary(serialNumber);
                        Toast.makeText(context, "Items added", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Dont save the object -> cancel the dialog
                        dialogInterface.dismiss();
                        cursor.close();
                    }
                });

        final AlertDialog dialog = helpBuilder2.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
            }
        });

        //Remove loading screen and please wait text
        dialog.show();

    }






    public void updateLibrary(String serialNumber) {
//        Hardcoded way to prevent error (for now)

        cursor.moveToFirst();


        ContentValues values = new ContentValues();
        values.put(LibraryEntry.COLUMN_SERIAL_NUMBER, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER)));
        values.put(LibraryEntry.COLUMN_MODEL, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_MODEL)));
        values.put(LibraryEntry.COLUMN_ITEMS, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_ITEMS)));
        values.put(LibraryEntry.COLUMN_SPECIFICATIONS, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_SPECIFICATIONS)));
        values.put(LibraryEntry.COLUMN_DESCRIPTION, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_DESCRIPTION)));
        values.put(LibraryEntry.COLUMN_BANNER_ID, cursor.getInt(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_BANNER_ID)));
        values.put(LibraryEntry.COLUMN_ICON_ID, cursor.getInt(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_ICON_ID)));
        values.put(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID, cursor.getString(cursor.getColumnIndex(LibraryDatabaseEntry.COLUMN_YOUTUBE_ID)));

        SQLiteDatabase dbWrite = mDbHelper.getWritableDatabase();
//        Insert the new row, returning the primary key value of the new row
        long newRowId = dbWrite.replace(LibraryEntry.TABLE_NAME, null, values);
        cursor.close();
    }

    public void deleteObjectFromLibrary(String serialNumber) {
        ItemsDbHelper mDbHelper = new ItemsDbHelper(this);
        SQLiteDatabase dbWrite = mDbHelper.getWritableDatabase();
        String selection = LibraryEntry.COLUMN_SERIAL_NUMBER + " LIKE ?";
        String[] selectionArgs = {serialNumber};
        dbWrite.delete(LibraryEntry.TABLE_NAME, selection, selectionArgs);

    }

    public Cursor createCursor(String serialNumber) {

        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
//                LibraryDatabaseEntry._ID,
                LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER,
                LibraryDatabaseEntry.COLUMN_ITEMS,
                LibraryDatabaseEntry.COLUMN_MODEL,
                LibraryDatabaseEntry.COLUMN_DESCRIPTION,
                LibraryDatabaseEntry.COLUMN_SPECIFICATIONS,
                LibraryDatabaseEntry.COLUMN_YOUTUBE_ID,
                LibraryDatabaseEntry.COLUMN_ICON_ID,
                LibraryDatabaseEntry.COLUMN_BANNER_ID

        };
        // Filter results WHERE "title" = 'My Title'
        String selection = LibraryDatabaseEntry.COLUMN_SERIAL_NUMBER + " = ?";
        String[] selectionArgs = {serialNumber};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                LibraryDatabaseEntry.COLUMN_ITEMS + " ASC";

//      Cursor object with one row of all the details from the database respective to the serial number
        cursor = dbRead.query(
                LibraryDatabaseEntry.TABLE_NAME,                  // The table to query
                projection,                               // The columns to return
                selection,                                     // The columns for the WHERE clause
                selectionArgs,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return cursor;
    }

    public void useSDR(View view) {
        sdrFail = false;
        final Context context = view.getContext();
        String ipAddress = getIpAddress(context);
        final int port = 14423;
        final int samplerate = 1024000;
        final int frequency = 1000000;
        Uri command = Uri.parse("iqsrc://-a " + ipAddress + " -p " + port + " -f " + frequency + " -s " + samplerate);
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(command);
        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        boolean isIntentSafe = activities.size() > 0;
        if (isIntentSafe) {
            startActivity(intent);
        } else {
            sdrFail = true;
            //Prompt user to attach SDR
        }
    }

    public String getDeviceId(View view) {
        AlertDialog.Builder helpBuilder3 = new AlertDialog.Builder(this);

        helpBuilder3.setTitle("ITEMS");
        helpBuilder3.setMessage("Press OK to calibrate");

        helpBuilder3.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        authorised = true;
                    }
                }
        );




        final Context context = view.getContext();

        String command;
        ServerThread serverThread = new ServerThread();

        //Tell server to start on pressing detect
        bundle.putString("outgoingMessage", "start");
        //Server prompts user to calibrate
        command = bundle.getString("incomingMessage");
        if (command == "calibrate") {
            //Insert dialog to tell user to calibrate
            AlertDialog calibrateDialog = helpBuilder3.create();
            calibrateDialog.show();

        } else {
            bundle.putString("outgoingMessage", "pause");
        }
        //User presses detect button (1st detection)
        if (authorised) {
            useSDR(view);
        }
        authorised = false;
        if (sdrFail) {
            return null;
        }

        helpBuilder3.setMessage("Touch the object with the scanner and press OK");
        bundle.putString("outgoingMessage", "calibrationDone");
        //Server prompts user to scan object
        command = bundle.getString("incomingMessage");
        if (command == "scan") {
            //Insert dialog to tell user to scan object
            AlertDialog scanDialog = helpBuilder3.create();
            scanDialog.show();

        } else {
            bundle.putString("outgoingMessage", "pause");
        }


        //User presses detect button (2nd detection)
        if (authorised) {
            //Show loading bar
            useSDR(view);
        }
        if (sdrFail) {
            return null;
        }
        //Remove loading bar
        //Show text = "Processing data, you may remove the scanner . . ."
        //Show
        bundle.putString("outgoingMessage", "scanDone");
        //Server send device ID after data processing is finished
        String deviceIdString = bundle.getString("incomingMessage");
        terminateFlag = true;
        return deviceIdString;
    }

    public String getIpAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(WIFI_SERVICE);
        int ipAddress = wifi.getConnectionInfo().getIpAddress();

        // Convert little-endian to big-endianif needed
        if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
            ipAddress = Integer.reverseBytes(ipAddress);
        }

        byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

        String ipAddressString;
        try {
            ipAddressString = InetAddress.getByAddress(ipByteArray).getHostAddress();
        } catch (UnknownHostException ex) {
            Log.e("WIFIIP", "Unable to get host address.");
            ipAddressString = null;
        }

        return ipAddressString;
    }

    public class ServerThread extends Thread {

        ServerThread() {
            terminateFlag = false;
        }

        @Override
        public void run() {
            Socket socket = null;
            try {
                serverSocket = new ServerSocket(portNum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    socket = serverSocket.accept();
                    OutputThread outputThread = new OutputThread(socket);
                    InputThread inputThread = new InputThread(socket);
                    inputThread.start();
                    outputThread.start();
                    if (terminateFlag) {
                        if (!inputThread.isAlive()) {
                            inputThread.close();
                        }
                        if (!outputThread.isAlive()) {
                            outputThread.close();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void close() {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class OutputThread extends Thread {
        private Socket hostThreadSocket;

        OutputThread(Socket socket) {
            hostThreadSocket = socket;
        }

        @Override
        public void run() {
            DataOutputStream outputStream;
            try {
                outputStream = new DataOutputStream(hostThreadSocket.getOutputStream());
                String outgoingMessage;
                while (!terminateFlag) {
                    outgoingMessage = bundle.getString("outgoingMessage");
                    outputStream.writeUTF(outgoingMessage);
                    outputStream.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void close() {
            try {
                hostThreadSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class InputThread extends Thread {
        private Socket hostThreadSocket;

        InputThread(Socket socket) {
            hostThreadSocket = socket;
        }

        @Override
        public void run() {
            DataInputStream inputStream;
            try {
                inputStream = new DataInputStream(hostThreadSocket.getInputStream());
                while (!terminateFlag) {
                    String incomingMessage = inputStream.readUTF();
                    bundle.putString("incomingMessage", incomingMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void close() {
            try {
                hostThreadSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
