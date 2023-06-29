package algoritmos;

import grafos.Arco;
import grafos.GrafoNoDirigido;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class Backtracking {
    private GrafoNoDirigido<Integer> grafo;
    private int ciclos;
    private int distanciaActual;
    private ArrayList<Arco<Integer>> solucionActual;

    public Backtracking(GrafoNoDirigido<Integer> grafo){
        this.grafo = grafo;
        this.distanciaActual = Integer.MAX_VALUE;
    }

    public int getCiclos(){
        return this.ciclos;
    }

    public ArrayList<Arco<Integer>> backtracking(){
        ciclos = 0;
        distanciaActual = Integer.MAX_VALUE;
        ArrayList<Arco<Integer>> solucionParcial = new ArrayList<>();
        Iterator<Integer> vertices = grafo.obtenerVertices();
        Hashtable<Integer, Integer> sinVisitar = new Hashtable<>();
        while(vertices.hasNext()){
            Integer v = vertices.next();
            sinVisitar.put(v, v);
        }
        vertices = grafo.obtenerVertices();
        while(vertices.hasNext()){
            Integer v = vertices.next();
            sinVisitar.remove(v);
            backtracking(solucionParcial, sinVisitar, 0, v);
            sinVisitar.put(v, v);
        }
        return solucionActual;
    }
    //ARREGLARLO PARA QUE VOLVER AL ANTERIOR TAMBIÉN SEA UNA OPCIÓN DE BACKTRACKING
    public void backtracking(ArrayList<Arco<Integer>> solucionParcial,
                                                 Hashtable<Integer, Integer> noVisitados,
                                                 int distanciaParcial,
                                                 Integer verticeActual){
        this.ciclos++;
        noVisitados.remove(verticeActual);
        if(noVisitados.isEmpty() && distanciaParcial < this.distanciaActual){
            distanciaActual = distanciaParcial;
            solucionActual = new ArrayList<>(solucionParcial);
            return;
        }

        for (Integer noVisitado : noVisitados.keySet()) {
            Arco<Integer> arco = grafo.obtenerArco(verticeActual, noVisitado);
            if (distanciaParcial + arco.getEtiqueta() < this.distanciaActual) {
                solucionParcial.add(arco);
                backtracking(solucionParcial, new Hashtable<Integer, Integer>(noVisitados), distanciaParcial + arco.getEtiqueta(), noVisitado);
                solucionParcial.remove(arco);
            }
        }
    }
}

