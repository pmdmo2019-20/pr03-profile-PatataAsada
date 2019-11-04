package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.profile.R
import kotlinx.android.synthetic.main.avatar_activity.*

class AvatarActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(AvatarActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avatar_activity)
        loadIntent()
        setClickListeners()
    }

    private fun setClickListeners() {
        imgAvatar1.setOnClickListener { markSelected(1) }
        lblAvatar1.setOnClickListener { markSelected(1) }
        chkAvatar1.setOnClickListener { markSelected(1) }

        imgAvatar2.setOnClickListener { markSelected(2) }
        lblAvatar2.setOnClickListener { markSelected(2) }
        chkAvatar2.setOnClickListener { markSelected(2) }

        imgAvatar3.setOnClickListener { markSelected(3) }
        lblAvatar3.setOnClickListener { markSelected(3) }
        chkAvatar3.setOnClickListener { markSelected(3) }

        imgAvatar4.setOnClickListener { markSelected(4) }
        lblAvatar4.setOnClickListener { markSelected(4) }
        chkAvatar4.setOnClickListener { markSelected(4) }

        imgAvatar5.setOnClickListener { markSelected(5) }
        lblAvatar5.setOnClickListener { markSelected(5) }
        chkAvatar5.setOnClickListener { markSelected(5) }

        imgAvatar6.setOnClickListener { markSelected(6) }
        lblAvatar6.setOnClickListener { markSelected(6) }
        chkAvatar6.setOnClickListener { markSelected(6) }

        imgAvatar7.setOnClickListener { markSelected(7) }
        lblAvatar7.setOnClickListener { markSelected(7) }
        chkAvatar7.setOnClickListener { markSelected(7) }

        imgAvatar8.setOnClickListener { markSelected(8) }
        lblAvatar8.setOnClickListener { markSelected(8) }
        chkAvatar8.setOnClickListener { markSelected(8) }

        imgAvatar9.setOnClickListener { markSelected(9) }
        lblAvatar9.setOnClickListener { markSelected(9) }
        chkAvatar9.setOnClickListener { markSelected(9) }

    }

    private fun loadIntent() {
        if (intent == null || !intent.hasExtra(EXTRA_AVATAR)) {
            throw RuntimeException("No llega el intent")
        }
        viewModel.id = intent.getIntExtra(EXTRA_AVATAR, 1)
        viewModel.lastId = viewModel.id
        markSelected(viewModel.id)
    }

    private fun markSelected(id: Int) {
        viewModel.id = id
        unMarkPrevious(viewModel.lastId)
        changeCheckBoxState(viewModel.id)
    }

    private fun unMarkPrevious(lastId: Int) {
        changeCheckBoxState(lastId)
        viewModel.lastId = viewModel.id
    }

    private fun changeCheckBoxState(id: Int) {
        when (viewModel.id) {
            1 -> {
                if (chkAvatar1.isSelected) {
                    chkAvatar1.isSelected = false
                } else {
                    chkAvatar1.isSelected = false
                }
            }
            2 -> {
                if (chkAvatar2.isSelected) {
                    chkAvatar2.isSelected = false
                } else {
                    chkAvatar2.isSelected = false
                }
            }
            3 -> {
                if (chkAvatar3.isSelected) {
                    chkAvatar3.isSelected = false
                } else {
                    chkAvatar3.isSelected = false
                }
            }
            4 -> {
                if (chkAvatar4.isSelected) {
                    chkAvatar4.isSelected = false
                } else {
                    chkAvatar4.isSelected = false
                }
            }
            5 -> {
                if (chkAvatar5.isSelected) {
                    chkAvatar5.isSelected = false
                } else {
                    chkAvatar5.isSelected = false
                }
            }
            6 -> {
                if (chkAvatar6.isSelected) {
                    chkAvatar6.isSelected = false
                } else {
                    chkAvatar6.isSelected = false
                }
            }
            7 -> {
                if (chkAvatar7.isSelected) {
                    chkAvatar7.isSelected = false
                } else {
                    chkAvatar7.isSelected = false
                }
            }
            8 -> {
                if (chkAvatar8.isSelected) {
                    chkAvatar8.isSelected = false
                } else {
                    chkAvatar8.isSelected = false
                }
            }
            9 -> {
                if (chkAvatar9.isSelected) {
                    chkAvatar9.isSelected = false
                } else {
                    chkAvatar9.isSelected = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.avatar_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnuSelect) {
            sendAvatarId()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun sendAvatarId() {
        var newintent = Intent(this, AvatarActivity::class.java)
        newintent.putExtra(EXTRA_AVATAR,viewModel.id)

        setResult(Activity.RESULT_OK,newintent)

    }

    companion object {

        const val EXTRA_AVATAR = "EXTRA_AVATAR"

    }

}


