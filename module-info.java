module gr.projects.dotsandboxes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.graphics;

    opens gr.projects.dotsandboxes to javafx.fxml;
    exports gr.projects.dotsandboxes;
}
