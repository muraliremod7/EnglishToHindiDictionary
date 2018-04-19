package indianservers.com.englishtohindidictionary;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import indianservers.com.englishtohindidictionary.database.DatabaseAccess;
import indianservers.com.englishtohindidictionary.database.DatabaseHelper;

public class SingleItemActivity extends AppCompatActivity {
    private TextView meaning_hindi,antonyms,example,adjective;
    private String serial,word;
    private ArrayList<MeaningClass> meaningClasses = new ArrayList<>();
    ProgressDialog pDialog;
    DatabaseHelper databaseHelper;
    private Cursor cursor=null;
    private MeaningClass meaningClass;
    Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_item);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        meaning_hindi = (TextView)findViewById(R.id.meaning);
        antonyms = (TextView)findViewById(R.id.antonyms);
        example = (TextView)findViewById(R.id.example);
        adjective = (TextView)findViewById(R.id.adjective);
        databaseHelper = new DatabaseHelper(this);
        Intent intent = getIntent();
        serial = intent.getStringExtra("serial");
        word  = intent.getStringExtra("word");
        meaningClass = new MeaningClass();
        getData(serial);
    }
    private void getData(String serial) {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(SingleItemActivity.this);
        meaningClasses = databaseAccess.getMeanings(serial);
        MeaningClass meaningClass = meaningClasses.get(0);
        meaning_hindi.setText(meaningClass.getWord());
                example.setText(meaningClasses.get(0).getExm());
                antonyms.setText(meaningClasses.get(0).getAnt());
                adjective.setText(meaningClasses.get(0).getEd());
//        for(int i=0;i<meaningClasses.size();i++){
//            String meaning = String.valueOf(meaningClasses.get(i).getSerial());
//            if(meaning.equals(serial)){
//                meaning_hindi.setText(meaningClasses.get(i).getWord());
//                example.setText(meaningClasses.get(i).getExm());
//                antonyms.setText(meaningClasses.get(i).getAnt());
//                adjective.setText(meaningClasses.get(i).getEd());
//            }
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
            case R.id.favourates:
                databaseHelper.insertNote(word,meaning_hindi.getText().toString(),antonyms.getText().toString(),example.getText().toString(),adjective.getText().toString());
                Toast.makeText(SingleItemActivity.this,word+" Has added in favourites",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
