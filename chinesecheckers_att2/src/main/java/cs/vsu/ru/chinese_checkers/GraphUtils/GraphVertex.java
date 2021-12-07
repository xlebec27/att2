package cs.vsu.ru.chinese_checkers.GraphUtils;

import cs.vsu.ru.chinese_checkers.Pieces.Checker;

public class GraphVertex {
    private Checker checker;
    private int vertexIndex;

    public GraphVertex(Checker checker, int vertexIndex){
        this.checker = checker;
        this.vertexIndex = vertexIndex;
    }

    GraphVertex(int vertexIndex){
        this.vertexIndex = vertexIndex;
    }

    GraphVertex(){}

    public Checker getChecker(){
        return checker;
    }
    public void setChecker(Checker checker){
        this.checker = checker;
    }

    public int getVertexIndex(){return vertexIndex;}
}
