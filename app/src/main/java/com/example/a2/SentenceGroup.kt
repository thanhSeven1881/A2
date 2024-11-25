package com.example.a2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SentenceGroup(
    val subject: String,
    val id: Int,
    val englishSentences: List<String>,
    val vietnameseSentences: List<String>
) : Parcelable
