package fr.traore.adama.boxotopapp.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import fr.traore.adama.boxotopapp.R

abstract class BaseFragment : Fragment(){

    private var errorSnackbar: Snackbar? = null

    protected fun showError(view: View, errorMessage:Int, retryListener : View.OnClickListener){
        errorSnackbar = Snackbar.make(view, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, retryListener)
        errorSnackbar?.show()
    }

    protected fun hideError() {
        errorSnackbar?.dismiss()
    }

    protected inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
        }
}