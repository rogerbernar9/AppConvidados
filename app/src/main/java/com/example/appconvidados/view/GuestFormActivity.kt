package com.example.appconvidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.appconvidados.viewmodel.GuestFormViewModel
import com.example.appconvidados.R
import com.example.appconvidados.service.constants.GuestConstants
import kotlinx.android.synthetic.main.activity_guest_form.*

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestFormViewModel
    private var mGuestId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_form)
        // onCreate  https://stackoverflow.com/questions/54313453/how-to-instantiate-viewmodel-in-androidx
        mViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        ).get(GuestFormViewModel::class.java)
//        mViewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        setListeners()
        observe()
        loadData()

        radioPresent.isChecked = true
    }

    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.GUESTID)
            mViewModel.load(mGuestId)
        }

    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "Sucesso !", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "Falha !", Toast.LENGTH_LONG).show()
            }
            finish()
        })

        mViewModel.guest.observe(this, Observer {
            editName.setText(it.name)
            if (it.presence) {
                radioPresent.isChecked = true
            } else {
                radioAbsent.isChecked = true
            }
        })
    }

    private fun setListeners() {
        buttonSave.setOnClickListener(this)
    }



    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.buttonSave) {

            // pega o texto escrito pelo usuario
            val name = editName.text.toString()

            // pega a radio button se for checado
            val presence = radioPresent.isChecked

            mViewModel.save(mGuestId, name, presence)

        }
    }


}