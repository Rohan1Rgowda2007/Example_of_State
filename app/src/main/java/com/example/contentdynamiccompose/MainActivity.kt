package com.example.contentdynamiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.contentdynamiccompose.ui.theme.ContentDynamicComposeTheme
import kotlin.math.round

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentDynamicComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() { // state Manager

    val listOfNames = remember {
        mutableStateListOf<String>("Rohan", "Doraemon", "Lenovo", "Apple")
    }

    val textFieldState = remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        GreetingList(
            list = listOfNames,
            textFieldContent = textFieldState.value,
            textFieldValue = {
            textFieldState.value = it
        }) {
            textFieldState.value.trim { it >= ' ' }
            listOfNames.add(textFieldState.value)
        }


    }
}

@Composable
fun GreetingList(
    list : List<String>,
    textFieldContent : String,
    textFieldValue : (newName : String) -> Unit,
    onCLickEvent : () -> Unit
) {

    for (name in list) {
        Text(
            text = name,
            fontSize = 20.sp,
        )
    }

    TextField(
        value = textFieldContent,
        onValueChange = textFieldValue,
        placeholder = {
            Text(
                text = "type Something..."
            )
        },
        label = {
            Text(
                text = "Enter..."
            )
        }
    )

    OutlinedButton(
        onClick = onCLickEvent,
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color.Blue
            )
            .width(244.dp)
            .height(60.dp)
    ) {
        Text(
            text = "Press Me To Add",
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ContentDynamicComposeTheme {
        MainScreen()
    }
}