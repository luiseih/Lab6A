package w06.layoutManager;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutManager extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;

    public BorderLayoutManager(){
        super("BorderLayout Manager Demo");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout()); //this.setLayout(anonymous, in-situ instantiation of a LayoutManager object)

        JLabel labelNorth = new JLabel("North");
        labelNorth.setHorizontalAlignment(SwingConstants.CENTER); //Sets the alignment of the label's contents along the X axis.
        add(labelNorth, BorderLayout.PAGE_START); //This is a convenience method for addImpl(java.awt.Component, java.lang.Object, int)

        JLabel labelEast = new JLabel("East");
        labelEast.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelEast, BorderLayout.LINE_START);

        JLabel labelWest = new JLabel("West");
        labelWest.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelWest, BorderLayout.LINE_END);

        JLabel labelCentre = new JLabel("Centre");
        labelCentre.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelCentre, BorderLayout.CENTER);

        JLabel labelSouth = new JLabel("South");
        labelSouth.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelSouth, BorderLayout.PAGE_END);
    }
}
