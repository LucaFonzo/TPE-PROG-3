package algoritmos;

import grafos.Grafo;
import grafos.Arco;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Greedy {
    private Grafo<Integer> grafo;
    private int ciclos;

    public Greedy(Grafo<Integer> grafo) {
        this.grafo = grafo;
    }

    public int getCiclos() {
        return ciclos;
    }

    public List<Arco<Integer>> greedy(){
        ciclos = 0;
        ArrayList<Arco<Integer>> solucion = new ArrayList<>();
        HashSet<Integer> noVisitados = new HashSet<>();
        Iterator<Integer> vertices = grafo.obtenerVertices();

        while(vertices.hasNext()){
            Integer vertice = vertices.next();
            noVisitados.add(vertice);
            ciclos++;
        }

        vertices = grafo.obtenerVertices();

        while(vertices.hasNext()){
            Integer vertice = vertices.next();
            noVisitados.remove(vertice);
            Arco<Integer> arco = seleccionar(vertice, noVisitados);
            if(factible(arco)){
                solucion.add(arco);
            }
            ciclos++;
        }
        return solucion;
    }

    public Arco<Integer> seleccionar(Integer vertice, HashSet<Integer> noVisitados){
        Arco<Integer> seleccionado = null;
        Iterator<Integer> noVisitadosIterator = noVisitados.iterator();
        //O(n)
        while(noVisitadosIterator.hasNext()){
            Arco<Integer> arco = grafo.obtenerArco(vertice, noVisitadosIterator.next());
            if((seleccionado == null) || (arco.getEtiqueta() < seleccionado.getEtiqueta())){
                seleccionado = arco;
            }
            ciclos++;
        }
        return seleccionado;
    }

    public boolean factible(Arco<Integer> arco){
        return arco != null;
    }
}
