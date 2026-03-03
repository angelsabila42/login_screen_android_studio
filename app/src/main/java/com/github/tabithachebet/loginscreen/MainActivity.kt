package com.github.tabithachebet.loginscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.tabithachebet.loginscreen.ui.theme.LoginScreenTheme
import kotlin.text.ifEmpty

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LogInScreen(
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun LogInScreen(paddingValues: PaddingValues) {
    var name by remember { mutableStateOf("") }
    var welcomeMessage by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            label = {
                Text(nameError.ifEmpty { "Enter a Name" }, color = if (nameError.isNotEmpty()) Color.Red else Color.Unspecified)
            }

        )
        Spacer(modifier = Modifier.height(16.dp))

        //Row with Buttons
       Row(
           modifier = Modifier.fillMaxWidth()
               .padding(horizontal = 16.dp),
           horizontalArrangement = Arrangement.Center,
       ){
           Button(
               onClick = {
                   if (name.isEmpty()){
                       nameError = "Please enter a name"

                   }else{
                       welcomeMessage = "Welcome $name!"
                   }
               }
           ){ Text(text = "Submit")}

           //Add space between the buttons
           Spacer(modifier = Modifier.padding(horizontal = 6.dp))

           Button(onClick = {
               welcomeMessage = ""
               nameError = ""
               name = ""
           }){ Text(text = "Clear")}
       }

    }
}

@Preview(showBackground = true)
@Composable
fun LogInScreenPreview() {
    LoginScreenTheme {
        LogInScreen(paddingValues = PaddingValues())
    }
}