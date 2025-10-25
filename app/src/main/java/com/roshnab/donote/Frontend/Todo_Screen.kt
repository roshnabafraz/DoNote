package com.roshnab.donote.Frontend

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roshnab.donote.Backend.Todo
import com.roshnab.donote.R
import com.roshnab.donote.ViewModel.App_ViewModel
import com.roshnab.donote.ui.theme.Mozilla

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(viewModel: App_ViewModel) {

    val todos by viewModel.todos.collectAsState(initial = emptyList())
    var newTodo by remember { mutableStateOf("") }

    val undone by viewModel.undoneTasks.collectAsState()
    val done by viewModel.doneTasks.collectAsState()

    val TotalTasks = viewModel.AllTasksCount.collectAsState(initial = 0)
    val DoneTasks = viewModel.DoneTasksCount.collectAsState(initial = 0)

    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tasks",
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                    )
                ) },


//                navigationIcon = {
//                    IconButton(onClick = { /* do something */ }) {
//                        Icon(
//                            painter = painterResource(R.drawable.arrow_left_circle_outline),
//                            contentDescription = "Back",
//                            modifier = Modifier.size(36.dp)
//                        )
//                    }
//                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                },
                shape = CircleShape,
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White
                //modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.plus),
                    contentDescription = "Add Todo",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        innerPadding ->

        if (todos.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Tasks Available", color = Color(0xFF898989)
                )
            }
        }

        Column(modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
        ) {
//            Column {
//
//                OutlinedTextField(
//                    value = newTodo,
//                    onValueChange = { newTodo = it },
//                    label = { Text("Enter a new todo") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Button(
//                    onClick = {
//                        viewModel.addTodo(Todo(newTodo, false))
//                        newTodo = ""
//                    },
//
//                    ) {
//                    Text("Add Note")
//                }
//            }

            if (!todos.isEmpty()) {
                Text(
                    text = "${DoneTasks.value} of ${TotalTasks.value} completed",
                    style = TextStyle(
                        fontFamily = Mozilla,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color(0xFF898989)
                    ),
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

            LazyColumn() {

                items(undone) { todo ->
                    todo_item(
                        todoitem = todo,

                        onDelete = {
                            viewModel.deleteTodo(id = todo.id)
                        },
                        onToggle = {
                            viewModel.updateisDone(todo.id, !todo.isDone)
                        }
                    )
                }

                if (done.isNotEmpty()) {
                    item {
                        HorizontalDivider(
                            thickness = 1.dp,
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 32.dp)
                        )
                    }
                }

                items(done) { todo ->
                    todo_item(
                        todoitem = todo,

                        onDelete = {
                            viewModel.deleteTodo(id = todo.id)
                        },
                        onToggle = {
                            viewModel.updateisDone(todo.id, !todo.isDone)
                        }
                    )
                }
            }
        }
    }

    if(showDialog){
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Add Task") },
            text = {
                OutlinedTextField(
                    value = newTodo,
                    onValueChange = { if (it.length <= 24) newTodo = it },
                    label = { Text("Enter a new todo") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.addTodo(Todo(newTodo, false))
                        newTodo = ""
                        showDialog = false
                    }
                ) {
                    Text("Add Task")
                }
            }

        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun todo_item(todoitem: Todo, onToggle: () -> Unit, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onToggle) {
            Icon(
                painter = if (todoitem.isDone) painterResource(R.drawable.bxs_check_circle) else painterResource(
                    R.drawable.bx_circle),
                contentDescription = if (todoitem.isDone) "Selected icon button" else "Unselected icon button.",
                modifier = Modifier.size(32.dp), tint = Color(0xFF9E9E9E)
            )
        }

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = todoitem.title,
            style = TextStyle(
                fontFamily = Mozilla,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = if (todoitem.isDone) Color(0xFF9E9E9E) else Color(0xFF212121),
                textDecoration = if (todoitem.isDone) TextDecoration.LineThrough else TextDecoration.None
            )
            //Color(0xFF898989)
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = {
            onDelete()
        }) {
            Icon(
                painter = painterResource(R.drawable.cross_sleek),
                contentDescription = "Delete",
                modifier = Modifier.size(34.dp),
                tint = Color(0xFF9E9E9E)
            )
        }
    }
}
