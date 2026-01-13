package com.example.jetpackcomposesampleapplication.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetpackcomposesampleapplication.R
import com.example.jetpackcomposesampleapplication.ui.theme.JetpackComposeSampleApplicationTheme

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeSampleApplicationTheme {
       // LoginScreen(PaddingValues())
    }
}

@Composable
fun LoginScreen(navController: NavController, paddingValues: PaddingValues) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf("") }
    var emailEroor by remember { mutableStateOf("") }
    val compositiob by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login))
    val progress by animateLottieCompositionAsState(
        isPlaying = true,
        speed = 0.7f,
        composition = compositiob,
        iterations = LottieConstants.IterateForever
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = compositiob,
            progress = {progress},
            modifier = Modifier.size(300.dp).align(Alignment.CenterHorizontally)
        )

        Text(text = "Login", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)

        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(
                    emailEroor.ifEmpty { "Email" },
                    color = if (emailEroor.isNotEmpty()) Color.Red else Color.Unspecified
                )
            },
            leadingIcon = {
                Icon(Icons.Rounded.AccountCircle, contentDescription = "Email Icon")
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 20.dp)
                .fillMaxWidth(),

            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(
                    passwordError.ifEmpty { "Password" },
                    color = if (passwordError.isNotEmpty()) Color.Red else Color.Unspecified
                )
            },
            leadingIcon = {
                Icon(Icons.Rounded.Lock, contentDescription = "Password Icon")
            },

            visualTransformation =
                if (isPasswordVisible)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            shape = RoundedCornerShape(8.dp),
            singleLine = true,

            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPasswordVisible)
                    KeyboardType.Text
                else
                    KeyboardType.Password
            ),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter = if (isPasswordVisible)
                            painterResource(R.drawable.visibility)
                        else
                            painterResource(R.drawable.visibility_off),
                        contentDescription = "Toggle password"
                    )
                }
            },
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 20.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent,
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                emailEroor = if (email.isBlank()) " Enter the email" else ""
                passwordError = if (password.isBlank()) " Enter the password" else " "

                if (emailEroor.isEmpty() && passwordError.isEmpty()) println("Login Successful")
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 90.dp)
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Forgot Password?", modifier = Modifier.clickable {})
        Spacer(modifier = Modifier.height(50.dp))

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Don't have an account? ")
            Text(text = "Register", color = Color.Blue,
                modifier = Modifier.clickable(
                    onClick = {
                        navController.navigate("Registration")
                    }
                ))


        }
    }
}