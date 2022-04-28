package com.company;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class RolodexFrame extends javax.swing.JFrame {

    ArrayList<String> contacts = new ArrayList<>();
    File contactsFile = new File("/Users/shamiya/Desktop/Rolodex/src/com/company/contactlist.txt");
    FileWriter fileWriter = new FileWriter(contactsFile, true);
    ArrayList<String> update = new ArrayList<>();
    Scanner sc = new Scanner(new File("/Users/shamiya/Desktop/Rolodex/src/com/company/contactlist.txt"));
        public RolodexFrame() throws IOException {
            setPreferredSize(new Dimension(875,650));
            setVisible(true);
            initComponents();
        }

    private void initComponents() {

        ScrollPanel = new ScrollPane();
        list = new JList(contacts.toArray());
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        firstNameLabel = new Label();
        lastNameLabel = new Label();
        phoneNumberLabel = new Label();
        addressLabel = new Label();
        firstName = new TextField();
        lastName = new TextField();
        phoneNumber = new TextField();
        address = new TextField();
        saveButton = new Button();
        clearButton = new Button();
        boolean empty = true;

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if(s.indexOf(',') != -1){
                empty = false;
                contacts.add(s);
            }
        }


        Collections.sort(contacts);
        update.clear();

        for (int i = 0; i < contacts.size(); i++) {
            if(empty == false)
                update.add(contacts.get(i).split(",")[0] + ", " + contacts.get(i).split(",")[1]);
        }

        list.setListData(update.toArray());



            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Rolodex - Shamiya Lin");

            ScrollPanel.setBackground(new Color(255, 255, 255));
            ScrollPanel.add(list);

        list.addListSelectionListener(new javax.swing.event.ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                if(list.getSelectedIndex()>=0) {
                    String l = contacts.get(list.getSelectedIndex()).split(",")[0];
                    String f = contacts.get(list.getSelectedIndex()).split(",")[1];
                    String p = contacts.get(list.getSelectedIndex()).split(",")[2];
                    String a = contacts.get(list.getSelectedIndex()).split(",")[3];
                    firstName.setText(f);
                    lastName.setText(l);
                    phoneNumber.setText(p);
                    address.setText(a);
                    System.out.println(list.getSelectedIndex());

                    clearButton.setLabel("Delete");
                }

                else {
                    clearButton.setLabel("Clear");
                }


            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {

                contactsFile.delete();
                try {
                    System.out.println("File Created");
                    contactsFile.createNewFile();
                    fileWriter = new FileWriter(contactsFile, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                PrintWriter printWriter = new PrintWriter(fileWriter);
                for(int i = 0; i < contacts.size(); i++){
                    printWriter.println(contacts.get(i));
                }
                printWriter.close();
            }
        });



            firstNameLabel.setFont(new Font("Lucida Grande", 1, 18)); // NOI18N
            firstNameLabel.setText("First Name:");

            lastNameLabel.setFont(new Font("Lucida Grande", 1, 18)); // NOI18N
            lastNameLabel.setText("Last Name:");

            phoneNumberLabel.setFont(new Font("Lucida Grande", 1, 18)); // NOI18N
            phoneNumberLabel.setText("Phone Number:");

            addressLabel.setFont(new Font("Lucida Grande", 1, 18)); // NOI18N
            addressLabel.setText("Address:");

            saveButton.setLabel("Save");
            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    try {
                        saveButtonActionPerformed(evt);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            clearButton.setLabel("Clear");
            clearButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    clearButtonActionPerformed(evt);
                }
            });

            GroupLayout layout = new GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(23, 23, 23)
                                    .addComponent(ScrollPanel, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(42, 42, 42)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                            .addComponent(firstNameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(lastNameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(phoneNumberLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                    .addGap(26, 26, 26)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(firstName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lastName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(phoneNumber, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(address, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(104, 104, 104)
                                                    .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                                    .addGap(48, 48, 48)
                                                    .addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
                                    .addContainerGap(38, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                            .addComponent(ScrollPanel, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                    .addGap(54, 54, 54)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(firstName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(firstNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGap(22, 22, 22)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(lastName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(lastNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGap(22, 22, 22)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(phoneNumber, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(phoneNumberLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGap(19, 19, 19)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(addressLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addComponent(address, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                    .addGap(44, 44, 44)
                                                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                            .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(clearButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))))
                                    .addContainerGap(24, Short.MAX_VALUE))
            );

            pack();
        }






    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        if(list.getSelectedIndex()<0) {
        if (!firstName.getText().equals("") && !lastName.getText().equals("")) {
            String contactLine = "";

            if (phoneNumber.getText().equals("") == true && address.getText().equals("") == false)
                contactLine = lastName.getText() + "," + firstName.getText() + "," + "no number entered" + "," + address.getText();

            if (phoneNumber.getText().equals("") == true && address.getText().equals("") == true)
                contactLine = lastName.getText() + "," + firstName.getText() + "," + "no number entered" + "," + "no address entered";

            else if (phoneNumber.getText().equals("") == false && address.getText().equals("") == true)
                contactLine = lastName.getText() + "," + firstName.getText() + "," + phoneNumber.getText() + "," + "no address entered";

            else
                contactLine = lastName.getText() + "," + firstName.getText() + "," + phoneNumber.getText() + "," + address.getText();

            contacts.add(contactLine);
            Collections.sort(contacts);
            update.clear();

            for (int i = 0; i < contacts.size(); i++) {
                System.out.println(contacts.get(i));
                update.add(contacts.get(i).split(",")[0] + ", " + contacts.get(i).split(",")[1]);
            }

            list.setListData(update.toArray());
            firstName.setText("");
            lastName.setText("");
            phoneNumber.setText("");
            address.setText("");
        }

        else{
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(null, "Your contact must have First and Last Name", "Missing Field",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                    null, options, options[0]);
        }

            list.clearSelection();


    }

        else  if(list.getSelectedIndex()>=0) {

            if (!firstName.getText().equals("") && !lastName.getText().equals("")) {
                String contactLine = "";

                if (phoneNumber.getText().equals("") == true && address.getText().equals("") == false)
                    contactLine = lastName.getText() + "," + firstName.getText() + "," + "no number entered" + "," + address.getText();

                if (phoneNumber.getText().equals("") == true && address.getText().equals("") == true)
                    contactLine = lastName.getText() + "," + firstName.getText() + "," + "no number entered" + "," + "no address entered";

                else if (phoneNumber.getText().equals("") == false && address.getText().equals("") == true)
                    contactLine = lastName.getText() + "," + firstName.getText() + "," + phoneNumber.getText() + "," + "no address entered";

                else
                    contactLine = lastName.getText() + "," + firstName.getText() + "," + phoneNumber.getText() + "," + address.getText();

                contacts.set(list.getSelectedIndex(), contactLine);
                Collections.sort(contacts);
                update.clear();

                for (int i = 0; i < contacts.size(); i++) {
                    update.add(contacts.get(i).split(",")[0] + ", " + contacts.get(i).split(",")[1]);
                }

                list.setListData(update.toArray());
                firstName.setText("");
                lastName.setText("");
                phoneNumber.setText("");
                address.setText("");
                list.clearSelection();
            }
             else{
                Object[] options = {"OK"};
                JOptionPane.showOptionDialog(null, "Your contact must have First and Last Name", "Missing Field",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                        null, options, options[0]);
            }


        }


        repaint();
    }

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {

            if(clearButton.getLabel().equals("Clear")){
                firstName.setText("");
                lastName.setText("");
                phoneNumber.setText("");
                address.setText("");
            }

        if(clearButton.getLabel().equals("Delete")){

            firstName.setText("");
            lastName.setText("");
            phoneNumber.setText("");
            address.setText("");

            contacts.remove(list.getSelectedIndex());
            Collections.sort(contacts);
            update.clear();

            for (int i = 0; i < contacts.size(); i++) {
                update.add(contacts.get(i).split(",")[0] + ", " + contacts.get(i).split(",")[1]);
            }

            list.setListData(update.toArray());
            list.clearSelection();
        }
    }


        public void main(String args[]) {
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(RolodexFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(RolodexFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(RolodexFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(RolodexFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        new RolodexFrame().setVisible(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        private java.awt.ScrollPane ScrollPanel;
        private java.awt.TextField address;
        private java.awt.Label addressLabel;
        private java.awt.Button clearButton;
        private java.awt.TextField firstName;
        private java.awt.Label firstNameLabel;
        private java.awt.TextField lastName;
        private java.awt.Label lastNameLabel;
        private JList list;
        private java.awt.TextField phoneNumber;
        private java.awt.Label phoneNumberLabel;
        private java.awt.Button saveButton;
    }

