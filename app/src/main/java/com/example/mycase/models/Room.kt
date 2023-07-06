package com.example.mycase.models

data class Room(
    var id:String?="",
    val title:String="",
    val description:String="",
    val categoryId:String=""
){
    companion object{
        val COLLECTION_NAME="rooms"
    }
}
