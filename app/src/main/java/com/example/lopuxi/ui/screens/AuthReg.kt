package com.example.lopuxi.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.sharp.Info
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lopuxi.R
import com.example.lopuxi.ui.screens.viewmodels.AuthRegViewModel

@Composable
fun TextField(
    icon: ImageVector,
    label: String,
    secureInput: Boolean,
    onValueChange: (s: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var value by rememberSaveable { mutableStateOf("") }
    var hidden by rememberSaveable { mutableStateOf(secureInput) }

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
            value = it
        },
        singleLine = true,
        label = { Text(text = label) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        visualTransformation =
        if (secureInput) {
            if (hidden)
                PasswordVisualTransformation()
            else
                VisualTransformation.None
        } else
            VisualTransformation.None,
        trailingIcon = {
            if (secureInput) {
                IconButton(onClick = { hidden = !hidden }) {
                    Icon(
                        imageVector = Icons.Sharp.Info,
                        contentDescription = null
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun AuthRegScreen(
    authRegViewModel: AuthRegViewModel,
    modifier: Modifier = Modifier
) {

    var isRegistration by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.bunny),
            contentDescription = null,
            modifier = Modifier
                .height(height = 240.dp)
                .width(width = 240.dp)
                .padding(
                    top = 40.dp,
                    bottom = 40.dp
                )
                .weight(1f)
        )

        Column(
            modifier = Modifier//.weight(1f)
        ) {
            TextField(
                icon = Icons.Filled.Person,
                label = "Login",
                secureInput = false,
                onValueChange = { authRegViewModel.setLogin(it) }
            )

            TextField(
                icon = Icons.Filled.Lock,
                label = "Password",
                secureInput = true,
                onValueChange = { authRegViewModel.setPassword(it) }
            )

            if (isRegistration)
                TextField(
                    icon = Icons.Filled.Person,
                    label = "Repeat password",
                    secureInput = false,
                    onValueChange = { authRegViewModel.submitPassword(it) }
                )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExtendedFloatingActionButton(
                onClick = { if(isRegistration) {authRegViewModel.register()} else { authRegViewModel.authorize() } },
                modifier = Modifier.padding(vertical = 40.dp)
            ) {
                Text(
                    text = "Sign in",
                    fontSize = 24.sp,
                    color = Color.Black,
                )
            }

            Text(
                text = if (isRegistration) "Have account?" else "No account?",
                fontSize = 16.sp
            )

            Text(
                text = if (isRegistration) "Sign In" else "Sign Up",
                fontSize = 40.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable(
                        onClick = { isRegistration = !isRegistration }
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginTextFieldPreview() {
    TextField(
        icon = Icons.Filled.Person,
        label = "Login",
        secureInput = false,
        onValueChange = { Log.v("TESTOSTERON", "hello") }
    )
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    TextField(
        icon = Icons.Filled.Lock,
        label = "Password",
        secureInput = true,
        onValueChange = { Log.v("TESTOSTERON", "hello") }
    )
}

@Preview(showBackground = true)
@Composable
fun AuthRegScreenPreview() {
    AuthRegScreen(authRegViewModel = AuthRegViewModel())
}