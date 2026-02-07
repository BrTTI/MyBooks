package com.canaldothiago.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.canaldothiago.mybooks.R
import com.canaldothiago.mybooks.databinding.FragmentDetailsBinding
import com.canaldothiago.mybooks.helper.BookConstants
import com.canaldothiago.mybooks.utils.setGenreGradient
import com.canaldothiago.mybooks.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private var bookId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, b: Bundle?): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        setObservers()
        setListeners()

        bookId = arguments?.getInt(BookConstants.KEY.BOOK_ID) ?: 0
        viewModel.getBookById(bookId)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setListeners() {
        binding.imageviewBack.setOnClickListener {
            backScreen()
        }
        binding.buttonRemoveBook.setOnClickListener { handleRemove(bookId) }
        binding.checkboxFavorite.setOnClickListener { hadleFavorite() }
    }

    private fun hadleFavorite() {
        viewModel.togleFavorite(bookId)
    }

    private fun handleRemove(id: Int) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.dialog_message_delete_item))
            .setPositiveButton(getString(R.string.dialog_positive_button_yes)) { dialog, _ ->
                viewModel.removeBook(id)
                dialog.dismiss()
            }.setNegativeButton(getString(R.string.dialog_negative_button_no)) { dialog, _ ->
                dialog.dismiss()
            }.show()
        builder.create()
    }

    private fun setObservers() {
        viewModel.book.observe(viewLifecycleOwner) {
            binding.textviewTitleValue.text = it.title
            binding.textviewAuthorValue.text = it.author
            binding.textviewGenreValue.text = it.genre
            binding.textviewGenreValue.setGenreGradient(it.genre)
            binding.checkboxFavorite.isChecked = it.favorite
        }
        viewModel.bookRemoved.observe((viewLifecycleOwner)) {
            if (it) {
                Toast.makeText(
                    requireContext(), getString(R.string.msg_removed_successfully),
                    Toast.LENGTH_SHORT
                ).show()
                backScreen()
            }
        }
    }

    private fun backScreen() {
        requireActivity().supportFragmentManager.popBackStack()
    }
}