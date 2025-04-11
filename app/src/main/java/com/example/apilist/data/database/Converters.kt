package com.example.apilist.data.database

import android.net.Uri
import androidx.room.TypeConverter

/*class Converters {
    @TypeConverter
    fun fromListToString(list: List<String>): String{
        return list.joinToString(",")
    }

    @TypeConverter
    fun fromStringToList(string: String): List<String>{
        return string.split(",")
    }
}*/

class Converters {

    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return uriString?.let { Uri.parse(it) }
    }
}

