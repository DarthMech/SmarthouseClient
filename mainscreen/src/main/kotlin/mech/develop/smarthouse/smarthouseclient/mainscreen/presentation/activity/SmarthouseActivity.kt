package mech.develop.smarthouse.smarthouseclient.mainscreen.presentation.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_smarthouse.*
import mech.develop.smarthouse.smarthouseclient.coremodule.SmarthouseApp
import mech.develop.smarthouse.smarthouseclient.coremodule.navigation.AboutScreenRouter
import mech.develop.smarthouse.smarthouseclient.mainscreen.R
import mech.develop.smarthouse.smarthouseclient.mainscreen.di.DaggerSmarthouseScreenComponent
import mech.develop.smarthouse.smarthouseclient.mainscreen.di.SmarthouseScreenModule
import mech.develop.smarthouse.smarthouseclient.mainscreen.presentation.presenters.SmarthousePresenter
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

    override fun setTapeLedCheckState(state: Boolean) {
        switch_tape_led.isChecked = state
    }

    override fun clearControls() {
        switch_bedroom.isChecked = false
        switch_library.isChecked = false
        switch_tape_led.isChecked = false

        setLedProgress(0)
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

        switch_tape_led.setOnCheckedChangeListener { _, state: Boolean ->
            presenter.setTapeLedState(state)
            setLedProgress(if (state) 255 else 0)
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
                red_led_value_textView.text = progress.toString()
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

    private fun setLedProgress(progress: Int) {
        green_led_value_textView.text = "$progress"
        green_led_value_seekBar.progress = progress
        red_led_value_textView.text = "$progress"
        red_led_value_seekBar.progress = progress
        blue_led_value_textView.text = "$progress"
        blue_led_value_seekBar.progress = progress
    }
}