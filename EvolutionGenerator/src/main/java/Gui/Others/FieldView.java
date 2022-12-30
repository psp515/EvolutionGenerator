package Gui.Others;

import Interfaces.Map.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//TODO narazie wstawione zeby pamiętać
public class FieldView {
    private Image image;
    private final ImageView view;
    private final Label label;

    public VBox container;

    public FieldView(IMapElement strongestAnimal, int animalsCount, boolean hasFood) throws FileNotFoundException {

        // wyglad pola
        // na śrtodku zwierze lub brak
        // w prawym górnym rogu liczba zwierząt
        // w lewym dolnym rogu ikona jest / nie ma pożywienia.

        String url = strongestAnimal == null? "" : strongestAnimal.getImage();
        this.label = new Label(strongestAnimal == null? "" : strongestAnimal.toString());

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
