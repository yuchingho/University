package uws.gymbuddy;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by drhal on 27/02/2018.
 */

public class Routines extends ArrayList<Routines> {

    int routine_id;
    String exercise;
    String reps;
    String sets;
    String weight;
    String day;

    //constructors
    public Routines(String string, String String, String s, String string1, String dataString1) {}

    public Routines(int id, String exercise, String reps, String sets, String weight, String day) {
        this.routine_id = id;
        this.exercise = exercise;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
        this.day = day;
    }

    public int getRoutine_id() {
        return routine_id;
    }

    public void setRoutine_id(int routine_id) {
        this.routine_id = routine_id;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public Stream<Routines> stream() {
        return null;
    }
}

