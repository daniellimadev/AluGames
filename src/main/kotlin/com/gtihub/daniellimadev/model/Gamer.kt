package com.gtihub.daniellimadev.model

import java.util.Scanner
import kotlin.random.Random

data class Gamer(var name:String, var email:String) {
    var birthDate:String? = null
    var user:String? = null
        set(value) {
            field = value
            if(internalId.isNullOrBlank()) {
                createInternalId()
            }
        }
    var internalId:String? = null
        private set
    val gamesSearch = mutableListOf<Game?>()

    constructor(name: String, email: String, birthDate:String, user:String):
            this(name, email) {
        this.birthDate = birthDate
        this.user = user
        createInternalId()
    }

    init {
        if (name.isNullOrBlank()) {
            throw IllegalArgumentException("Name is blank")
        }
        this.email = validateEmail()
    }

    override fun toString(): String {
        return "Gamer(name='$name', email='$email', birthDate=$birthDate, user=$user, internalId=$internalId)"
    }

    fun createInternalId() {
        val number = Random.nextInt(10000)
        val tag = String.format("%04d", number)

        internalId = "$user#$tag"
    }

    fun validateEmail(): String {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (regex.matches(email)) {
            return email
        } else {
            throw IllegalArgumentException("Invalid email")
        }

    }

    companion object {
        fun createGamer(reading: Scanner): Gamer {
            println("Welcome to AluGames! Let's register you. Type your name:")
            val name = reading.nextLine()
            println("Type your e-mail:")
            val email = reading.nextLine()
            println("Do you want to complete your registration with username and date of birth? (Y/N)")
            val option = reading.nextLine()

            if (option.equals("y", true)) {
                println("Enter your date of birth (DD/MM/YYYY):")
                val birth = reading.nextLine()
                println("Enter your username:")
                val user = reading.nextLine()

                return Gamer(name, email, birth, user)
            } else {
                return Gamer (name, email)
            }

        }
    }

}
