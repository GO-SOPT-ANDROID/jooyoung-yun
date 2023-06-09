package org.android.go.sopt.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.showToast(message: String, isShort: Boolean = true){
    val duration = if(isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
    Toast.makeText(this, message, duration).show()
}

fun View.showSnackBar(message: String, isShort: Boolean = true){
    val duration = if(isShort) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG
    Snackbar.make(this,message,duration).show()
}
fun Context.hideKeyboard(view: View, isShown: Boolean = false){
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).run{
        if (isShown) showSoftInput(view,0)
        else hideSoftInputFromWindow(view.windowToken, 0)
    }
}
