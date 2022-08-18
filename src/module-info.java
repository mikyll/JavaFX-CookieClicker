module cookie {
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.base;
	requires transitive javafx.graphics;
	requires javafx.media;
	
	opens controller;
	
	opens application;
}