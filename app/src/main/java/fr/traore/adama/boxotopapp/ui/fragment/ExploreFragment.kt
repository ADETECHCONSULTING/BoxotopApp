package fr.traore.adama.boxotopapp.ui.fragment


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.databinding.FragmentExploreBinding
import fr.traore.adama.boxotopapp.viewmodel.ExploreViewModel


class ExploreFragment : BaseFragment() {

    private lateinit var binding: FragmentExploreBinding
    private lateinit var viewModel: ExploreViewModel

    companion object {
        fun newInstance(): ExploreFragment {
            return ExploreFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity?.title = getString(R.string.explore_title)

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)
        val view = binding.root

        binding.rcvMovies.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        //ViewModel init
        viewModel = ViewModelProviders.of(this).get(ExploreViewModel::class.java)
        viewModel.errorMessage.observe(this,
            Observer { errorMessage ->
                if (errorMessage != null)
                    showError(binding.root, errorMessage, viewModel.errorClickListener)
                else
                    hideError()
            })

        //Set values to layout
        binding.viewModel = viewModel

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater)

        menuInflater.inflate(R.menu.menu_toolbar, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val refreshItem = menu.findItem(R.id.action_refresh)
        val searchManager: SearchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.getMovieListAdapter().filterData(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))

        refreshItem?.setOnMenuItemClickListener(viewModel.refreshOnClickListener)

    }


}
