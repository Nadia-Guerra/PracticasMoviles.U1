package com.nadiaguerra.cuartitos_app.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nadiaguerra.cuartitos_app.presentation.viewmodels.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(navController: NavController, viewModel: StudentViewModel, studentId: Long) {
    val student = viewModel.getStudentById(studentId)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(student?.name ?: "Detalles") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        student?.let {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(it.image),
                    contentDescription = it.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.description, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        viewModel.deleteStudent(studentId)
                        navController.popBackStack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE57373),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Eliminar", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

