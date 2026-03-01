package com.example.mytam1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytam1.model.BillMateSource
import com.example.mytam1.ui.theme.MyTam1Theme

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyTam1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    BillMateScreen()
                }
            }
        }
    }
}

@Composable
fun BillMateScreen() {
    val listBill = BillMateSource.dummyBill

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF87CEEB)).padding(top = 40.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        listBill.forEach { bill ->
            Row(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = bill.imageRes),
                    contentDescription = bill.namaTempat,
                    modifier = Modifier.size(90.dp).padding(end = 12.dp)
                )
                Column {
                    val hasilSplit = bill.totalTagihan / bill.jumlahOrang

                    Text(text = bill.namaTempat)
                    Text(text = bill.deskripsi)
                    Text(text = "Total: Rp${bill.totalTagihan}")
                    Text(text = "Jumlah Orang: ${bill.jumlahOrang}")
                    Text(text = "Per Orang: Rp$hasilSplit")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBillMate() {
    MyTam1Theme {
        BillMateScreen()
    }
}