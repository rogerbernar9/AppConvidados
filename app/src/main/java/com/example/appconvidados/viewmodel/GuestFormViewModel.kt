package com.example.appconvidados.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appconvidados.service.model.GuestModel
import com.example.appconvidados.service.repository.GuestRepository

class GuestFormViewModel: ViewModel() {

    private val mGuestRepository: GuestRepository = GuestRepository()

    // o mutablelive data os valores podem ser mudados
    private var mSaveGuest = MutableLiveData<Boolean>()

    // livedata os valores não podem ser mudados
    val saveGuest: LiveData<Boolean> = mSaveGuest

    fun save(nome: String, presence: Boolean) {
        val guest = GuestModel(nome, presence)
        mGuestRepository.save(guest)
    }
}