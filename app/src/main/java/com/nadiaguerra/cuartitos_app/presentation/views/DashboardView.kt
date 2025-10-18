package com.nadiaguerra.cuartitos_app.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.nadiaguerra.cuartitos_app.presentation.viewmodels.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardView(navController: NavController, viewModel: StudentViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Dashboard") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("AddStudent") }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Student")
            }
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(viewModel.students) { student ->
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { navController.navigate("Details/${student.id}") },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(student.image),
                            contentDescription = student.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(student.name, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}


