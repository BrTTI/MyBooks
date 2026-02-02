package com.canaldothiago.mybooks.utils

import android.graphics.drawable.GradientDrawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.canaldothiago.mybooks.R

fun TextView.setGenreGradient(genreName: String) {
    // 1. Criamos o objeto que desenha o degradê
    val gradientDrawable = GradientDrawable(
        GradientDrawable.Orientation.TL_BR, // Direção: Top-Left para Bottom-Right
        intArrayOf(0, 0) // Cores temporárias que vamos mudar abaixo
    )

    // 2. Definimos as cores baseadas no nome do gênero
    val (startColorRes, endColorRes) = when (genreName.lowercase()) {
        "romance" -> R.color.genre_romance_start to R.color.genre_romance_end
        "ficção científica" -> R.color.genre_sci_fi_start to R.color.genre_sci_fi_end
        "mistério" -> R.color.genre_mystery_start to R.color.genre_mystery_end
        "fantasia" -> R.color.genre_fantasy_start to R.color.genre_fantasy_end
        "suspense" -> R.color.genre_suspense_start to R.color.genre_suspense_end
        "terror" -> R.color.genre_horror_start to R.color.genre_horror_end
        "biografia" -> R.color.genre_biography_start to R.color.genre_biography_end
        "história" -> R.color.genre_history_start to R.color.genre_history_end
        "autoajuda" -> R.color.genre_self_help_start to R.color.genre_self_help_end
        "poesia" -> R.color.genre_poetry_start to R.color.genre_poetry_end
        else -> R.color.black to R.color.black // Cor padrão caso não encontre
    }

    // 3. Convertemos os IDs do XML para cores reais
    val startColor = ContextCompat.getColor(context, startColorRes)
    val endColor = ContextCompat.getColor(context, endColorRes)

    // 4. Aplicamos as cores e o arredondamento
    gradientDrawable.colors = intArrayOf(startColor, endColor)
    gradientDrawable.cornerRadius = 50f // Define o arredondamento (em pixels)

    // 5. Colocamos o fundo no TextView
    this.background = gradientDrawable
}