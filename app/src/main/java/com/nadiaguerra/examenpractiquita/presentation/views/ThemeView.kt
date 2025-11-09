package com.nadiaguerra.examenpractiquita.presentation.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nadiaguerra.examenpractiquita.data.ThemeDataStore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeView(navController: NavController) {

    //variable para que recuerde la corutina
    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val dataStore = ThemeDataStore(context)

    val theme by dataStore.ThemePreferences.collectAsState(initial = false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Tema")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Dashboard") }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Regresa al Dashboard")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Cambia a light/dark theme")
            Spacer(modifier = Modifier.height(16.dp))


            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Light")
                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                Switch( //se hace con onCheckedChange que es de jetpack compose
                    checked = theme,
                    onCheckedChange = { //de donde viene no regresa nd
                        newValue ->
                        scope.launch {
                            dataStore.saveTheme(newValue)
                        }
                    }
                )
                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                Text("Dark")
            }
        }
    }
}
