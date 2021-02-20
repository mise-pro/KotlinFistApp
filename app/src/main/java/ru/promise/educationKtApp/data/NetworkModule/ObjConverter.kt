package ru.promise.networktest.netModule

import ru.promise.educationKtApp.model.Actor
import ru.promise.educationKtApp.model.Genre
import ru.promise.educationKtApp.model.Movie
import ru.promise.networktest.netModule.dataClass.CastItem
import ru.promise.networktest.netModule.dataClass.GenresItem
import ru.promise.networktest.netModule.dataClass.MovieItem

class ObjConverter {
    fun convertToMovieList(movieItemList: List<MovieItem>): List<Movie> {
        val movieList = mutableListOf<Movie>()
        for (item in movieItemList) {
            val movie = Movie(
                id = item.id,
                pgAge = 18,
                title = item.title,
                genres = convertGenreItemIdListToGenre(item.genreIds),
                imageUrl = item.posterPath,
                detailImageUrl = item.posterPath,
                runningTime = 100,
                isLiked = false,
                rating = Math.round(item.voteAverage).toInt(),
                storyLine = item.overview,
                reviewCount = item.voteCount,
                actors = mutableListOf()
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun convertToGenreList(genresItemList: List<GenresItem>): List<Genre> {
        val genreList = mutableListOf<Genre>()
        for (item in genresItemList) {
            val genre = Genre(
                id = item.id,
                name = item.name
            )
            genreList.add(genre)
        }
        return genreList
    }

    private fun convertGenreItemIdListToGenre(genreItemIdList: List<Int>): List<Genre> {
        val genreList = mutableListOf<Genre>()
        for (item in genreItemIdList) {
            val genre = Genre(
                id = item,
                name = ""
            )
            genreList.add(genre)
        }
        return genreList
    }

    private fun convertActorItemIdListToActor(actorItemIdList: List<Int>): List<Actor> {
        val actorList = mutableListOf<Actor>()
        for (item in actorItemIdList) {
            val actor = Actor(
                id = item,
                name = "",
                imageUrl = ""
            )
            actorList.add(actor)
        }
        return actorList
    }

    fun updateWithActors(movie: Movie, persons: List<CastItem>): Movie {
        val actorList = mutableListOf<Actor>()
        for (item in persons) {
            if ((item.profilePath != null) && (item.name != null)) {
                val actor = Actor(
                    id = item.id,
                    name = item.name,
                    imageUrl = item.profilePath
                )
                actorList.add(actor)
            }
        }
        movie.actors = actorList
        return movie
    }

    fun updateWithGenres(movieList: List<Movie>, genresList: List<Genre>): List<Movie> {
        for (movie in movieList) {
            for (genre in movie.genres) {
                for (item in genresList) {
                    if (item.id == genre.id) {
                        genre.name = item.name
                        break
                    }
                }
            }
        }
        return movieList
    }

    fun updateImageUrlForMovie(movieList: List<Movie>, url: String): List<Movie> {
        for (movie in movieList) {
            movie.imageUrl = url + movie.imageUrl
            movie.detailImageUrl = url + movie.detailImageUrl
        }
        return movieList
    }

    fun updateImageUrlForActors(movie: Movie, url: String): Movie {
        for (actor in movie.actors) {
            actor.imageUrl = url + actor.imageUrl
        }
        return movie
    }

}
