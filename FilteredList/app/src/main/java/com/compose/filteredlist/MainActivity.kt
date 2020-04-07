package com.compose.filteredlist

import android.media.Image
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.state
import androidx.ui.core.setContent
import androidx.ui.foundation.*
import androidx.ui.layout.Column
import androidx.ui.layout.Row
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                //                Greeting("Android")
                FilteredList(contacts = contactList, filterText = "")
            }
        }
    }
}

@Composable
fun FilteredList(contacts: List<Contact>, filterText: String = "") {
    val state = state { filterText }
    VerticalScroller {
        Column {
            TextField(filterText, onValueChange = { state.value = it }) @Composable() {
                contacts.filter { it.name.contains(state.value) }.forEach {
                    Row {
                        Text(text = it.name)
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
        Greeting("Android")
    }
}

@Model
class ContactState() {

}

class Contact(var name: String)

val contactList = listOf<Contact>(Contact("ABC"), Contact("BCD"))