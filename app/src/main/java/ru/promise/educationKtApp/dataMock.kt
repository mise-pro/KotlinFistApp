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
                Movie("movieName1", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg", pg = 16, tagline = "action, drama", rating = 4, reviews = 1001, storyLine = storyLine,
                        actorList = listOf(actors[0], actors[1])),
                Movie("movieName2", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg", pg = 4, tagline = "action, fiction", rating = 3, reviews = 2001, storyLine = storyLine,
                        actorList = listOf(actors[2], actors[3])),
                Movie("movieName3", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg", pg = 10, tagline = "action, drama", rating = 2, reviews = 3001, storyLine = storyLine,
                        actorList = listOf(actors[0], actors[1], actors[8], actors[5])),
                Movie("movieName4", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg", pg = 12, tagline = "fiction, drama", rating = 1, reviews = 4001, storyLine = storyLine,
                        actorList = listOf(actors[0], actors[1], actors[4], actors[5])),
                Movie("movieName5", "https://image.ibb.co/nKNBrd/Alicia_Vikander.jpg", pg = 21, tagline = "fun, drama", rating = 5, reviews = 5001, storyLine = storyLine,
                        actorList = listOf(actors[0], actors[1], actors[2], actors[8]))
        )
        return data
    }
}
