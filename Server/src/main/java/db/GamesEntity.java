package db;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "games", schema = "chinese_checkers")
public class GamesEntity {
    private int id;
    private int noPlayers;
    private Timestamp date;
    private List<MovesEntity> moves;

    public GamesEntity() {
        this.date = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }


    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="game_id")
    public List<MovesEntity> getMoves() {
        return moves;
    }

    public void setMoves(List<MovesEntity> moves) {
        this.moves = moves;
    }

    @Basic
    @Column(name = "noPlayers", nullable = false)
    public int getNoPlayers() {
        return noPlayers;
    }

    public void setNoPlayers(int noPlayers) {
        this.noPlayers = noPlayers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GamesEntity that = (GamesEntity) o;
        return id == that.id &&
            noPlayers == that.noPlayers &&
            Objects.equals(date, that.date) &&
            Objects.equals(moves, that.moves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, noPlayers, date, moves);
    }
}