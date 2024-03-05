package com.gtihub.daniellimadev.model

data class Game(val title:String,
                val cover:String) {
    var description: String? = null
    override fun toString(): String {
        return "My Game: \n" +
                "Títle: $title \n" +
                "Cover: $cover \n" +
                "Description: $description"

    }


}