package com.roshnab.donote.Backend

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    var title: String,
    var isDone: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
