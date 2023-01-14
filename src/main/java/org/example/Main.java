package org.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import org.apache.pdfbox.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;


public class Main {
    public static void main(String[] args) {
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        int e=0;
        int other=0;
        String years = "";
        String start = "https://www.math.purdue.edu/php-scripts/courses/oldexams/serve_file.php?file=Ans-26100";
        class hold {
            String[] years;
            String url;
            public hold(String[] years, String url) {
                this.years = years;
                this.url = url;
            }
        }
        //Fall Sem - Midterm one
        String[] FE1 = {"2005", "2007", "2008", "2016", "2017", "2018", "2019"};
        hold HFE1 = new hold(FE1, "E1-F");
        //Midterm two
        String[] FE2 = {"2005", "2007", "2011", "2014", "2015", "2016", "2017", "2018", "2019"};
        hold HFE2 = new hold(FE2, "E2-F");
        //final
        String[] FEF = {"2001", "2002", "2005", "2007", "2008", "2011", "2013", "2014", "2015", "2017", "2018",
                "2019"};
        hold HFEF = new hold(FEF, "FE-F");

        //Spring Sem - Midterm one
        String[] SE1 = {"2006", "2009", "2014", "2016", "2017", "2018", "2019", "2020", "2022"};
        hold HSE1 = new hold(SE1, "E1-S");
        //Midterm two
        String[] SE2 = {"2006", "2008", "2009", "2014", "2015", "2016", "2017", "2018", "2019", "2022"};
        hold HSE2 = new hold(SE2, "E2-S");
        //final
        String[] SEF = {"2002", "2003", "2006", /*"2008",*/ "2015", "2016", "2017", "2018", "2019"};
        hold HSEF = new hold(SEF, "FE-S");

        hold[] all = {HFE1, HFE2, HFEF, HSE1, HSE2, HSEF};

        for (hold h : all) {
            for (String s : h.years) {
                try {
                    //System.out.println("opening connection");
                    URL url = new URL(start + h.url + s + ".pdf");
                    InputStream in = url.openStream();
                    FileOutputStream fos = new FileOutputStream(new File("yourFile.pdf"));

                    //System.out.println("reading from resource and writing to file...");
                    int length = -1;
                    byte[] buffer = new byte[1024];// buffer for portion of data from connection
                    while ((length = in.read(buffer)) > -1) {
                        fos.write(buffer, 0, length);
                    }
                    fos.close();
                    in.close();
                    //System.out.println("File downloaded");


                } catch (Exception ex) {
                    ex.printStackTrace();
                    break;
                }
                String text = "";
                try {
                    File file = new File("yourFile.pdf");
                    PDDocument document = Loader.loadPDF(file);
                    PDFTextStripper pdfTextStripper = new PDFTextStripper();
                    text = pdfTextStripper.getText(document);
                    if (text.isBlank()) {
                        //System.out.println(s);
                    } else {
                        //System.out.println(text);
                    }
                    document.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    break;
                }

                //parse text
                if (!text.isBlank()) {
                    Scanner scan = new Scanner(text);
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine();
                        //parse line here
                        if (!line.isBlank()) {
                            if (Character.isDigit(line.charAt(0))) {
                                if (line.endsWith("A ") || line.endsWith("A")) {
                                    a++;
                                } else if (line.endsWith("B ") || line.endsWith("B")) {
                                    b++;
                                } else if (line.endsWith("C ") || line.endsWith("C")) {
                                    c++;
                                } else if (line.endsWith("D ") || line.endsWith("D")) {
                                    d++;
                                } else if (line.endsWith("E ") || line.endsWith("E")) {
                                    e++;
                                } else {
                                    other++;
                                }
                            } else if (Character.isDigit(line.charAt(1)) && line.charAt(0) == ' ') {
                                if (line.endsWith("A ") || line.endsWith("A")) {
                                    a++;
                                } else if (line.endsWith("B ") || line.endsWith("B")) {
                                    b++;
                                } else if (line.endsWith("C ") || line.endsWith("C")) {
                                    c++;
                                } else if (line.endsWith("D ") || line.endsWith("D")) {
                                    d++;
                                } else if (line.endsWith("E ") || line.endsWith("E")) {
                                    e++;
                                } else {
                                    other++;
                                }
                            }
                        }
                    }
                } else {
                    years += s + " " + h.url + ",";
                }
            }
        }
        System.out.println(years);
        System.out.printf("%d A's, %d B's, %d C's, %d D's, %d E's, %d Other", a, b, c, d, e, other);
    }
}