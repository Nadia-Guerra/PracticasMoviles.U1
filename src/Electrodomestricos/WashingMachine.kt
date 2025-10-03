package Electrodomestricos

class WashingMachine(
    var load: Int = LOAD,
    basePrice: Int,
    color:String,
    energyConsumption: Char,
    weight:Int,


): Electrodomestic(basePrice, color, energyConsumption, weight) {
    companion object{
        const val LOAD = 5
    }
    // Constructor con price y weight, delega al constructor primario
    constructor(basePrice: Int, weight: Int) : this(
        load = LOAD,
        basePrice = basePrice,
        color = COLOR,
        energyConsumption = ENERGY_CONSUMPTION,
        weight = weight
    )

    // Default constructor, se delega al constructor primario
    constructor() : this(
        load = LOAD,
        basePrice = BASE_PRICE,
        color = COLOR,
        energyConsumption = ENERGY_CONSUMPTION,
        weight = WEIGHT
    )

    override fun finalPrice(): Int {
        var precio: Int = super.finalPrice()
        if (load > 30) {
            precio += 50
        }
        return precio
    }

}