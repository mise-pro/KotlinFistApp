package ru.promise.educationKtApp.fragments

import androidx.lifecycle.ViewModel
import ru.promise.educationKtApp.MainActivityViewModel

class MovieDetailsViewModel(
) : ViewModel() {

    private var mainActivityVM: MainActivityViewModel? = null

    fun initVM(
        mainActivityViewModel: MainActivityViewModel
    ) {
        mainActivityVM = mainActivityViewModel
    }

    fun backButtonPressed() {
        mainActivityVM?.toMovieList()
    }

}