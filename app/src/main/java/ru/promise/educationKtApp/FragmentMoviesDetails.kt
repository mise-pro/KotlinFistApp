package ru.promise.educationKtApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.promise.educationKtApp.model.Movie

class FragmentMoviesDetails : Fragment() {

    private var backToMovieListListener: IBackToMovieListListener? = null
    private var recycler: RecyclerView? = null
    private var selectedMovie: Movie? = null
    private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)

    companion object {
        const val FRAGMENT_NAME = "VISIBLE_FRAGMENT"
        const val SELECTED_MOVIE = "SELECTED_MOVIE"
        fun newInstance(movie: Movie): FragmentMoviesDetails {
            val fragment = FragmentMoviesDetails()
            val bundle = Bundle()
            bundle.putParcelable(SELECTED_MOVIE, movie)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedMovie = arguments?.getParcelable(SELECTED_MOVIE)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.backButton).apply {
            setOnClickListener {
                backToMovieListListener?.backToMovieListTransition()
            }
        }

        val name: TextView = view.findViewById(R.id.movieName)
        val pg: TextView = view.findViewById(R.id.pg)
        val poster: ImageView = view.findViewById(R.id.moviePoster)
        val tagline: TextView = view.findViewById(R.id.tagLine)
        val reviews: TextView = view.findViewById(R.id.reviewCounter)

        name.text = selectedMovie?.name

        Glide.with(context)
                .load(selectedMovie?.poster)
                .apply(imageOption)
                .into(poster)

        pg.text = "${selectedMovie?.pg.toString()}+"
        tagline.text = selectedMovie?.tagline
        reviews.text = "${selectedMovie?.reviews.toString()} reviews"

        val ratingStars = listOf<ImageView>(view.findViewById(R.id.rateStar1),
                view.findViewById(R.id.rateStar2),
                view.findViewById(R.id.rateStar3),
                view.findViewById(R.id.rateStar4),
                view.findViewById(R.id.rateStar5))

        for (i in 0..4) {
            if (selectedMovie!!.rating > i) {
                ratingStars[i].setImageResource(R.drawable.star_icon_en)
            } else {
                ratingStars[i].setImageResource(R.drawable.star_icon_dis)
            }
        }

        recycler = view.findViewById(R.id.rvActorList)

        recycler?.adapter = selectedMovie?.let { ActorAdapter(it.actorList) }
    }

    override fun onDestroy() {
        super.onDestroy()
        backToMovieListListener = null
    }

    fun setBackToMovieListListener(l: IBackToMovieListListener) {
        backToMovieListListener = l
    }


}