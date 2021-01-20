package techprog;

import javafx.scene.paint.Color;
import techprog.board.Field;
import techprog.board.Pawn;

import static java.lang.Math.sqrt;

public class WatchGameController extends BoardController {
    @Override
    protected void receiveWelcomeMessage() {
        var welcomeMessage = client.getWelcomeMessage();

        noPlayers = welcomeMessage.getNoPlayers();
    }

    @Override
    protected void drawFields(int radius, int space) {
        for (int i = 0; i < board.getNoRows(); i++) {
            double y = (i * (2 * radius + space)) * sqrt(3) / 2 + (radius + space);

            for (int j = 0; j < board.getNoFieldsInRow(i); j++) {
                Field field = board.getField(i, board.getHorizontalConstant(i) + j);
                int x = (board.getNoIgnoredFields(i) + j) * (2 * radius + space);

                if (i % 2 == 1) {
                    x += (2 * radius + space) / 2;
                }

                field.setRadius(radius);
                field.setCenterX(x);
                field.setCenterY(y);
                field.setFill(Color.GRAY);


                board.getChildren().addAll(field);
            }
        }
    }

    @Override
    protected void drawPawns(int radius, int space) {
        for (int i = 0; i < board.getNoRows(); i++) {
            double y = (i * (2 * radius + space)) * sqrt(3) / 2 + (radius + space);

            for (int j = 0; j < board.getNoFieldsInRow(i); j++) {
                Pawn pawn = board.getPawn(i, board.getHorizontalConstant(i) + j);
                if (pawn != null) {
                    int x = (board.getNoIgnoredFields(i) + j) * (2 * radius + space);

                    if (i % 2 == 1) {
                        x += (2 * radius + space) / 2;
                    }

                    pawn.setRadius(radius);
                    pawn.setCenterX(x);
                    pawn.setCenterY(y);
                    pawn.setFill(pawn.getColor());

                    board.getChildren().addAll(pawn);
                }
            }
        }
    }

}
