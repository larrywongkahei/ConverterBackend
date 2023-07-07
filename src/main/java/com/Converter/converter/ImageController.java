package com.Converter.converter;

import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("image")
public class ImageController {

    public byte[] WordToPdf(MultipartFile file){
        try {
            Path pathOfFile = Paths.get("PngToPdf.pdf");
            Files.deleteIfExists(pathOfFile);
            File myObj = new File("PngToPdf.pdf");
            Document document = new Document();
            OutputStream outputStream = new FileOutputStream(myObj);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
            document.open();
            Image imageToAdd = Image.getInstance(file.getBytes());
            float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
            float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
            imageToAdd.scaleToFit(documentWidth, documentHeight);
            document.add(imageToAdd);
            document.close();
            outputStream.flush();
            outputStream.close();
            return Files.readAllBytes(myObj.toPath());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public byte[] ImageToPdf(MultipartFile file){
        try {
                Path pathOfFile = Paths.get("PngToPdf.pdf");
                Files.deleteIfExists(pathOfFile);
                File myObj = new File("PngToPdf.pdf");
                Document document = new Document();
                OutputStream outputStream = new FileOutputStream(myObj);
                PdfWriter pdfWriter = PdfWriter.getInstance(document, outputStream);
                document.open();
                Image imageToAdd = Image.getInstance(file.getBytes());
                float documentWidth = document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin();
                float documentHeight = document.getPageSize().getHeight() - document.topMargin() - document.bottomMargin();
                imageToAdd.scaleToFit(documentWidth, documentHeight);
                document.add(imageToAdd);
                document.close();
                outputStream.flush();
                outputStream.close();
                return Files.readAllBytes(myObj.toPath());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] printImage(@RequestBody MultipartFile file) throws IOException {
        byte[] fileinput = file.getBytes();
        return fileinput;
    }

    @PostMapping(value ="/{type}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] printData(@PathVariable String type, @RequestBody MultipartFile file) throws IOException {
        if(type.equals("image")){
            return ImageToPdf(file);
        }

        System.out.println("no");
        return null;
    }
}
