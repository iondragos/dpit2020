package com.example.dpit2020navem.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.dpit2020navem.OwnedObjectsList.OwnedObject;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;


public class OwnedObjectsDatabase extends SQLiteAssetHelper {

    public static final String DB_NAME = "OwnedObjectsDatabase.db";
    public static final String TABLE_NAME = "OwnedObjectsDetail";
    public static final int DB_VER = 1;
    public static final String COL1 = "IDD";
    public static final String COL2 = "ObjectId";
    public static final String COL3 = "ObjectType";
    public static final String COL4 = "ObjectName";
    public static final String COL5 = "ObjectDisinfectionTime";

    public OwnedObjectsDatabase(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public List<OwnedObject> getOwnedObjects() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ObjectId", "ObjectType", "ObjectName", "ObjectDisinfectionTime"};
        String sqlTable = "OwnedObjectsDetail";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        List<OwnedObject> result = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                OwnedObject ownedObject = extractOwnedObjectsFromCursor(c);

                result.add(ownedObject);
            } while (c.moveToNext());
        }

        return result;
    }

    private OwnedObject extractOwnedObjectsFromCursor(Cursor c) {

        OwnedObject ownedObject = new OwnedObject();
        ownedObject.setOwnedObjectId(c.getLong(c.getColumnIndex("ObjectId")));
        ownedObject.setOwnedObjectType(c.getString(c.getColumnIndex("ObjectType")));
        ownedObject.setOwnedObjectName(c.getString(c.getColumnIndex("ObjectName")));
        ownedObject.setOwnedObjectDisinfectionTime(c.getInt(c.getColumnIndex("ObjectDisinfectionTime")));

        return ownedObject;
    }

    public void addToOwnedObjectsDatabase(OwnedObject ownedObject) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OwnedObjectsDetail(ObjectId,ObjectType,ObjectName,ObjectDisinfectionTime) VALUES('%s','%s','%s','%s');",
                ownedObject.getOwnedObjectId(),
                ownedObject.getOwnedObjectType(),
                ownedObject.getOwnedObjectName(),
                ownedObject.getOwnedObjectDisinfectionTime());
        db.execSQL(query);
    }

    public void cleanOwnedObjectsDatabase() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OwnedObjectsDetail");
        db.execSQL(query);
    }

    public void removeObjectFromOwnedObjectsDatabase(Long ownedObjectId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OwnedObjectsDetail where ObjectId=%d", ownedObjectId);
        db.execSQL(query);
    }


    /*
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (IDD INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,ObjectId INTEGER, ObjectType TEXT,ObjectName TEXT,ObjectDisinfectionTime INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertObjectToOwnedObjectsDatabase(Long id, String type, String name, Integer disinfectionTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,id);
        contentValues.put(COL3,type);
        contentValues.put(COL4,name);
        contentValues.put(COL5,disinfectionTime);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getOwnedObjects() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

    public boolean updateObjectInOwnedObjectsDatabase(String IDD, Long id, String type, String name, Integer disinfectionTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1,IDD);
        contentValues.put(COL2,id);
        contentValues.put(COL3,type);
        contentValues.put(COL4,name);
        contentValues.put(COL5,disinfectionTime);
        db.update(TABLE_NAME, contentValues, "IDD = ?",new String[] { IDD });
        return true;
    }

    public Integer removeObjectFromOwnedObjectsDatabase (String IDD) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "IDD = ?",new String[] {IDD});
    }*/

}
