package android.template.feature.weighbridge.utils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController

fun NavController.popUpToMain() {
    this.navigate("main") {
        popUpTo("main") {
            inclusive = true
        }
    }
}

fun Double.toReadableInTons(): String {
    return "$this Tons"
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}