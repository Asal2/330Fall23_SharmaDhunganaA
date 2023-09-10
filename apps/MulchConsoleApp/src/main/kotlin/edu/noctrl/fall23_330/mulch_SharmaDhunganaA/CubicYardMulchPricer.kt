package edu.noctrl.fall23_330.mulch_SharmaDhunganaA

import edu.noctrl.fall23_330.mulch_SharmaDhunganaA.MulchPricer

class CubicYardMulchPricer: MulchPricer {
    override fun calculatePrice(cubicYards: Int): Double {
        return when{
            cubicYards <=3 -> cubicYards * 33.50
            cubicYards <10 -> cubicYards * 31.50
            else -> cubicYards * 29.50
        }
    }
}