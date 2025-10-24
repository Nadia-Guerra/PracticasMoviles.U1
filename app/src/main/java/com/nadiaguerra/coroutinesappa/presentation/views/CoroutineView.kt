package com.nadiaguerra.coroutinesappa.presentation.views

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
import com.nadiaguerra.coroutinesappa.presentation.viewmodels.CoroutineViewModel


@Composable
fun CoroutineView(navController: NavController, viewModel: CoroutineViewModel){
    var color by remember {mutableStateOf(false)} //remember ess para q sea una v modificables
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ){
        Button(
            onClick = {
                color = !color
            },
            colors = ButtonDefaults.buttonColors(
                containerColor =  if (color) Color.Green else Color.Red
            )
        ) {
            Text("Click")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                viewModel.callToApi()
            }
        ) {
            Text ("Click to block")
        }

        if(viewModel.isLoading.value){
            Spacer(modifier = Modifier.height(10.dp))
            CircularProgressIndicator()
        }


        if(viewModel.result.value.isNotEmpty() && !viewModel.isLoading.value){
            Text(viewModel.result.value)
        }

    }
}