package mech.develop.smarthouse.smarthouseclient.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_smarthouse.*
import mech.develop.smarthouse.smarthouseclient.R
import mech.develop.smarthouse.smarthouseclient.SmarthouseApp
import mech.develop.smarthouse.smarthouseclient.di.DaggerSmarthouseScreenComponent
import mech.develop.smarthouse.smarthouseclient.di.SmarthouseScreenModule
import mech.develop.smarthouse.smarthouseclient.navigation.AboutScreenRouter
import mech.develop.smarthouse.smarthouseclient.presentation.presenters.SmarthousePresenter
import javax.inject.Inject

class SmarthouseActivity : AppCompatActivity(), SmarthouseMainView {

    @Inject
    lateinit var presenter: SmarthousePresenter

    @Inject
    lateinit var router: AboutScreenRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        presenter.onAttach(this)

        setContentView(R.layout.activity_smarthouse)
    }

    override fun onStart() {
        super.onStart()
        initViewStartValue()
        initLedsProgressView()

        presenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.about_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_menu_item -> {
                navigateToAboutScreen()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // --- View ---
    override fun setBedroomCheckState(state: Boolean) {
        switch_bedroom.isChecked = state
    }

    override fun setLibraryCheckState(state: Boolean) {
        switch_library.isChecked = state
    }

    override fun clearControls() {
        switch_bedroom.isChecked = false
        switch_library.isChecked = false

        green_led_value_textView.text = "0"
        green_led_value_seekBar.progress = 0
        red_led_value_textView.text = "0"
        red_led_value_seekBar.progress = 0
        blue_led_value_textView.text = "0"
        blue_led_value_seekBar.progress = 0
    }

    // --- Inner Methods ---
    private fun inject() {
        DaggerSmarthouseScreenComponent.builder()
                .repoProvider((application as SmarthouseApp).getRepoComponent())
                .routingProvider((application as SmarthouseApp).getRoutingComponent())
                .smarthouseScreenModule(SmarthouseScreenModule())
                .build()
                .inject(this)
    }

    private fun initViewStartValue() {
        switch_bedroom.setOnCheckedChangeListener { _, state: Boolean ->
            presenter.setBedroomLedState(state)
        }

        switch_library.setOnCheckedChangeListener { _, state: Boolean ->
            presenter.setLibraryLedState(state)
        }

        kill_all_button.setOnClickListener {
            presenter.killAll()
        }
    }

    private fun initLedsProgressView() {
        green_led_value_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                green_led_value_textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let {
                    presenter.setGreenLedStripState(it.progress)
                }
            }
        })

        blue_led_value_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blue_led_value_textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let {
                    presenter.setBlueLedStripState(it.progress)
                }
            }
        })

        red_led_value_seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blue_led_value_textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.let {
                    presenter.setRedLedStripState(it.progress)
                }
            }
        })
    }

    private fun navigateToAboutScreen() {
        router.openAboutScreen(this)
    }
}