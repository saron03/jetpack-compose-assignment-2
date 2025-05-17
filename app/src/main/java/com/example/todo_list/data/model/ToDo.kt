package com.example.todo_list.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "todos")
data class ToDo(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("completed")
    val completed: Boolean
)