package com.xcomp.ytemoi.support
import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    private val loadingIndicator: View? = null
    private val currentLoadingTask: TextView? = null
    var progressdialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun onBackButtonClicked() {
        finish()
    }

    fun showProgressDialog() {
        hideProgressDialog()
        progressdialog = ProgressDialog.show(
            this@BaseActivity, "",
            "Loading. Please wait...", true
        )
    }

    fun showProgressDialogWithText(messageContent: String?) {
        hideProgressDialog()
        progressdialog = ProgressDialog.show(
            this@BaseActivity, "",
            messageContent, true
        )
    }

    fun hideProgressDialog() {
//        Toast.makeText(BaseActivity.this,progressdialog., Toast.LENGTH_SHORT).show();
        if (progressdialog != null && progressdialog!!.isShowing) {
            progressdialog!!.dismiss()
        }
    }

    fun hideKeyboard() {
        val imm = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = this.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun TienSulyApi(response:String):String{
        var strResponse = response.substring(1, response.length - 1)
        return strResponse.replace("\\", "")
    }
}

