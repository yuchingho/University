package uws.gymbuddy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by drhal on 06/03/2018.
 */

public class FiveColumn_ListAdapter extends ArrayAdapter<Routines> {

    private LayoutInflater mInflater;
    private ArrayList<Routines> users;
    private int mViewResourceId;

    public FiveColumn_ListAdapter (Context context, int textViewResourceId, ArrayList<Routines> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        Routines user = users.get(position);

        if (user != null) {
            TextView day = (TextView) convertView.findViewById(R.id.textDay);
            TextView exercise = (TextView) convertView.findViewById(R.id.textExercise);
            TextView reps= (TextView) convertView.findViewById(R.id.textNoReps);
            TextView sets= (TextView) convertView.findViewById(R.id.textNoSets);
            TextView weight= (TextView) convertView.findViewById(R.id.textWeight);

            if (day != null) {
                day.setText(user.getDay());
            }
            if (exercise != null) {
                exercise.setText((user.getExercise()));
            }
            if (reps != null) {
                reps.setText((user.getReps()));
            }
            if (sets != null) {
                sets.setText((user.getSets()));
            }
            if (weight != null) {
                weight.setText((user.getWeight()));
            }
        }
        return convertView;
    }
}