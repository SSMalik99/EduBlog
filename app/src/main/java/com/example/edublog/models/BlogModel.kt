package com.example.edublog.models

import com.google.android.material.datepicker.SingleDateSelector
import java.util.*

data class BlogModel(val id:Int, val title: String, val content: String, val author: String,val date: String){}
