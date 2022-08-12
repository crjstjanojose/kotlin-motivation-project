package br.com.rcsistemas.motivationapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import br.com.rcsistemas.motivationapp.infra.MotivationConstants
import br.com.rcsistemas.motivationapp.R
import br.com.rcsistemas.motivationapp.data.Mock
import br.com.rcsistemas.motivationapp.infra.SecurityPreferences
import br.com.rcsistemas.motivationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esconder Barra de Navegacao
        supportActionBar?.hide()

        handleUserName()
        handleFilter(R.id.image_all)
        handleNextPhrase(categoryId)

        // EVENTOS
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.editName.setOnClickListener(this)

    }

    override fun onResume() {
        super.onResume()
        handleUserName()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNextPhrase(view.id)
        } else if (view.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)) {
            handleFilter(view.id)
        } else if (view.id == R.id.edit_name) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    private fun handleNextPhrase(id: Int) {
        binding.textPhrase.text = Mock().getPhrase(categoryId)
    }

    private fun handleFilter(id: Int) {

        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }

    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.editName.text = "Olá, $name!"
    }
}