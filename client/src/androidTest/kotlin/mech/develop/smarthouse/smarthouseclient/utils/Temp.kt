package mech.develop.smarthouse.smarthouseclient.utils

import android.content.Context
import androidx.test.InstrumentationRegistry

fun getId(id: String): Int {
    val targetContext: Context = InstrumentationRegistry.getTargetContext()
    val packageName: String = targetContext.packageName
    return targetContext.resources.getIdentifier(id, "id", packageName)
}