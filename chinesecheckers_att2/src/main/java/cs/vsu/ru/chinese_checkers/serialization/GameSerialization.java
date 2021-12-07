package cs.vsu.ru.chinese_checkers.serialization;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.vsu.ru.chinese_checkers.Game.GameStatus;
import cs.vsu.ru.chinese_checkers.GraphUtils.Graph;
import cs.vsu.ru.chinese_checkers.Pieces.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GameSerialization {

    private static final Logger log = LoggerFactory.getLogger(GameSerialization.class);
    private GameStatus status;
    private int turn;
    private int players;
    private Board board;
    private boolean jumping;


    public GameSerialization(GameStatus status, int turn, int players, Board board, boolean isJumping) {
        this.status = status;
        this.turn = turn;
        this.players = players;
        this.board = board;
        jumping = isJumping;
    }

    public GameSerialization() {
    }

    public GameStatus getStatus() {
        return status;
    }

    public int getTurn() {
        return turn;
    }

    public int getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    @JsonProperty("isJumping")
    public boolean isJumping() {
        return jumping;
    }

    @JsonProperty("jumping")
    public boolean jump() {
        return jumping;
    }

    @JsonProperty("boardGraph")
    public Graph getBoardGraph() {
        return board.getBoardGraph();
    }

    public static void save(String saveName, GameSerialization gc) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        File savesDir = FileSystems.getDefault().getPath("saves").toAbsolutePath().toFile();
        if (savesDir.mkdir()) {
            log.info("Saves directory created" + savesDir.getAbsolutePath());
        }
        File save = new File(savesDir.getAbsolutePath(), saveName + ".json");
        if (save.createNewFile()) {
            log.info("File " + save.getAbsolutePath() + " saved");
        }
        objectMapper.writeValue(save, gc);
    }

    public static GameSerialization read(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        return objectMapper.readValue(file, GameSerialization.class);
    }
}