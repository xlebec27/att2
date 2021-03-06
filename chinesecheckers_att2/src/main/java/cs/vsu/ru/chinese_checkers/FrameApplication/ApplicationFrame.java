package cs.vsu.ru.chinese_checkers.FrameApplication;

import cs.vsu.ru.chinese_checkers.Game.ChineseCheckersGame;
import cs.vsu.ru.chinese_checkers.Utils.ScriptedCommandsProvider;
import cs.vsu.ru.chinese_checkers.serialization.GameSerialization;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ApplicationFrame extends Application {

    private static final Logger log = LoggerFactory.getLogger(ApplicationFrame.class);
    private static final int BOARD_HEIGHT = 17;
    private static final int BOARD_LENGTH = 25;
    private ChineseCheckersGame game;
    private int selectedNode;
    private Scene scene;
    private int players;

    @Override
    public void start(Stage primaryStage) throws Exception {
        players = 2;
        scene = new Scene(startNewGame(players));
        primaryStage.setTitle("CheckersApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent startNewGame(int players){
        game = new ChineseCheckersGame(players);
        return createBoard();
    }


    public Parent createBoard() {
        GridPane gameBoard = new GridPane();
        int[][] boardArr = {
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, -1, 2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 3, -1, 4, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, 6, -1, 7, -1, 8, -1, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {50, -1, 52, -1, 55, -1, 59, -1, 116, -1, 69, -1, 68, -1, 67, -1, 66, -1, 16, -1, 13, -1, 11, -1, 10},
                {-1, 51, -1, 54, -1, 58, -1, 117, -1, 113, -1, 65, -1, 64, -1, 63, -1, 79, -1, 17, -1, 14, -1, 12, -1},
                {-1, -1, 53, -1, 57, -1, 118, -1, 114, -1, 111, -1, 62, -1, 61, -1, 75, -1, 78, -1, 18, -1, 15, -1, -1},
                {-1, -1, -1, 56, -1, 119, -1, 115, -1, 112, -1, 110, -1, 60, -1, 72, -1, 74, -1, 77, -1, 19, -1, -1, -1},
                {-1, -1, -1, -1, 106, -1, 103, -1, 101, -1, 100, -1, 120, -1, 70, -1, 71, -1, 73, -1, 76, -1, -1, -1, -1},
                {-1, -1, -1, 49, -1, 107, -1, 104, -1, 102, -1, 90, -1, 80, -1, 82, -1, 85, -1, 89, -1, 26, -1, -1, -1},
                {-1, -1, 45, -1, 48, -1, 108, -1, 105, -1, 91, -1, 92, -1, 81, -1, 84, -1, 88, -1, 27, -1, 23, -1, -1},
                {-1, 42, -1, 44, -1, 47, -1, 109, -1, 93, -1, 94, -1, 95, -1, 83, -1, 87, -1, 28, -1, 24, -1, 21, -1},
                {40, -1, 41, -1, 43, -1, 46, -1, 96, -1, 97, -1, 98, -1, 99, -1, 86, -1, 29, -1, 25, -1, 22, -1, 20},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1, 38, -1, 37, -1, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 35, -1, 34, -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 32, -1, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
        };
        for (int i = 0; i < boardArr.length; i++) {
            for (int j = 0; j < boardArr[i].length; j++) {
                if (boardArr[i][j] != -1) {
                    final CheckersNode node = new CheckersNode(boardArr[i][j], game.getCheckerPlayer(boardArr[i][j]));
                    node.setPickOnBounds(true);
                    node.setOnDragDetected(event -> {
                        node.startFullDrag();
                        selectedNode = node.getNodeNum();
                    });
                    node.setOnMouseDragReleased(event ->{
                        CheckersNode target = (CheckersNode) event.getTarget();
                        move(selectedNode, target.getNodeNum());
                    });
                    gameBoard.add(node, j + 2, i);
                }
            }
        }
        Button settings = new Button("??????????????????");
        settings.setOnAction(settingsEvent ->{
            GameSettings gameSettings = new GameSettings();
            gameSettings.show();
        });
        settings.setPickOnBounds(false);
        gameBoard.add(settings, 0, 0);
        Button nextTurn = new Button("?????????? ??????");
        nextTurn.setOnAction(settingsEvent ->{
            game.nextTurn();
            log.info("?????? ?????????????? ?? ???????????? " + game.checkTurn());
        });
        gameBoard.add(nextTurn, 1, 0);
        gameBoard.setVgap(15);

        gameBoard.setPickOnBounds(false);
        return gameBoard;
    }

    private void refreshBoard(){
        GridPane board = (GridPane) scene.getRoot();
        for (int i = 0; i < 121; i++) {
            ((CheckersNode) board.getChildren().get(i)).setChecker(game.getCheckerPlayer((((CheckersNode) board.getChildren().get(i)).getNodeNum())));
        }

    }

    public class CheckersNode extends Circle{
        private int nodeNum;

        public CheckersNode(int num, int player){
            this.setRadius(20);
            this.setStroke(Color.BLACK);
            setChecker(player);
            nodeNum = num;
        }
        public int getNodeNum(){
            return nodeNum;
        }

        private void setChecker(int player){
            switch (player) {
                case 0 :
                    this.setFill(Color.FIREBRICK);
                    this.setStrokeWidth(2);
                    break;
                case 1:
                    this.setFill(Color.ROYALBLUE);
                    this.setStrokeWidth(2);
                    break;
                case 2:
                    this.setFill(Color.LAWNGREEN);
                    this.setStrokeWidth(2);
                    break;
                case 3:
                    this.setFill(Color.IVORY);
                    this.setStrokeWidth(2);
                    break;
                case 4:
                    this.setFill(Color.INDIGO);
                    this.setStrokeWidth(2);
                    break;
                case 5:
                    this.setFill(Color.DIMGRAY);
                    this.setStrokeWidth(2);
                    break;
                default:
                    this.setFill(Color.BURLYWOOD);
                    this.setStrokeWidth(1);
            }
        }
    }

    public class GameSettings extends Stage{
        public GameSettings(){
            this.setTitle("Settings");
            this.initModality(Modality.APPLICATION_MODAL);
            this.setResizable(false);
            GridPane settingsPane = new GridPane();
            MenuBar menubar = new MenuBar();
            Menu newGameMenu = new Menu("?????????? ????????");
            MenuItem twoPlayersGame = new MenuItem("2 ????????????");
            twoPlayersGame.setOnAction(newGameEvent -> {
                startNewGame(2);
                refreshBoard();
            });
            MenuItem threePlayersGame = new MenuItem("3 ????????????");
            threePlayersGame.setOnAction(newGameEvent -> {
                startNewGame(3);
                refreshBoard();
            });
            MenuItem fourPlayersGame = new MenuItem("4 ????????????");
            fourPlayersGame.setOnAction(newGameEvent -> {
                startNewGame(4);
                refreshBoard();
            });
            MenuItem sixPlayersGame = new MenuItem("6 ????????????");
            sixPlayersGame.setOnAction(newGameEvent -> {
                startNewGame(6);
                refreshBoard();
            });
            TextField fileName = new TextField();
            newGameMenu.getItems().addAll(twoPlayersGame, threePlayersGame, fourPlayersGame, sixPlayersGame);
            menubar.getMenus().add(newGameMenu);
            settingsPane.addRow(1, menubar);
            Button loadButton = new Button("?????????????????? ????????");
            loadButton.setOnAction(load ->{
                File savesDirectory = FileSystems.getDefault().getPath("saves").toAbsolutePath().toFile();
                if (!savesDirectory.exists()) {
                    log.error("Cannot load file. Saves directory does not exist.");
                } else {
                    GameSerialization gs = null;
                    try {
                        gs = GameSerialization.read((new File(FileSystems.getDefault().getPath("saves").toAbsolutePath() + "\\" + fileName.getText() + ".json")));
                    } catch (IOException e) {
                        e.printStackTrace();
                        log.error("Cannot read file: " + e.getMessage());
                    }
                    game.fromSerialization(gs);
                    log.info("Game deserialized");
                    refreshBoard();
                }
            });
            Button saveButton = new Button("?????????????????? ????????");
            saveButton.setOnAction(load ->{
                GameSerialization gs = game.getGameSerialization();
                try {
                    gs.save(fileName.getText(), gs);
                    log.info("Game saved: " + fileName.getText());
                } catch (IOException e) {
                    log.error("Failed to save game.\n Error: " + e.getMessage());
                }
            });
            settingsPane.addRow(3, fileName, saveButton, loadButton);
            Button demoPlay = new Button("???????????????????????????????? ????????");
            demoPlay.setOnAction(demo ->{
                log.info("???????????????? ???????????????????????????????? ????????");
                demoPlay();
            });
            settingsPane.addRow(1, demoPlay);
            Scene s = new Scene(settingsPane);
            this.setScene(s);
        }
    }

    private void demoPlay() {
        ScriptedCommandsProvider scp = new ScriptedCommandsProvider();
        String commandLine = "null";
        int cell;
        while (commandLine != null){
            commandLine = scp.getNextLine();
            if (!commandLine.equals("next")){
                cell = Integer.parseInt(commandLine);
                selectedNode = cell;
                commandLine = scp.getNextLine();
                cell = Integer.parseInt(commandLine);
                move(selectedNode, cell);
            }
            else {
                game.nextTurn();
            }
        }
    }

    private void move(int i, int j) {
        game.move(i, j);
        refreshBoard();
    }
}