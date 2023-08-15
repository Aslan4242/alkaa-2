@file:JvmName("extension-context")

package com.escodro.core.extension

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.app.AlarmManagerCompat
import androidx.core.net.toUri
import java.util.Calendar
import timber.log.Timber

private const val INVALID_VERSION = "x.x.x"

/**
 * Sets a alarm using [AlarmManagerCompat] to be triggered based on the given parameter.
 *
 * @param triggerAtMillis time in milliseconds that the alarm should go off, using the
 * appropriate clock (depending on the alarm type).
 * @param operation action to perform when the alarm goes off
 * @param type type to define how the alarm will behave
 */
fun Context.setAlarm(
    triggerAtMillis: Long,
    operation: PendingIntent?,
    type: Int = AlarmManager.RTC_WAKEUP
) {
    val currentTime = Calendar.getInstance().timeInMillis
    if (triggerAtMillis <= currentTime) {
        Timber.w("It is not possible to set alarm in the past")
        return
    }

    if (operation == null) {
        Timber.e("PendingIntent is null")
        return
    }

    val manager = getAlarmManager()
    manager?.let { AlarmManagerCompat.setAndAllowWhileIdle(it, type, triggerAtMillis, operation) }
}

/**
 * Cancels a alarm set on [AlarmManager], based on the given [PendingIntent].
 *
 * @param operation action to be canceled
 */
fun Context.cancelAlarm(operation: PendingIntent?) {
    if (operation == null) {
        Timber.e("PendingIntent is null")
        return
    }

    val manager = getAlarmManager()
    manager?.let { manager.cancel(operation) }
}

/**
 * Shows a [Toast] with the given message.
 *
 * @param messageId the message String resource id
 * @param duration the Toast duration, if not provided will be set to [Toast.LENGTH_SHORT]
 */
fun Context.showToast(@StringRes messageId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageId, duration).show()
}

/**
 * Gets string from color in format "#XXXXXX".
 *
 * @param colorRes the color resource id
 *
 * @return string from color in format "#XXXXXX"
 */
@SuppressLint("ResourceType")
fun Context.getStringColor(@ColorRes colorRes: Int): String =
    resources.getString(colorRes)

/**
 * Opens the given url in string format.
 *
 * @param url the url in string format
 */
fun Context.openUrl(url: String) {
    with(Intent(Intent.ACTION_VIEW)) {
        this.data = url.toUri()
        startActivity(this)
    }
}

/**
 * Returns the version name of the application.
 *
 * @return the version name of the application.
 */
fun Context.getVersionName(): String {
    var packageInfo: PackageInfo? = null
    packageName.let {
        try {
            packageInfo = packageManager.getPackageInfo(it, 0)
        } catch (e: PackageManager.NameNotFoundException) {
            Timber.e(e)
        }
    }
    return packageInfo?.versionName ?: INVALID_VERSION
}
