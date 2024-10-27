package com.example.learnlambdas

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnlambdas.ui.theme.LearnLambdasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val listOfUsers = mutableListOf<User>()
        listOfUsers.add(User("Abhay","9834758788"))
        listOfUsers.add(User("Abhi","493759348938"))
        listOfUsers.add(User("Ravi","6347238736"))
        listOfUsers.add(User("Abhimanyu","65342873838"))
        setContent {
//            SimpleButton(clickAction = {
//                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
//            })
//            Box(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                UserLazyColumn(users = listOfUsers) { name, number ->
//                    Toast.makeText(this@MainActivity, number, Toast.LENGTH_SHORT).show()
//                }
//            }
            var etFieldValue by remember {
                mutableStateOf("")
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                EditBox(etFieldValue) {
                    etFieldValue = it
                }
                SimpleButton(etFieldValue)
            }
        }
    }
}

@Composable
fun EditBox(etFieldValue: String, onValueChangeListener:(String) -> Unit) {
    OutlinedTextField(value = etFieldValue, onValueChange = {
        onValueChangeListener(it)
    })
}

@Composable
fun SimpleButton(message: String) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun UserLazyColumn(users: List<User>, clickAction:(String, String) -> Unit) {
    LazyColumn {
        items(users) { user ->
            UserUi(user = user) {
                clickAction(user.name, user.number)
            }
        }
    }
}

@Composable
fun UserUi(user: User, clickAction: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable {
                clickAction()
            }
    ) {
        Text(text = user.name, style = TextStyle(fontSize = 20.sp))
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = user.number, style = TextStyle(fontSize = 20.sp))
    }
}

data class User (
    val name: String,
    val number: String
)










