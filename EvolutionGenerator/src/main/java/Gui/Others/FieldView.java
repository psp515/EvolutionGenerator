package Gui.Others;

import Interfaces.Map.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//TODO narazie wstawione zeby pamiętać
public class FieldView {
    private Image image;
    private ImageView view;
    private Label label;

    public VBox container;

    public FieldView(IMapElement strongestAnimal, int animalsCount, IMapElement food)  {

        this.container = new VBox();
        this.container.setMaxHeight(30);
        this.container.setMinWidth(30);

        if(strongestAnimal == null)
        {
            if(food != null){
                try
                {
                    this.image = new Image( new FileInputStream(food.getImage()));
                    this.view = new ImageView(this.image);
                    this.view.setFitHeight(20);
                    this.view.setFitWidth(20);

                    this.label = new Label("No Animals");
                    this.container = new VBox();
                    this.container.getChildren().add(this.view);
                    this.container.getChildren().add(this.label);
                    this.container.setAlignment(Pos.CENTER);

                }
                catch (FileNotFoundException e) {}


            }
        }
        else
        {
            try
            {
                this.image = new Image( new FileInputStream(strongestAnimal.getImage()));
                this.view = new ImageView(this.image);
                this.view.setFitHeight(20);
                this.view.setFitWidth(20);

                this.label = new Label("Number of animals: " + String.valueOf(animalsCount));
                this.container.getChildren().add(this.view);
                this.container.getChildren().add(this.label);
                this.container.setAlignment(Pos.CENTER);

            }
            catch (FileNotFoundException e) {}

        }
    }
}
