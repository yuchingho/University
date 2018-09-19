package uws.gymbuddy;

import android.text.Editable;

/**
 * Created by drhal on 21/02/2018.
 */

public class WorkoutItem {
    String routineName;
    String exercise;
    int sets, reps, weight, days, weeks, months;

    public WorkoutItem(String routineName, String exercise, int sets, int reps) {
        this.routineName = routineName;
        this.exercise = exercise;
        this.sets = sets;
        this.reps = reps;
    }
}