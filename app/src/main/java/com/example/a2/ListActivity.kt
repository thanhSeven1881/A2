package com.example.a2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    private lateinit var sentenceGroup: SentenceGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Lấy dữ liệu từ Intent
        sentenceGroup = intent.getParcelableExtra<SentenceGroup>("GROUP")!!

        // Cài đặt RecyclerView
        recyclerViewSentences.layoutManager = LinearLayoutManager(this)
        recyclerViewSentences.adapter = SentenceAdapter(sentenceGroup)
    }

    companion object {
        fun start(context: Context, group: SentenceGroup) {
            val intent = Intent(context, ListActivity::class.java)
            intent.putExtra("GROUP", group)
            context.startActivity(intent)
        }
    }
}
