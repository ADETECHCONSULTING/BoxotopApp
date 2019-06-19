package fr.traore.adama.boxotopapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.utils.extensions.getParentActivity
import fr.traore.adama.boxotopapp.utils.extensions.half


@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value ?: View.VISIBLE })
    }

}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }

}

@BindingAdapter("mutableAppendText")
fun setMutableAppendText(view: TextView, text: MutableLiveData<String>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->

            view.text = view.context.getString(R.string.release_date, value)

        })
    }
}


@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("picasso")
fun setPicasso(view: ImageView, text: MutableLiveData<String>?) {

    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && text != null) {

        text.observe(parentActivity, Observer {

            Picasso.get().load(text.value).into(view)

        })
    }

}

@BindingAdapter("fillRating")
fun setFillRating(view: AppCompatRatingBar, stars: MutableLiveData<Double>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()

    if (parentActivity != null && stars != null) {
        stars.observe(parentActivity, Observer {
            view.rating = stars.value?.half()?.toFloat() ?: 0f
            view.invalidate()
        })
    }

}