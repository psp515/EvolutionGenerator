package Gui;

import Interfaces.Map.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//TODO narazie wstawione zeby pamiętać
public class VieldView {
    private Image image;
    private final ImageView view;
    private final Label label;

    public VBox container;

    public VieldView(IMapElement element, int elementsCount, boolean hasGrass) throws FileNotFoundException {

        String url = element == null? "" : element.getImage();
        this.label = new Label(element == null? "" : element.toString());

        this.image = !url.equals("") ? new Image( new FileInputStream(url)) : null;
        this.view = new ImageView(this.image);
        this.view.setFitHeight(40);
        this.view.setFitWidth(40);
        this.container = new VBox();
        this.container.getChildren().add(this.view);
        this.container.getChildren().add(this.label);
        this.container.setAlignment(Pos.CENTER);
    }

}
