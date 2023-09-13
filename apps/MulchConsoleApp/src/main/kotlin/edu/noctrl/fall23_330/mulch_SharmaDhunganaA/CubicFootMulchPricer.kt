package edu.noctrl.fall23_330.mulch_SharmaDhunganaA

import edu.noctrl.fall23_330.mulch_SharmaDhunganaA.MulchPricer
import kotlin.math.ceil

class CubicFootMulchPricer: MulchPricer {
    override fun calculatePrice(cubicYards: Int): Double {
        val cubicFeet:Double= cubicYards * 27.0
        val noOfBags = ceil(cubicFeet)/2
        return when{
            noOfBags < 5  -> noOfBags*3.97
            noOfBags < 10 -> noOfBags*3.47
            else -> noOfBags*2.97
        }
    }
}