package com.agileengine.FindElement;


import java.io.File;


public class Runner {

    public static void main(String[] args) {
        File file = new File(args[0]);
        File file2 = new File(args[1]);
        String element = null;

        if (args.length > 2) {
            StringBuilder builder = new StringBuilder();
            builder.append("//*[@id=\"");
            builder.append(args[2]);
            builder.append("\"]");
            element = builder.toString();
        } else {
            element = "//*[@id=\"make-everything-ok-button\"]";
        }


        ElementFinder finder = new ElementFinder();
        try {
            System.out.println("Element path in first file: ");
            System.out.println(finder.findElement(file, element));
            System.out.println("Element path in second file: ");
            System.out.println(finder.findElement(file2, element));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
