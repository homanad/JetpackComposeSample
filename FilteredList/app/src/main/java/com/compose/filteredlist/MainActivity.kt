package com.compose.filteredlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.state
import androidx.ui.core.Alignment
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.foundation.Image
import androidx.ui.foundation.Text
import androidx.ui.foundation.TextField
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.gestures.scrollable
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.Surface
import androidx.ui.res.vectorResource
import androidx.ui.text.TextStyle
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.TextUnit
import androidx.ui.unit.dp
import androidx.ui.unit.sp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                FilteredList(contacts = contactList)
            }
        }
    }
}

@Composable
fun MyApp(children: @Composable() () -> Unit) {
    MaterialTheme {
        children()
    }
}

@Composable
fun FilteredList(contacts: List<Contact>, filterText: String = "") {
    val state = state { filterText }
    Column {
        Surface(color = Color.Gray) {
            TextField(
                state.value,
                onValueChange = { state.value = it },
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier.padding(10.dp),
                focusIdentifier = "Type"
            )
        }
        Spacer(modifier = Modifier.preferredSize(10.dp))
        VerticalScroller {
            Column {
                contacts.filter { it.name.contains(state.value) }.forEach {
                    Row(modifier = Modifier.padding(10.dp)) {
                        //                    Image(asset = vectorResource(id = it.photo))
                        Text(
                            text = it.name,
                            modifier = Modifier.padding(10.dp),
                            style = TextStyle(fontSize = TextUnit.Sp(18))
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Column() {
            Greeting("An")
            FilteredList(contacts = contactList, filterText = "")
        }
    }
}

class Contact(var photo: Int = R.drawable.ic_launcher_background, var name: String)

val contactList = listOf(
    Contact(name = "test1"),
    Contact(name = "test2"),
    Contact(name = "test3"),
    Contact(name = "test4"),
    Contact(name = "test5"),
    Contact(name = "test6"),
    Contact(name = "test7"),
    Contact(name = "test8"),
    Contact(name = "test9"),
    Contact(name = "test10"),
    Contact(name = "test11"),
    Contact(name = "test12")
)