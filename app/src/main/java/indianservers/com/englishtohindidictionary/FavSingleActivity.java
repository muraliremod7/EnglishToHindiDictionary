package indianservers.com.englishtohindidictionary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class FavSingleActivity extends AppCompatActivity {
    private TextView meaning_hindi,antonyms,example,adjective;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_single);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Favourites");
        meaning_hindi = (TextView)findViewById(R.id.favmeaning);
        antonyms = (TextView)findViewById(R.id.favantonyms);
        example = (TextView)findViewById(R.id.favexample);
        adjective = (TextView)findViewById(R.id.favadjective);

        Intent intent = getIntent();
        meaning_hindi.setText(intent.getStringExtra("meaning"));
        antonyms.setText(intent.getStringExtra("antonyms"));
        example.setText(intent.getStringExtra("example"));
        adjective.setText(intent.getStringExtra("adjective"));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
