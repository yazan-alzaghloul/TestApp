package com.example.testapp.helpers

import android.content.Context
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.example.testapp.R

class DialogHelper(private val context: Context) {

    private var progress: MaterialDialog? = null

    fun loading() {
        progress = MaterialDialog(context).show {
            setCanceledOnTouchOutside(false)
            customView(R.layout.dialog_loading)
        }
    }

    fun isShowing(): Boolean {
        if (progress == null) return false
        return progress!!.isShowing
    }

    fun dismissLoading() {
        if (progress == null) return
        progress!!.dismiss()
    }

    fun alert(title: String, message: String) {
        MaterialDialog(context).show {
            title(text = title)
            message(text = message)
        }
    }

    fun alert(title: Int, message: Int) {
        MaterialDialog(context).show {
            title(title)
            message(message)
        }
    }

    fun failed(message: Int) {
        MaterialDialog(context).show {
            title(R.string.gen_failed)
            message(message)
        }
    }

    fun failed(message: String) {
        MaterialDialog(context).show {
            title(R.string.gen_failed)
            message(text = message)
        }
    }

}