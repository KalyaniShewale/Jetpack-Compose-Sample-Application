package com.example.jetpackcomposesampleapplication.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.jetpackcomposesampleapplication.R
import com.example.jetpackcomposesampleapplication.ui.theme.JetpackComposeSampleApplicationTheme


@Preview(showBackground = true)
@Composable
fun RegistrationScreenPreview() {
    JetpackComposeSampleApplicationTheme {

        val navController = rememberNavController()

        RegistrationScreen(
            navController = navController,
            paddingValues = PaddingValues()
        )
    }
}

@Composable
fun RegistrationScreen (navController: NavController , paddingValues: PaddingValues){

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var confirmPasswordError by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf( false) }
    var confirmPasswordVisibility by remember { mutableStateOf( false) }
    val compositiob by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.registration))
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
    ){

        LottieAnimation(
            composition = compositiob,
            progress = {progress},
            modifier = Modifier.size(300.dp).align(Alignment.CenterHorizontally)
        )
        Text(text = "Create Account", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Please enter your details", fontSize = 19.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(
                    nameError.ifEmpty { "Name" },
                    color = if (nameError.isNotEmpty()) Color.Red else Color.Unspecified
                )
            },
            leadingIcon = {
                Icon(Icons.Rounded.Person, contentDescription = "Name Icon")
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
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(
                    emailError.ifEmpty { "Email" },
                    color = if (emailError.isNotEmpty()) Color.Red else Color.Unspecified
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
                if (passwordVisibility)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            shape = RoundedCornerShape(8.dp),
            singleLine = true,

            keyboardOptions = KeyboardOptions(
                keyboardType = if (passwordVisibility)
                    KeyboardType.Text
                else
                    KeyboardType.Password
            ),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(
                        painter = if (passwordVisibility)
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

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = confirmPasswordError,
            onValueChange = {
                confirmPasswordError = it
            },
            label = {
                Text(
                    confirmPasswordError.ifEmpty { "Confirm Password" },
                    color = if (confirmPasswordError.isNotEmpty()) Color.Red else Color.Unspecified
                )
            },
            leadingIcon = {
                Icon(Icons.Rounded.Lock, contentDescription = "Password Icon")
            },

            visualTransformation =
                if (confirmPasswordVisibility)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),

            shape = RoundedCornerShape(8.dp),
            singleLine = true,

            keyboardOptions = KeyboardOptions(
                keyboardType = if (confirmPasswordVisibility)
                    KeyboardType.Text
                else
                    KeyboardType.Password
            ),
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisibility = !confirmPasswordVisibility }) {
                    Icon(
                        painter = if (confirmPasswordVisibility)
                            painterResource(R.drawable.visibility)
                        else
                            painterResource(R.drawable.visibility_off),
                        contentDescription = "Toggle confirm password"
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
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                emailError = if (email.isBlank()) " Enter the email" else ""
                nameError = if (name.isBlank()) " Enter the email" else ""
                passwordError = if (password.isBlank()) " Enter the password" else " "
                confirmPasswordError = if (confirmPassword.isBlank()) " Enter the confirm password"
                else if (password != confirmPassword) { "Passwords do not match" } else " "

                if (emailError.isEmpty() && passwordError.isEmpty() && confirmPasswordError.isEmpty() && nameError.isEmpty()) {
                    println("Login Successful")
                }
            }, modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 90.dp)
        ) {
            Text(text = "Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { navController.navigate("Login")}) {
            Text(text = "All ready have an account? Login")
        }
    }

}