package com.example.appconvidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appconvidados.R
import com.example.appconvidados.service.model.GuestModel
import com.example.appconvidados.view.listener.GuestListener
import kotlinx.android.synthetic.main.row_guest.view.*

class GuestViewHolder(itemView: View, private val listener: GuestListener) : RecyclerView.ViewHolder(itemView) {

    fun bind(guest: GuestModel) {
        val txtName = itemView.findViewById<TextView>(R.id.textName)
        txtName.text = guest.name

        txtName.setOnClickListener {
            listener.OnClick(guest.id)
        }

        txtName.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle(R.string.remocao_convidado)
                .setMessage(R.string.deseja_remover)
                .setPositiveButton(R.string.remover) { dialog, which ->
                    listener.OnDelete(guest.id)
                }
                .setNeutralButton(R.string.cancelar, null)
                .show()

            listener.OnDelete(guest.id)
            true
        }

    }

}