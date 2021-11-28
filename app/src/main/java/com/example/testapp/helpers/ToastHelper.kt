package com.example.testapp.helpers

import android.widget.Toast
import com.example.testapp.AppController

class ToastHelper {

    /**
     * Show toast message.
     * @param message message to show.
     * @param length duration eg Toast.LENGTH_LONG.
     */
    fun show(message: String, length: Int) {
        Toast.makeText(AppController.context, message, length).show()
    }

    /**
     * Show toast message.
     * @param message message to show.
     */
    fun long(message: String) {
        Toast.makeText(AppController.context, message, Toast.LENGTH_LONG).show()
    }

    /**
     * Show toast message.
     * @param message message to show.
     */
    fun short(message: String) {
        Toast.makeText(AppController.context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Show toast message.
     * @param string message to show.
     */
    fun short(string: Int) {
        Toast.makeText(
            AppController.context,
            AppController.context.getString(string),
            Toast.LENGTH_SHORT
        ).show()
    }

}