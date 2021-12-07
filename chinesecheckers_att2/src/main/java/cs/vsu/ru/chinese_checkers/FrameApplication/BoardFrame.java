package cs.vsu.ru.chinese_checkers.FrameApplication;

public class BoardFrame {
    private int[][] board_matrix;
    final int BOARD_HEIGHT;
    final int BOARD_LENGTH;

    public BoardFrame() {
        this.BOARD_HEIGHT = 17;
        this.BOARD_LENGTH = 25;
        this.board_matrix = new int[BOARD_HEIGHT][BOARD_LENGTH];
    }
}
