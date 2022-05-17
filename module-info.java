module gr.csihu.ntolias {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.graphics;

    opens gr.csihu.ntolias to javafx.fxml;
    exports gr.csihu.ntolias;
}
