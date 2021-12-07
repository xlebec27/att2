package cs.vsu.ru.chinese_checkers.Game;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs.vsu.ru.chinese_checkers.GraphUtils.Graph;
import cs.vsu.ru.chinese_checkers.GraphUtils.GraphVertex;
import cs.vsu.ru.chinese_checkers.Pieces.Board;
import cs.vsu.ru.chinese_checkers.Pieces.Checker;
import cs.vsu.ru.chinese_checkers.Utils.BoardSetter;
import cs.vsu.ru.chinese_checkers.serialization.GameSerialization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class ChineseCheckersGame {

    private static final Logger log = LoggerFactory.getLogger(ChineseCheckersGame.class);

    private GameStatus status;
    private int turn;
    int players;
    private Board board;
    private boolean isJumping;

    public ChineseCheckersGame(int players, Board board) {
        this.board = board;
        this.status = GameStatus.INACTIVE;
        this.turn = 0;
        this.players = players;
        log.info("Game initialized.");
    }

    public ChineseCheckersGame(int players){
        this.players = players;
        startNewGame(players);
        log.info("Game initialized.");
    }

    public ChineseCheckersGame(){
        players = 2;
        startNewGame(players);
    }

    public GameStatus getStatus(){
        return status;
    }

    public void setStatus(GameStatus status){
        this.status = status;
    }

    public int checkTurn(){
        return turn;
    }

    public void nextTurn(){
        if (turn < players - 1) {
            turn++;
        }
        else {
            turn = 0;
        }
        isJumping = false;
    }

    public void startNewGame(int players){
        if (players < 2 || players == 5 || players > 6){
            return;
        }
        board = new BoardSetter().setBoard(players);;
        log.info("Board initialized");
        status = GameStatus.ACTIVE;
        log.info("Game started.");
    }

    public boolean move(int from, int to){
        if (from < 0 || to < 0 || from > 120 || to > 120) {
            log.error("Invalid indices of cells: " + from + ", " + to);
            return false;
        }
        Checker checker = board.checkChecker(from);

        if (checker == null) {
            log.info("Move is not possible. Place " + from + " is empty.");
            return false;
        }
        if (checker.player() != turn) {
            log.info("Move is not possible. " + from + " is not current player's checker");
            return false;
        }
        boolean connected = board.connected(from, to);
        boolean hopOver = board.connectedOverOne(from, to);
        if ((!isJumping && connected) || hopOver) {
            board.getChecker(from);
            board.setChecker(to, checker);
            log.info(turn + " player's checker " + from + " successfully moved at position " + to);
            if (connected) {
                nextTurn();
                return true;
            } else if (hopOver) {
                isJumping = true;
                return true;
            }
        }
        log.info("Move from " + from + " to " + to + " is not possible.");
        return false;
    }

    public void checkWinner(){
        if (turn < 3) {
            for (int i = 0; i < 10; i++) {
                if (board.checkChecker(((turn + 3) * 10) + i).player() != turn) {
                    log.info("The winner is not determined");
                    break;
                } else {
                    status = GameStatus.ENDED;
                    log.info("The winner is player " + turn);
                }
            }
        }
        else {
            for (int i = 0; i < 10; i++) {
                if (board.checkChecker(((turn - 3) * 10) + i).player() != turn) {
                    log.info("The winner is not determined");
                    break;
                } else {
                    status = GameStatus.ENDED;
                    log.info("The winner is player " + turn);
                }
            }
        }
    }

    public int getCheckerPlayer(int vertexIndex){
        Checker checker = board.checkChecker(vertexIndex);
        if (checker == null){
            return -1;
        }
        else {
            return checker.player();
        }
    }

    @JsonProperty("isJumping")
    public boolean isJumping(){return isJumping;}

    public GameSerialization getGameSerialization(){return new GameSerialization(status, turn, players, board, isJumping);}

    public void fromSerialization(GameSerialization gs){
        status = gs.getStatus();
        board = new BoardSetter().setBoard(gs.getPlayers());
        board = gs.getBoard();
        players = gs.getPlayers();
        turn = gs.getTurn();
        isJumping = gs.isJumping();
    }
}
