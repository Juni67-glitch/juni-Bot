
package com.juni.bot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Context

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RepliesScreen(this)
        }
    }
}

@Composable
fun RepliesScreen(context: Context) {
    val purple = Color(0xFF9C27B0)
    val dark = Color(0xFF121212)

    val preferences = remember {
        context.getSharedPreferences("JuniBot", Context.MODE_PRIVATE)
    }

    val replies = remember {
        mutableStateListOf<String>().apply {
            addAll(preferences.getStringSet("replies", emptySet()) ?: emptySet())
        }
    }

    var replyText by remember { mutableStateOf("") }
    var editingIndex by remember { mutableStateOf(-1) }

    fun saveReplies() {
        preferences.edit()
            .putStringSet("replies", replies.toSet())
            .apply()
    }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(dark)
                .padding(18.dp)
        ) {
            Text(
                "JUNI'S BOT",
                color = purple,
                fontSize = 28.sp
            )

            Text(
                "Manage Your Replies",
                color = Color.White,
                fontSize = 19.sp
            )

            Spacer(Modifier.height(20.dp))

            OutlinedTextField(
                value = replyText,
                onValueChange = { replyText = it },
                label = { Text("Enter your reply") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = purple,
                    focusedLabelColor = purple
                )
            )

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = {
                    if (replyText.isNotBlank()) {
                        if (editingIndex >= 0) {
                            replies[editingIndex] = replyText.trim()
                            editingIndex = -1
                        } else {
                            replies.add(replyText.trim())
                        }
                        saveReplies()
                        replyText = ""
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple
                )
            ) {
                Text(if (editingIndex >= 0) "Update Reply" else "Add Reply")
            }

            Spacer(Modifier.height(20.dp))

            Text(
                "Saved Replies (${replies.size})",
                color = Color.White,
                fontSize = 20.sp
            )

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(replies) { index, reply ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF292929)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(Modifier.padding(12.dp)) {
                            Text(reply, color = Color.White)

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(onClick = {
                                    replyText = reply
                                    editingIndex = index
                                }) {
                                    Text("Edit", color = purple)
                                }

                                TextButton(onClick = {
                                    replies.removeAt(index)
                                    saveReplies()
                                    if (editingIndex == index) {
                                        editingIndex = -1
                                        replyText = ""
                                    }
                                }) {
                                    Text("Delete", color = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
