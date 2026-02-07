package com.canaldothiago.mybooks.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.canaldothiago.mybooks.R
import com.canaldothiago.mybooks.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        // Configura bottom navigation
        setUpNavigation()

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
    }

    /**
     * Configura a navegação com o `BottomNavigationView` e a `ActionBar`.
     *
     * Este método realiza a configuração da navegação entre os diferentes fragmentos utilizando o `BottomNavigationView`.
     * Ele associa o `BottomNavigationView` ao controlador de navegação (`NavController`) e define a `AppBarConfiguration`
     * para que a `ActionBar` possa se comportar de acordo com a navegação.
     * Além disso, o método faz a configuração do `NavController` para que ele controle a navegação entre os fragmentos definidos,
     * como `navigation_home` e `navigation_favorite`.
     */
    private fun setUpNavigation() {
        val navView: BottomNavigationView = binding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)
    }
}