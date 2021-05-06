module javafx.controller {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.base;
	requires java.xml.bind;
	requires transitive javafx.graphics;
	requires transitive java.sql;
	requires java.base;
	
	opens javafx.model to java.xml.bind;
    opens javafx.controller to javafx.fxml, java.xml.bind;
    opens javafx.utils to java.xml.bind, com.sun.xml.bind;
    exports javafx.controller;
    exports javafx.model;
    exports javafx.utils;
}