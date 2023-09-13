package edu.noctrl.fall23_330.mulch_SharmaDhunganaA

fun main(){
    val cubicFootPricer = CubicFootMulchPricer()
    val mulchOrder2 = MulchOrder (PlantingBedDimensions(30, 10, 5))
    mulchOrder2.addPlantingBedDimensions(PlantingBedDimensions(30,10,5));
    mulchOrder2.addPlantingBedDimensions(PlantingBedDimensions(43,14, 4))
    mulchOrder2.mulchPricer = cubicFootPricer

//    val cubicYardPricer = CubicYardMulchPricer()
//    val mulchOrder3 = MulchOrder(PlantingBedDimensions(20,15,40))
//    mulchOrder3.addPlantingBedDimensions(PlantingBedDimensions(20, 15, 40))
//    mulchOrder2.addPlantingBedDimensions((PlantingBedDimensions(55, 20, 32)))
//    mulchOrder3.mulchPricer = cubicYardPricer



    mulchOrder2.printOrderDetails ()

    println()

//    mulchOrder3.printOrderDetails()
}