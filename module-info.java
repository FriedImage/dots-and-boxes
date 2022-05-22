module com.test.jfxgame {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.test.jfxgame to javafx.fxml;
    exports com.test.jfxgame;
}
