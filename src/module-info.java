module myjfx {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    opens MainPage to javafx.graphics, javafx.fxml, javafx.base;
    opens Searching to javafx.graphics, javafx.fxml, javafx.base;
    opens Login to javafx.graphics, javafx.fxml, javafx.base;
}