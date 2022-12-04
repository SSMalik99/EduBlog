package com.example.edublog.helpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import com.example.edublog.models.BlogModel
import java.text.SimpleDateFormat
import java.time.Clock
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


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

        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val current = formatter.format(time)

        val values = contentValuesOf().apply {
            put("title", title)
            put("content", content)
            put("author", author)
            put("date", current)
        }

        val newRowId = sqliteDatabase.insert("blogs",null, values)
        return newRowId.toInt() != -1
    }

    fun allBlogs() : ArrayList<BlogModel> {
        val sqliteDatabase = this.readableDatabase
        var cursor = sqliteDatabase.rawQuery("Select * from blogs", null)
        val blogs = ArrayList<BlogModel>()

        while (cursor.moveToNext()) {
            val model = BlogModel(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                (cursor.getString(4))
            )

            blogs.add(model)
        }
        return  blogs
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