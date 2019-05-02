package com.test.report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IncomingReport {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = IncomingReport.class.getClassLoader().getResourceAsStream("data.csv");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line = br.readLine();
            while (line != null) {

                String[] attributes = line.split(",");
                System.out.println(attributes[0]);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
