package cs.vsu.ru.chinese_checkers.Pieces;

import cs.vsu.ru.chinese_checkers.GraphUtils.Graph;
import cs.vsu.ru.chinese_checkers.Utils.BoardSetter;

public class Board {
    private Graph boardGraph;

    public Board(Graph boardGraph){
        this.boardGraph = boardGraph;
    }
    public Board(){
    }

    public void setChecker(int vertexIndex, Checker checker){
        boardGraph.getVertex(vertexIndex).setChecker(checker);
    }

    public Checker getChecker(int vertexIndex){
        Checker checker = boardGraph.getVertex(vertexIndex).getChecker();
        boardGraph.getVertex(vertexIndex).setChecker(null);
        return checker;
    }

    public Graph getBoardGraph(){return boardGraph;}

    public boolean connected(int i, int k) {
        return boardGraph.isAdj(i, k);
    }
    public boolean connectedOverOne(int i, int k){
        for (Integer l : boardGraph.adjacency(i)) {
            if (checkChecker(l) == checkChecker(i)) {
                if (boardGraph.isAdj(k, l)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Checker checkChecker(int vertexIndex){
        return boardGraph.getVertex(vertexIndex).getChecker();
    }
}
