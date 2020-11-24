package com.example.appconvidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconvidados.R
import com.example.appconvidados.view.adapter.GuestAdapter
import com.example.appconvidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private lateinit var allGuestsViewModel: AllGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        allGuestsViewModel =
            ViewModelProviders.of(this).get(AllGuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)

        // RecyclerView
        // obter recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_all)

        // definir layout
        recycler.layoutManager = LinearLayoutManager(context)

        // definir adapter
        recycler.adapter = GuestAdapter()

        return root
    }
}