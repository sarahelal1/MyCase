package com.example.mycase.home_activity

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.mycase.*
import com.example.mycase.databinding.ActivityHomeBinding
import com.example.mycase.models.Category
import com.example.mycase.profile.Lawyer_Activity
import com.example.mycase.requests.RequestsFragment
import com.example.mycase.sign_in.SignInActivity
import com.example.mycase.ui.CategoriesFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File

class HomeActivity :  AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var drawerImage: ImageView
    lateinit var profileImage: ImageView
    lateinit var settings: LinearLayout
    lateinit var storageReference: StorageReference
    var categoriesFragment: CategoriesFragment = CategoriesFragment()
    lateinit var binding: ActivityHomeBinding
    val auth = Firebase.auth
    val user = Firebase.auth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        showFragment(categoriesFragment)
        getProfilePic()
    }

    fun initViews() {

        drawerLayout = findViewById(R.id.drawer_layout)
        drawerImage = findViewById(R.id.drawer_image)
        settings = findViewById(R.id.settings)
        categoriesFragment.onCategoryClicked = object : CategoriesFragment.OnCategoryClicked {
            override fun onCategory(category: Category) {
                if (category.title.equals("القضايا")) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CasesFragment.getInstance(category))
                        .addToBackStack(null)
                        .commit()

                } else if (category.title.equals("المواعيد")) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CalendarFragment.getInstance(category))
                        .addToBackStack(null)
                        .commit()

                } else if (category.title.equals("المدفوعات")) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, PaymentsFragment.getInstance(category))
                        .addToBackStack(null)
                        .commit()

                } else if (category.title.equals("الطلبات")) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, RequestsFragment.getInstance(category))
                        .addToBackStack(null)
                        .commit()

                }
            }
        }
        drawerImage.setOnClickListener {
            drawerLayout.open()
        }
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        binding.brief.setOnClickListener {
            val intent = Intent(this, BriefActivity::class.java)
            startActivity(intent)
        }
        binding.help.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
        binding.settings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        binding.appBarHome.profileImage.setOnClickListener {
            val intent = Intent(this, Lawyer_Activity::class.java)
            startActivity(intent)
        }


    }

    fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun getProfilePic() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()
        storageReference =
            FirebaseStorage.getInstance().reference.child("gs://my-case-81d7e.appspot.com/users/$uid/profilePicture.jpg")
        val localFile = File.createTempFile("tempImage", "jpg")


        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            binding.appBarHome.profileImage.setImageBitmap(bitmap)


        }
    }
}