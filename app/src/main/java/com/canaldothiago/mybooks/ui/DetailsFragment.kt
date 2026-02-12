package com.canaldothiago.mybooks.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookId = arguments?.getInt(BookConstants.KEY.BOOK_ID) ?: 0
        viewModel.getBookById(bookId)

        setObservers()
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.imageviewBack.setOnClickListener { backScreen() }
        binding.buttonRemoveBook.setOnClickListener { handleRemove(bookId) }
        binding.checkboxFavorite.setOnClickListener { handleFavorite() }
    }

    private fun handleFavorite() {
        viewModel.toggleFavorite(bookId)
    }

    private fun handleRemove(id: Int) {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.dialog_message_delete_item))
            .setPositiveButton(getString(R.string.dialog_positive_button_yes)) { _, _ ->
                viewModel.removeBook(id)
            }
            .setNegativeButton(getString(R.string.dialog_negative_button_no), null)
            .show()
    }

    private fun setObservers() {
        viewModel.book.observe(viewLifecycleOwner) { book ->
            binding.apply {
                textviewTitleValue.text = book.title
                textviewAuthorValue.text = book.author
                textviewGenreValue.text = book.genre
                textviewGenreValue.setGenreGradient(book.genre)
                checkboxFavorite.isChecked = book.favorite
            }
        }

        viewModel.bookRemoved.observe(viewLifecycleOwner) { success ->
            if (success) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_removed_successfully),
                    Toast.LENGTH_SHORT
                ).show()
                backScreen()
            }
        }
    }

    private fun backScreen() {
        findNavController().popBackStack()
    }
}