package com.example.a2
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dataModel: DataModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load data từ JSON
        dataModel = loadJsonData(this)

        // Hiển thị câu ngẫu nhiên
        random_sentence.text = if (dataModel.subjects.isNotEmpty()) {
            getRandomSentence(dataModel)
        } else {
            "Không có dữ liệu"
        }

        // Setup RecyclerView
        recycler_view_topics.layoutManager = LinearLayoutManager(this)
        recycler_view_topics.adapter = TopicAdapter(dataModel.subjects) { group ->
            // Mở ListActivity khi chọn chủ đề
            ListActivity.start(this, group)
        }

        // Tìm kiếm
        search_bar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                val filteredTopics = dataModel.subjects.filter {
                    it.subject.contains(query, ignoreCase = true)
                }
                recycler_view_topics.adapter = TopicAdapter(dataModel.subjects) { group ->
                    ListActivity.start(this, group)
                }

            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun getRandomSentence(data: DataModel): String {
        val randomGroup = data.subjects.random()
        val randomIndex = randomGroup.englishSentences.indices.random()
        return randomGroup.englishSentences[randomIndex]
    }
}
