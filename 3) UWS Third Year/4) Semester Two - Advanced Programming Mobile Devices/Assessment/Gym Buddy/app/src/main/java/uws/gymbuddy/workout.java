package uws.gymbuddy;
import java.util.Arrays;

/**
 * Created by drhal on 19/02/2018.
 */

public class workout {

    protected WorkoutItem[] routines;
    int size;

    public workout(int initialcapacity) {
        routines = new WorkoutItem[initialcapacity];
        size = 0;
    }

    public workout()
    {
        this(5);
    }

    public void addWorkoutItem(WorkoutItem workoutItem) {
        if (size >= routines.length)
            increaseCapacity();
            routines[size++] = workoutItem;
    }

    private void increaseCapacity() {
        routines = Arrays.copyOf(routines,routines.length*2);
    }

    public void removeWorkoutItem(int id) {
        routines[id] = null;
        for (int i = id; i < routines.length; i++) {
            routines [i] = routines [i+1];
        }
        size --;
    }

    public void editWorkoutItem(int id, WorkoutItem workoutItem) {
        routines[id] = workoutItem;
    }
}