package ru.promise.educationKtApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.promise.educationKtApp.model.Movie

class FragmentMoviesDetails : Fragment() {

    private var backToMovieListListener: IBackToMovieListListener? = null
    private var selectedMovie: Movie? = null
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
        findViews(view)
        view.findViewById<TextView>(R.id.backButton).apply {
            setOnClickListener {
                backToMovieListListener?.backToMovieListTransition()
            }
        }
        name?.text = selectedMovie?.title

        Glide.with(context)
                .load(selectedMovie?.imageUrl)
                .apply(imageOption)
                .into(poster)

        pg?.text = "${selectedMovie?.pgAge.toString()}+"
        tagline?.text = selectedMovie!!.genres.joinToString(separator = ", ")
        reviews?.text = "${selectedMovie?.reviewCount.toString()} reviews"

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

        if (selectedMovie?.actors!=null && selectedMovie?.actors?.size != 0){
        recycler?.adapter = selectedMovie?.let { ActorAdapter(it.actors) }}
        else {
            castLineStatic?.isVisible=false;
        }
    }
    fun findViews(view: View){
        name = view.findViewById(R.id.movieName)
        pg = view.findViewById(R.id.pg)
        poster = view.findViewById(R.id.moviePoster)
        tagline = view.findViewById(R.id.genreLine)
        reviews = view.findViewById(R.id.reviewCounter)
        recycler = view.findViewById(R.id.rvActorList)
        recycler?.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        castLineStatic = view.findViewById(R.id.castLine)
    }

    override fun onDestroy() {
        selectedMovie = null
        backToMovieListListener = null
        super.onDestroy()

    }

    override fun onDetach() {
        selectedMovie = null
        backToMovieListListener = null
        super.onDetach()
    }

    fun setBackToMovieListListener(l: IBackToMovieListListener) {
        backToMovieListListener = l
    }


}