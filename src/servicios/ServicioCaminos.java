package servicios;

import grafos.Grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class ServicioCaminos {

    private Grafo<?> grafo;
    private int origen;
    private int destino;
    private int lim;

    // Servicio caminos
    public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
        this.grafo = grafo;
        this.origen = origen;
        this.destino = destino;
        this.lim = lim;
    }

    public List<List<Integer>> caminos() {
        List<List<Integer>> caminos = new ArrayList<>();
        LinkedList<ArrayList<Integer>> fila = new LinkedList<>();
        ArrayList<Integer> comienzo = new ArrayList<>();
        comienzo.add(origen);
        fila.add(comienzo);

        while(!fila.isEmpty()){
            ArrayList<Integer> caminoActual = fila.pop();
            Integer actual = caminoActual.get(caminoActual.size() - 1);

            if(actual == destino){
                caminos.add(caminoActual);
                continue;
            }
            if(caminoActual.size() > lim){
                continue;
            }

            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
            while(adyacentes.hasNext()){
                Integer adyacente = adyacentes.next();
                ArrayList<Integer> nuevaRama = new ArrayList<>(caminoActual);
                nuevaRama.add(adyacente);
                fila.addLast(nuevaRama);
            }
        }

        return caminos;
    }
}