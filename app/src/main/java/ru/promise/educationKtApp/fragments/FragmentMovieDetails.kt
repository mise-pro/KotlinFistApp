package ru.promise.educationKtApp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.promise.educationKtApp.MainActivityViewModel
import ru.promise.educationKtApp.R
import ru.promise.educationKtApp.model.Movie

class FragmentMovieDetails : Fragment() {

    private val imageOption = RequestOptions()
        .placeholder(R.drawable.ic_launcher_background)
        .fallback(R.drawable.ic_launcher_background)

    private var name: TextView? = null
    private var pg: TextView? = null
    private var poster: ImageView? = null
    private var tagline: TextView? = null
    private var reviews: TextView? = null
    private var recycler: RecyclerView? = null
    private var castLineStatic: TextView? = null
    private var ratingStars: List<ImageView>? = null

    private var backButton: TextView? = null

    private val movieDetailsVM: MovieDetailsViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)
        findViews(view)
        return view
    }

    fun initSubscription(
        mainActivityViewModel: MainActivityViewModel
    ) {
        movieDetailsVM.initSubscription(mainActivityViewModel)

        mainActivityViewModel.activityState.observe(this, { state ->
            if (state is MainActivityViewModel.State.MovieDetails) {
                showMovieDetails(state.selectedMovie)
            }
        })

    }

    fun showMovieDetails(selectedMovie: Movie) {
        name?.text = selectedMovie.title

        Glide.with(context)
            .load(selectedMovie.imageUrl)
            .apply(imageOption)
            .into(poster)

        pg?.text = "${selectedMovie.pgAge}+"
        tagline?.text = selectedMovie.genres.joinToString(separator = ", ")
        reviews?.text = "${selectedMovie?.reviewCount.toString()} reviews"

        for (i in 0..4) {
            if (selectedMovie.rating > i) {
                ratingStars?.get(i)?.setImageResource(R.drawable.star_icon_en)
            } else {
                ratingStars?.get(i)?.setImageResource(R.drawable.star_icon_dis)
            }
        }

        if (selectedMovie.actors?.size != 0) {
            recycler?.adapter = selectedMovie.let { ActorAdapter(it.actors) }
        } else {
            castLineStatic?.isVisible = false;
        }
    }


    fun findViews(view: View) {
        backButton = view.findViewById(R.id.backButton)
        backButton?.setOnClickListener { movieDetailsVM.backButtonPressed() }

        name = view.findViewById(R.id.movieName)
        pg = view.findViewById(R.id.pg)
        poster = view.findViewById(R.id.moviePoster)
        tagline = view.findViewById(R.id.genreLine)
        reviews = view.findViewById(R.id.reviewCounter)
        recycler = view.findViewById(R.id.rvActorList)
        recycler?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        castLineStatic = view.findViewById(R.id.castLine)

        ratingStars = listOf<ImageView>(
            view.findViewById(R.id.rateStar1),
            view.findViewById(R.id.rateStar2),
            view.findViewById(R.id.rateStar3),
            view.findViewById(R.id.rateStar4),
            view.findViewById(R.id.rateStar5)
        )
    }
}