package com.example.mulchapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mulchapp.databinding.ActivityMainBinding
import com.example.mulchapp.databinding.ActivityOrderMulchBinding
import com.example.mulchapp.databinding.ActivityOrderSummaryBinding

class OrderSummaryActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.extras?.getString("KeyName2").toString()


        val recievetext = intent.getStringExtra("KeyName")
        val putText = binding.mulchOrder
        putText.text = recievetext

        val recievetextA = intent.getStringExtra("KeyName0")
        val putTextA = binding.pricepermulch
        putTextA.text = recievetextA

        val recievetextB = intent.getStringExtra("KeyName1")
        val putTextB = binding.orderDetail
        putTextB.text = recievetextB

        val recievetextC = intent.getStringExtra("KeyName2")
        val putTextC = binding.streetDetail
        putTextC.text = recievetextC

        val recievetextD = intent.getStringExtra("KeyName3")
        val putTextD = binding.cityState
        putTextD.text = recievetextD

        val recievetextE = intent.getStringExtra("KeyName4")
        val putTextE = binding.zipDetail
        putTextE.text = recievetextE

        val recievetextF = intent.getStringExtra("KeyName5")
        val putTextF = binding.emailDetail
        putTextF.text = recievetextF

        val recievetextG = intent.getStringExtra("KeyName6")
        val putTextG = binding.phoneDetail
        putTextG.text = recievetextG

        val recievetextH = intent.getStringExtra("KeyName7")
        val putTextH = binding.Cost
        putTextH.text = recievetextH

        val recievetextI = intent.getStringExtra("KeyName8")
        val putTextI = binding.sales
        putTextI.text = recievetextI

        val recievetextJ = intent.getStringExtra("KeyName9")
        val putTextJ = binding.delivey
        putTextJ.text = recievetextJ


        val recievetextK = intent.getStringExtra("KeyName10")
        val putTextK = binding.total
        putTextK.text = recievetextK

        binding.button.setOnClickListener{
            showToast("Ordered Placed")
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}