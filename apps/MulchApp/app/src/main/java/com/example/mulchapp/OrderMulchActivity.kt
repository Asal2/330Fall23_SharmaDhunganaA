package com.example.mulchapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import com.example.mulchapp.databinding.ActivityMainBinding
import com.example.mulchapp.databinding.ActivityOrderMulchBinding
import android.util.Log
class OrderMulchActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrderMulchBinding

    
    private val mulchTypeList = mutableMapOf<String,Int>(
        "Premium Dark Mulch" to 56,
        "Special Blend" to 35,
        "Triple Ground" to 40,
        "Chocolate Dyed" to 38,
        "Red Dyed" to 38,
        "Black Dyed" to 38,
        "Play Mat" to 38,
        "Cedar" to 38
    )

    private var deliveryCharge: Int = 0;
    private var salesTax: Double = 0.07;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderMulchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent?.extras?.getString("KeyName1").toString()

        val receivedText = intent.getStringExtra("KeyName1")
        val putReceivedText = binding.mulchType
        putReceivedText.text = receivedText


        val getMulchtype = mulchTypeList[receivedText]
        val mulchCostOption = binding.mulchCostOption;
        mulchCostOption.text = getMulchtype?.let { "$$it per cubic yard" }


        binding.calculateBtn.setOnClickListener{
            calculateTotal()

            val intent = Intent(this, OrderSummaryActivity::class.java)


            var selectMulch = binding.mulchType
            var selectcubicyard = binding.numberCubicyard
            var takestring  = selectMulch.text.toString()
            var takecubicyard = selectcubicyard.text.toString()
            var addtypePrice = "$takestring - $takecubicyard cubic yards"
            intent.putExtra("KeyName", addtypePrice);


            var selectMulchOption = binding.mulchCostOption
            var takestringA  = selectMulchOption.text.toString()
            intent.putExtra("KeyName0", takestringA);

            var selectcubicYard = binding.numberCubicyard
            var selectMulchType = binding.mulchType
            var takestringB  = selectcubicYard.text.toString()
            var taketype = selectMulchType.text.toString()
            var adddelivery = "Delivering $takestringB cubic yard of $taketype to: "
            intent.putExtra("KeyName1", adddelivery);

            var selectStreet = binding.Street
            var takestringC  = selectStreet.text.toString()
            intent.putExtra("KeyName2", takestringC);

            var selectCity = binding.City
            val selectState = binding.State
            var takestringD  = selectCity.text.toString()
            var takestate = selectState.text.toString()
            var addtext = "$takestringD, $takestate"
            intent.putExtra("KeyName3", addtext);


            var selectZip = binding.zipCode
            var takestringE  = selectZip.text.toString()
            intent.putExtra("KeyName4", takestringE);


            var selectEmail = binding.email
            var takestringF  = selectEmail.text.toString()
            intent.putExtra("KeyName5", takestringF);


            var selectPhone = binding.phone
            var takestringG  = selectPhone.text.toString()
            intent.putExtra("KeyName6", takestringG);


            var selectCost = binding.Cost
            var takestringH  = selectCost.text.toString()
            intent.putExtra("KeyName7", takestringH);


            var selectSale = binding.sales
            var takestringI  = selectSale.text.toString()
            intent.putExtra("KeyName8", takestringI);


            var selectDelivery = binding.delivery
            var takestringJ  = selectDelivery.text.toString()
            intent.putExtra("KeyName9", takestringJ);


            var selectToal = binding.total
            var takestringK  = selectToal.text.toString()
            intent.putExtra("KeyName10", takestringK);




            startActivity(intent)
        }
    }
    private fun calculateTotal(){
        // getting the text from edit view
        val cubicMulchId = binding.numberCubicyard.text.toString()
        //changing the text recieve to double
        val numberofCubicYards = cubicMulchId.toDouble();
        //geting the zipcode text from edit view
        val getZipeCode = binding.zipCode.text.toString();
        // changing the text to int
        val takezipcode = getZipeCode.toInt()
        // getting the price of mulchtype
        val getmulchprice = findViewById<TextView>(R.id.mulchCostOption)
        //chaging the price got into text
        val getprice = getmulchprice.text.toString()
        //receive the an string value that is number
        val numericValue = getprice.replace(Regex("[^0-9]"), "").toInt()

        if (takezipcode === 60540){
            deliveryCharge = 25
        }else if (takezipcode === 60563){
            deliveryCharge = 30
        }else if(takezipcode === 60564){
            deliveryCharge = 35
        }else if(takezipcode === 60565){
            deliveryCharge = 35
        }
        else if(takezipcode === 60187){
            deliveryCharge = 40
        }else if(takezipcode === 60188){
            deliveryCharge = 40
        }else if(takezipcode === 60189){
            deliveryCharge = 35
        }else if(takezipcode === 60190){
            deliveryCharge = 40
        }else{
            println("Delivery not available")
        }

        // rendering mulch cost on text view
        val mulchwithoutTax = (numericValue*numberofCubicYards);
        val mulchCost = binding.Cost
        mulchCost.text = "Mulch Cost: $$mulchwithoutTax"


        // rendering sales tax on text view
        val salesTax = salesTax * mulchwithoutTax;
        val formatSalestax = String.format("%.2f",salesTax)
        val sale = binding.sales
        sale.text = "Sales Tax: $$formatSalestax"

        // rendering delivery charge on text view
        val delivery = binding.delivery
        delivery.text = "Delivery Charge: $$deliveryCharge"

        //rendering total cost of mulch on text view
        val mulchwithTax = mulchwithoutTax + salesTax
        val totalCost = mulchwithTax + deliveryCharge;
        val total = binding.total
        total.text = "Total: $$totalCost"

    }


}
