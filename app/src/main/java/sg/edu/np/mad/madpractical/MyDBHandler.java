package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyDBHandler extends SQLiteOpenHelper {

    String title = "MyDBhandler";

    public static int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "accountDB.db";
    public static String ACCOUNTS = "Accounts";

    private static String COLUMN_ID = "id";
    private static String COLUMN_NAME = "name";
    private static String COLUMN_DESCRIPTION = "description";
    private static String COLUMN_FOLLOWED = "followed";


    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + ACCOUNTS + "("
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        onCreate(db);
    }

    public void addUser(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.name);
        values.put(COLUMN_DESCRIPTION, user.description);
        values.put(COLUMN_FOLLOWED, user.followed ? 1 : 0);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ACCOUNTS, null, values);
        Log.i(title, " Inserted/Created user" + values);
//        db.close();
    }

    public List<User> getUsers(){
        List<User> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOUNTS, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String description = cursor.getString(1);
                int id = cursor.getInt(2);
                int followedValue = cursor.getInt(3);
                boolean followed = followedValue == 1;

                User user = new User(name, description, id, followed);
                userList.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
//        db.close();

        return userList;
    }
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.name);
        values.put(COLUMN_DESCRIPTION, user.description);
        values.put(COLUMN_FOLLOWED, user.followed ? 1 : 0);

        db.update(ACCOUNTS, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.id)});


        db.close();
    }


}



