package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ReportCreate {
    private HashMap<String, Rows> csv_reports;

    public ReportCreate(HashMap<String, Rows> csv_reports) {
        this.csv_reports = csv_reports;
    }

    public HashMap<String, Rows> getCsv_reports() {
        return csv_reports;
    }

    public void setCsv_reports(HashMap<String, Rows> csv_reports) {
        this.csv_reports = csv_reports;
    }

    public void buildReport() throws IOException {

        FileWriter csvWriter = new FileWriter("report.csv", true);


            for(Rows rows : csv_reports.values()){
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);

                csvWriter.append(rows.getName());
                csvWriter.append(",");
                String idrow = rows.getId();
                csvWriter.append(idrow);
                csvWriter.append(",");
                String publ_from = formatter.format(rows.getPublished_from());
                csvWriter.append(publ_from);
                csvWriter.append(",");
                String publ_to = formatter.format(rows.getPublished_to());
                csvWriter.append(publ_to);
                csvWriter.append(",");
                String avgcnt = Double.toString(rows.getAvg_content_length());
                csvWriter.append(avgcnt);
                csvWriter.append("\n");

        }

        csvWriter.flush();
        csvWriter.close();


    }

}
