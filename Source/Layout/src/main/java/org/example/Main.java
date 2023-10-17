package org.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //frame

        JFrame frame = new JFrame();
        frame.setTitle("SmallComp Phonebook");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1400, 900);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        //menubar

        JMenuBar menuBar = new JMenuBar();
        JMenu languageMenu = new JMenu("Language");
        JRadioButtonMenuItem lan_eng_radio = new JRadioButtonMenuItem("Englisz");
        JRadioButtonMenuItem lan_de_radio = new JRadioButtonMenuItem("Deutch");
        languageMenu.add(lan_eng_radio);
        languageMenu.add(lan_de_radio);
        lan_eng_radio.setSelected(true);
        menuBar.add(languageMenu);
        frame.setJMenuBar(menuBar);
        ButtonGroup jmenu_radio_group = new ButtonGroup();
        jmenu_radio_group.add(lan_de_radio);
        jmenu_radio_group.add(lan_eng_radio);
        lan_eng_radio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lan_eng_radio.isSelected()){
                    System.out.println("Englisz is selected");
                }
            }
        });
        lan_de_radio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(lan_de_radio.isSelected()){
                    System.out.println("German is selected");
                }
            }
        });

        //panels
        Container pane=frame.getContentPane();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        //JPanel panel3 = new JPanel();
        //JPanel panel4 = new JPanel();
        //JPanel panel5 = new JPanel();
        panel1.setBackground(new Color(128,128,128));
        panel2.setBackground(Color.yellow);
        //panel3.setBackground(Color.pink);
        //panel4.setBackground(Color.orange);
        //panel5.setBackground(Color.cyan);
        panel1.setPreferredSize(new Dimension(1400, 200));
        panel2.setPreferredSize(new Dimension(1400, 700));
        //panel3.setPreferredSize(new Dimension(1400, 100));
        //panel4.setPreferredSize(new Dimension(100, 100));
        //panel5.setPreferredSize(new Dimension(100, 100));
        pane.setLayout(new BorderLayout());
        pane.add(panel1, BorderLayout.PAGE_START);
        pane.add(panel2, BorderLayout.CENTER);
        //pane.add(panel3, BorderLayout.PAGE_END);
        //frame.add(panel4, BorderLayout.SOUTH);
        //frame.add(panel5, BorderLayout.CENTER);
        //panel1.setLayout(new GridLayout(2, 0));
        FlowLayout flowLayout_panel1=new FlowLayout();
        panel1.setLayout(flowLayout_panel1);
        panel1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JButton add = new JButton("Add");
        panel1.add(add);
        add.setSize(100, 50);
        add.setText("Przycisk nr UNO");
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Dziala!!");
                panel1.setBackground(Color.black);

            }
        });

        JButton button2 = new JButton("Przycisk numero dos");
        button2.setSize(100, 50);

        panel1.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setBackground(Color.blue);
            }
        });
        String[] columnNames={"First Name",
                "Last Name",
                "Phone Number",
                "E-Mail"};
        Object[][] data={
                {"data","data2","data","Data",new JButton("EDIT"),new JButton("DELETE")},
                {"data","data","data","data",new JButton("EDIT"),new JButton("DELETE")}
        };




        JTable table=new JTable(data, columnNames);
        panel2.add(table);
        table.setVisible(true);
        table.setSize(1000,700);
        table.setRowHeight(70);
        for(int i=0;i<4;i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(150);
            //table.set
        }
    }
}