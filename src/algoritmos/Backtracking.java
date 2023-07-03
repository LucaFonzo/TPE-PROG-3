package algoritmos;

import grafos.Arco;
import grafos.GrafoNoDirigido;
import java.util.ArrayList;
import java.util.HashSet;
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
        HashSet<Integer> sinVisitar = new HashSet<>();
        while(vertices.hasNext()){Integer v = vertices.next();
            sinVisitar.add(v);
        }
        vertices = grafo.obtenerVertices();
        while(vertices.hasNext()){
            Integer v = vertices.next();
            backtracking(solucionParcial, new HashSet<>(sinVisitar), 0, v, null);
        }
        return solucionActual;
    }

    public void backtracking(ArrayList<Arco<Integer>> solucionParcial,
                                                 HashSet<Integer> noVisitados,
                                                 int distanciaParcial,
                                                 Integer verticeActual,
                                                 Integer verticeAnterior){
        this.ciclos++;
        noVisitados.remove(verticeActual);
        if(noVisitados.isEmpty() && distanciaParcial < this.distanciaActual){
            distanciaActual = distanciaParcial;
            solucionActual = new ArrayList<>(solucionParcial);
            return;
        }

        //Explora la opción de volver al anterior
        if(verticeAnterior != null){
            backtracking(solucionParcial, new HashSet<Integer>(noVisitados), distanciaParcial, verticeAnterior, null);
        }
        //Explora la opción de ir por el resto de los nodos que están aún sin visitar
        for (Integer noVisitado : noVisitados) {
            Arco<Integer> arco = grafo.obtenerArco(verticeActual, noVisitado);
            if (distanciaParcial + arco.getEtiqueta() < this.distanciaActual) {
                solucionParcial.add(arco);
                backtracking(solucionParcial, new HashSet<Integer>(noVisitados), distanciaParcial + arco.getEtiqueta(), noVisitado, verticeActual);
                solucionParcial.remove(arco);
            }
        }
    }
}

