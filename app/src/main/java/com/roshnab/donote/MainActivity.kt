package com.roshnab.donote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.roshnab.donote.Backend.AppDatabase
import com.roshnab.donote.ui.theme.DoNoteTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.roshnab.donote.Frontend.Homescreen
import com.roshnab.donote.Frontend.Notes_Screen
import com.roshnab.donote.Frontend.TodoScreen
import com.roshnab.donote.ViewModel.App_ViewModel
import com.roshnab.donote.ViewModel.NotesRepository
import com.roshnab.donote.ViewModel.TodoRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val database = AppDatabase.GetDatabase(this)
        val todoRepository = TodoRepository(database.Todo_Dao())
        val notesRepository = NotesRepository(database.Notes_Dao())

        setContent {

            val viewModel: App_ViewModel = viewModel { App_ViewModel(todoRepository, notesRepository) }
            val navController = rememberNavController()

            DoNoteTheme {
                Navigation(navController, viewModel)
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, viewModel: App_ViewModel) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            Homescreen(
                onTasksClick = { navController.navigate("todo") },
                onNotesClick = { navController.navigate("notes") }
            )
        }
        composable("todo") {
            TodoScreen(viewModel)
        }
        composable("notes") {
            Notes_Screen(viewModel)
        }

    }

}