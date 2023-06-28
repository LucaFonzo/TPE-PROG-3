package algoritmos;

import grafos.Grafo;
import grafos.Arco;

import java.util.ArrayList;
import java.util.Hashtable;
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

    //O(n+n^2) = O(n^2)
    public List<Arco<Integer>> greedy(){
        ArrayList<Arco<Integer>> solucion = new ArrayList<>();
        ciclos = 0;
        Hashtable<Integer, Integer> noVisitados = new Hashtable<>();
        Iterator<Integer> vertices = grafo.obtenerVertices();

        //O(n)
        while(vertices.hasNext()){
            Integer vertice = vertices.next();
            noVisitados.put(vertice, vertice);
            ciclos++;
        }

        vertices = grafo.obtenerVertices();
        //O(n^2) porque se llama n veces a seleccionar que es de orden n
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

    public Arco<Integer> seleccionar(Integer vertice, Hashtable<Integer, Integer> noVisitados){
        Arco<Integer> seleccionado = null;
        Iterator<Integer> noVisitadosIterator = noVisitados.values().iterator();
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
