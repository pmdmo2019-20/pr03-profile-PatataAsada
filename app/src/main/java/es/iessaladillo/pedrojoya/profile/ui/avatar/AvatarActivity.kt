package es.iessaladillo.pedrojoya.profile.ui.avatar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar
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
        imgAvatar1.setOnClickListener { markSelected(0) }
        lblAvatar1.setOnClickListener { markSelected(0) }
        chkAvatar1.setOnClickListener { markSelected(0) }

        imgAvatar2.setOnClickListener { markSelected(1) }
        lblAvatar2.setOnClickListener { markSelected(1) }
        chkAvatar2.setOnClickListener { markSelected(1) }

        imgAvatar3.setOnClickListener { markSelected(2) }
        lblAvatar3.setOnClickListener { markSelected(2) }
        chkAvatar3.setOnClickListener { markSelected(2) }

        imgAvatar4.setOnClickListener { markSelected(3) }
        lblAvatar4.setOnClickListener { markSelected(3) }
        chkAvatar4.setOnClickListener { markSelected(3) }

        imgAvatar5.setOnClickListener { markSelected(4) }
        lblAvatar5.setOnClickListener { markSelected(4) }
        chkAvatar5.setOnClickListener { markSelected(4) }

        imgAvatar6.setOnClickListener { markSelected(5) }
        lblAvatar6.setOnClickListener { markSelected(5) }
        chkAvatar6.setOnClickListener { markSelected(5) }

        imgAvatar7.setOnClickListener { markSelected(6) }
        lblAvatar7.setOnClickListener { markSelected(6) }
        chkAvatar7.setOnClickListener { markSelected(6) }

        imgAvatar8.setOnClickListener { markSelected(7) }
        lblAvatar8.setOnClickListener { markSelected(7) }
        chkAvatar8.setOnClickListener { markSelected(7) }

        imgAvatar9.setOnClickListener { markSelected(8) }
        lblAvatar9.setOnClickListener { markSelected(8) }
        chkAvatar9.setOnClickListener { markSelected(8) }

    }

    private fun loadIntent() {
        if (intent == null || !intent.hasExtra(EXTRA_AVATAR)) {
            throw RuntimeException("No llega el intent")
        }
        viewModel.avatar = intent.getParcelableExtra<Avatar>(EXTRA_AVATAR)
        viewModel.id = viewModel.avatar.id-1
        viewModel.lastId = viewModel.id
        markSelected(viewModel.id)
    }

    private fun markSelected(id: Int) {
        viewModel.id = id
        unMarkPrevious(viewModel.lastId)
        changeCheckBoxState(viewModel.id, true)
    }

    private fun unMarkPrevious(lastId: Int) {
        changeCheckBoxState(lastId, false)
        viewModel.lastId = viewModel.id
    }

    private fun changeCheckBoxState(id: Int, state: Boolean) {
        when (id) {
            0 -> chkAvatar1.isChecked = state
            1 -> chkAvatar2.isChecked = state
            2 -> chkAvatar3.isChecked = state
            3 -> chkAvatar4.isChecked = state
            4 -> chkAvatar5.isChecked = state
            5 -> chkAvatar6.isChecked = state
            6 -> chkAvatar7.isChecked = state
            7 -> chkAvatar8.isChecked = state
            8 -> chkAvatar9.isChecked = state
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
        setActivityResult()
        finish()

    }

    private fun setActivityResult() {
        val newintent = Intent().putExtra(EXTRA_AVATAR, Database.queryAllAvatars()[viewModel.id])

        setResult(Activity.RESULT_OK, newintent)
    }

    companion object {

        const val EXTRA_AVATAR = "EXTRA_AVATAR"


    }

}


