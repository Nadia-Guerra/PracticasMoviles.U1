import Raices
fun main() {

    //ejercicio posión multijugos
    var respuesta: Int
   println("Ingresa una cantidad de miligramos para su posión multijugos")

    respuesta = readLine()!!.toInt()
    if (respuesta > 100){
        println("Felicidades, es una buena posión multijugos")
    }else{
        println("La pocion es mediocre, sangre sucia inmunda")
    }


    //ejercicio ecuación cuadrática
    println("Ingresa 3 numeros para realizar una ecuación cuadrática")
    println("Ingresa el valor de a: ")
    val a = readLine()!!.toDouble()
    println("Ingresa el valor de b: ")
    val b = readLine()!!.toDouble()
    println("Ingresa el valor de c: ")
    val c = readLine()!!.toDouble()

    val ecuacion = Raices(a, b, c) //se le pasan los nums ingresados
    ecuacion.calcular()


}