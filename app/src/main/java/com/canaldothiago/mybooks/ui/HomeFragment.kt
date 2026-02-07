package com.canaldothiago.mybooks.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.canaldothiago.mybooks.R
import com.canaldothiago.mybooks.databinding.FragmentHomeBinding
import com.canaldothiago.mybooks.helper.BookConstants
import com.canaldothiago.mybooks.ui.adapter.BookAdapter
import com.canaldothiago.mybooks.ui.listener.BookListener
import com.canaldothiago.mybooks.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    // Binding do Fragment
    // Guarda as views do layout fragment_home.xml
    private var _binding: FragmentHomeBinding? = null

    // Atalho seguro para usar o binding
    // O !! assume que ele não será nulo enquanto a view existir
    private val binding get() = _binding!!

    // ViewModel da tela
    // Responsável por fornecer os dados
     private val viewModel: HomeViewModel by viewModels()

    // Adapter do RecyclerView
    // Conecta os dados com a lista
    private val adapter = BookAdapter()

    /**
     * Método chamado quando a tela vai ser criada
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Infla o layout do fragment
        // Transforma o XML em objetos Kotlin
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Atribui um layout que diz como a RecyclerView se comporta
        binding.recyclerviewBooks.layoutManager = LinearLayoutManager(context)

        attachListener()

        // Conecta o adapter ao RecyclerView
        // Sem isso, nada aparece
        binding.recyclerviewBooks.adapter = adapter

        setObserver()
        // Retorna a view principal da tela
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllBooks()
    }

    // Chamado quando a view do fragment é destruída
    override fun onDestroyView() {
        super.onDestroyView()

        // Remove a referência ao layout
        // Evita vazamento de memória (memory leak)
        _binding = null
    }

    private fun attachListener() {
        adapter.attachListener(object : BookListener {
            override fun onClick(id: Int) {
                val bundle = Bundle()
                bundle.putInt(BookConstants.KEY.BOOK_ID, id)
                findNavController().navigate(R.id.navigation_details, bundle)
            }

            override fun onFavoriteClick(id: Int) {
                viewModel.favorite(id)
                viewModel.getAllBooks()
            }
        })
    }

    private fun setObserver() {
        // Observa a lista de livros do ViewModel
        // Sempre que os dados mudarem,
        // o RecyclerView será atualizado
        viewModel.books.observe(viewLifecycleOwner) {
            adapter.updateBooks(it)
        }
    }
}