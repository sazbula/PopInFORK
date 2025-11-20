module net.javaguids.popin {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens net.javaguids.popin to javafx.fxml;
    exports net.javaguids.popin;
}