package com.example.navigatonapp.data

import android.app.ProgressDialog
import android.content.Context
import android.service.autofill.UserData
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.navigatonapp.navigation.models.User
import com.example.navigatonapp.navigation.ROUTE_HOME
import com.example.navigatonapp.navigation.ROUTE_LOGIN
import com.example.navigatonapp.navigation.ROUTE_SIGNUP
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthRepository(var navController: NavHostController, var context:Context) {
    var mAuth: FirebaseAuth
    val progress:ProgressDialog

    init {
        mAuth = FirebaseAuth.getInstance()
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun signup(name: String, email: String, password: String) {
        progress.show()
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context,"sign up successful", Toast.LENGTH_LONG).show()
                var userData = User(name, email, password, mAuth.currentUser!!.uid)
                var regRef = FirebaseDatabase.getInstance().getReference()
                    .child("Users/"+mAuth.currentUser!!.uid)
                regRef.setValue(userData).addOnCompleteListener{
                    if (it.isSuccessful){
                        navController.navigate(ROUTE_HOME)
                    }else{
                        Toast.makeText(context,"Error: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
                        navController.navigate(ROUTE_LOGIN)
                    }
                }


            } else {
                navController.navigate(ROUTE_SIGNUP)
            }
        }
    }

    fun login(email:String,password:String) {
        progress.show()
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Login succsessful", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_HOME)
            }else{
                Toast.makeText(context,"Error: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_LOGIN)
            }
        }

    }

    fun logout() {
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN)

    }

    fun isLoggedIn():Boolean = mAuth.currentUser !=null
//        fun isLockedIn():Boolean {
//            if (mAuth.currentUser == null){
//                return false
//            }else{
//                return true
//            }
//        }
}

