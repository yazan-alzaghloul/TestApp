package com.example.testapp.ui.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.R
import com.example.testapp.ui.view.fragments.CategoriesFragment

class CategoriesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = getString(R.string.categories)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment, CategoriesFragment.newInstance())
            .commitNow()
    }
}