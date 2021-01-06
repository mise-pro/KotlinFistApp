package ru.promise.educationKtApp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentMoviesDetails: Fragment() {

    private var backButton : TextView? = null
    private var listener: IFragmentMovieDetailsListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movies_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backButton = view.findViewById<TextView>(R.id.backButton).apply {
            setOnClickListener{
                listener?.backButtonPressed()}
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener = null
    }

    interface IFragmentMovieDetailsListener{
        fun backButtonPressed()
    }

    fun setClickListener (l: IFragmentMovieDetailsListener){
        listener = l
    }
}