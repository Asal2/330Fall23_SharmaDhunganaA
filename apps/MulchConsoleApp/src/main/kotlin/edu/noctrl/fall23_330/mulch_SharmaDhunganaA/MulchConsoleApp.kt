package edu.noctrl.fall23_330.mulch_SharmaDhunganaA

fun main(){
    val cubicFootPricer = CubicFootMulchPricer()
    val mulchOrder2 = MulchOrder (PlantingBedDimensions(30, 10, 5))
    mulchOrder2.addPlantingBedDimensions(PlantingBedDimensions(30,10,5));
    mulchOrder2.addPlantingBedDimensions(PlantingBedDimensions(43,14, 4))
    mulchOrder2.mulchPricer = cubicFootPricer

    val mulchOrder4 = MulchOrder (PlantingBedDimensions(3, 1, 5))
    mulchOrder4.addPlantingBedDimensions(PlantingBedDimensions(3,1,5));
    mulchOrder4.mulchPricer = cubicFootPricer



    val cubicYardPricer = CubicYardMulchPricer()
    val mulchOrder3 = MulchOrder(PlantingBedDimensions(20,15,40))
    mulchOrder3.addPlantingBedDimensions(PlantingBedDimensions(20, 15, 40))
    mulchOrder3.addPlantingBedDimensions((PlantingBedDimensions(55, 20, 32)))
    mulchOrder3.mulchPricer = cubicYardPricer

    val mulchOrder5 = MulchOrder(PlantingBedDimensions(34,22,15))
    mulchOrder5.addPlantingBedDimensions(PlantingBedDimensions(34, 22, 15))
    mulchOrder5.mulchPricer = cubicYardPricer


    mulchOrder2.printOrderDetails ()
    println("Another Cubic Foot Pricer: ......")
    mulchOrder4.printOrderDetails()

    println()

    mulchOrder3.printOrderDetails()
    println("Another Cubic Yard Pricer: ......")
    mulchOrder5.printOrderDetails()
}