package com.example.mycase.profile

data class User(var name:String,var specilization:String,var streetName:String,var buildingNum:String,var city:String,var customersNum:String,
var experience:String,var phoneNum:String,var email:String
                ){
    constructor() : this("", "", "",
    "","","","",""
    ,""
        )
}
