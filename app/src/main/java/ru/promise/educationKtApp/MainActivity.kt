package ru.promise.educationKtApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(), FragmentMoviesList.IFragmentMoviesListListener, FragmentMoviesDetails.IFragmentMovieDetailsListener {

    private var moviesListFragment : FragmentMoviesList? = null
    private var movieDetailsFragment : FragmentMoviesDetails? = null
    private var visibleFragment : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moviesListFragment = FragmentMoviesList().apply { setClickListener(this@MainActivity) }
        supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.mainFrame, moviesListFragment!!)
                    commit()
                }
        movieDetailsFragment = FragmentMoviesDetails().apply { setClickListener(this@MainActivity) }
        supportFragmentManager.beginTransaction()
                .apply {
                    add(R.id.mainFrame, movieDetailsFragment!!)
                    commit()
                }

        if (savedInstanceState==null) {
            visibleFragment = FragmentMoviesList.FRAGMENT_NAME
            setupUi()
        }


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(VISIBLE_FRAGMENT, visibleFragment)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        visibleFragment = savedInstanceState.getString(VISIBLE_FRAGMENT)

        setupUi()
    }

    private fun setupUi() {
        if (visibleFragment == FragmentMoviesDetails.FRAGMENT_NAME) {
            supportFragmentManager.beginTransaction()
                    .apply {
                        show(movieDetailsFragment!!)
                        commit()
                    }
        } else {
            supportFragmentManager.beginTransaction()
                    .apply {
                        hide(movieDetailsFragment!!)
                        commit()
                    }
        }

    }

    override fun movieCardClick(movieId: Int) {
        visibleFragment = FragmentMoviesDetails.FRAGMENT_NAME
        supportFragmentManager.beginTransaction()
            .apply {
                show(movieDetailsFragment!!)
                commit()
                //TODO pass args to movieDetails
            }
    }

    override fun backButtonPressed() {
        visibleFragment = FragmentMoviesList.FRAGMENT_NAME
        supportFragmentManager.beginTransaction()
            .apply {
                hide(movieDetailsFragment!!)
                commit()
            }
    }

    companion object {
        private const val VISIBLE_FRAGMENT = "VISIBLE_FRAGMENT"
    }
}