package com.cvs.printer.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PdfReport {

    public static ByteArrayInputStream inferenceReport(String htmlOutput) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(htmlOutput));
            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(PdfReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(PdfReport.class.getName()).log(Level.SEVERE, null, e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}