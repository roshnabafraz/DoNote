package com.roshnab.donote.Frontend

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.roshnab.donote.ui.theme.Mozilla

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homescreen(onTasksClick: () -> Unit,
               onNotesClick: () -> Unit){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("DoNote",
                style = TextStyle(fontFamily = Mozilla,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp)) })
        }
    ) {
            innerPadding ->

        Column(modifier = Modifier
            .clickable{
                onTasksClick()
            }
            .padding(innerPadding)
            .fillMaxHeight()) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(
                        color = Color(0xFFB2FF59),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Tasks",
                    style = TextStyle(fontFamily = Mozilla,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp))
            }

            Box(
                modifier = Modifier
                    .clickable{
                        onNotesClick()
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .background(
                        color = Color(0xFF64B5F6),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Notes",
                    style = TextStyle(fontFamily = Mozilla,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp))
            }
        }
    }
}