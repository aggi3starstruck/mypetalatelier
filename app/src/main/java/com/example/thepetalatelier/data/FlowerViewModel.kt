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
import com.example.thepetalatelier.models.Flower
import com.example.thepetalatelier.models.buyer1
import com.example.thepetalatelier.navigation.ROUTE_VIEW_FLOWER
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FlowerViewModel(): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _successMessage = MutableLiveData<String>()
    val successMessage: LiveData<String> get() = _successMessage

    fun saveFlower(
        flowername: String, description: String,
        price: String, navController: NavController, context: Context
    ) {
        val id = System.currentTimeMillis().toString()
        val dbRef = FirebaseDatabase.getInstance().getReference("Flower/$id")

        val FlowerData = Flower("", flowername, description, price, id)

        dbRef.setValue(FlowerData)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Flower saved Succesfully", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_VIEW_FLOWER)

                } else {
                    Toast.makeText(context, "flowere update failed", Toast.LENGTH_LONG).show()
                }

            }
    }

    fun viewFlower(
        flower: MutableState<Flower>,
        flowers: SnapshotStateList<Flower>, context: Context
    ): SnapshotStateList<Flower> {
        val ref = FirebaseDatabase.getInstance().getReference()
            .child("Flower")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                flowers.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Flower::class.java)
                   flower.value = value!!
                   flowers.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Flower Update failed", Toast.LENGTH_LONG).show()
            }
        })

        return flowers
    }


        fun updateFlower(
            context: Context, navController: NavController,
            flowername: String, description: String, price: String, id: String
        ) {
            val databaseReference = FirebaseDatabase.getInstance()
                .getReference("Flower/$id")
            val updateFlower = Flower("", flowername, description, price, id)
            databaseReference.setValue(updateFlower)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Flower Updated Successfully", Toast.LENGTH_LONG)
                            .show()
                        navController.navigate(ROUTE_VIEW_FLOWER)
                    } else {
                        Toast.makeText(context, "Flower update failed", Toast.LENGTH_LONG).show()


                    }
                }

        }

        fun deleteFlower(
            context: Context, id: String,
            navController: NavController
        ) {
            AlertDialog.Builder(context)
                .setTitle("Delete Flower")
                .setMessage("Are you sure you want to delete this flower")
                .setPositiveButton("Yes") { _, _ ->
                    val databaseReference = FirebaseDatabase.getInstance()
                        .getReference("Flower/$id")
                    databaseReference.removeValue().addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Flower deleted Succesfully", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
        }
    }






















