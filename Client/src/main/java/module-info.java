module techprog {
    requires javafx.controls;
    requires javafx.fxml;

    opens techprog to javafx.fxml;
    exports techprog;
}