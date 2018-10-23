package com.example.egghunt3r.favoritoapp
data class FavoritoResult(val favoritos: List<Item>)  // favoristos es la llave por la cual va a buscar en el arreglo de json

data class Item (
    val id: String?,
    val title: String?,
    val description: String?,
    val url: String?
)

