package com.example.edublog.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class DataBaseHelper ( val context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME,null,
    DATABASE_VERSION
) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "edublog.db"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        // do nothing
    }


//    fun singleCompany(companyId : Int) : CompanyModel {
//        val sqliteDatbase = this.writableDatabase
//        val cursor = sqliteDatbase.rawQuery("SELECT * FROM companies where ID= $companyId", null)
//        cursor.moveToFirst()
//
//        val model = CompanyModel(
//            cursor.getInt(0),
//            cursor.getString(1),
//            cursor.getString(2),
//            cursor.getString(3)
//        )
//        cursor.close()
//        return model
//
//
//
//    }


//    fun singleTutorial(tutorialId : Int) : TutorialModel {
//        val sqLiteDatabase = this.readableDatabase
//
//        val query = "SELECT * FROM tutorials where ID=$tutorialId"
//
//        val cursor = try {
//            sqLiteDatabase.rawQuery(query, null)
//        }catch (e:Exception) {
//            arrangeDatabase(sqLiteDatabase)
//            sqLiteDatabase.rawQuery(query, null)
//        }
//        cursor.moveToFirst()
//        val tutorial = TutorialModel(
//            cursor.getInt(0),
//            cursor.getString(1),
//            cursor.getString(2),
//            cursor.getString(3),
//            cursor.getInt(4) == 1,
//            cursor.getInt(5),
//        )
//        cursor.close()
//
//        return tutorial
//    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


}