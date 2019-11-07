package es.iessaladillo.pedrojoya.profile.ui.main

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import android.content.Intent.EXTRA_EMAIL
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.profile.R
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity
import es.iessaladillo.pedrojoya.profile.ui.avatar.AvatarActivity.Companion.EXTRA_AVATAR
import es.iessaladillo.pedrojoya.profile.utils.*
import kotlinx.android.synthetic.main.profile_avatar.*
import kotlinx.android.synthetic.main.profile_form.*

class ProfileActivity : AppCompatActivity() {
    private val REQUEST_AVATAR = 1
    private val viewModel by lazy {
        ViewModelProvider(this)
            .get(ProfileActivityViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_activity)
        setAvatar()
        setForm()
        setClickListeners()
    }

    private fun setClickListeners() {
        lblAvatar.setOnClickListener { goToAvatarActivity() }
        imgAvatar.setOnClickListener { goToAvatarActivity() }
        imgEmail.setOnClickListener { sendEmail(txtEmail.text.toString()) }
        imgAddress.setOnClickListener { sendAddress(txtAddress.text.toString()) }
        imgPhonenumber.setOnClickListener { sendPhone(txtPhonenumber.text.toString()) }
        imgWeb.setOnClickListener { sendWeb(txtWeb.text.toString()) }

    }

    private fun sendPhone(text: String) {
        if (text.isValidPhone()) startActivity(newDialIntent(text))
        else txtPhonenumber.error = getString(R.string.profile_invalid_phonenumber)
    }

    private fun sendEmail(text: String) {
        if (text.isValidEmail())startActivity(newEmailIntent(text))
        else txtEmail.error = getString(R.string.profile_invalid_email)
}

private fun sendWeb(text: String) {
    if (text.isValidUrl()) startActivity(newViewUriIntent(Uri.parse(text)))
    else txtWeb.error = getString(R.string.profile_invalid_web)
}

private fun sendAddress(text: String) {
    if (text.isNotEmpty()) startActivity(newSearchInMapIntent(text))
    else txtAddress.error = getString(R.string.profile_invalid_address)
}


private fun setForm() {
    txtName.setText(viewModel.name)
    txtEmail.setText(viewModel.email)
    txtPhonenumber.setText(viewModel.phoneNumber)
    txtAddress.setText(viewModel.address)
    txtWeb.setText(viewModel.web)
    txtName.requestFocus()
}

private fun setAvatar() {
    lblAvatar.text = viewModel.avatar.name
    imgAvatar.setImageResource(viewModel.avatar.imageResId)
}

private fun goToAvatarActivity() {
    val intention = Intent(this, AvatarActivity::class.java)
    intention.putExtra(EXTRA_AVATAR,viewModel.avatar)

    startActivityForResult(intention, REQUEST_AVATAR)

}

override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == REQUEST_AVATAR && resultCode == Activity.RESULT_OK) {
        if (data != null) {
            viewModel.avatar = data.getParcelableExtra(EXTRA_AVATAR)
        }
        setAvatar()
    }
}


override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.profile_activity, menu)
    return super.onCreateOptionsMenu(menu)
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    if (item.itemId == R.id.mnuSave) {
        save()
        return true
    }
    return super.onOptionsItemSelected(item)
}

private fun save() {
    if (checkForm()) toast("Profile saved successfuly", Toast.LENGTH_LONG)
}

private fun checkForm(): Boolean {
    if (txtName.text.isEmpty()) {
        txtName.error = getString(R.string.profile_invalid_name)
        return false
    }
    if (!txtEmail.text.toString().isValidEmail()) {
        txtEmail.error = getString(R.string.profile_invalid_email)
        return false
    }
    if (!txtPhonenumber.text.toString().isValidPhone()) {
        txtPhonenumber.error = getString(R.string.profile_invalid_phonenumber)
        return false
    }
    if (txtAddress.text.isEmpty()) {
        txtAddress.error = getString(R.string.profile_invalid_address)
        return false
    }
    if (!txtWeb.text.toString().isValidUrl()) {
        txtWeb.error = getString(R.string.profile_invalid_web)
        return false
    }

    return true
}

}
