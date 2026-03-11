package com.example.mytam1.model
import androidx.annotation.DrawableRes

data class BillMate(
    val namaTempat: String,
    val deskripsi: String,
    val totalTagihan: Int,
    val jumlahOrang: Int,
    @DrawableRes val imageRes: Int
)
