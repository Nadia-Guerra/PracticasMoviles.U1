import kotlin.math.sqrt

class Raices(var a: Double, var b: Double, var c: Double) {


    fun getDiscriminante(): Double{
        return (b * b) - (4 * a * c)
    }

    fun obtenerRaices(){
        if (tieneRaices()) {
            val discriminante = getDiscriminante()
            val raiz1 = (-b + sqrt(discriminante)) / (2 * a)
            val raiz2 = (-b - sqrt(discriminante)) / (2 * a)
            println("Las dos raíces son: $raiz1 y $raiz2")
        } else {
            println("La ecuación no tiene raíces reales.")
        }
    }
    fun obtenerRaiz(){
        if (tieneRaiz()) {
            val raiz = -b / (2 * a)
            println("La única raíz es: $raiz")
        } else {
            println("La ecuación no tiene una única raíz.")
        }
    }


    fun tieneRaices(): Boolean{
        return getDiscriminante() >=0 //si lo q te regresa es >= 0 es true, si no false
    }

    fun tieneRaiz():Boolean{
        return getDiscriminante() == 0.0 //si lo q te regresa es = 0 es true, si no false
    }
    fun calcular(){
        when {
            tieneRaices() && !tieneRaiz() -> {
                println("La ecuación tiene dos soluciones:")
                obtenerRaices()
            }
            tieneRaiz() -> {
                println("La ecuación tiene una solución:")
                obtenerRaiz()
            }
            else -> println("No existen soluciones reales.")
        }
    }
}


