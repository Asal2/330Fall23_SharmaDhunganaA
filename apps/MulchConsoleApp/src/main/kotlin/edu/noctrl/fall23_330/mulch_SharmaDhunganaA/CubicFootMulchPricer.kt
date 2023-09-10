package edu.noctrl.fall23_330.mulch_SharmaDhunganaA

import edu.noctrl.fall23_330.mulch_SharmaDhunganaA.MulchPricer

class CubicFootMulchPricer: MulchPricer {
    override fun calculatePrice(cubicYards: Int): Double {
        val cubicFeet = cubicYards * 27
        val bagPrice = 2
        return when{
            cubicFeet < 5*bagPrice -> cubicFeet*3.97
            cubicFeet < 10*bagPrice -> cubicFeet*3.47
            else -> cubicFeet*2.97*bagPrice
        }
    }
}