package com.project.smartgreen.screens.login


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.project.smartgreen.ui.components.bgImagen

@Composable
fun LoginFormComponent(
    viewModel: LoginViewModel,
    navController: NavHostController
) {
    val loginButtonState = viewModel.loginState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        // Background image
        bgImagen()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Spacer(modifier = Modifier.padding(32.dp))
            LoginField(
                value = viewModel.loginData.login,
                onChange = { data -> viewModel.loginData = viewModel.loginData.copy(login = data) },
                modifier = Modifier.fillMaxWidth()
            )
            PasswordField(
                value = viewModel.loginData.pwd,
                onChange = { data -> viewModel.loginData = viewModel.loginData.copy(pwd = data) },
                submit = {
                    if (!viewModel.checkCredentials()) viewModel.loginData = LoginData()
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            LabeledCheckbox(
                label = "Remember me",
                onCheckChanged = {
                    viewModel.loginData = viewModel.loginData.copy(remember = !viewModel.loginData.remember)
                },
                isChecked = viewModel.loginData.remember
            )
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    viewModel.checkLogin()
                    navController?.navigate("home")
                },
                enabled = (viewModel.loginData.isNotEmpty() && (loginButtonState.value == LoginButtonState.Ready)),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                when (loginButtonState.value) {
                    LoginButtonState.Loading ->
                        CircularProgressIndicator(
                            modifier = Modifier.then(Modifier.size(32.dp))
                        )
                    else -> Text("Login")
                }
            }

        }
    }
}

@Composable
fun LabeledCheckbox(
    label: String,
    onCheckChanged: () -> Unit,
    isChecked: Boolean
) {
    Row(
        Modifier
            .clickable(onClick = onCheckChanged)
            .padding(4.dp)
    ) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(Modifier.size(6.dp))
        Text(label)
    }
}
