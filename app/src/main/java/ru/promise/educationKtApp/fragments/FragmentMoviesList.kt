package ru.promise.educationKtApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.promise.educationKtApp.IMovieSelectionListener
import ru.promise.educationKtApp.R
import ru.promise.educationKtApp.data.loadMovies
import ru.promise.educationKtApp.model.Movie

//todo add itemDecoration for movies

class FragmentMoviesList : Fragment(), IMovieSelectionListener {

    private var movieSelectionListener: IMovieSelectionListener? = null
    private var recycler: RecyclerView? = null
    private val scope = CoroutineScope(Dispatchers.IO)
    private var movies: List<Movie>? = null
    private var nothingToShowLabel: TextView? = null

    companion object {
        const val FRAGMENT_NAME = "FRAGMENT_MOVIE_LIST"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        // todo does it the right place to start coroutine? maybe mainActivity?
        scope.launch {
            movies = loadMovies(view.context)
            //Log.d("lengthOfMovie", movies?.size.toString())
            newMoviesLoaded()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rvMovieList)
        nothingToShowLabel = view.findViewById(R.id.nothingToShowLabel)
    }

    private suspend fun newMoviesLoaded() = withContext(Dispatchers.Main) {
        if (movies != null) {
            recycler?.adapter = MovieAdapter(getMovieSelectionListener(), movies!!)
        } else {
            nothingToShowLabel?.text = "No movies were downloaded. Seriously!"
        }
    }

    private fun getMovieSelectionListener(): IMovieSelectionListener {
        return this
    }

    override fun onDestroy() {
        recycler = null
        movieSelectionListener = null
        super.onDestroy()
    }

    override fun onDetach() {
        recycler = null
        movieSelectionListener = null
        super.onDetach()
    }

    fun sunscribeMovieSelection(l: IMovieSelectionListener) {
        movieSelectionListener = l
    }

    override fun movieSelectionClick(movie: Movie) {
        recycler?.let { rv ->
            Snackbar.make(
                rv,
                "You chose: ${movie.title}",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
        movieSelectionListener?.movieSelectionClick(movie)
    }
}