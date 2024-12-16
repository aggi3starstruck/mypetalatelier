package com.example.thepetalatelier.ui.theme.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.matchParentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.thepetalatelier.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dashboard(navController: NavController){
    Box {
        Image(painter = painterResource(R.drawable.wreath),
            contentDescription = "backgroundimage",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        TopAppBar(title = { Text(text = "User:$//userName") },
            navigationIcon = {
                IconButton(onClick ={} ) {
                    Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black),
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector =Icons.Filled.Search, contentDescription = "Search" )
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Log out")
                }


            }

        )
        Row(modifier = Modifier.wrapContentWidth()) {
            Card(modifier = Modifier
                .padding(10.dp)
                .clickable {

                },
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(70.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.rosess),
                            contentDescription = "New Client"
                        )
                        Box(modifier = Modifier.padding(10.dp),
                            contentAlignment = Alignment.Center) {
                            Text(
                                text = "",
                                color = Color.Black,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold,
                                fontSize = 8.sp
                            )
                        }

                    }

                }
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {

                        },
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Box(modifier = Modifier.height(70.dp)) {
                        Image(
                            painter = painterResource(R.drawable.vrosse),
                            contentDescription = "View Client"
                        )
                        Box(
                            modifier = Modifier.matchParentSize().padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "", color = Color.Black,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold, fontSize = 8.sp
                            )
                        }

                    }
                }
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {

                        },
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Box(modifier = Modifier.height(70.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.white),
                            contentDescription = "View Client"
                        )
                        Box(
                            modifier = Modifier.matchParentSize().padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "", color = Color.Black,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold, fontSize = 8.sp
                            )
                        }
                    }
                }
                Card(
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(15.dp),
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Box(modifier = Modifier.height(70.dp)) {
                        Image(
                            painter = painterResource(R.drawable.bunch1),
                            contentDescription = "New Client"
                        )
                        Box(
                            modifier = Modifier.matchParentSize().padding(10.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "", color = Color.Black,
                                fontSize = 8.sp,
                                fontStyle = FontStyle.Normal,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }


            }
        }


    }


}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DashBoardPreview(){
    Dashboard(rememberNavController())

}