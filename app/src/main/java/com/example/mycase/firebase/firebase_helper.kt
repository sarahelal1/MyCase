package com.example.mycase.firebase

import com.example.mycase.sign_in.Navigator
import com.example.mycase.models.AppUser
import com.example.mycase.models.Message
import com.example.mycase.models.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Document

fun addToFireStore(user:AppUser,successListener: OnSuccessListener<in Void>
                   , failureListener: OnFailureListener){
    val fireStore=Firebase.firestore
    val documentRef =fireStore.collection(AppUser.COLLECTION_NAME).document(user.id?:"")
    documentRef.set(user).addOnSuccessListener(successListener)
        .addOnFailureListener(failureListener)

}
fun getUserFromFireStore(uid:String,onSuccess:OnSuccessListener<DocumentSnapshot>
                         ,onFailure:OnFailureListener){
    val fireStore=Firebase.firestore
    fireStore.collection(AppUser.COLLECTION_NAME).document(uid)
        .get().addOnSuccessListener(onSuccess)
        .addOnFailureListener(onFailure)
}
fun addRoomToFireStore(room: Room, successListener: OnSuccessListener<in Void>
                       , failureListener: OnFailureListener){
    val fireStore=Firebase.firestore
    val docRef = fireStore.collection(Room.COLLECTION_NAME).document()
    room.id=docRef.id
    docRef.set(room).addOnSuccessListener (successListener)
        .addOnFailureListener(failureListener)
}
fun getAllRooms(onSuccessListener: OnSuccessListener<QuerySnapshot>,onFailure: OnFailureListener){
    val fireStore=Firebase.firestore
    fireStore.collection(Room.COLLECTION_NAME).get().addOnSuccessListener(
        onSuccessListener
    ).addOnFailureListener(onFailure)

}
fun addMessageToFireStore(roomId:String,message: Message,successListener: OnSuccessListener<in Void>
                          , failureListener: OnFailureListener){
    val fireStore=Firebase.firestore
    val docRef= fireStore.collection(Room.COLLECTION_NAME).document(roomId).
    collection(Message.COLLECTION_NAME)
        .document()
    message.id=docRef.id
    docRef.set(message).addOnSuccessListener(successListener)
        .addOnFailureListener(failureListener)

}