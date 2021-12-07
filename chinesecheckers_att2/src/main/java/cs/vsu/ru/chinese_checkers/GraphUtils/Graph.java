package cs.vsu.ru.chinese_checkers.GraphUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Graph {

    private boolean[][] adjMatrix = null;
    private ArrayList<GraphVertex> vertices = new ArrayList<>();
    private int vCount = 0;
    private int eCount = 0;

    public Graph(int vertexCount) {
        adjMatrix = new boolean[vertexCount][vertexCount];
        vCount = vertexCount;
        for (int i = 0; i < vertexCount; i++){
            vertices.add(new GraphVertex(i));
        }
    }

    public Graph() {
        this(0);
    }

    public Graph(boolean[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
        this.vCount = adjMatrix.length;
        for (int i = 0; i < vCount; i++){
            for (int j = 0; j < vCount; j++){
                if (adjMatrix[i][j]){
                    eCount++;
                }
            }
        }
    }

    public Graph(boolean[][] adjMatrix, ArrayList<GraphVertex> vertices) {
        this.vertices = vertices;
        this.adjMatrix = adjMatrix;
        this.vCount = adjMatrix.length;
        for (int i = 0; i < vCount; i++){
            for (int j = 0; j < vCount; j++){
                if (adjMatrix[i][j]){
                    eCount++;
                }
            }
        }
    }

    public int vertexCount() {
        return vCount;
    }

    public int edgeCount() {
        return eCount;
    }

    public void addEdge(int v1, int v2) {
        int maxV = Math.max(v1, v2);
        if (maxV >= vertexCount()) {
            adjMatrix = Arrays.copyOf(adjMatrix, maxV + 1);
            for (int i = 0; i <= maxV; i++) {
                adjMatrix[i] = i < vCount ? Arrays.copyOf(adjMatrix[i], maxV + 1) : new boolean[maxV + 1];
            }
            vCount = maxV + 1;
        }
        if (!adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = true;
            adjMatrix[v2][v1] = true;
            eCount++;
        }
    }

    public void removeEdge(int v1, int v2) {
        if (adjMatrix[v1][v2]) {
            adjMatrix[v1][v2] = false;
            eCount--;
        }
    }

    public Iterable<Integer> adjacency(final int v) {
        return new Iterable<Integer>() {
            Integer nextAdj = null;

            public Iterator<Integer> iterator() {
                for (int i = 0; i < vCount; i++) {
                    if (adjMatrix[v][i]) {
                        nextAdj = i;
                        break;
                    }
                }

                return new Iterator<Integer>() {

                    public boolean hasNext() {
                        return nextAdj != null;
                    }

                    public Integer next() {
                        Integer result = nextAdj;
                        nextAdj = null;
                        for (int i = result + 1; i < vCount; i++) {
                            if (adjMatrix[v][i]) {
                                nextAdj = i;
                                break;
                            }
                        }
                        return result;
                    }
                };
            }
        };
    }

    public GraphVertex getVertex(int v) {return vertices.get(v);}

    public ArrayList<GraphVertex> getVertices(){return vertices;}

    public void setAdjMatrix(boolean[][] adjMatrix){
        this.adjMatrix = adjMatrix;
    }

    @JsonProperty("vCount")
    public int vCount(){return vCount;}

    @JsonProperty("eCount")
    public int eCount(){return eCount;}

    public boolean isAdj(int v1, int v2) {
        return adjMatrix[v1][v2];
    }
    @JsonProperty("adjMatrix")
    public boolean[][] getAdjMatrix() {
        return adjMatrix;
    }
}
