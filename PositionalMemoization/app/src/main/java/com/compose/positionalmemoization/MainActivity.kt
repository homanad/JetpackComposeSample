package com.compose.positionalmemoization

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.memo
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Column
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Column {
                    App(query = "b")
                    App(query = "b")
//                    Button(text = "Tap to refresh data")
//                    App(items = items, query = "c")
                }
            }
        }
    }
}

@Composable
fun App(query: String) {
    val results = +memo(items, query) {
        items.filter {
            Log.d("FILTERHANDLING", "YES!")
            it.matches(query.toRegex())
        }
    }
    Column {
        for (item in results) {
            Text(text = item)
        }
    }
    //...
}

val items = listOf("a", "b", "c", "d")

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Greeting("Android")
    }
}