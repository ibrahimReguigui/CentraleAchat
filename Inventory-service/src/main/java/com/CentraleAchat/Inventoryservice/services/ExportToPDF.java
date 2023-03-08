package com.CentraleAchat.Inventoryservice.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.CentraleAchat.Inventoryservice.entities.Product;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class ExportToPDF {
	 private List<Product> listP;
     
	    public ExportToPDF(List<Product> listP) {
	        this.listP = listP;
	    }
	 
	    private void writeTableHeader(PdfPTable table) {
	        PdfPCell cell = new PdfPCell();
	        
	       cell.setPadding(7);
	         
	        Font font = FontFactory.getFont(FontFactory.HELVETICA);
	        


	        cell.setPhrase(new Phrase("Name", font));
	        table.addCell(cell);
	         
	        cell.setPhrase(new Phrase("Description", font));
	        table.addCell(cell);
			cell.setPhrase(new Phrase("Catégorie", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("UnitPriceHT", font));
			table.addCell(cell);
	        
	        cell.setPhrase(new Phrase("Quantity", font));
	        table.addCell(cell);

	        

	        


			cell.setPhrase(new Phrase("Unit", font));
			table.addCell(cell);
			cell.setPhrase(new Phrase("Discount", font));
			table.addCell(cell);
	        
	        
	         
	               
	    }
	     
	    private void writeTableData(PdfPTable table) {
	        for (Product produit : listP) {


	        	table.addCell(produit.getName());
	            table.addCell(produit.getDescription());
				table.addCell(String.valueOf(produit.getCategorie().getNameCategorie()));
				table.addCell(String.valueOf(produit.getUnitPriceHT()));
				table.addCell(String.valueOf(produit.getQuantity()));
				table.addCell(String.valueOf(produit.getUnit().getName()));
	            table.addCell(String.valueOf(produit.getDiscount()));




	            
	            
	        }
	    }
	     
	    public void export(HttpServletResponse response) throws DocumentException, IOException {
	        Document document = new Document(PageSize.A4);
	        PdfWriter.getInstance(document, response.getOutputStream());
	         
	        document.open();
	        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	        font.setSize(18);
	        
	         
	        Paragraph p = new Paragraph("List product with low Quantity ", font);
	        p.setAlignment(Paragraph.ALIGN_CENTER);
	        
	         
	        document.add(p);

			PdfPTable table = new PdfPTable(7);
	        table.setWidthPercentage(100f);
	        table.setWidths(new float[] {3f, 3f, 2f, 2f, 2f, 2f, 2f});
	        table.setSpacingBefore(10);
	         
	        writeTableHeader(table);
	        writeTableData(table);
	         
	        document.add(table);
	         
	        document.close();
	         
	    }

}
