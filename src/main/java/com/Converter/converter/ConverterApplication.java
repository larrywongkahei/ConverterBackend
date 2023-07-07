package com.Converter.converter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.imageio.ImageIO;
import javax.print.Doc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@SpringBootApplication
public class ConverterApplication {

    public static void main(String[] args) {
        try {
            File myObj = new File("PngToPdf.pdf");
            Document document = new Document();
            OutputStream outputStream = new FileOutputStream(myObj);
            InputStream wordFileInputStream = new FileInputStream("WordFile.docx");
            XWPFDocument wordDocment = new XWPFDocument(wordFileInputStream);
            XWPFWordExtractor extractor = new XWPFWordExtractor(wordDocment);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
            document.open();
            XWPFRun run = wordDocment.getParagraphs().get(1).getRuns().get(0);
            Paragraph textToAdd = new Paragraph(run.text());
            System.out.println(run.text());


            document.add(textToAdd);

            document.close();
            outputStream.close();
            wordFileInputStream.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        System.out.println("PNG created");
        SpringApplication.run(ConverterApplication.class, args);


    }
}
