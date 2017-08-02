package com.example.android.emsense3.Activity;

/**
 * Created by slzh645 on 7/11/2017.
 */

//Activity controlling the Library page
//1. Query the database for the content of the user's database
//2. Create the cards using Album and AlbumAdapterLibrary class
//3. Display the cards in the library page based on the items in the category e.g Laptop category

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.android.emsense3.Other.ObjectItemClickListener;
import com.example.android.emsense3.R;
import com.example.android.emsense3.data.ItemsContract.LibraryEntry;
import com.example.android.emsense3.data.ItemsDbHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class ObjectsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ObjectItemClickListener {

    private static final String TAG = "ObjectsActivity";
    private ItemsDbHelper mDbHelper = new ItemsDbHelper(this);
    private RecyclerView recyclerView;
    private AlbumAdapterObjects adapter;
    private List<Album> albumList;
    private ImageView bannerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objects);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        bannerImage = (ImageView) findViewById(R.id.objects_banner);

        toolbar.setTitle("Laser Cutters");
        setSupportActionBar(toolbar);
        initCollapsingToolbar();


        //Database Stuff

        SQLiteDatabase dbRead = mDbHelper.getReadableDatabase();


        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
//                LibraryEntry._ID,
                LibraryEntry.COLUMN_SERIAL_NUMBER,
                LibraryEntry.COLUMN_MODEL,
                LibraryEntry.COLUMN_ICON_ID



        };

        // Filter results WHERE "title" = 'My Title'
        String selection = LibraryEntry.COLUMN_ITEMS + " = ?";

        Intent intent = getIntent();
        String object = intent.getStringExtra(EXTRA_MESSAGE);
        setBannerImage(object);



        String[] selectionArgs = {object};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                LibraryEntry.COLUMN_MODEL + " ASC";

        Cursor cursor = dbRead.query(
                LibraryEntry.TABLE_NAME,                  // The table to query
                projection,                               // The columns to return
                selection,                                     // The columns for the WHERE clause
                selectionArgs,                                     // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        albumList = new ArrayList<>();
        adapter = new AlbumAdapterObjects(this, albumList, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums(cursor);
        cursor.close();

        try {
            Glide.with(this).load(R.drawable.library_banner).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onDestroy() {
        mDbHelper.close();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        ArrayList<Album> newList = new ArrayList<>();
        for (Album album : albumList) {
            String name = album.getName().toLowerCase();
            if (name.contains(newText))
                newList.add(album);
        }

        adapter.setFilter(newList);
        return true;
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums(Cursor cursor) {

        Album a;





        int modelNameColumnIndex = cursor.getColumnIndex(LibraryEntry.COLUMN_MODEL);
        String modelName;
//        int itemsNameColumnIndex = cursor.getColumnIndex(LibraryEntry.COLUMN_ITEMS);
//        String itemsName = cursor.getString(itemsNameColumnIndex);
        int iconIdColumnIndex = cursor.getColumnIndex(LibraryEntry.COLUMN_ICON_ID);
        int iconId;
        int i = 0;
        int counter = 1;

        while (cursor.moveToNext()) {
            modelName = cursor.getString(modelNameColumnIndex);
            iconId = cursor.getInt(iconIdColumnIndex);
            a = new Album(modelName, counter, iconId);
            albumList.add(a);
        }
//        a = new Album(modelName, counter, imageId);
//        albumList.add(a);


//        Set<String> objectSet = new LinkedHashSet<String>();
//        for (int i = 0; i < cursor.getCount();i++){
//            cursor.moveToNext();
//            int nameColumnIndex =
//                    cursor.getColumnIndex(LibraryEntry.COLUMN_ITEMS);
//            String name = cursor.getString(nameColumnIndex);
//            objectSet.add(name);
//            String done = "done" + i;
//            Log.v(TAG,done);
//        }
//        cursor.close();
//        Log.v(TAG,objectSet.toString());

//        Album a;
//        Iterator iterator = objectSet.iterator();
//        while (iterator.hasNext()){
//            Object name = iterator.next();
//            a = new Album(name.toString(), 1, covers[0]);
//            albumList.add(a);
//        }


        adapter.notifyDataSetChanged();
    }


    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public void onObjectItemClick(int pos, Album albumItem, ImageView objectView) {
        Intent intent = new Intent(this, SpecificObjectActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setEnterTransition(new Slide());
//            getWindow().setEnterTransition(new Fade(Fade.OUT));

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    objectView,
                    "image_transition");
            String object = albumItem.getName();
            intent.putExtra(EXTRA_MESSAGE, object);
            startActivity(intent, options.toBundle());
        }

    }

    public void setBannerImage(String objectName) {
        Map banner = new HashMap();
//        add image accordingly here
        banner.put("3D Printer", R.drawable.banner_threedprinter);
        banner.put("Mobile Phone", R.drawable.banner_phone);
        banner.put("Laser Cutter", R.drawable.banner_lasercutter);
        banner.put("Laptop", R.drawable.banner_laptop);
        banner.put("Printer", R.drawable.banner_printer);
        bannerImage.setImageResource((int) banner.get(objectName));
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }
}
