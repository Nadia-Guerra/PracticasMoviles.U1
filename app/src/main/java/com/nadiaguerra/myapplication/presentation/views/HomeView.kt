package com.nadiaguerra.myapplication.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nadiaguerra.myapplication.data.Student

//:Composable es que recibe un componente visual



@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun HomeView(navController: NavController) {
    var students = listOf<Student>(
        Student(1L, "Emiliana", "es la jefa de grupo"),
        Student(2L, "Nadia", "es la amiga de Emiliana"),
        Student(3L, "Toño", "es el amigo de Nadia"),
        Student(4L, "Tony", "es el amigo de Toño"),
        Student(5L, "Luis", "es el amigo de Tony"),
        Student(6L, "Emma", "es el amigo de Luis"),
    )
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Dashboard")
                }
            )
        }
    ){Content(it, students, navController)} //el it es para pasarle el padding
}


@Composable
fun Content(paddingValues: PaddingValues, students: List<Student>, navController: NavController){ //student es el parametro que le pasas que sera la lista tipo student
    LazyColumn (
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp) //es una unidad de pixeles orientadas hacia los dispositivos moviles
    ){          //tambn estan los sp que son tamaños para fuentes
        //Text("Hola mundo", fontSize = 30.sp)
        items(students){ student ->
            Box(
                modifier = Modifier
                    .clickable{
                        navController.navigate("Details/${student.id}")
                    }
                    .size(80.dp)
                    .background(Color.Yellow, CircleShape)
                    .wrapContentSize(Alignment.Center)

            ){
                Text(student.name, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}