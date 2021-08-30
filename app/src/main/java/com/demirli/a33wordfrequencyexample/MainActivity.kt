package com.demirli.a33wordfrequencyexample

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {

    private var contentText: String? = null
    private var frequencyWords: HashMap<String,Int>? = null

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        frequencyWords = hashMapOf()

        content_et.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().length >= 2048){
                    Toast.makeText(this@MainActivity,"Text must not contain more than 2048 characters.",Toast.LENGTH_LONG).show()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        count_btn.setOnClickListener {
            if(content_et.text.toString() != ""){
                contentText = content_et.text.toString()
                countAndSort(contentText!!)
            }
        }
    }

    fun countAndSort(content: String){
        val contentTextCap = content.toUpperCase()

        var contentList= contentTextCap.split(" ")

        for(i in contentList){
            frequencyWords!!.put(i,contentList.count{it == i})
        }

        val result = frequencyWords!!.entries.sortedBy { it.value }.associate {it.toPair()}
        val reverseResult = result.map { it }.asReversed()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter(reverseResult)
    }
}
