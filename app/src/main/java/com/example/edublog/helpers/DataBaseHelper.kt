package com.example.edublog.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import java.util.*


class DataBaseHelper ( val context: Context): SQLiteOpenHelper(context,
    DATABASE_NAME,null,
    DATABASE_VERSION
) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "edublog.db"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE blogs(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, author TEXT, date TEXT );")
    }

    fun createBlog( title: String, content: String, author: String ): Boolean {

        val sqliteDatabase = this.writableDatabase

        val values = contentValuesOf().apply {
            put("title", title)
            put("content", content)
            put("author", author)
            put("date", Date().toString())
        }

        val newRowId = sqliteDatabase.insert("blogs",null, values)
        return newRowId.toInt() != -1
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
        db?.execSQL("DROP table blogs;")
    }


}