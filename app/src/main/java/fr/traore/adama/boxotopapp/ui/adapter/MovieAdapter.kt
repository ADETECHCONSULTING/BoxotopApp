package fr.traore.adama.boxotopapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.model.MovieItem
import fr.traore.adama.boxotopapp.utils.Constants
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieHolder>(){

    var movies: List<MovieItem>? = null

    fun resetData(items: List<MovieItem>){
        this.movies = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): MovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)

        val mHolder = MovieHolder(view)

        //Click Event

        return mHolder
    }

    override fun getItemCount(): Int {
        return if(movies == null) 0 else movies!!.size
    }

    override fun onBindViewHolder(holder: MovieHolder, pos: Int) {
        val movie = movies?.get(pos)

        if(movie != null){
            holder.txvTitle.text = movie.title
            holder.txvSubText.text = movie.overview

            Picasso.get().load(Constants.BASE_IMAGE_URL+movie.posterPath).into(holder.imvMovie)
        }

    }


    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imvMovie = itemView.imv_movie
        val txvTitle = itemView.txv_title
        val txvSubText = itemView.txv_subtext
        val txvContent = itemView.txv_content
    }
}