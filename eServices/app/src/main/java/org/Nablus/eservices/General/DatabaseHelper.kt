package org.Nablus.eservices.General
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASENAME,null,DBVERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        CreateDatabase(db)
    }


    override fun onUpgrade(db: SQLiteDatabase?, OldVersion: Int, NewVersion: Int) {

    }


    fun CreateDatabase(db: SQLiteDatabase?) {
        try {
        } catch (ex: Exception) {
            Log.d("Error", ex.toString())
        }
    }
}