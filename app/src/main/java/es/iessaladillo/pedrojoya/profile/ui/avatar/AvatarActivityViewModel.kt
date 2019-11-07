package es.iessaladillo.pedrojoya.profile.ui.avatar

import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.profile.data.local.entity.Avatar

class AvatarActivityViewModel: ViewModel(){
    var id:Int = 1
    var lastId: Int = 1
    lateinit var avatar: Avatar
}