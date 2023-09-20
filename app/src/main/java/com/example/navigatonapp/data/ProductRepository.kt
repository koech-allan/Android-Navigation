package com.example.navigatonapp.data

import android.app.ProgressDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.animation.core.snap
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.navigatonapp.navigation.models.Product
import com.example.navigatonapp.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProductRepository(var navController: NavHostController, var context: Context) {
    var authRepository: AuthRepository
    var progress:ProgressDialog
    var products:ArrayList<Product>

    init {
        authRepository = AuthRepository(navController,context)
        if (!authRepository.isLoggedIn()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait..")

        products = mutableListOf<Product>() as ArrayList<Product>

    }

    fun saveProduct(productName:String, productQuantity:String, productPrice:String) {
        var id = System.currentTimeMillis().toString()
        var productData = Product(productName,productQuantity,productPrice,id)
        var productRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        progress.show()
        productRef.setValue(productData).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT)
            }else{
                Toast.makeText(context,"Error: ${it.exception!!.message}", Toast.LENGTH_SHORT).show()
            }
        }


    }

    fun viewProducts(product: MutableState<Product>, products: SnapshotStateList<Product>): SnapshotStateList<Product> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Products")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                products.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(Product::class.java)
                    product.value = value!!
                    products.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return products
    }

    fun deleteProduct(id:String) {
        var delRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener(){
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context,"Product deleted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun updateProduct(name:String,quantity:String,price:String,id:String) {
        var updateRef = FirebaseDatabase.getInstance().getReference().child("Products/$id")
        progress.show()
        var updateData = Product(name, quantity, price,id)
        updateRef.setValue(updateData).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }

        }


    }
}