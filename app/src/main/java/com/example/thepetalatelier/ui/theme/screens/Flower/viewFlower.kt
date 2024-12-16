package com.example.thepetalatelier.ui.theme.screens.Flower

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.thepetalatelier.R
import com.example.thepetalatelier.data.FlowerViewModel
import com.example.thepetalatelier.models.Flower
import com.example.thepetalatelier.navigation.ROUTE_UPDATE_FLOWER

@Composable
fun ViewFlowerScreen(navController: NavHostController){
    val context = LocalContext.current
    val flowerRepository = FlowerViewModel()
    val emptyUploadState = remember {
        mutableStateOf(com.example.thepetalatelier.models.Flower("",
            "","","",""))
    }
    val emptyUploadListState = remember {
        mutableStateListOf<com.example.thepetalatelier.models.Flower>()
    }
    val flower= flowerRepository.viewFlower(
        emptyUploadState,emptyUploadListState,context)

    Column (modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally){

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "All Flowers",
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                color = Color.Black)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn (){
                items(flower){
                    FlowerItem(
                        flowername =it.flowername,
                        description = it.description,
                        price = it.price,
                        id =it.id,
                        navController = navController,
                        flowerRepository = flowerRepository)
                }
            }
        }
    }


}
@Composable
fun FlowerItem(flowername: String, description:String, price:String, id:String, navController: NavHostController, flowerRepository: FlowerViewModel
) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Card (
            modifier = Modifier
                .padding(10.dp)
                .height(210.dp), shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors
                (containerColor = Color.Gray)
        )
        {
            Row  {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.white),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(200.dp)
                            .height(150.dp)
                            .padding(10.dp)
                    )
                    Row(horizontalArrangement = Arrangement.SpaceBetween)
                    {
                        Button (
                            onClick = { flowerRepository.deleteFlower(context,id,navController)},
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.Red)
                        )
                        {
                            Text(
                                text = "REMOVE",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Button(
                            onClick = {navController.navigate("$ROUTE_UPDATE_FLOWER/$id")  },
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(Color.Red)
                        )
                        {
                            Text(
                                text = "UPDATE",
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(
                            vertical = 10.dp,
                            horizontal = 10.dp
                        )
                        .verticalScroll(rememberScrollState())
                )
                {
                    Text(
                        text = "FLOWER NAME",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = flowername,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "DESCRIPTION",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = description,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "PRICE",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = price,
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )



                }
            }
        }
    }
}
@Preview
@Composable
fun ViewFlowerPreview(){
    ViewFlowerScreen(rememberNavController())
}