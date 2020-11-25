package com.example.appconvidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appconvidados.R
import com.example.appconvidados.service.constants.GuestConstants
import com.example.appconvidados.view.adapter.GuestAdapter
import com.example.appconvidados.view.listener.GuestListener
import com.example.appconvidados.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

    private lateinit var mViewModel: GuestsViewModel
    private val mAdapter: GuestAdapter = GuestAdapter()
    private lateinit var mListener: GuestListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        s: Bundle?
    ): View? {
        mViewModel =
            ViewModelProviders.of(this).get(GuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_absent, container, false)

        // RecyclerView
        // obter recycler
        val recycler = root.findViewById<RecyclerView>(R.id.recycler_absents)

        // definir layout
        recycler.layoutManager = LinearLayoutManager(context)

        // definir adapter
        recycler.adapter = mAdapter

        mListener = object: GuestListener {
            override fun OnClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)

                val bundle = Bundle()
                bundle.putInt(GuestConstants.GUESTID, id)

                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun OnDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.ABSENT)
            }
        }

        mAdapter.attachListener(mListener)
        observer()

        return root

    }

    override fun onResume() {
        super.onResume()
        mViewModel.load(GuestConstants.FILTER.ABSENT)
    }

    private fun observer() {
        mViewModel.guesList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}