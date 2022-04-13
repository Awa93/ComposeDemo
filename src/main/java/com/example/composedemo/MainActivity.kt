package com.example.composedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    lateinit var myContext:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        myContext = this
        setContent {
            val users = ArrayList<Users>()
            users.add(Users(true,"Awadhesh Singh",25,"awa@gmail.com","9494949492"))
            users.add(Users(false,"Ramesh Kumar",30,"ramesh@gmail.com","8803425353"))
            users.add(Users(false,"XYZ LTD",30,"xyz@gmail.com","9665474659"))
            users.add(Users(false,"Muesh",30,"mukesh@gmail.com","9559857699"))
            users.add(Users(false,"Android",30,"android@gmail.com","9550685489"))
            users.add(Users(false,"Developer",30,"developer@gmail.com","95596847449"))
            LazyColumnDemo(myContext,users)
        }

    }
}


@Composable
fun LazyColumnDemo(context: Context,users: ArrayList<Users>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = users, itemContent = { item ->
            ListItemView(context,item)
        })
    }
}

fun cardViewCallBack(context: Context,user: Users) {
    Toast.makeText(context,"Hello ${user.name}",Toast.LENGTH_SHORT).show()
    val intent =   Intent(context,ItemDetail::class.java)
    val bundle = Bundle()
    bundle.putParcelable("ITEM",user)
    intent.putExtras(bundle)

    context.startActivity(intent,bundle)

}

@Composable
fun ListItemView(context: Context,user: Users) : Unit {
    Card(
        backgroundColor = Color.White,
        elevation = Dp(2F),
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .clickable {
                cardViewCallBack(context, user)
            }


    ) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp))  {

            Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "",contentScale = ContentScale.Crop,

                modifier = Modifier
                    .padding(8.dp)
                    .size(100.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(50.dp))))


            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp)) {
                user.name?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(all = 4.dp),
                        textAlign = TextAlign.Start,
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                }
                user.email?.let {
                    Text(
                        text = it,
                        modifier = Modifier.padding(all = 4.dp),
                        textAlign = TextAlign.Start,
                        fontSize = 22.sp,
                        color = Color.Blue
                    )
                }
                user.age?.let {
                    Text(
                        text = it.toString(),
                        modifier = Modifier.padding(all = 4.dp),
                        textAlign = TextAlign.Start,
                        fontSize = 22.sp,
                        color = Color.Green
                    )
                }
            }

        }

    }

}




@Preview
@Composable
fun DefaultPreview() {
    val users = ArrayList<Users>()
    users.add(Users(true,"Awadhesh Singh",25,"awa@gmail.com","9494949492"))
    LazyColumnDemo(LocalContext.current, users)
}