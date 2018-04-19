package indianservers.com.englishtohindidictionary;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by JNTUH on 12-03-2018.
 */

public class TitlesAdapter extends BaseAdapter {
    ArrayList<TitlesClass> titlesClasses;
    ArrayList<TitlesClass> titles;
    Context context;
    TitlesAdapter(Context context,ArrayList<TitlesClass> titlesClasses){
        this.context = context;
        this.titlesClasses = titlesClasses;
        titles = new ArrayList<TitlesClass>();
        this.titles.addAll(titlesClasses);
    }
    @Override
    public int getCount() {
        return titlesClasses.size();
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
        TextView serial = (TextView)v.findViewById(R.id.titleSerial);
        TitlesClass titlesClass = titlesClasses.get(i);
        title.setText(titlesClass.getWord());
        serial.setText(titlesClass.getSerial());

        return v;
    }
    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        titlesClasses.clear();
        if (charText.length() == 0) {
            titlesClasses.addAll(titles);
        } else {
            for (TitlesClass wp : titles) {
                if (wp.getWord().toLowerCase(Locale.getDefault()).contains(charText)) {
                    titlesClasses.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
