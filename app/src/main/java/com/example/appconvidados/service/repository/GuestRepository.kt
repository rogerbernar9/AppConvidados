package com.example.appconvidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.example.appconvidados.service.constants.DataBaseConstants
import com.example.appconvidados.service.model.GuestModel

class GuestRepository (context: Context) {

    private val mDatabase = GuestDatabase.getDatabase(context).guestDAO()

    fun get(id: Int): GuestModel {
        return mDatabase.get(id)
    }

    fun getAll(): List<GuestModel> {
        return mDatabase.getInvited()

    }

    fun getPresent(): List<GuestModel> {
        return mDatabase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return mDatabase.getAbsent()
    }

    fun save(guest: GuestModel): Boolean {
        return mDatabase.save(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return mDatabase.update(guest) > 1
    }

    fun delete(guest: GuestModel) {
        return mDatabase.delete(guest)
    }

}