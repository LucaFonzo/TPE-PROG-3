import algoritmos.Backtracking;
import algoritmos.Greedy;
import grafos.Arco;
import grafos.GrafoNoDirigido;
import util.Timer;

import java.util.List;

public class Main {
    public static void main(String[] args){
        Timer timer = new Timer();

        GrafoNoDirigido<Integer> grafo = new GrafoNoDirigido<>();
        CSVReader reader = new CSVReader("src\\datasets\\dataset3.txt", grafo);
        reader.read();

        Backtracking backtracking = new Backtracking(grafo);
        timer.start();
        List<Arco<Integer>> solucion = backtracking.backtracking();
        double tiempo = timer.stop();
        int ciclos = backtracking.getCiclos();
        imprimirSolucion("Backtracking", solucion, tiempo, ciclos);

        System.out.println("--------------------------------------------------");

        Greedy greedy = new Greedy(grafo);
        timer.start();
        solucion = greedy.greedy();
        tiempo = timer.stop();
        ciclos = greedy.getCiclos();
        imprimirSolucion("Greedy", solucion, tiempo, ciclos);

    }

    public static void imprimirSolucion(String algoritmo, List<Arco<Integer>> solucion, double tiempo, int ciclos) {
        String resultado = "";
        Integer distancia  = 0;
    	for (int i = 0; i < solucion.size(); i++) {
            Arco<Integer> arco = solucion.get(i);
            if(i < solucion.size()-1){
                resultado += "E" + arco.getVerticeOrigen() + "-E" + arco.getVerticeDestino() + ",";
            }
    		else {
                resultado += "E" + arco.getVerticeOrigen() + "-E" + arco.getVerticeDestino();
            }
            distancia += arco.getEtiqueta();
    	}
        System.out.println(algoritmo);
        System.out.println(resultado);
        System.out.println(distancia + " km");
        System.out.println(ciclos + " ciclos en un tiempo total de ejecución de " + tiempo + "ms");
    }
}
