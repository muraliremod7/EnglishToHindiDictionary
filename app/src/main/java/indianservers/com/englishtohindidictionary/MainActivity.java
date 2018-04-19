package indianservers.com.englishtohindidictionary;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import indianservers.com.englishtohindidictionary.database.DatabaseAccess;

public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener,View.OnClickListener {
    private SearchView sv;
    private ListView listView;
    private ArrayList<TitlesClass> titlesClasses;
    private ArrayList<String> strings = new ArrayList<>();
    private TitlesAdapter titlesAdapter;
    Map<String, Integer> mapIndex;
    ProgressDialog dialog;
    boolean isLoading=false;
    int next;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);
        getWords();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.main_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // attaching bottom sheet behaviour - hide / show on scroll
        // Show progress dialog
        displayIndex();
        sv= (SearchView) findViewById(R.id.searchview);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //SEARCH FILTER
//                titlesAdapter.filter(newText);
                getWordss(newText);
                return false;
            }
        });

    }
    private void displayIndex() {
        LinearLayout indexLayout = (LinearLayout) findViewById(R.id.side_index);

        TextView textView;
        List<String> indexList = new ArrayList<String>();
        indexList.add("A");
        indexList.add("B");
        indexList.add("C");
        indexList.add("D");
        indexList.add("E");
        indexList.add("F");
        indexList.add("G");
        indexList.add("H");
        indexList.add("I");
        indexList.add("J");
        indexList.add("K");
        indexList.add("L");
        indexList.add("M");
        indexList.add("N");
        indexList.add("O");
        indexList.add("P");
        indexList.add("Q");
        indexList.add("R");
        indexList.add("S");
        indexList.add("T");
        indexList.add("U");
        indexList.add("V");
        indexList.add("W");
        indexList.add("X");
        indexList.add("Y");
        indexList.add("Z");
        for (String index : indexList) {
            textView = (TextView) getLayoutInflater().inflate(R.layout.side_index_item, null);
            textView.setText(index);
            textView.setOnClickListener(this);
            indexLayout.addView(textView);
        }
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(MainActivity.this,FavouritesActivity.class));
                    finish();
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                    finish();
                    return true;
            }
            return false;
        }
    };
    private void getWords() {
        dialog = new ProgressDialog(MainActivity.this);
        // Set progress dialog title
        // Set progress dialog message
        dialog.setMessage("Loading more...");
        dialog.setIndeterminate(false);
        dialog.show();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
        databaseAccess.openDatabase();
        Log.d("sai", "in open create");
        titlesClasses = databaseAccess.getQuotes("englishword");
        databaseAccess.closeDatabase();
        titlesAdapter = new TitlesAdapter(this,titlesClasses);
        listView.setAdapter(titlesAdapter);
        dialog.dismiss();
        titlesAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String serial = ((TextView)view.findViewById(R.id.titleSerial)).getText().toString();
                String word = ((TextView)view.findViewById(R.id.titleWord)).getText().toString();
                Intent intent = new Intent(MainActivity.this,SingleItemActivity.class);
                intent.putExtra("serial",serial);
                intent.putExtra("word",word);
                startActivity(intent);
            }
        });
    }
    private void getWordss(String newText) {
        dialog = new ProgressDialog(MainActivity.this);
        // Set progress dialog title
        // Set progress dialog message
        dialog.setMessage("Loading more...");
        dialog.setIndeterminate(false);
        dialog.show();
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(MainActivity.this);
        databaseAccess.openDatabase();
        Log.d("sai", "in open create");
        titlesClasses = databaseAccess.getQuotess("eng",newText);
        databaseAccess.closeDatabase();
        titlesAdapter = new TitlesAdapter(this,titlesClasses);
        listView.setAdapter(titlesAdapter);
        dialog.dismiss();
        titlesAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String serial = ((TextView)view.findViewById(R.id.titleSerial)).getText().toString();
                String word = ((TextView)view.findViewById(R.id.titleWord)).getText().toString();
                Intent intent = new Intent(MainActivity.this,SingleItemActivity.class);
                intent.putExtra("serial",serial);
                intent.putExtra("word",word);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    @Override
    public void onClick(View view) {
        TextView selectedIndex = (TextView) view;
        String newtext = selectedIndex.getText().toString();
        getWordss(newtext);
    }
}
