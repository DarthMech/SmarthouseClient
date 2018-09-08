package mech.develop.smarthouse.smarthouseclient.presentation.widget.view

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast
import mech.develop.smarthouse.smarthouseclient.R
import mech.develop.smarthouse.smarthouseclient.SmarthouseWidgetApp
import mech.develop.smarthouse.smarthouseclient.presentation.widget.presenter.RedButtonPresenter
import javax.inject.Inject

class RedButtonWidget: AppWidgetProvider() {
    companion object {
        private const val KILL_THEM_ALL_ACTION = "RedButtonClickAction"
    }

    @Inject
    lateinit var presenter: RedButtonPresenter

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        inject(context)

        val remoteViews = RemoteViews(context.packageName, R.layout.widget_red_button)

        val active: Intent = Intent(context, RedButtonWidget::class.java)
        active.action = KILL_THEM_ALL_ACTION
        val actionPendingIntent: PendingIntent = PendingIntent.getBroadcast(context, 0, active, 0)

        remoteViews.setOnClickPendingIntent(R.id.big_red_button, actionPendingIntent)

        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews)
    }

    override fun onReceive(context: Context, intent: Intent) {
        inject(context)

        val action: String? = intent.action
        if (KILL_THEM_ALL_ACTION == action) {

            presenter.onKillAction()

            Toast.makeText(context, "Нажали на кнопку", Toast.LENGTH_SHORT).show()
        }

        super.onReceive(context, intent)
    }

    // ---
    private fun inject(context: Context) {
        (context.applicationContext as SmarthouseWidgetApp).getWidgetComponent().inject(this)
    }
}
