package com.company;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class CSV_Annalyzer implements Runnable{
    private String csv;
    private String filePath;


    public CSV_Annalyzer(String fileName){
        this.filePath = fileName;
    }

    @Override
    public void run() {
        HashMap<String, Rows> articles = new HashMap<>();
        BufferedReader csvReader = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {
            csvReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String row = null;
        int i = 0;
        while (true) {

            try {
                if (!((row = csvReader.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(i==0){
                i = 1;
                continue;
            }
            String[] data = row.split(",");

            Date date_cur = null;

            try {
                date_cur = formatter.parse(data[5].split("T")[0]);
            } catch (ParseException e) {

                System.out.println(data[5].split("T")[0]);
            } catch (ArrayIndexOutOfBoundsException e1){


            }

            if (articles.containsKey(data[3])){

                Rows old_row = articles.get(data[3]);

                if(old_row.getPublished_from().after(date_cur)){
                    old_row.setPublished_from(date_cur);
                }

                if(old_row.getPublished_to().before(date_cur)){
                    old_row.setPublished_to(date_cur);
                }

                old_row.setTotal_length(old_row.getTotal_length()+data[4].length());
                old_row.setTotal_publishes(old_row.getTotal_publishes()+1);
                double totl_len_curr = old_row.getTotal_length();
                old_row.setAvg_content_length(totl_len_curr/old_row.getTotal_publishes());

                articles.remove(data[3]);
                articles.put(data[3], old_row);

            }

            else{

                try{
                    Rows newrow = new Rows(data[3], data[1], date_cur, date_cur, data[4].length(), data[4].length(), 1);

                    articles.put(data[3], newrow);
                }catch (ArrayIndexOutOfBoundsException e){

                }


            }


        }
        try {
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ReportCreate reportCreate = new ReportCreate(articles);
        try {
            reportCreate.buildReport();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
