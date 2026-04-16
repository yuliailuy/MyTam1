package com.example.mytam1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.*
import androidx.navigation.compose.*
import kotlinx.coroutines.delay
import com.example.mytam1.model.BillMate
import com.example.mytam1.model.BillMateSource
import com.example.mytam1.ui.theme.MyTam1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTam1Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }

        composable("detail/{index}") { backStack ->
            val index = backStack.arguments?.getString("index")?.toInt() ?: 0
            val data = BillMateSource.dummyBill[index]
            DetailScreen(data, navController)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text("Rekomendasi Tempat", fontWeight = FontWeight.Bold)

            Spacer(Modifier.height(12.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                itemsIndexed(BillMateSource.dummyBill) { index, item ->
                    Card(
                        modifier = Modifier
                            .width(160.dp)
                            .clickable {
                                navController.navigate("detail/$index")
                            },
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column {
                            Image(
                                painterResource(item.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp),
                                contentScale = ContentScale.Crop
                            )
                            Column(Modifier.padding(8.dp)) {
                                Text(item.namaTempat, fontWeight = FontWeight.Bold)
                                Text("Klik untuk split", color = Color.Gray)
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            Text("Daftar Split Bill", fontWeight = FontWeight.Bold)
        }

        itemsIndexed(BillMateSource.dummyBill) { index, item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("detail/$index")
                    },
                shape = RoundedCornerShape(16.dp)
            ) {
                Column {
                    Image(
                        painterResource(item.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(Modifier.padding(16.dp)) {
                        Text(item.namaTempat, fontWeight = FontWeight.Bold)
                        Text(item.deskripsi)

                        Spacer(Modifier.height(12.dp))

                        Button(
                            onClick = {},
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Split Sekarang")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailScreen(data: BillMate, navController: NavController) {

    var isProcessing by remember { mutableStateOf(false) }
    var showSuccess by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Column {

            Image(
                painterResource(data.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(Modifier.height(16.dp))

            Text(data.namaTempat, fontWeight = FontWeight.Bold)
            Text(data.deskripsi)

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    isProcessing = true
                },
                enabled = !isProcessing,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isProcessing) Color.Gray else MaterialTheme.colorScheme.primary
                )
            ) {
                if (isProcessing) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("Memproses...")
                    }
                } else {
                    Text("Split Sekarang")
                }
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Kembali")
            }
        }

        if (showSuccess) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .background(
                        color = Color(0xFF2E2E2E),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(14.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Split bill berhasil!",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        if (isProcessing) {
            LaunchedEffect(Unit) {
                delay(1500)
                isProcessing = false
                showSuccess = true
                delay(1500)
                showSuccess = false
            }
        }
    }
}