package ru.promise.educationKtApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.promise.educationKtApp.model.Movie

//todo add itemDecoration for movies

class FragmentMoviesList : Fragment(), IMovieSelectionListener {

    private var movieSelectionListener: IMovieSelectionListener? = null
    private var recycler: RecyclerView? = null

    companion object {
        const val FRAGMENT_NAME = "FRAGMENT_MOVIE_LIST"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.rvMovieList)
        recycler?.adapter = MovieAdapter(this)
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
                "You chose: ${movie.name}",
                Snackbar.LENGTH_SHORT)
                .show()
        }
        movieSelectionListener?.movieSelectionClick(movie)
    }
}