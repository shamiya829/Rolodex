package com.company;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class Main {

    public static void main(String[] args) throws IOException {
        new RolodexFrame();

    }
}
