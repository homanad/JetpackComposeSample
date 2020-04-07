package com.compose.firstapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.LayoutHeight
import androidx.ui.layout.LayoutPadding
import androidx.ui.material.*
import androidx.ui.res.colorResource
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

//Kotlin & Compose
@Composable
fun MyScreenContent(
    names: List<String> = nameList,
    counterState: CounterState = CounterState(),
    formState: FormState = FormState(false)
) {
    Column(modifier = LayoutHeight.Fill) {
        Column(modifier = LayoutWeight(1f)) {
            for (name in names) {
                Greeting(name = name)
                Divider(color = Color.Black)
            }
        }
        Counter(state = counterState)
        Form(formState = formState)
    }
}

@Composable
fun MyApp(children: @Composable() () -> Unit) {
    AppTheme {
        Surface(color = colorResource(id = R.color.colorPrimary)) {
            children()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = LayoutPadding(24.dp))
}

@Preview
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}

val nameList = listOf("Android", "iOS", "Flutter")

@Composable
fun Counter(state: CounterState) {
    Button(onClick = { state.count++ }) {
        Text(text = "I've been clicked ${state.count} times")
    }
}

@Model
class CounterState(var count: Int = 0)

@Composable
fun Form(formState: FormState) {
    Checkbox(
        checked = formState.optionChecked,
        onCheckedChange = { newState -> formState.optionChecked = newState })
}

@Model
class FormState(var optionChecked: Boolean)