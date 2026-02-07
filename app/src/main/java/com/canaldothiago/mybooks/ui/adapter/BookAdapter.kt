package com.canaldothiago.mybooks.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.canaldothiago.mybooks.databinding.ItemBookBinding
import com.canaldothiago.mybooks.entity.BookEntity
import com.canaldothiago.mybooks.ui.listener.BookListener
import com.canaldothiago.mybooks.ui.viewholder.BookViewHolder

// Adapter é o "gerente" do RecyclerView
// Ele decide:
// 1) quantos itens existem
// 2) como criar cada item
// 3) como ligar os dados ao layout
class BookAdapter : RecyclerView.Adapter<BookViewHolder>() {

    // Lista de dados que o RecyclerView vai mostrar
    // Se essa lista estiver vazia, a tela fica vazia
    private var bookList = listOf<BookEntity>()
    private lateinit var bookListener: BookListener

    // Esse método é chamado quando o RecyclerView
    // precisa CRIAR um novo item visual
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BookViewHolder {

        // Infla o layout do item (item_book.xml)
        // Transforma o XML em uma View que o Kotlin entende
        val view = ItemBookBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        // Entrega essa View para o ViewHolder
        return BookViewHolder(view, bookListener)
    }

    // Esse método é chamado para LIGAR os dados
    // a um item que já foi criado
    override fun onBindViewHolder(
        holder: BookViewHolder, position: Int
    ) {
        // Pega o livro da posição atual
        // Manda o livro para o ViewHolder
        // preencher o layout com os dados
        holder.bind(bookList[position])
    }

    // Diz ao RecyclerView quantos itens existem
    // Ele chama isso antes de montar a lista
    override fun getItemCount(): Int {
        return bookList.size
    }

    // Método usado para atualizar os dados do adapter
    // SEM isso, o RecyclerView não muda
    fun updateBooks(list: List<BookEntity>) {
        bookList = list
        notifyDataSetChanged()
    }

    fun attachListener(listener: BookListener) {
        bookListener = listener
    }
}