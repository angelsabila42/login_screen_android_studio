package com.github.tabithachebet.loginscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
        if (welcomeMessage.isNotEmpty()) {
            Text(
                text = welcomeMessage,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }

        androidx.compose.material3.OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = {
                Text(nameError.ifEmpty { "Enter a Name" }, color = if (nameError.isNotEmpty()) Color.Red else Color.Unspecified)
            },
            modifier = Modifier.padding(bottom = 16.dp)
        )
       Row {

           //Submit Button
           Button(
               onClick = {
               if(name.isEmpty()){
                   nameError = "Please enter a name"
               }else{
                   welcomeMessage = "Welcome, $name! \n Have a great day!"
               }
           }
           ){ Text(text = "Submit")}

           Spacer(modifier = Modifier.padding(6.dp))

           //Clear Button
           Button(onClick = {
               welcomeMessage = ""
               name = ""
               nameError = ""
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