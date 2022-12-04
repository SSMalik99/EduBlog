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

    fun singleBlog(blogId : Int) : BlogModel{
        val sqliteDatabase = this.readableDatabase
        var cursor = sqliteDatabase.rawQuery("Select * from blogs where id=$blogId", null)
        val blogs = ArrayList<BlogModel>()

        cursor.moveToFirst()

        val blog = BlogModel(cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            (cursor.getString(4))
        )

        cursor.close()

        return  blog
    }

    fun updateBlog(blogId:Int, title: String, content: String, author: String) : Boolean{
        val sqliteDatabase = this.writableDatabase

        val values = contentValuesOf().apply {
            put("title", title)
            put("content", content)
            put("author", author)
        }
        val result = sqliteDatabase.update("blogs", values, "id=$blogId",null)

        return result >= 1

    }

    fun deleteBlog(blogId: Int) : Boolean{
        val sqliteDatabase = this.writableDatabase
        return sqliteDatabase.delete("blogs", "id=$blogId", null) >= 1

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP table blogs;")
    }


}