package com.example.mycase.profile

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mycase.R
import com.example.mycase.databinding.ActivityLawyerBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.util.*

class Lawyer_Activity : AppCompatActivity() {
    lateinit var binding: ActivityLawyerBinding
    lateinit var auth: FirebaseAuth
    lateinit var databaseReference: DatabaseReference
    lateinit var storageReference: StorageReference
    lateinit var uid: String
    lateinit var storage: FirebaseStorage
    lateinit var imageUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lawyer)
        binding = ActivityLawyerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        storage=FirebaseStorage.getInstance()

        uid = auth.currentUser?.uid.toString()
        initViews()
        getUserData()
    }

    fun initViews() {
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
        binding.profData.setOnClickListener {


            val name = binding.lawyerName.text.toString()
            val specilization = binding.spec.text.toString()
            val streetName = binding.strName.text.toString()
            val buildingNum = binding.buidingNum.text.toString()
            val city = binding.city.text.toString()
            val clientsNum = binding.clientsNum.text.toString()
            val experience = binding.experience.text.toString()
            val phoneNum = binding.phone.text.toString()
            val lawyerMail = binding.lawyerEmail.text.toString()
            val user = User(
                name, specilization, streetName, buildingNum, city, clientsNum, experience,
                phoneNum, lawyerMail
            )
            if (uid != null) {
                databaseReference.child(uid).setValue(user).addOnSuccessListener {

                    uploadProfilePic()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to update profile", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.lawyerImg.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }


    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            val imageUri = data.data
            if (imageUri != null) {
                val storageReference = storage.reference.child("gs://my-case-81d7e.appspot.com/users/$uid/profilePicture.jpg")
                val uploadTask = storageReference.putFile(imageUri)
                uploadTask.addOnSuccessListener {
                    Toast.makeText(this, "successed to upload the image", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to upload the image", Toast.LENGTH_SHORT).show()
                }
            }
        }




    }
    private fun uploadProfilePic() {
        imageUri = Uri.parse("android.resource://$packageName/${R.drawable.circle_profile}")
        storageReference =
            FirebaseStorage.getInstance().getReference("users/" + auth.currentUser?.uid + ".jpg")
        storageReference.putFile(imageUri).addOnSuccessListener {
            Toast.makeText(this, "profile successfully updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to upload the image", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getUserData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        if (uid.isNotEmpty()) {
            databaseReference.child(uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                   val user = snapshot.getValue(User::class.java)
                    binding.lawyerName.setText(user?.name)
                    binding.spec.setText(user?.specilization)
                    binding.strName.setText(user?.streetName)
                    binding.buidingNum.setText(user?.buildingNum)
                    binding.city.setText(user?.city)
                    binding.clientsNum.setText(user?.customersNum)
                    binding.experience.setText(user?.experience)
                    binding.phone.setText(user?.phoneNum)
                    binding.lawyerEmail.setText(user?.email)
                    getUserProfile()

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@Lawyer_Activity, "Failed to get user data", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }
    private var shouldRefresh = false

    override fun onResume() {
        super.onResume()
        if (shouldRefresh) {
            // Call recreate() to refresh the activity and display the new photo
            recreate()
            shouldRefresh = false
        }
    }

    private fun getUserProfile() {
        val uid=FirebaseAuth.getInstance().currentUser?.uid.toString()
        storageReference=FirebaseStorage.getInstance().reference.child("gs://my-case-81d7e.appspot.com/users/$uid/profilePicture.jpg")
        val localFile=File.createTempFile("tempImage","jpg")


        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap=BitmapFactory.decodeFile(localFile.absolutePath)
            binding.lawyerImg.setImageBitmap(bitmap)



        }.addOnFailureListener{
            Log.e(TAG, "Error downloading file: ${it.message}")
            Toast.makeText(this@Lawyer_Activity, "Failed to get pic", Toast.LENGTH_SHORT).show()
        }
    }


}


