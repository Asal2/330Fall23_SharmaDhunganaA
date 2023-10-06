package com.example.mulchapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.TextView
import com.example.mulchapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener{
            val intent = Intent(this, OrderMulchActivity::class.java)
            var selectRadioButton = binding.MulchOption.checkedRadioButtonId;
            var idChecked = findViewById<RadioButton>(selectRadioButton);
            var takeString = idChecked.text.toString()
            intent.putExtra("KeyName1", takeString);
            startActivity(intent)
        }
    }
}