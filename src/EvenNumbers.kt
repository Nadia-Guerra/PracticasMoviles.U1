fun main(){
    println("Ingresa un numero")
    var number = readln().toInt()
    var contador = 0

    for(i in 1..number){
        contador++
        var residuo = contador%2
        if(residuo==0){
            println("Numero $contador")
        }
    }

}
