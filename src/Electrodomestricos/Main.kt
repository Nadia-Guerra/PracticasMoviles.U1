package Electrodomestricos

public fun main(){
    //aregglo feo
    val electrodomestics: Array<Electrodomestic?> = arrayOfNulls(10)

    electrodomestics[0] = Electrodomestic(basePrice = 200, weight = 20, color = "blanco", energyConsumption = 'F')
    electrodomestics[1] = WashingMachine(basePrice = 200, weight = 45)
    electrodomestics[2] = Television(resolution = 50, tuner = true, weight = 50, basePrice = 500, color = "rojo", energyConsumption = 'B')
    electrodomestics[3] = Electrodomestic(basePrice = 300, weight = 30)
    electrodomestics[4] = WashingMachine(basePrice = 200, weight = 45)
    electrodomestics[5] = Television(resolution = 40, tuner = true, weight = 60, basePrice = 400, color = "gris", energyConsumption = 'A')
    electrodomestics[6] = Electrodomestic(basePrice = 200, weight = 30)
    electrodomestics[7] = WashingMachine(basePrice = 600, weight = 40, color = "azul", energyConsumption = 'D')
    electrodomestics[8] = Television(resolution = 70, tuner = true, weight = 60, basePrice = 700, color = "rojo", energyConsumption = 'E')
    electrodomestics[9] = Electrodomestic(basePrice = 300, weight = 60, color = "verde", energyConsumption = 'C')

    var totalElectrodomestics = 0
    var totalWashingMachines = 0
    var totalTelevisions = 0
    println("== PRECIOS FINALES ==\n")

    for (i in electrodomestics.indices) {
        val e = electrodomestics[i]   // aquí ya es Electrodomestic?

        if (e != null) {
            val precio = e.finalPrice()

            totalElectrodomestics += precio

            when (e) {
                is WashingMachine -> {
                    totalWashingMachines += precio
                    println("Lavadora ${i + 1}: $precio €")
                }
                is Television -> {
                    totalTelevisions += precio
                    println("Televisión ${i + 1}: $precio €")
                }
                else -> {
                    println("Electrodoméstico ${i + 1}: $precio €")
                }
            }
        }
    }


    println("\n=== TOTALES ===")
    println("Total Lavadoras: " + totalWashingMachines + " €")
    println("Total Televisiones: " + totalTelevisions + " €")
    println("Total Electrodomésticos: " + totalElectrodomestics + " €")
}


