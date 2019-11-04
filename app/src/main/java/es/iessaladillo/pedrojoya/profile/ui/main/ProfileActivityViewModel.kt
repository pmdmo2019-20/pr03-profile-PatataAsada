package es.iessaladillo.pedrojoya.profile.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import es.iessaladillo.pedrojoya.profile.data.local.Database
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar

class ProfileActivityViewModel(application: Application): AndroidViewModel(application){
    var avatar: Avatar = Database.queryDefaultAvatar()
    var name: String = ""
    var email: String = ""
    var phoneNumber: String = ""
    var address: String = ""
    var web:String = ""
}