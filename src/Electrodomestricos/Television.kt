package Electrodomestricos

import Electrodomestricos.WashingMachine


class Television(
    var resolution: Int = RESOLUTION,
    var tuner:Boolean = false,
    basePrice: Int,
    color:String,
    energyConsumption: Char,
    weight:Int,
): Electrodomestic(basePrice, color, energyConsumption, weight) {
    companion object{
        const val RESOLUTION = 20
        const val TUNER = false

    }
    // Default constructor, se delega al constructor primario
    constructor() : this(
        RESOLUTION,
        tuner = TUNER,
        basePrice = BASE_PRICE,
        color = COLOR,
        energyConsumption = ENERGY_CONSUMPTION,
        weight = WEIGHT
    )
    //constructor price y weight
    constructor(basePrice: Int, weight: Int) : this(
        resolution = RESOLUTION,
        tuner = TUNER,
        basePrice = basePrice,
        color = COLOR,
        energyConsumption = ENERGY_CONSUMPTION,
        weight = weight
    )

    override fun finalPrice(): Int {
        var price: Int = super.finalPrice()
        if (resolution > 40) {
            price = (price * 1.3).toInt()
        }
        if (tuner) {
            price += 50
        }
        return price
    }
}

//class Television(
//    // Primary constructor with specific properties and inherited properties
//    resolution: Int,
//    tuner: Boolean,
//    basePrice: Int,
//    color: String,
//    energyConsumption: Char,
//    weight: Int,
//) : Electrodomestic(basePrice, color, energyConsumption, weight) {
//
//    // Properties of Television
//    var resolution: Int = resolution
//    var tuner: Boolean = tuner
//
//    companion object {
//        const val RESOLUTION = 20
//        const val TUNER = false
//  }