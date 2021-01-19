package db;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "moves", schema = "chinese_checkers")
public class MovesEntity {
    private int moveId;
    private String playerColor;
    private int oldVerticalId;
    private int oldHorizontalId;
    private int newVerticalId;
    private int newHorizontalId;
    private GamesEntity game;

    public MovesEntity() {

    }

    public MovesEntity(String playerColor, int oldVerticalId, int oldHorizontalId, int newVerticalId, int newHorizontalId) {
        this.playerColor = playerColor;
        this.oldVerticalId = oldVerticalId;
        this.oldHorizontalId = oldHorizontalId;
        this.newVerticalId = newVerticalId;
        this.newHorizontalId = newHorizontalId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "move_id", nullable = false)
    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    @ManyToOne
    public GamesEntity getGame() {
        return game;
    }

    public void setGame(GamesEntity game) {
        this.game = game;
    }

    @Basic
    @Column(name = "player_color", nullable = false, length = 10)
    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    @Basic
    @Column(name = "old_vertical_id", nullable = false)
    public int getOldVerticalId() {
        return oldVerticalId;
    }

    public void setOldVerticalId(int oldVerticalId) {
        this.oldVerticalId = oldVerticalId;
    }

    @Basic
    @Column(name = "old_horizontal_id", nullable = false)
    public int getOldHorizontalId() {
        return oldHorizontalId;
    }

    public void setOldHorizontalId(int oldHorizontalId) {
        this.oldHorizontalId = oldHorizontalId;
    }

    @Basic
    @Column(name = "new_vertical_id", nullable = false)
    public int getNewVerticalId() {
        return newVerticalId;
    }

    public void setNewVerticalId(int newVerticalId) {
        this.newVerticalId = newVerticalId;
    }

    @Basic
    @Column(name = "new_horizontal_id", nullable = false)
    public int getNewHorizontalId() {
        return newHorizontalId;
    }

    public void setNewHorizontalId(int newHorizontalId) {
        this.newHorizontalId = newHorizontalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovesEntity that = (MovesEntity) o;
        return moveId == that.moveId && oldVerticalId == that.oldVerticalId && oldHorizontalId == that.oldHorizontalId && newVerticalId == that.newVerticalId && newHorizontalId == that.newHorizontalId && Objects.equals(playerColor, that.playerColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moveId, playerColor, oldVerticalId, oldHorizontalId, newVerticalId, newHorizontalId);
    }
}
