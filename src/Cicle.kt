//Haz un ciclo for y while que obtenga la sumatoria de los números hasta n,
//ejemplo, para 5 debes obtener 15 (1+2+3+4+5), para 3 debes obtener 6.
//Imprime el resultado así: "La suma es 15" usando formatos de String


fun main(){
    println("Ingresa un numero: ")
    var counter:Int = 0
    var number:Int = 1
    var n:Int = readln().toInt()

    for(i in 1..n){
        counter = counter +(number++).toInt()
    }
    println("La suma es $counter")

    println("Factorial de $n")

    var factorial:Long = 1
    var index = 1
    while(index < n){
        factorial *= index
        index++

    }
    println("Factorial de $factorial")


}