package ru.promise.educationKtApp

import ru.promise.educationKtApp.model.Actor
import ru.promise.educationKtApp.model.Movie

class dataMock {
    public fun getData() : List<Movie> {
        val actors = listOf<Actor>(
                Actor("Alicia Vikander", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg"),
                Actor("Amanda Seyfried", "https://image.ibb.co/j142xJ/Amanda_Seyfried.jpg"),
                Actor("Anne Hathaway", "https://image.ibb.co/euy6Py/Anne_Hathaway.jpg"),
                Actor("Emma Stone", "https://image.ibb.co/dJY6Py/Emma_Stone.jpg"),
                Actor("Keira Knightley", "https://image.ibb.co/cxX0jy/Keira_Knightley.jpg"),
                Actor("George Clooney", "https://image.ibb.co/ce1t4y/George_Clooney.jpg"),
                Actor("Lucy Liu", "https://image.ibb.co/dWurrd/Lucy_Liu.jpg"),
                Actor("Matthew McConaughey", "https://image.ibb.co/e3JHWd/Matthew_Mc_Conaughey.jpg"),
                Actor("Morgan Freeman", "https://image.ibb.co/h9GhxJ/Morgan_Freeman.jpg"),
                Actor("Ryan Gosling", "https://image.ibb.co/buLLjy/Ryan_Gosling.jpg"),
                Actor("Will Smith", "https://image.ibb.co/gxoUcJ/Will_Smith.jpg"),
                Actor("Robert de Niro", "https://image.ibb.co/e6T6Py/Robert_de_Niro.jpg"),
                Actor("Zoe Saldana", "https://image.ibb.co/i9WRPy/Zoe_Saldana.jpg")
        )
        val storyLine = "asdasdasd asdadasd asdasdasdas asdasdasdasd asdasdasdasdad"
        val data = listOf<Movie>(
                Movie("movieName1", "https://m.media-amazon.com/images/M/MV5BM2EwMmRhMmUtMzBmMS00ZDQ3LTg4OGEtNjlkODk3ZTMxMmJlXkEyXkFqcGdeQXVyMjM5ODk1NDU@._V1_UY209_CR0,0,140,209_AL_.jpg", pg = 16, tagline = "action, drama", rating = 5, reviews = 1001, storyLine = storyLine,
                        duration = 120, actorList = listOf(actors[0], actors[1])),
                Movie("movieName2", "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UY209_CR0,0,140,209_AL_.jpg", pg = 4, tagline = "action, fiction", rating = 4, reviews = 2001, storyLine = storyLine,
                        duration = 140, actorList = listOf(actors[2], actors[3])),
                Movie("movieName3", "https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_UY209_CR3,0,140,209_AL_.jpg", pg = 10, tagline = "action, drama", rating = 3, reviews = 3001, storyLine = storyLine,
                        duration = 160, actorList = listOf(actors[0], actors[1], actors[8], actors[5])),
                Movie("movieName4", "https://m.media-amazon.com/images/M/MV5BODY0NTA5OTMtMDFmYy00NzlmLWE1MWUtYTVmY2E5YjZkNTZkXkEyXkFqcGdeQXVyNjEwNTM2Mzc@._V1_UY209_CR0,0,140,209_AL_.jpg", pg = 12, tagline = "fiction, drama", rating = 2, reviews = 4001, storyLine = storyLine,
                        duration = 100, actorList = listOf(actors[0], actors[1], actors[4], actors[5])),
                Movie("movieName5", "https://m.media-amazon.com/images/M/MV5BNDI1MzM0Y2YtYmIyMS00ODE3LTlhZjEtZTUyNmEzMTNhZWU5XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_UY209_CR0,0,140,209_AL_.jpg", pg = 21, tagline = "fun, drama", rating = 0, reviews = 5001, storyLine = storyLine,
                        duration = 20, actorList = listOf(actors[0], actors[1], actors[2], actors[8]))
        )
        return data
    }
}
