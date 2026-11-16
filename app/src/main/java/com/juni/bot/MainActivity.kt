
package com.juni.bot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuniBotScreen()
        }
    }
}

@Composable
fun JuniBotScreen() {
    val purple = Color(0xFF9C27B0)
    val dark = Color(0xFF121212)

    var botEnabled by remember { mutableStateOf(false) }

    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(dark)
                .padding(20.dp)
        ) {
            Text(
                text = "JUNI'S BOT",
                color = purple,
                fontSize = 30.sp
            )

            Text(
                text = "Messenger Automation",
                color = Color.White,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(30.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF242424)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Bot Status",
                        color = Color.White,
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (botEnabled) "ON" else "OFF",
                            color = if (botEnabled) Color.Green else Color.Red
                        )

                        Spacer(modifier = Modifier.weight(1f))

                        Switch(
                            checked = botEnabled,
                            onCheckedChange = { botEnabled = it }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple
                )
            ) {
                Text("Manage Chats")
            }

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple
                )
            ) {
                Text("Manage Replies")
            }

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple
                )
            ) {
                Text("Import / Export CSV")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Activity History",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}

