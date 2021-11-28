package com.example.testapp.ui.view.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.R
import com.example.testapp.ui.view.fragments.ProductsFragment

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = getString(R.string.products)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, ProductsFragment.newInstance())
            .commitNow()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}