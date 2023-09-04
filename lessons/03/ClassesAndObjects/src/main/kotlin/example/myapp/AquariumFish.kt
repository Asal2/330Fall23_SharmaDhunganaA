package example.myapp

interface FishAction  {
    fun eat()
}
abstract class AquariumFish : FishAction {
    abstract val color: String
    override fun eat() = println("yum")
}

class Shark: FishAction, FishColor {
    override val color = "grey"
    override fun eat() {
        println("hunt and eat fish")
    }
}

interface FishColor {
    val color: String
}
class Plecostomus (fishColor: FishColor = GoldColor):
    FishAction by PrintingFishAction("eat algae"),
    FishColor by fishColor

object GoldColor : FishColor {
    override val color = "gold"
}

class PrintingFishAction(val food: String) : FishAction {
    override fun eat() {
        println(food)
    }
}
