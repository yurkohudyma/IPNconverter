package ua.hudyma;

import javax.swing.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main
{
    public static void main( String[] args )
    {
        IPNController ipn = new IPNController("Hudyma IPNconv.1");
        ipn.setVisible(true);
        ipn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ipn.setSize(250,95);
        ipn.setResizable(false);
        ipn.setLocationRelativeTo(null);
    }
}
