package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class ItemDetail : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        setContent {
            val users = bundle?.getParcelable<Users>("ITEM")
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    users?.let { Greeting(it) }
                }
            }
        }
    }
}

@Composable
fun Greeting(user: Users) {
//    Text(text = "Hello $name!")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(all = 0.dp)) {

        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",contentScale = ContentScale.Crop,

            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .size(200.dp)
                .clip(RoundedCornerShape(corner = CornerSize(2.dp))))

        user.name?.let { Text(text = it,
            modifier = Modifier.padding(5.dp,10.dp,5.dp,0.dp),
            textAlign = TextAlign.Start,
            fontSize = 18.sp,
            color = Color.White) }
        user.email?.let { Text(text = it,
            modifier = Modifier.padding(5.dp,4.dp,5.dp,0.dp),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.Blue) }
        user.phone?.let { Text(text = it,
            modifier = Modifier.padding(5.dp,4.dp,5.dp,0.dp),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.Gray) }
        user.age?.let { Text(text = it.toString(),
            modifier = Modifier.padding(5.dp,4.dp,5.dp,0.dp),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            color = Color.Green) }
    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
        Greeting(Users(true,"Awadhesh Singh",21,"Singh@globallogic.com","4598459854"))
}