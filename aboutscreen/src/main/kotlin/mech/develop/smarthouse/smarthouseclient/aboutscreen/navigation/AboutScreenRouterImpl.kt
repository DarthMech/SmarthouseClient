package mech.develop.smarthouse.smarthouseclient.aboutscreen.navigation

import android.content.Context
import android.content.Intent
import mech.develop.smarthouse.smarthouseclient.aboutscreen.presentation.AboutActivity
import mech.develop.smarthouse.smarthouseclient.coremodule.navigation.AboutScreenRouter

class AboutScreenRouterImpl :
        AboutScreenRouter {

    override fun openAboutScreen(context: Context) {
        context.startActivity(Intent(context, AboutActivity::class.java))
    }

}