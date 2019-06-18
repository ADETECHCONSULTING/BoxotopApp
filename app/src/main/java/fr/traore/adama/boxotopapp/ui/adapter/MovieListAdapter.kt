package fr.traore.adama.boxotopapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.databinding.ItemMovieBinding
import fr.traore.adama.boxotopapp.model.MovieItem
import fr.traore.adama.boxotopapp.ui.MainActivity
import fr.traore.adama.boxotopapp.ui.fragment.DetailFragment
import fr.traore.adama.boxotopapp.utils.extensions.searchable
import fr.traore.adama.boxotopapp.viewmodel.MovieListItemViewModel

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    private lateinit var movieList: List<MovieItem>
    private lateinit var lockedMovieList : List<MovieItem>

    fun resetData(items: List<MovieItem>) {
        this.movieList = items
        this.lockedMovieList = items
        notifyDataSetChanged()
    }

    fun filterData(text: String) {
        this.movieList = lockedMovieList.filter { item ->
            item.title.searchable().contains(text.searchable()) ||
                    item.overview.searchable().contains(text.searchable())
        }

        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): MovieHolder {
        val binding: ItemMovieBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)

        return MovieHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::movieList.isInitialized) movieList.size else 0
    }

    override fun onBindViewHolder(holder: MovieHolder, pos: Int) {
        holder.bind(movieList[pos])
    }


    class MovieHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private val viewModel = MovieListItemViewModel()

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(item: MovieItem) {
            viewModel.bind(item)
            binding.viewModel = viewModel
        }

        override fun onClick(v: View?) {
            val activity = v?.context as MainActivity
            activity.changeFragment(DetailFragment.newInstance(viewModel.movieId.value))
        }


    }
}