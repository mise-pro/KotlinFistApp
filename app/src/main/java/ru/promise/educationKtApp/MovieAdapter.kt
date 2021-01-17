package ru.promise.educationKtApp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.promise.educationKtApp.model.Movie


class MovieAdapter(
        private val clickItemMovieListener: IMovieSelectionListener
) : RecyclerView.Adapter<MovieViewHolder>() {
    private var movies = dataMock().getData()
    private val imageOption = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .fallback(R.drawable.ic_launcher_background)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(imageOption, movies[position])
        holder.itemView.setOnClickListener {
            clickItemMovieListener.movieSelectionClick(movies[position])
        }
    }

    override fun getItemCount(): Int = movies.size
}

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.movieName)
    private val poster: ImageView = itemView.findViewById(R.id.moviePoster)
    private val pg: TextView = itemView.findViewById(R.id.pg)
    private val tagline: TextView = itemView.findViewById(R.id.tagLine)
    private val reviews: TextView = itemView.findViewById(R.id.reviewCounter)
    private val duration: TextView = itemView.findViewById(R.id.duration)

    private val ratingStars = listOf<ImageView>(itemView.findViewById(R.id.rateStar1),
            itemView.findViewById(R.id.rateStar2),
            itemView.findViewById(R.id.rateStar3),
            itemView.findViewById(R.id.rateStar4),
            itemView.findViewById(R.id.rateStar5))


    fun onBind(options: RequestOptions, movie: Movie) {
        Glide.with(context)
                .load(movie.poster)
                .apply(options)
                .into(poster)

        name.text = movie.name
        pg.text = "${movie.pg.toString()}+"
        duration.text = "${movie.duration.toString()} min"
        tagline.text = movie.tagline
        reviews.text = "${movie.reviews.toString()} reviews"

        for (i in 0..4) {
            if (movie.rating > i) {
                ratingStars[i].setImageResource(R.drawable.star_icon_en)
            } else {
                ratingStars[i].setImageResource(R.drawable.star_icon_dis)
            }
        }

    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

