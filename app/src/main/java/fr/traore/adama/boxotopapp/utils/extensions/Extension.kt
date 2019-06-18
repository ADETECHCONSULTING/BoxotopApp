package fr.traore.adama.boxotopapp.utils.extensions

import android.app.Activity
import android.content.ContextWrapper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Activity.logd(message: String){
    Log.d(this::class.java.simpleName, message)
}

fun Fragment.logd(message: String){
    Log.d(this::class.java.simpleName, message)
}

fun Activity.shortToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.shortToast(message: String){
    Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun String.searchable() : String{
    return this.toLowerCase().trim()
}