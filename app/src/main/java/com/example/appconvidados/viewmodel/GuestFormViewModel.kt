package com.example.appconvidados.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appconvidados.service.model.GuestModel
import com.example.appconvidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application): AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    // o mutablelive data os valores podem ser mudados
    private var mSaveGuest = MutableLiveData<Boolean>()

    // livedata os valores não podem ser mudados
    val saveGuest: LiveData<Boolean> = mSaveGuest

    fun save(nome: String, presence: Boolean) {
        val guest =
            GuestModel(name = nome, presence =  presence)
        mSaveGuest.value = mGuestRepository.save(guest)
    }
}