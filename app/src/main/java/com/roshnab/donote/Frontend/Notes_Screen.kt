package com.roshnab.donote.Frontend

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.util.TableInfo
import com.roshnab.donote.Backend.Notes
import com.roshnab.donote.Backend.Todo
import com.roshnab.donote.R
import com.roshnab.donote.ViewModel.App_ViewModel
import com.roshnab.donote.ui.theme.Mozilla

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Notes_Screen(viewModel: App_ViewModel) {
    val notes by viewModel.notes.collectAsState(initial = emptyList())
    var newNoteTitle by remember { mutableStateOf("") }
    var newNote by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Notes",
                        style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold)
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                shape = CircleShape,
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(R.drawable.plus),
                    contentDescription = "Add Note",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->

        if (notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No Notes Available", color = Color(0xFF898989))
            }
        } else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(notes) { note ->
                    note_item(
                        notes = note,
                        onDelete = { viewModel.deleteNote(id = note.id) }
                    )
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add Task") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = newNoteTitle,
                            onValueChange = { newNoteTitle = it },
                            label = { Text("Enter Title") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        OutlinedTextField(
                            value = newNote,
                            onValueChange = { newNote = it },
                            label = { Text("Enter Note") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            viewModel.addNote(newNoteTitle, newNote)
                            newNoteTitle = ""
                            newNote = ""
                            showDialog = false
                        }
                    ) {
                        Text("Add Note")
                    }
                }
            )
        }
    }
}

@Composable
fun note_item(notes: Notes, onDelete: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .background(
                color = Color(0xFFfeff9c),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = notes.title,
                    style = TextStyle(fontFamily = Mozilla, fontWeight = FontWeight.Bold, fontSize = 22.sp)
                )

                IconButton(onClick = {
                    onDelete()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.cross_sleek),
                        contentDescription = "Delete",
                        modifier = Modifier.size(28.dp),
                        tint = Color(0xFF9E9E9E)
                    )
                }
            }
            Text(
                text = notes.description,
                style = TextStyle(fontFamily = Mozilla, fontWeight = FontWeight.Normal, fontSize = 16.sp)
            )
        }
    }
}
