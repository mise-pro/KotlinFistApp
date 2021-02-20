package ru.promise.networktest.netModule

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.*
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import ru.promise.educationKtApp.fragments.MovieListViewModel
import ru.promise.educationKtApp.model.Movie
import ru.promise.networktest.netModule.dataClass.*

class NetworkModule {

    var coroutineScope = createCoroutineScope()
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(LOG_TAG, "Coroutine exception, scope active:${coroutineScope.isActive}", throwable)
        coroutineScope = createCoroutineScope()
    }

    fun createCoroutineScope() = CoroutineScope(Job() + Dispatchers.IO)

    suspend fun init() {
        if (!initialized) {
            val config = RetrofitModule.movieAPI.getConfig()
            BASE_IMAGE_URL = config.config.baseUrl
            initialized = true
        }
    }

    fun getMoviesPopular(fragment: MovieListViewModel) {
        coroutineScope.launch(exceptionHandler) {
            init()
            val movieItemList = RetrofitModule.movieAPI.getMoviesPopular().results
            var movieList = ObjConverter().convertToMovieList(movieItemList)
            val genresItemList = RetrofitModule.movieAPI.getGenres().genres
            val genresList = ObjConverter().convertToGenreList(genresItemList)
            movieList = ObjConverter().updateWithGenres(movieList, genresList)
            movieList = ObjConverter().updateImageUrlForMovie(movieList, BASE_IMAGE_URL + IMAGE_QUALITY)
            fragment.receiveResult(movieList)
        }
    }

    fun getMovieDetails(movie: Movie, fragment: MovieListViewModel) {
        coroutineScope.launch(exceptionHandler) {
            val castItems = RetrofitModule.movieAPI.getMovieCredits(movie.id).cast
            var updatedMovie = ObjConverter().updateWithActors(movie,castItems)
            updatedMovie = ObjConverter().updateImageUrlForActors(updatedMovie, BASE_IMAGE_URL + IMAGE_QUALITY)
            //fragment.receiveResult2(updatedMovie)
        }

    }

    companion object {
        private const val LOG_TAG = "NetworkModule - exceptionHandler"
        private const val API_KEY = "c732b0d876698b21c1fd4c8168519bdc"
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private var BASE_IMAGE_URL = ""
        private const val IMAGE_QUALITY = "w300"
        private var initialized = false;
    }

    interface MovieAPI {
        @GET("movie/popular?api_key=${API_KEY}")
        suspend fun getMoviesPopular(): MoviePopularResponse

        //https://api.themoviedb.org/3/configuration?api_key=c732b0d876698b21c1fd4c8168519bdc
        @GET("configuration?api_key=${API_KEY}")
        suspend fun getConfig(): ConfigResponse

        @GET("movie/{movie_id}/credits?api_key=${API_KEY}")
        suspend fun getMovieCredits(@Path("movie_id") movieId: Int): CreditsResponse

        @GET("person/{person_id}?api_key=${API_KEY}")
        suspend fun getActor(@Path("person_id") personId: Int): PersonResponse

        @GET("genre/movie/list?api_key=${API_KEY}")
        suspend fun getGenres(): GenresResponse

        /*@GET
        suspend fun testReq(@Url url:String): ConfigResponse*/
    }


    internal object RetrofitModule {
        internal val json = Json {
            ignoreUnknownKeys = true
        }

        @Suppress("EXPERIMENTAL_API_USAGE")
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        val movieAPI: MovieAPI = retrofit.create()

    }


}