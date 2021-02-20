package ru.promise.educationKtApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.promise.educationKtApp.MainActivityViewModel
import ru.promise.educationKtApp.R
import ru.promise.educationKtApp.model.Movie

//todo add itemDecoration for movies

class FragmentMovieList() : Fragment() {

    private var recycler: RecyclerView? = null
    private val scopeMain = CoroutineScope(Dispatchers.Main)

    private var movies: List<Movie>? = null
    private var nothingToShowLabel: TextView? = null

    private val movieListViewModel: MovieListViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_list, container, false)
        movieListViewModel.currentMovieList.observe(
            this.viewLifecycleOwner,
            { movies ->
                newMoviesLoaded(movies)
                showNewMoviesLoadedHint()
            })
        movieListViewModel.getMovieList()
        return view
    }

    private fun newMoviesLoaded(newMovies: List<Movie>) {
        movies = newMovies
        scopeMain.launch {
            if (movies != null) {
                recycler?.adapter = MovieAdapter(movies!!, movieListViewModel)
            } else {
                nothingToShowLabel?.text = "No movies were downloaded. Seriously!"
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.rvMovieList)
        nothingToShowLabel = view.findViewById(R.id.nothingToShowLabel)
    }


    override fun onDestroy() {
        recycler = null
        super.onDestroy()
    }

    override fun onDetach() {
        recycler = null
        super.onDetach()
    }

    fun showNewMoviesLoadedHint() {
        recycler?.let { rv ->
            Snackbar.make(
                rv,
                "New movies downloaded!",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    fun showSelectedMovieHint(movie: Movie) {
        recycler?.let { rv ->
            Snackbar.make(
                rv,
                "You choose: ${movie.title}",
                Snackbar.LENGTH_SHORT
            )
                .show()
        }
    }

    fun initSubscription(mainActivityViewModel: MainActivityViewModel) {
        movieListViewModel.initSubscription(mainActivityViewModel)
        mainActivityViewModel.activityState.observe(this, { state ->
            if (state is MainActivityViewModel.State.MovieDetails) {
                showSelectedMovieHint(state.selectedMovie)
            }
        })
    }

}