package com.example.mycase.models

data class AppUser(
    var id:String?="",
    var name:String?="",
    var email:String?=""
) {
    companion object {
        val COLLECTION_NAME = "users"
    }
}
