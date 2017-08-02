package com.example.android.emsense3.Activity;

//Received intent containing EXTRA_MESSAGE from the specific object activity which specify the serialnumber of the item.
//Query the IMAGE database for the rows containing the serialnumber
//Create the ViewPager Fragment to show the tutorial in a slide left-and-right layout, dynamically generating the image and text.


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.emsense3.R;
import com.example.android.emsense3.data.ItemsContract.ImageEntry;
import com.example.android.emsense3.data.ItemsDbHelper;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by setia on 7/22/2017.
 */

public class TutorialStepActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int numOfSteps;
    private String serialNumber;
    private Cursor cursor;
    private Button btnClose, btnNext;
    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == numOfSteps - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(getString(R.string.tutorial_step_done));
                btnClose.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(getString(R.string.tutorial_step_next));
                btnClose.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tutorial_step);

        Intent intent = getIntent();
        serialNumber = intent.getStringExtra(EXTRA_MESSAGE);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnClose = (Button) findViewById(R.id.btn_close);

        cursor = createCursor();


        numOfSteps = cursor.getCount();

        addBottomDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < numOfSteps) {
                    //move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    onBackPressed();
                }
            }
        });
    }

    //Initialising the dots on the bottom of the screen based on number of slides
    private void addBottomDots(int currentPage) {
        dots = new TextView[numOfSteps];
        int colorsActive = getResources().getColor(R.color.White);
        int colorsInactive = getResources().getColor(R.color.DarkGrey);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);

        }

        if (dots.length > 0) {
            dots[currentPage].setTextColor(colorsActive);
        }
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }


    private Cursor createCursor() {
        ItemsDbHelper mDbHelper = new ItemsDbHelper(this);

        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();


        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                //LibraryEntry._ID,
                ImageEntry.COLUMN_STEP,
                ImageEntry.COLUMN_IMAGE_ID,
                ImageEntry.COLUMN_TEXT_ID
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = ImageEntry.COLUMN_SERIAL_NUMBER + " = ?";
        String[] selectionArgs = {serialNumber};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                ImageEntry.COLUMN_STEP + " ASC";

        Cursor cursor = dbRead.query(
                ImageEntry.TABLE_NAME,                  // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        return cursor;
    }

    public static class TutorialStepFragment extends Fragment {
        public int mNum, stepNumber, imageId, textId;
        int imageIdColumnIndex, textIdColumnIndex;
        String stepText;
        ImageView imageView;
        TextView textViewInstructions, textViewCurrentStep;
        private Cursor mCursor;

        public static TutorialStepFragment newInstance(int num, Cursor cursor) {
            TutorialStepFragment f = new TutorialStepFragment();
            f.setCursor(cursor);

            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);
            return f;
        }


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? mNum = getArguments().getInt("num") : 1;


        }

        @Override
        public void onDestroy() {
            super.onDestroy();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            mCursor.moveToPosition(mNum);
            stepNumber = mNum + 1;
            imageIdColumnIndex = mCursor.getColumnIndex(ImageEntry.COLUMN_IMAGE_ID);
            textIdColumnIndex = mCursor.getColumnIndex(ImageEntry.COLUMN_TEXT_ID);
            imageId = mCursor.getInt(imageIdColumnIndex);
            textId = mCursor.getInt(textIdColumnIndex);
            stepText = "STEP " + stepNumber;
            View v = inflater.inflate(R.layout.fragment_tutorial_step, container, false);


            imageView = v.findViewById(R.id.fragment_tutorial_step_imageView);
            imageView.setImageResource(imageId);

            textViewCurrentStep = v.findViewById(R.id.fragment_tutorial_step_textView_currentStep);
            textViewCurrentStep.setText(stepText);


            textViewInstructions = v.findViewById(R.id.fragment_tutorial_step_textView_instruction);
            textViewInstructions.setText(textId);


            return v;
        }


        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
        }

        private void setCursor(Cursor cursor) {
            mCursor = cursor;
        }


    }

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TutorialStepFragment.newInstance(position, cursor);
        }


        @Override
        public int getCount() {
            return numOfSteps;
        }
    }


}
