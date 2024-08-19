package model;

public class CalculadoraMetroJardas {

    public CalculadoraMetroJardas(){

    }



    public Double converteMetroJardas(Double metro){

        Double jardas;

        jardas = metro/0.9144;

        return jardas;
    }


    public Double converteJardasMetro(Double jardas){

        Double metro;

        metro = jardas*0.9144;

        return metro;

    }
}
