package fr.traore.adama.boxotopapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.databinding.FragmentExploreBinding
import fr.traore.adama.boxotopapp.ui.adapter.MovieAdapter
import fr.traore.adama.boxotopapp.viewmodel.ExploreViewModel


class ExploreFragment : BaseFragment() {

    private lateinit var binding: FragmentExploreBinding
    private lateinit var viewModel: ExploreViewModel

    companion object {
        fun newInstance(): ExploreFragment {
            return ExploreFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)
        val view = binding.root

        binding.rcvMovies.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        viewModel = ViewModelProviders.of(this).get(ExploreViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {errorMessage ->
            if (errorMessage != null)
                showError(binding.root, errorMessage, viewModel.errorClickListener)
            else
                hideError()
        })

        //Set values to layout
        binding.viewModel = viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MovieAdapter(context!!)
        binding.rcvMovies.adapter = adapter
    }





}
