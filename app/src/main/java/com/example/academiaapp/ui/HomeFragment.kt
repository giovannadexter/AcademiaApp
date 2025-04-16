package com.example.academiaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.academiaapp.R

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<Button>(R.id.buttonTreinos).setOnClickListener {
            findNavController().navigate(R.id.montarTreinoFragment)
        }

        view.findViewById<Button>(R.id.buttonImc).setOnClickListener {
            findNavController().navigate(R.id.calculadoraImcFragment)
        }

        view.findViewById<Button>(R.id.buttonTimer).setOnClickListener {
            findNavController().navigate(R.id.timerFragment)
        }

        return view
    }
}
