package Electrodomestricos

import java.util.*

open class Electrodomestic(
    open var basePrice: Int = BASE_PRICE,
    open var color:String = COLOR,
    open var energyConsumption:Char = ENERGY_CONSUMPTION,
    open var weight:Int = WEIGHT,
) {
//    init {
//        this.color = checkColor(color)
//        this.energyConsumption = checkEnergyConsumption(energyConsumption)
//    }

    //el constructor primario siempre se manda a llamar


  //  constructor():this(BASE_PRICE, COLOR, ENERGY_CONSUMPTION, WEIGHT)
    constructor(basePrice: Int, weight: Int ) : this(basePrice, COLOR, ENERGY_CONSUMPTION, weight)
  //  constructor(basePrice: Int, color: String, weight: Int, energyConsumption: Char):this(BASE_PRICE, COLOR, ENERGY_CONSUMPTION, weight)


    companion object{
        const val BASE_PRICE= 100
        const val COLOR = "Blanco"
        const val ENERGY_CONSUMPTION = 'F'
        const val WEIGHT = 5
    }

    //METODS
    fun checkEnergyConsumption(letra: Char){
        var letters = charArrayOf('A', 'B', 'C', 'D', 'E', 'F')
        var flag = false

        {
            var i = 0
            while (i < letters.size && !flag) {
                if (letters[i] == letra) {
                    flag = true
                }
                i++
            }
        }
        this.energyConsumption = if (flag) this.energyConsumption else ENERGY_CONSUMPTION
    }

    private fun checkColor(color: String): String {
        val colors = arrayOf<String?>("blanco", "negro", "rojo", "azul", "gris") //se pasa a minusculas todo
        var flag = false
        var i = 0
        while (i < colors.size && !flag) {
            if (colors[i].equals(
                    color.lowercase(Locale.getDefault()),
                    ignoreCase = true
                )
            ) {
                flag = true
            }

            i++
        }
        return if (flag) color else COLOR
    }

    open fun finalPrice(): Int{
        var finalPrice = 0
        when(energyConsumption){
            'A' -> finalPrice = 100
            'B' -> finalPrice = 80
            'C' -> finalPrice = 60
            'D' -> finalPrice = 50
            'E' -> finalPrice = 30
            'F' -> finalPrice = 10
        }
        finalPrice += when (weight) {
            in 0..19 -> 10
            in 20..49 -> 50
            in 50..79 -> 80
            else -> 100
        }
        return finalPrice
    }


}
