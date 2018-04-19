package indianservers.com.englishtohindidictionary;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import indianservers.com.englishtohindidictionary.database.DatabaseHelper;

public class FavouritesActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<MeaningClass> titlesClasses;
    private DatabaseHelper databaseHelper;
    private FavouritesAdapter favouritesAdapter;
    private BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        getSupportActionBar().setTitle("Favourites");
        listView = (ListView)findViewById(R.id.favourateslistview);
        navigation = (BottomNavigationView) findViewById(R.id.fav_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        databaseHelper = new DatabaseHelper(this);
        getWords();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    startActivity(new Intent(FavouritesActivity.this,FavouritesActivity.class));
                    finish();
                    return true;
                case R.id.navigation_dashboard:
                    startActivity(new Intent(FavouritesActivity.this,MainActivity.class));
                    finish();
                    return true;
            }
            return false;
        }
    };
    private void getWords() {
        titlesClasses = databaseHelper.getAllNotes();
        favouritesAdapter = new FavouritesAdapter(this,titlesClasses);
        listView.setAdapter(favouritesAdapter);
        favouritesAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String serial = ((TextView)view.findViewById(R.id.titleMeaning)).getText().toString();
                String word = ((TextView)view.findViewById(R.id.titleWord)).getText().toString();
                String antonyms = ((TextView)view.findViewById(R.id.titleAntonyms)).getText().toString();
                String example = ((TextView)view.findViewById(R.id.titleExample)).getText().toString();
                String adjective = ((TextView)view.findViewById(R.id.titleAdjective)).getText().toString();

                Intent intent = new Intent(FavouritesActivity.this,FavSingleActivity.class);
                intent.putExtra("meaning",serial);
                intent.putExtra("word",word);
                intent.putExtra("antonyms",antonyms);
                intent.putExtra("example",example);
                intent.putExtra("adjective",adjective);
                startActivity(intent);
            }
        });
    }
}
