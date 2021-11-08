package com.xcomp.ytemoi.utils
import android.content.Context.MODE_PRIVATE
import android.content.Context
import android.content.SharedPreferences
import com.xcomp.ytemoi.R


class Utils {
    companion object{
        fun getCommonSharepreference(context: Context): SharedPreferences? {
            return context.getSharedPreferences(
                context.resources.getString(R.string.common_preference),
                MODE_PRIVATE
            )
        }

        fun setCommonSharepreference(context: Context, key: String?, value: String?) {
            val pref = context.getSharedPreferences(
                context.resources.getString(R.string.common_preference),
                MODE_PRIVATE
            ).edit()
            pref.putString(key, value)
            pref.apply()
        }

        fun removeCommonSharepreference(context: Context, key: String?) {
            val pref = context.getSharedPreferences(
                context.resources.getString(R.string.common_preference),
                MODE_PRIVATE
            ).edit()
            pref.remove(key).commit()
        }

        fun ChecPhoneknumber(number:String):Boolean{
            var reg = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$"
            var p = java.util.regex.Pattern.compile(reg)
            var m = p.matcher(number)
            return m.matches()
        }

    }
}