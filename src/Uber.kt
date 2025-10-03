
fun main(){
    var disponibility:Boolean = true
    println("===UBER====")
    println("Ingresa la distancia a la que te encuentras en km: ")
    var distance = readln().toDouble()

    print("Ingresa su disponiblidad\n")
    println("1. Disponible")
    println("2. No Disponible")
    var disponibilityChoice = readln().toInt()

    if(disponibilityChoice ==1){
        disponibility = true
    }else if(disponibilityChoice ==2){
        disponibility = false
    }
    when{
        distance <= 0.5 && disponibility -> println("Listo para iniciar el recorrido")
        distance <= 0.5 && !disponibility -> println("Conductor cercano pero no disponible")
        distance > 0.5 && disponibility -> println("Conductor disponible pero muy lejos")
        distance > 0.5 && !disponibility -> println("No hay conductores disponibles :(")

    }


}