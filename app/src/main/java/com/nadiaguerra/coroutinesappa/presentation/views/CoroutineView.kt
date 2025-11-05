package com.nadiaguerra.coroutinesappa.presentation.views

import CoroutineViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CoroutineView(navController: NavController, viewModel: CoroutineViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Button(
            onClick = { viewModel.callToApi1() }
        ) {
            Text("Corutina 1")
        }
        if (viewModel.isLoading1.value) {
            Spacer(modifier = Modifier.height(10.dp))
            CircularProgressIndicator()
        }
        if (viewModel.result1.value.isNotEmpty() && !viewModel.isLoading1.value) {
            Text(viewModel.result1.value)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { viewModel.callToApi2() }
        ) {
            Text("Corutina 2")
        }
        if (viewModel.isLoading2.value) {
            Spacer(modifier = Modifier.height(10.dp))
            CircularProgressIndicator()
        }
        if (viewModel.result2.value.isNotEmpty() && !viewModel.isLoading2.value) {
            Text(viewModel.result2.value)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { viewModel.callToApi3() }
        ) {
            Text("Corutina 3")
        }
        if (viewModel.isLoading3.value) {
            Spacer(modifier = Modifier.height(10.dp))
            CircularProgressIndicator()
        }
        if (viewModel.result3.value.isNotEmpty() && !viewModel.isLoading3.value) {
            Text(viewModel.result3.value)
        }
    }
}
