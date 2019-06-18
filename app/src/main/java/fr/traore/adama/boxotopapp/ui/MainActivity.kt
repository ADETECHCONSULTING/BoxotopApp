package fr.traore.adama.boxotopapp.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        val searchItem = menu?.findItem(R.id.action_search)
        val searchManager:SearchManager = this.getSystemService(Context.SEARCH_SERVICE) as SearchManager

        var searchView: SearchView? = null
        if(searchItem != null){
            searchView = searchItem.actionView as SearchView
        }

        searchView?.setSearchableInfo(searchManager.getSearchableInfo(this.componentName))

        return super.onCreateOptionsMenu(menu)
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

    fun clearBackStack(){
        val manager = supportFragmentManager
        if(manager.backStackEntryCount > 0){
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }
}
