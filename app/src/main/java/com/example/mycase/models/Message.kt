package com.example.mycase.models

import com.google.firebase.Timestamp


data class Message(
    var id:String?="",
    val senderId:String="",
    val content:String="",
    val senderName:String="",
    val time:Timestamp=Timestamp.now()
){
    companion object{
        const val COLLECTION_NAME="messages"
    }
}
