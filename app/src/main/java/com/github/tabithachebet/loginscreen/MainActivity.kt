package com.github.tabithachebet.loginscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.github.tabithachebet.loginscreen.ui.theme.LoginScreenTheme

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
@Preview(showBackground = true)
@Composable
fun LogInScreen(paddingValues: PaddingValues) {
    var name by remember { mutableStateOf("") }
    var welcomeMessage by remember { mutableStateOf("") }

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
            label = { Text("Enter your name") },
            modifier = Modifier.padding(bottom = 16.dp)
        )
       Row(

       ){
           Button(
               onClick = {
               welcomeMessage = "Welcome " + "$name +! \n Have a nice day."
           }
           ){ Text("Submit")}

           Button(onClick = {
               welcomeMessage = ""
               name = ""
           }){ Text("Clear")}
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