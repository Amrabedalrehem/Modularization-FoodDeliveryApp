package com.example.home.view.component

fun getFoodEmoji(category: String): String = when (category) {
    "برجر"  -> "🍔"
    "بيتزا" -> "🍕"
    "سوشي"  -> "🍣"
    "شاورما"-> "🌯"
    "باستا" -> "🍝"
    "فراخ"  -> "🍗"
    else    -> "🍽"
}