package com.example.needcalendar;

import com.example.needcalendar.list;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBcheck extends SQLiteOpenHelper {

    // 체크리스트 저장 정보 테이블

    private static final String DATABASE_NAME = "ChecklistDB.db";
    public static final String TABLE_NAME = "check_table_name";
    private static final int DATABASE_VERSION = 3;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_PLACE = "place";
    public static final String COLUMN_MEMO = "memo";

    private static final String DATABASE_CREATE =
            "CREATE TABLE check_table_name (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "place TEXT," +
                    "memo TEXT" +
                    ");";

    public DBcheck(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insertData(String title, String place, String memo, boolean isChecked) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_PLACE, place);
        values.put(COLUMN_MEMO, memo);
        // 데이터를 데이터베이스에 추가하고 결과를 반환합니다.
        return db.insert(TABLE_NAME, null, values);
    }

    public long deleteData(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = DBcheck.COLUMN_ID + " = ?";
        String[] whereArgs = {String.valueOf(id)};
        int deletedRows = db.delete(DBcheck.TABLE_NAME, whereClause, whereArgs);
        db.close();


        // 삭제된 항목의 ID를 반환합니다.
        if (deletedRows > 0) {
            return id;
        } else {
            return -1;
        }

    }

    public List<list> getAllItems() {
        List<list> items = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_PLACE, COLUMN_MEMO};

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE));
            String place = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLACE));
            String memo = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MEMO));

            list item = new list(id, title, place, memo);
            items.add(item);
        }

        cursor.close();
        return items;
    }


}
