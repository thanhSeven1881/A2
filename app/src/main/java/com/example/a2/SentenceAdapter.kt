package com.example.a2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SentenceAdapter(private val group: SentenceGroup) :
    RecyclerView.Adapter<SentenceAdapter.SentenceViewHolder>() {

    inner class SentenceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val englishText: TextView = view.findViewById(R.id.text_english)
        val vietnameseText: TextView = view.findViewById(R.id.text_vietnamese)
        val speakButton: ImageButton = view.findViewById(R.id.button_speak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SentenceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_card, parent, false)
        return SentenceViewHolder(view)
    }

    override fun onBindViewHolder(holder: SentenceViewHolder, position: Int) {
        holder.englishText.text = group.englishSentences[position]
        holder.vietnameseText.text = group.vietnameseSentences[position]

        // Thêm chức năng phát âm
        holder.speakButton.setOnClickListener {
            // Thêm Text-to-Speech
            val ttsHelper = TextToSpeechHelper(holder.itemView.context)
            ttsHelper.speak(group.englishSentences[position])
        }
    }

    override fun getItemCount(): Int = group.englishSentences.size
}
