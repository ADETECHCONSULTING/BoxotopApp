package fr.traore.adama.boxotopapp.utils.extensions

import android.content.ContextWrapper
import android.view.View
import androidx.appcompat.app.AppCompatActivity


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

fun Double.half() : Double{
    return this / 2
}