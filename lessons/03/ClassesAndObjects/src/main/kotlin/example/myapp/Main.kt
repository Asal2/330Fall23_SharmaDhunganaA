package example.myapp

import example.myapp.decor.Choice
import example.myapp.decor.Direction
import example.myapp.decor.makeDecorations
import example.myapp.example.myapp.Aquarium
import example.myapp.example.myapp.TowerTank


fun buildAquarium() {
//    val aquarium1 = Aquarium()
//    aquarium1.printSize()
//    // default height and length
//    val aquarium2 = Aquarium(width = 25)
//    aquarium2.printSize()
//    // default width
//    val aquarium3 = Aquarium(height = 35, length = 110)
//    aquarium3.printSize()
//    // everything custom
//    val aquarium4 = Aquarium(width = 25, height = 35, length = 110)
//    aquarium4.printSize()

    val aquarium6 = Aquarium(length = 25, width = 25, height = 40)
    aquarium6.printSize()
//    aquarium6.volume = 70
//    aquarium6.printSize()

    val myTower = TowerTank(diameter = 25, height = 40)
    myTower.printSize()
}

fun makeFish() {
    val shark = Shark()
    val pleco = Plecostomus()
    println("Shark: ${shark.color}")
    shark.eat()
    println("Plecostomus: ${pleco.color}")
    pleco.eat()
}


fun main(){
//    buildAquarium()
//    makeFish()
//    makeDecorations()

//    println(Direction.EAST.name)
//    println(Direction.EAST.ordinal)
//    println(Direction.EAST.degrees)

    println(Choice.name)
    Choice.showDescription("pick")
    Choice.showDescription("selection")
}