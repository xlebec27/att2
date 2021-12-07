package cs.vsu.ru.chinese_checkers.Utils;

import cs.vsu.ru.chinese_checkers.GraphUtils.Graph;
import cs.vsu.ru.chinese_checkers.Pieces.Board;
import cs.vsu.ru.chinese_checkers.Pieces.Checker;

public class BoardSetter {

    public BoardSetter(){}

    public Board setBoard(int players){
        Graph boardGraph = new Graph(121);
        for (int i = 0; i < 12; i++){
            boardGraph.addEdge(i * 10, i * 10 + 1);
            boardGraph.addEdge(i * 10, i * 10 + 2);
            boardGraph.addEdge(i * 10 + 1, i * 10 + 3);
            boardGraph.addEdge(i * 10 + 1, i * 10 + 4);
            boardGraph.addEdge(i * 10 + 2, i * 10 + 4);
            boardGraph.addEdge(i * 10 + 2, i * 10 + 5);
            boardGraph.addEdge(i * 10 + 3, i * 10 + 6);
            boardGraph.addEdge(i * 10 + 3, i * 10 + 7);
            boardGraph.addEdge(i * 10 + 4, i * 10 + 7);
            boardGraph.addEdge(i * 10 + 4, i * 10 + 8);
            boardGraph.addEdge(i * 10 + 5, i * 10 + 8);
            boardGraph.addEdge(i * 10 + 5, i * 10 + 9);
            boardGraph.addEdge(i * 10 + 1, i * 10 + 2);
            boardGraph.addEdge(i * 10 + 3, i * 10 + 4);
            boardGraph.addEdge(i * 10 + 4, i * 10 + 5);
            boardGraph.addEdge(i * 10 + 6, i * 10 + 7);
            boardGraph.addEdge(i * 10 + 7, i * 10 + 8);
            boardGraph.addEdge(i * 10 + 8, i * 10 + 9);
        }
        for (int i = 0; i < 6; i++) {
            boardGraph.addEdge(i * 10 + 6, i * 10 + 69);
            boardGraph.addEdge(i * 10 + 7, i * 10 + 69);
            boardGraph.addEdge(i * 10 + 7, i * 10 + 68);
            boardGraph.addEdge(i * 10 + 8, i * 10 + 68);
            boardGraph.addEdge(i * 10 + 8, i * 10 + 67);
            boardGraph.addEdge(i * 10 + 6, i * 10 + 67);
            boardGraph.addEdge(i * 10 + 9, i * 10 + 66);
        }
        for (int i = 0; i < 5; i++){
            boardGraph.addEdge(i * 10 + 60, i * 10 + 70);
            boardGraph.addEdge(i * 10 + 60, i * 10 + 72);
            boardGraph.addEdge(i * 10 + 61, i * 10 + 72);
            boardGraph.addEdge(i * 10 + 61, i * 10 + 75);
            boardGraph.addEdge(i * 10 + 63, i * 10 + 75);
            boardGraph.addEdge(i * 10 + 66, i * 10 + 79);
        }
        boardGraph.addEdge(110, 60);
        boardGraph.addEdge(110, 62);
        boardGraph.addEdge(111, 62);
        boardGraph.addEdge(111, 65);
        boardGraph.addEdge(113, 65);
        boardGraph.addEdge(116, 69);

        boardGraph.addEdge(6, 116);
        boardGraph.addEdge(16, 66);
        boardGraph.addEdge(26, 76);
        boardGraph.addEdge(36, 86);
        boardGraph.addEdge(46, 96);
        boardGraph.addEdge(56, 106);

        boardGraph.addEdge(60, 120);
        boardGraph.addEdge(70, 120);
        boardGraph.addEdge(80, 120);
        boardGraph.addEdge(90, 120);
        boardGraph.addEdge(100, 120);
        boardGraph.addEdge(110, 120);

        if (players == 2){
            for (int i = 0; i < 10; i++){
                boardGraph.getVertex(i).setChecker(new Checker(0));
                boardGraph.getVertex(30 + i).setChecker(new Checker(1));
            }
        }
        if (players == 3){
            for (int i = 0; i < 10; i++){
                boardGraph.getVertex(i).setChecker(new Checker(0));
                boardGraph.getVertex(20 + i).setChecker(new Checker(1));
                boardGraph.getVertex(40 + i).setChecker(new Checker(2));
            }
        }
        if (players == 4){
            for (int i = 0; i < 10; i++){
                boardGraph.getVertex(10 + i).setChecker(new Checker(0));
                boardGraph.getVertex(20 + i).setChecker(new Checker(1));
                boardGraph.getVertex(40 + i).setChecker(new Checker(2));
                boardGraph.getVertex(50 + i).setChecker(new Checker(3));
            }
        }
        if (players == 6){
            for (int i = 0; i < 10; i++){
                boardGraph.getVertex(i).setChecker(new Checker(0));
                boardGraph.getVertex(10 + i).setChecker(new Checker(1));
                boardGraph.getVertex(20 + i).setChecker(new Checker(2));
                boardGraph.getVertex(30 + i).setChecker(new Checker(3));
                boardGraph.getVertex(40 + i).setChecker(new Checker(4));
                boardGraph.getVertex(50 + i).setChecker(new Checker(5));
            }
        }
        Board board = new Board(boardGraph);
        return board;
    }
}
