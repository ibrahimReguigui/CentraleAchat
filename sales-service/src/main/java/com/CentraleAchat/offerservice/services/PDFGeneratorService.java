package com.CentraleAchat.offerservice.services;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

@Service
public class PDFGeneratorService {
    public void export(HttpServletResponse response) throws  IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("pdf.pdf"));
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        fontTitle.setColor(Color.blue);

        Paragraph paragraph = new Paragraph("OFFER", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        fontParagraph.setColor(Color.MAGENTA);

        Paragraph paragraph2 = new Paragraph("offer description", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);

        //////////////
        // Lire le contenu du fichier HTML et le convertir en une chaîne de caractères
       /* FileReader reader = new FileReader("pdf.html");
        StringBuilder sb = new StringBuilder();
        int c = reader.read();
        while (c != -1) {
            sb.append((char) c);
            c = reader.read();
        }
        String html = sb.toString();
        // Créer un objet StringReader pour lire la chaîne HTML
        StringReader stringReader = new StringReader(html);

        // Convertir la chaîne HTML en PDF en utilisant l'objet HTMLWorker d'iText
        HTMLWorker htmlWorker = new HTMLWorker(document);
        htmlWorker.parse(stringReader);*/

        document.close();

    }
}
