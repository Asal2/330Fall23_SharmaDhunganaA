package edu.noctrl.fall23_330.mulch_SharmaDhunganaA

import kotlin.math.ceil

class MulchOrder( plantingBedDimensionsList: PlantingBedDimensions) {
    private val plantingBedDimensionsAList: MutableList<PlantingBedDimensions> = mutableListOf()

    var mulchPricer: MulchPricer? = null
        set(value) {
            field = value
        }

    fun addPlantingBedDimensions(bedDimension: PlantingBedDimensions) {
        plantingBedDimensionsAList.add(bedDimension)
    }

    fun getTotalCubicYards(): Int {
        return ceil(plantingBedDimensionsAList.sumOf { (it.length * it.width * (it.depth / 12.0))/27 }).toInt()
    }
//    fun getTotalCubicYards(): Int {
//        return ceil(plantingBedDimensionsAList.sumOf { (it.length * it.width * it.depth)/300}.toDouble()).toInt()
//    }

    fun getTotalCubicFeet(): Int {
        return ceil((getTotalCubicYards() * 27).toDouble()).toInt()
    }



    fun printOrderDetails() {
        println("Planting Bed Dimensions:")
        for (bed in plantingBedDimensionsAList) {
            println("  ${bed.length}*${bed.width}*${bed.depth}")
        }
        val totalCubicYards = getTotalCubicYards()
        val totalCubicFeet = getTotalCubicFeet()
        val totalPrice = mulchPricer?.calculatePrice(totalCubicYards)
        println("Total cubic yards: $totalCubicYards")
        println("Total cubic feet: $totalCubicFeet")
        println("Total Price: $$totalPrice")
    }
}