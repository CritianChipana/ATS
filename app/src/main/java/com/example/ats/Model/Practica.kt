package com.example.ats.Model

class Practica {

    var solucion: String
    var problema: String
    var alternativas : Any

    var numeros = mutableListOf<Number>(12,15,14)


    constructor(solucion: String, problema: String, alternativas: Any) {
        this.solucion = solucion
        this.problema = problema
        this.alternativas = alternativas
    }



}