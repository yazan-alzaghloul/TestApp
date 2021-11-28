package com.example.testapp.helpers

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.testapp.R

class AlertDialogHelper(private val alertDialogInterface: AlertDialogInterface,
                        context: Context, private val id: String,
                        private val title: String, private val message: String,
                        private val positiveButtonText: String, private val negativeButton: String) {

    private val builder = AlertDialog.Builder(context)
    private lateinit var alertDialog: AlertDialog

    fun show() {
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage(message)

        //performing positive action
        builder.setPositiveButton(positiveButtonText) { _, _ ->
            alertDialogInterface.onPositiveButtonClicked()
        }

        //performing negative action
        builder.setNegativeButton(negativeButton) { _, _ ->
            alertDialogInterface.onNegativeButtonClicked()
        }
        // Create the AlertDialog
        alertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun dismiss() {
        if (alertDialog.isShowing)
            alertDialog.dismiss()
    }

    fun getID(): String {
        return id
    }

    interface AlertDialogInterface {
        fun onPositiveButtonClicked()
        fun onNegativeButtonClicked()
    }


}