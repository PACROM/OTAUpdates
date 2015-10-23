package com.ota.updates.db.helpers;
/*
 * Copyright (C) 2015 Matt Booth.
 *
 * Licensed under the Attribution-NonCommercial-ShareAlike 4.0 International
 * (the "License") you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://creativecommons.org/licenses/by-nc-sa/4.0/legalcode
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ota.updates.utils.Constants;

public class BaseSQLiteHelper extends SQLiteOpenHelper implements Constants {

    public BaseSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createVersionTable(db);
        createUploadTable(db);
        createAddonTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + VERSION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UPLOAD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ADDON_TABLE_NAME);
        this.onCreate(db);
    }

    /**
     * Crates the Addon table in the database
     * @param db  The database to create the table in
     */
    private void createAddonTable(SQLiteDatabase db) {
        // SQL statement to create addon table
        String CREATE_ADDON_TABLE = "CREATE TABLE IF NOT EXISTS " + ADDON_TABLE_NAME + " (" +
                NAME_ID + " INTEGER PRIMARY KEY," +
                NAME_NAME + " TEXT," +
                NAME_SLUG + " TEXT," +
                NAME_DESCRIPTION + " TEXT," +
                NAME_CREATED_AT + " TEXT," +
                NAME_PUBLISHED_AT + " TEXT," +
                NAME_DOWNLOADS + " INTEGER," +
                NAME_SIZE + " INTEGER," +
                NAME_MD5 + " TEXT," +
                NAME_DOWNLOAD_LINK + " TEXT," +
                NAME_CATEGORY + " TEXT" +
                ")";
        // create addon table
        db.execSQL(CREATE_ADDON_TABLE);
    }

    /**
     * Creates the Upload table in the database
     * @param db  The database to create the table in
     */
    private void createUploadTable(SQLiteDatabase db) {
        // SQL statement to create upload table
        String CREATE_UPLOAD_TABLE = "CREATE TABLE IF NOT EXISTS " + UPLOAD_TABLE_NAME + " (" +
                NAME_ID + " INTEGER PRIMARY KEY," +
                NAME_SIZE + " INTEGER," +
                NAME_MD5 + " TEXT," +
                NAME_STATUS + " TEXT," +
                NAME_DOWNLOADS + " INTEGER," +
                NAME_DOWNLOAD_LINK + " TEXT" +
                ")";
        // create upload table
        db.execSQL(CREATE_UPLOAD_TABLE);
    }

    /**
     * Creates the Version table in the database
     * @param db  The database to create the table in
     */
    private void createVersionTable(SQLiteDatabase db) {
        // SQL statement to create version table
        String CREATE_VERSION_TABLE = "CREATE TABLE IF NOT EXISTS " + VERSION_TABLE_NAME + " (" +
                NAME_ID + " INTEGER PRIMARY KEY," +
                NAME_FULL_NAME + " TEXT," +
                NAME_SLUG + " TEXT," +
                NAME_ANDROID_VERSION + " TEXT," +
                NAME_CHANGELOG + " TEXT," +
                NAME_CREATED_AT + " TEXT," +
                NAME_PUBLISHED_AT + " TEXT," +
                NAME_DOWNLOADS + " INTEGER," +
                NAME_VERSION_NUMBER + " INTEGER," +
                NAME_FULL_ID + " INTEGER," +
                NAME_DELTA_ID + " INTEGER" +
                ")";
        // create version table
        db.execSQL(CREATE_VERSION_TABLE);
    }

    /**
     * Gets all the entries in the supplied table name
     * @param tableName  The table to collect the entries from
     * @return  A Cursor object with the entries in
     */
    public Cursor getAllEntries(String tableName) {
        String query = "SELECT * FROM " + tableName;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(query, null);
    }
}
