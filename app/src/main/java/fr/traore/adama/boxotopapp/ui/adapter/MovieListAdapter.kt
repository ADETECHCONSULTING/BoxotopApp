package fr.traore.adama.boxotopapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.databinding.ItemMovieBinding
import fr.traore.adama.boxotopapp.model.MovieItem
import fr.traore.adama.boxotopapp.utils.Constants
import fr.traore.adama.boxotopapp.viewmodel.MovieListItemViewModel
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieHolder>(){

    private lateinit var movieList: List<MovieItem>

    fun resetData(items: List<MovieItem>){
        this.movieList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): MovieHolder {
        val binding : ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)

        return MovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::movieList.isInitialized) movieList.size else 0
    }

    override fun onBindViewHolder(holder: MovieHolder, pos: Int) {
        holder.bind(movieList.get(pos))
    }


    class MovieHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {

        private val viewModel = MovieListItemViewModel()

        fun bind(item: MovieItem) {
            viewModel.bind(item)
            binding.viewModel = viewModel
        }
    }
}