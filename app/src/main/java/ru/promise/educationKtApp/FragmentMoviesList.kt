package ru.promise.educationKtApp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class FragmentMoviesList: Fragment() {

    private var cardClicked : CardView? = null
    private var listener: IFragmentMoviesListListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cardClicked = view.findViewById<CardView>(R.id.movieCardView).apply {
            setOnClickListener{
                listener?.movieCardClick(0)
                Log.d("setOnClickListener","catch!")}
            //todo pass real movieId
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
    }

    interface IFragmentMoviesListListener{
        fun movieCardClick(movieId : Int)
    }

    fun setClickListener (l: IFragmentMoviesListListener){
        listener = l
    }
}