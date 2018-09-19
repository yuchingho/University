package uws.gymbuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.os.Build.ID;

/**
 * Created by Yoshi on 23/02/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "GymBuddy.db";

    // Table Names
    private static final String TABLE_ROUTINES = "routines";

    // private static final String TABLE_WEEKDAYS = "weekdays";

    // Common column names

    // Routines Table - column names
    private static final String KEY_ROUTINE_ID = "routine_id";
    private static final String KEY_EXERCISE = "exercise";
    private static final String KEY_REPS = "reps";
    private static final String KEY_SETS = "sets";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_DAY = "day";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
        // this.getWritableDatabase().execSQL("CREATE TABLE " + TABLE_ROUTINES + "(" + KEY_ROUTINE_ID + " INTEGER PRIMARY KEY, " + KEY_EXERCISE + " TEXT NOT NULL, " + KEY_REPS + " TEXT NOT NULL, " + KEY_SETS + " TEXT NOT NULL, " + KEY_WEIGHT + " TEXT NOT NULL, " + KEY_DAY + " TEXT NOT NULL)");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       System.out.println("***************************************************************************i");
       db.execSQL ("CREATE TABLE " + TABLE_ROUTINES + "(" + KEY_ROUTINE_ID + " INTEGER PRIMARY KEY, " + KEY_EXERCISE + " TEXT NOT NULL, " + KEY_REPS + " TEXT NOT NULL, " + KEY_SETS + " TEXT NOT NULL, " + KEY_WEIGHT + " TEXT NOT NULL, " + KEY_DAY + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTINES);
        // create new tables
        onCreate(db);
    }


    // Create a Routine
    public boolean addData(String exName, String noReps, String noSets, String noWeight, String myDays) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // values.put(KEY_ROUTINE_ID,item);
        values.put(KEY_EXERCISE, exName);
        values.put(KEY_REPS, noReps);
        values.put(KEY_SETS, noSets);
        values.put(KEY_WEIGHT,noWeight);
        values.put(KEY_DAY, myDays );
        //insert row
        System.out.println(values);
        long result = db.insert(TABLE_ROUTINES, null, values);

        // Check if Inserted
        if (result == -1)
            return false;
        else
            return true;
    }

    // Delete an exercise from the database
    public void deleteExercise(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_ROUTINES, KEY_ROUTINE_ID + " = '" + id + "'", null);
    }

    public Cursor getListContents(String day) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data;
        if (day != "All Days")
            data = db.rawQuery("SELECT * FROM " + TABLE_ROUTINES + " WHERE " + KEY_DAY + " ='" + day +"'", null);
        else data = db.rawQuery("SELECT * FROM " + TABLE_ROUTINES, null);
            return data;
    }

    // Cursor class provides random read-write access to the result set returned by a database query
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query =  "Select * from " + TABLE_ROUTINES;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
