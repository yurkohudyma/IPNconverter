package ua.hudyma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class IPNController extends JFrame {
    static JTextField inputIPN, inputDate;
    static JLabel labelIPN, labelDate;
    static IPNhandler handler = new IPNhandler();
    static DateHandler dateHandler = new DateHandler();

    public IPNController(String s) {
        super(s);
        setLayout(new FlowLayout());
        inputIPN = new JTextField(6);
        labelIPN = new JLabel("5 перших літер ІПН");
        inputDate = new JTextField(6);
        labelDate = new JLabel("Дата yyyy-mm-DD");
        add(labelIPN);
        add(inputIPN);
        add(labelDate);
        add(inputDate);
        inputIPN.addActionListener(handler);
        inputDate.addActionListener(dateHandler);
    }

    static class DateHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String res;
            if (e.getSource() == inputDate) {
                JTextField field = (JTextField) e.getSource();
                res = field.getText();
                inputIPN.setText(convertDateToIPN(res)+"");
            }
        }

        private static long convertDateToIPN(String date) {
            LocalDate startDate = LocalDate.of(1899, 12, 31);
            LocalDate userDate = null;
            try {
                userDate = LocalDate.parse(date);
                return ChronoUnit.DAYS.between(startDate, userDate);
            } catch (Exception e) {
                inputDate.setText("Крива дата");
            }
            return -1L;
        }
    }

    static class IPNhandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String res, output;
            int number;
            if (e.getSource() == inputIPN) {
                JTextField field = (JTextField) e.getSource();
                res = field.getText();
                try {
                    number = Integer.parseInt(res);
                    output = (res + "").length() <= 5 ? convertIPNtoDate(number) : "Забагато";
                    inputDate.setText(output);
                } catch (NumberFormatException ex) {
                    inputDate.setText("Не число");
                }
            }
        }

        private String convertIPNtoDate(int daysAmount) {
            GregorianCalendar startDay = new GregorianCalendar(1899, Calendar.DECEMBER, 31);
            startDay.add(Calendar.DAY_OF_YEAR, daysAmount);
            return new SimpleDateFormat("yyyy-MM-dd").format(startDay.getTime());
        }
    }
}


