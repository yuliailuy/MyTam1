package com.example.mytam1.model
import com.example.mytam1.R

object BillMateSource {

    val dummyBill = listOf(

        BillMate(
            namaTempat = "Cakery",
            deskripsi = "Cafe and Bakery",
            totalTagihan = 150000,
            jumlahOrang = 5,
            imageRes = R.drawable.cakery
        ),

        BillMate(
            namaTempat = "Caflower",
            deskripsi = "Cafe and Flowers",
            totalTagihan = 300000,
            jumlahOrang = 6,
            imageRes = R.drawable.caflower
        ),

        BillMate(
            namaTempat = "Bakoffee",
            deskripsi = "Bakery and coffee",
            totalTagihan = 90000,
            jumlahOrang = 3,
            imageRes = R.drawable.bakoffee
        )
    )
}