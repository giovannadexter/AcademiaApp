package com.example.academiaapp.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.academiaapp.R

class TreinosFragment : Fragment() {

    private val treinosFixos = listOf(
        Treino("Treino de Peito", listOf("Supino Reto", "Supino Inclinado", "Crucifixo")),
        Treino("Treino de Costas", listOf("Puxada", "Remada", "Levantamento Terra")),
        Treino("Treino de Pernas", listOf("Agachamento", "Leg Press", "Extensora")),
        Treino("Treino de Braços", listOf("Rosca Direta", "Tríceps Testa", "Martelo")),
        Treino("Treino de Ombros", listOf("Elevação Lateral", "Desenvolvimento", "Crucifixo Inverso"))
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_montar_treino, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewTreinos)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = TreinoAdapter(treinosFixos)

        return view
    }

    data class Treino(val nome: String, val exercicios: List<String>)

    class TreinoAdapter(private val lista: List<Treino>) :
        RecyclerView.Adapter<TreinoAdapter.TreinoViewHolder>() {

        class TreinoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nome = view.findViewById<TextView>(android.R.id.text1)
            val exercicios = view.findViewById<TextView>(android.R.id.text2)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreinoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_2, parent, false)
            return TreinoViewHolder(view)
        }

        override fun onBindViewHolder(holder: TreinoViewHolder, position: Int) {
            val treino = lista[position]
            holder.nome.text = treino.nome
            holder.exercicios.text = treino.exercicios.joinToString(", ")
        }

        override fun getItemCount(): Int = lista.size
    }
}
