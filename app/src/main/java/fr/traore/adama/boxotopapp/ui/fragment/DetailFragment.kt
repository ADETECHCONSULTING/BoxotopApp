package fr.traore.adama.boxotopapp.ui.fragment

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.databinding.FragmentDetailBinding
import fr.traore.adama.boxotopapp.viewmodel.DetailViewModel



class DetailFragment : BaseFragment(){


    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel

    companion object {
        val bundleMovieId = "BundleMovieId"

        fun newInstance(id: Int?): DetailFragment{
            val args = Bundle()
            args.putInt(bundleMovieId, id ?: -1)
            val detailFragment = DetailFragment()
            detailFragment.arguments = args
            return detailFragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bundle = arguments
        val movieId = bundle?.getInt(bundleMovieId)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        val view = binding.root

        //ViewModel init
        viewModel = ViewModelProviders.of(this, viewModelFactory { DetailViewModel(movieId) }).get(DetailViewModel::class.java)
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

}