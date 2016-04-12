package w06.labelAndColour;

import javax.swing.*;
import java.awt.*;

public class LabelAndColour extends JFrame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 200;

    public LabelAndColour(Color theColour){
        super("Label and Colour"); //this constructor overload sets window's title

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(theColour);

        JLabel theLabel = new JLabel("Window's close button now terminates program!");
        add(theLabel);
    }

    public LabelAndColour(){
        this(Color.GREEN); //this is how to call one constructor from another in java
        //LabelAndColour(Color.GREEN); //makes more sense but is illegal syntax
    }

}
