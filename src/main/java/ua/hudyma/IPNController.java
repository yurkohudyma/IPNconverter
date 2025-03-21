package ua.hudyma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class IPNController extends JFrame {
    static JTextField input;
    static JLabel label;
    static TextHandler handler = new TextHandler();

    public IPNController(String s) {
        super(s);
        setLayout(new FlowLayout());
        input = new JTextField(6);
        label = new JLabel("5 перших літер ІПН");
        add(label);
        add(input);
        input.addActionListener(handler);
    }

    static class TextHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String res, output;
            int number = 0;
            if (e.getSource() == input) {
                JTextField field = (JTextField) e.getSource();
                res = field.getText();
                try {
                    number = Integer.parseInt(res);
                    output = (res + "").length() <= 5 ? convertIPNtoDate(number) : "Забагато";
                    input.setText(output);
                } catch (NumberFormatException ex) {
                    input.setText("Не число");
                }
            }
        }

        private String convertIPNtoDate(int res) {
            GregorianCalendar dayCountofYear = new GregorianCalendar(1899, Calendar.DECEMBER, 31);
            dayCountofYear.add(Calendar.DAY_OF_YEAR, res);
            return new SimpleDateFormat("dd-MM-yyyy").format(dayCountofYear.getTime());
        }
    }
}


