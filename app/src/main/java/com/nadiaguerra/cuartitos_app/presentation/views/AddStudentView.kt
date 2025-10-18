package com.nadiaguerra.cuartitos_app.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadiaguerra.cuartitos_app.presentation.viewmodels.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudentView(navController: NavController, viewModel: StudentViewModel) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var image by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Agregar estudiante") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
            OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
            OutlinedTextField(value = image, onValueChange = { image = it }, label = { Text("URL de imagen") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.addStudent(name, description, image)
                navController.popBackStack()
            }) {
                Text("Guardar")
            }
        }
    }
}
