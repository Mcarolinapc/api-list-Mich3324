package com.example.apilist.Navigation
import kotlinx.serialization.Serializable


sealed class Destinacion {
    @Serializable
    object Pantalla1 : Destinacion()

    @Serializable
    data class Pantalla2(val id: Int)

    @Serializable
    object Pantalla3

    @Serializable
    object Pantalla4

}