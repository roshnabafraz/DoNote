package com.roshnab.donote.Backend

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notes (
    var title: String,
    var description: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)