package com.example.ats.Model

class Pregunta {


    var solucion: String
    var problema: String
    var alternativas : Any

    var numeros = mutableListOf<Number>()


    constructor(solucion: String, problema: String, alternativas: Any) {
        this.solucion = solucion
        this.problema = problema
        this.alternativas = alternativas
    }

}