package fr.traore.adama.boxotopapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import fr.traore.adama.boxotopapp.R
import fr.traore.adama.boxotopapp.ui.fragment.ExploreFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(ExploreFragment.newInstance())

        setSupportActionBar(toolbar)
    }


    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }


    fun changeFragment(f: Fragment, cleanStack: Boolean = false){
        val ft = supportFragmentManager.beginTransaction()
        if(cleanStack)
            clearBackStack()

        ft.replace(R.id.ctnFragment, f)
        ft.addToBackStack(null)
        ft.commit()
    }

    private fun clearBackStack(){
        val manager = supportFragmentManager
        if(manager.backStackEntryCount > 0){
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun setTitle(title: String){
        toolbar.title = title
    }
}
