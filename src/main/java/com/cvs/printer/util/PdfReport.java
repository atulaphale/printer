package com.cvs.printer.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PdfReport {

    public static ByteArrayInputStream inferenceReport(String htmlOutput) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(htmlOutput));
            document.close();

        } catch (DocumentException ex) {

            Logger.getLogger(PdfReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            Logger.getLogger(PdfReport.class.getName()).log(Level.SEVERE, null, e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}