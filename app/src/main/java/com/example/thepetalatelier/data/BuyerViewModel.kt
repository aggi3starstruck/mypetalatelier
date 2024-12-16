package com.example.thepetalatelier.data
import android.app.AlertDialog

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.thepetalatelier.models.Buyer
import com.example.thepetalatelier.models.buyer1
import com.example.thepetalatelier.navigation.ROUTE_VIEW_BUYER
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BuyerViewModel(): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _successMessage = MutableLiveData<String>()
    val successMessage: LiveData<String> get() = _successMessage

    public fun showToast(message: String, context: Context){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show()
    }

    fun saveBuyer(firstname: String, lastname: String, venue: String,
                    navController: NavController, context: Context
    ){
        @Suppress("NAME_SHADOWING")
        val id = System.currentTimeMillis().toString()
        val dbRef = FirebaseDatabase.getInstance().getReference("Buyer/$id")

        val buyerData = buyer1(firstname, lastname, venue, id)

        dbRef.setValue(buyerData)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    showToast("Buyer added successfully",context)
                    navController.navigate(ROUTE_VIEW_BUYER)

                }else{
                    showToast("Buyer not added successfully",context)
                }

            }
    }
    fun viewBuyer(
        buyer: MutableState<buyer1>,
        buyers: SnapshotStateList<buyer1>, context: Context
    ):
            SnapshotStateList<buyer1> {
        val ref = FirebaseDatabase.getInstance().getReference()
            .child("Buyer")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot){
                buyers.clear()
                for(snap in snapshot.children){
                    val value = snapshot.getValue(buyer1::class.java)
                    buyer.value = value!!
                    buyers.add(value)
                }
            }
            override fun onCancelled(error: DatabaseError){
                showToast("Failed To Fetch Buyers",context)
            }
        })
        return buyers


    }
    fun updateBuyer(context: Context, navController: NavController,
                     firstname: String, lastname: String, venue: String, id: String){
        val databaseReference = FirebaseDatabase.getInstance()
            .getReference("Buyer/$id")
        val updateBuyer = buyer1("",firstname, lastname, venue,id)
        databaseReference.setValue(updateBuyer)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful){
                    showToast("Buyer Updated Successfully",context)
                    navController.navigate(ROUTE_VIEW_BUYER)
                }else{
                    showToast("Record Update failed",context)
                }
            }

    }
    fun deleteBuyer(context: Context, id: String,
                     navController: NavController) {
        AlertDialog.Builder(context)
            .setTitle("Delete Buyer")
            .setMessage("Are you sure you want to delete this buyer?")
            .setPositiveButton("Yes") { _, _ ->
                val databaseReference = FirebaseDatabase.getInstance()
                    .getReference("Buyer/$id")
                databaseReference.removeValue().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        showToast("Buyer deleted Successfully,", context)
                    } else {
                        showToast("Buyer not deleted", context)
                    }

                }
            }
            .setNegativeButton("No"){ dialog, _ ->
                dialog.dismiss()

            }
            .show()

    }



}