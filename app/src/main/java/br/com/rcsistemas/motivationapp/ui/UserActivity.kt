package br.com.rcsistemas.motivationapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.rcsistemas.motivationapp.infra.MotivationConstants
import br.com.rcsistemas.motivationapp.R
import br.com.rcsistemas.motivationapp.infra.SecurityPreferences
import br.com.rcsistemas.motivationapp.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }


}