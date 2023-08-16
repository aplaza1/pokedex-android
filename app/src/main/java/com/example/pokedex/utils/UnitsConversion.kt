package com.example.pokedex.utils

class UnitsConversion {
    companion object {
        fun hectogramsToPounds(hectograms: Double): Double {
            return hectograms * 0.220462
        }

        fun decimetersToInches(decimeters: Double): Double {
            return decimeters * 3.93701
        }
    }
}