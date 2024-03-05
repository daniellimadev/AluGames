package com.gtihub.daniellimadev.main
import com.gtihub.daniellimadev.model.Gamer
fun main() {
    val gamer1 = Gamer("Daniel", "daniel@email.com")
    println(gamer1)

    val gamer2 = Gamer(
        "Bruna",
        "bruna@email.com",
        "19/19/1992",
        "jeniblo")

    println(gamer2)

    gamer1.let {
        it.birthDate = "18/09/2000"
        it.user = "danielPereira"

    }.also {
        println(gamer1.internalId)
    }

    println(gamer1)
    gamer1.user= "Daniel"
    println(gamer1)
}