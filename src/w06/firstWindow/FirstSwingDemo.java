package w06.firstWindow;

import javax.swing.*;

public class FirstSwingDemo {
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;

    public static void main(String[] args) {
        JFrame firstWindow = new JFrame();

        firstWindow.setSize(WIDTH, HEIGHT);
        firstWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JButton endButton = new JButton("Click to end program.");

        EndButtonClickListener clickListener = new EndButtonClickListener();

        endButton.addActionListener(clickListener); //addActionListener requires a parameter of type
                                                    //which is an interface in java.awt.event
        firstWindow.add(endButton);

        firstWindow.setVisible(true);
    }
}
