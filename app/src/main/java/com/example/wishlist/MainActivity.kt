package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var items: ArrayList<Item> = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the RecyclerView in activity layout
        val wishlistRv = findViewById<RecyclerView>(R.id.wishlistRv)
        // Create adapter passing in the list of emails
        var adapter = ItemAdapter(items)
        // Attach the adapter to the RecyclerView to populate items
        wishlistRv.adapter = adapter
        // Set layout manager to position the items
        wishlistRv.layoutManager = LinearLayoutManager(this)

        val name = findViewById<TextView>(R.id.itemNameEt)
        val price = findViewById<TextView>(R.id.priceEt)
        val url = findViewById<TextView>(R.id.urlEt)
        val submit = findViewById<Button>(R.id.submitBtn)

        submit.setOnClickListener {
            items.add(Item(name.text.toString(), url.text.toString(), price.text.toString().toDouble()))
            // Notify the adapter there's new emails so the RecyclerView layout is updated
            adapter.notifyDataSetChanged()
        }

    }
}