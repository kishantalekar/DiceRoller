package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
               Column {
                   DiceWithButtonAndImage(modifier = Modifier
                       .fillMaxWidth()
                       .wrapContentSize(Alignment.Center))
                   LemonadeApp()
               }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
                    Text(
                    text = stringResource(id = R.string.lemon_content_description1),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .background(
                            Color.Yellow
                        )
                        .fillMaxWidth()
                        .height(
                            50.dp
                        )
                        .wrapContentSize(Alignment.Center)
                )
                LemonadeButtonAndImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
}

@Composable
fun LemonadeButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember {
        mutableStateOf(1)
    }
    var noOfTimesClicked by remember {
        mutableStateOf(1)
    }
    var text = when(result){
        1->R.string.desc1
        2->R.string.desc2
        3->R.string.desc3
        else->R.string.desc4
    }
    var imageSource = when(result){
        1->R.drawable.lemon_tree
        2->R.drawable.lemon_squeeze
        3->R.drawable.lemon_drink
        else->R.drawable.lemon_restart
    }
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
       Button(onClick = {
          if(result == 1 || result == 3){
              result++
              noOfTimesClicked = (2..4).random()
          }else if(result == 2){
              if(noOfTimesClicked ==1){
                  result++
              }else{
                  noOfTimesClicked--
              }
          }else{
              result = 1
          }
       }) {
           Image(painter = painterResource(id = imageSource), contentDescription = null)

       }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(id =text))
    }
}


@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    println("Rebuild")
    val imageSource = when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = imageSource), contentDescription = "1")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            result = (1..6).random()
            println(result)
        }) {
            Text(text = stringResource(id = R.string.roll))
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}
