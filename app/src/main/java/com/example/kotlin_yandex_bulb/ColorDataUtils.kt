package com.example.kotlin_yandex_bulb

object ColorDataUtils {
    fun getColorHexCode(id: Int): String {
        return when (id) {
            9 -> "#FFFF00"    // YELLOW
            0 -> "#FF0000"    // RED
            15 -> "#2E8B57"   // SEAGREEN
            1 -> "#008000"    // GREEN
            18 -> "#800080"   // PURPLE
            4 -> "#008080"    // TEAL
            11 -> "#800000"   // MAROON
            17 -> "#FF6347"   // TOMATO
            13 -> "#FFC0CB"   // PINK
            3 -> "#FFFFFF"    // WHITE
            14 -> "#CD853F"   // PERU
            16 -> "#6A5ACD"   // SLATEBLUE
            8 -> "#FFD700"    // GOLD
            2 -> "#0000FF"    // BLUE
            7 -> "#00FFFF"    // CYAN
            6 -> "#FF7F50"    // CORAL
            10 -> "#4B0082"   // INDIGO
            5 -> "#A52A2A"    // BROWN
            12 -> "#9370DB"   // MEDIUMPURPLE
            else -> "#000000"
        }
    }
}