package indianservers.com.englishtohindidictionary;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by JNTUH on 24-03-2018.
 */

public class FavouritesAdapter extends BaseAdapter {
    private ArrayList<MeaningClass> titles;
    private Context context;
    public FavouritesAdapter(Context context,ArrayList<MeaningClass> meaningClasses){
        this.context = context;
        this.titles = meaningClasses;
    }
    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.single_title, null);
        TextView title = (TextView)v.findViewById(R.id.titleWord);
        TextView serial = (TextView)v.findViewById(R.id.titleMeaning);
        TextView antonyms = (TextView)v.findViewById(R.id.titleAntonyms);
        TextView example = (TextView)v.findViewById(R.id.titleExample);
        TextView adjective = (TextView)v.findViewById(R.id.titleAdjective);

        MeaningClass titlesClass = titles.get(i);
        title.setText(titlesClass.getWord());
        serial.setText(titlesClass.getSerial());
        antonyms.setText(titlesClass.getAnt());
        example.setText(titlesClass.getExm());
        adjective.setText(titlesClass.getEd());
        return v;
    }
}
