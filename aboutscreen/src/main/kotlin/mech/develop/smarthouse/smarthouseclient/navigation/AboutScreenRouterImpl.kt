package mech.develop.smarthouse.smarthouseclient.navigation

import android.content.Context
import android.content.Intent
import mech.develop.smarthouse.smarthouseclient.presentation.AboutActivity

class AboutScreenRouterImpl : AboutScreenRouter {

    override fun openAboutScreen(context: Context) {
        context.startActivity(Intent(context, AboutActivity::class.java))
    }

}