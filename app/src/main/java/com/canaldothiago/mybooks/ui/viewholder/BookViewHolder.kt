package com.canaldothiago.mybooks.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.canaldothiago.mybooks.R
import com.canaldothiago.mybooks.databinding.ItemBookBinding
import com.canaldothiago.mybooks.entity.BookEntity
import com.canaldothiago.mybooks.utils.setGenreGradient

// ViewHolder representa UM item da lista
// Ele segura as referências das views do layout do item
class BookViewHolder(
    private val item: ItemBookBinding
) : RecyclerView.ViewHolder(item.root) {

    // Esse método recebe UM livro
    // e coloca os dados dele na tela
    fun bind(book: BookEntity) {
        // Aqui também é onde você colocaria:
        // - clique no item
        // - carregar imagem
        // - mudar cor, visibilidade etc
        item.textviewTitle.text = book.title
        item.textviewGenre.text = book.genre
        item.textviewAuthor.text = book.author
        item.textviewGenre.setGenreGradient(book.genre)
        setFavoriteIcon(book.favorite)

    }

    private fun setFavoriteIcon(isFavorite: Boolean) {
        if (isFavorite) {
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite_empty)
        }
    }
}