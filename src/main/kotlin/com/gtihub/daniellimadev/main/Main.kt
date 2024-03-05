package com.gtihub.daniellimadev.main

import com.gtihub.daniellimadev.model.Gamer
import com.gtihub.daniellimadev.model.Game
import com.gtihub.daniellimadev.services.ConsumptionApi
import java.util.Scanner
import transformEmAge


fun main() {
    val reading = Scanner(System.`in`)
    val gamer = Gamer.createGamer(reading)
    println("Registration completed successfully. Gamer data:")
    println(gamer)
    println("Gamer age: " + gamer.birthDate?.transformEmAge())

    do {
        println("Enter a game code to search:")
        val search = reading.nextLine()

        val searchApi = ConsumptionApi()
        val informationGame = searchApi.searchGame(search)


        var myGame: Game? = null

        val result = runCatching {
            myGame = Game(
                informationGame.info.title,
                informationGame.info.thumb
            )
        }

        result.onFailure {
            println("Nonexistent game. Try another id.")
        }

        result.onSuccess {
            println("Want to enter a custom description? Y/N")
            val option = reading.nextLine()
            if (option.equals("y", true)) {
                println("Enter your custom description for the game:")
                val descriptionPersonalized = reading.nextLine()
                myGame?.description = descriptionPersonalized
            } else {
                myGame?.description = myGame?.title

            }

            gamer.gamesSearch.add(myGame)
        }

        println("Want to look for a new game? Y/N")
        val response = reading.nextLine()

    } while (response.equals("y", true))

    println("Games searched:")
    println(gamer.gamesSearch)

    println("\n Games sorted by title: ")
    gamer.gamesSearch.sortBy {
        it?.title
    }

    gamer.gamesSearch.forEach {
        println("Title: " + it?.title)
    }

    val gamesFiltered = gamer.gamesSearch.filter {
        it?.title?.contains("batman", true) ?: false
    }
    println("\n Filtered games: ")
    println(gamesFiltered)

    println("Do you want to delete any games from the original list? Y/N")
    val option = reading.nextLine()
    if (option.equals("y", true)) {
        println(gamer.gamesSearch)
        println("\nEnter the position of the game you want to delete: ")
        val position =reading.nextInt()
        gamer.gamesSearch.removeAt(position)
    }

    println("\n Updated list:")
    println(gamer.gamesSearch)

    println("Search completed successfully.")

}