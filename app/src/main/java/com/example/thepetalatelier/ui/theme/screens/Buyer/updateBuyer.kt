package com.example.thepetalatelier.ui.theme.screens.Buyer

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.thepetalatelier.R
import com.example.thepetalatelier.data.BuyerViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.Snapshot
import com.example.thepetalatelier.models.buyer1

@Composable
fun UpdateBuyerScreen(navController: NavController, id: String){
    val imageUri = rememberSaveable (){
        mutableStateOf<Uri?>(null)

    }
    val painter = rememberImagePainter(
        data = imageUri.value?: R.drawable.placeholder, builder = {crossfade(true)}
    )
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) {
            uri: Uri? ->
            uri?.let { imageUri.value =it }
        }
    val context = LocalContext.current
    var firstname by remember {
        mutableStateOf(value = "")
    }
    var lastname by remember {
        mutableStateOf(value = "")
    }
    var venue by remember {
        mutableStateOf(value = "")
    }
    var id by remember {
        mutableStateOf(value = "")
    }
    val currentDataRef = FirebaseDatabase.getInstance()
        .getReference().child("Buyer/$id")
    DisposableEffect(Unit) {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val buyer1 = snapshot.getValue(buyer1::class.java)
                buyer1?.let{
                    firstname = it.firstname
                    lastname = it.lastname
                    venue = it.venue
                }

            }




            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message,
                    Toast.LENGTH_LONG).show()

            }

        }
        currentDataRef.addValueEventListener(listener)
        onDispose { currentDataRef.removeEventListener(listener) }

    }






    Scaffold (bottomBar = {
        BottomAppBar(
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Home, contentDescription = "Home")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Settings, contentDescription = "Settings")
                }
                IconButton(onClick = {}) {
                    Icon(Icons.Filled.Email, contentDescription = "Email")

                }


            },
            floatingActionButton = {
                FloatingActionButton(onClick = {},
                    containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                    elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()) {
                    Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
                }
            }

        )


    }
    ){innerPadding ->
        Column (modifier = Modifier.padding(innerPadding)){
            Text(text = "UPDATE BUYER",
                fontSize = 25.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.White)
            )
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Button(onClick = {}) {
                    Text(text = "BACK")
                }
                Button(onClick ={val buyerRepository = BuyerViewModel()
                buyerRepository.saveBuyer(
                    firstname = firstname,
                    lastname = lastname,
                    venue = venue,
                    navController = navController,
                    context = context
                )
                }) {
                    Text(text = "UPDATE")
                }
            }
            Column (modifier = Modifier
                .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally){

                Card (
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(180.dp)
                )

                { Image(painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .clickable { launcher.launch("image/*") },
                    contentScale = ContentScale.Crop)
                }
                Text(text = "Update Picture")
            }
            OutlinedTextField(value = firstname,
                onValueChange = {newFirstname -> firstname = newFirstname},
                placeholder = { Text(text = "Enter First Name") },
                label = { Text(text = "Enter First Name") },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = lastname,
                onValueChange = {newLastname -> lastname = newLastname},
                placeholder = { Text(text = "Enter Last Name") },
                label = { Text(text = "Enter Last Name") },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(value = venue,
                onValueChange = {newVenue -> venue = newVenue},
                placeholder = { Text(text = "Enter Venue") },
                label = { Text(text = "Enter Venue") },
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(10.dp))



        }


    }


}



//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun UpdateBuyerScreenPreview(){
  //  UpdateBuyerScreen(rememberNavController())
//}