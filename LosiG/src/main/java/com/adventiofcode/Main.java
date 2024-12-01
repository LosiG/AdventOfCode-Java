package com.adventiofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

public class Main {
    static final String INPUT_PATH = "input.txt";

    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();

        try {
            File inputFile = new File(INPUT_PATH);
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // exercise1(data);
        exercise2(data);

    }

    private static void exercise1(ArrayList<String> data) {
        Integer sum = 0;

        Integer[] location1 = new Integer[data.size()];
        Integer[] location2 = new Integer[data.size()];

        for (int i = 0; i < data.size(); i++) {
            String[] splittedData = data.get(i).split("   ");
            location1[i] = Integer.decode(splittedData[0]);
            location2[i] = Integer.decode(splittedData[1]);
        }

        Arrays.sort(location1);
        Arrays.sort(location2);

        for (int i = 0; i < location1.length; i++) {
            if (location1[i].intValue() > location2[i].intValue()) {
                sum += location1[i] - location2[i];
            } else {
                sum += location2[i] - location1[i];
            }
        }

        System.out.println(sum);
    }

    private static void exercise2(ArrayList<String> data) {
        Integer sum = 0;

        Integer[] location1 = new Integer[data.size()];
        Integer[] location2 = new Integer[data.size()];

        Integer locations = data.size();

        for (int i = 0; i < locations; i++) {
            String[] splittedData = data.get(i).split("   ");
            location1[i] = Integer.decode(splittedData[0]);
            location2[i] = Integer.decode(splittedData[1]);
        }

        Map<Integer, Integer> locationMap = new HashMap<>();

        for (int i = 0; i < locations; i++) {
            if (!locationMap.containsKey(location1[i])) {
                Integer appears = 0;
                for (int j = 0; j < locations; j++) {
                    if (location1[i].intValue() == location2[j].intValue()) {
                        appears++;
                    }
                }
                locationMap.put(location1[i], appears);
            }
        }

        Integer result = 0;
        for (int i = 0; i < locations; i++) {
            result += location1[i] * locationMap.get(location1[i]);
        }

        System.out.println(result);
    }
}