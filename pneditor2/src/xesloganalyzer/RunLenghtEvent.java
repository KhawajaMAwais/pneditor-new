/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xesloganalyzer;

/**
 *
 * @author XY
 */
  
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.awt.Color;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
 
public class RunLenghtEvent {
 
    public XESanalyze analyze;
    public int counte=0;
    public int countr=0;
    public int maxe=0;
    public int maxr=0;
    public double maxse = 0;
    public double maxee = 0;
    public double maxsr = 0;
    public double maxer = 0;
    public double allevent=0;
    public double allresources=0;
    public PdfPTableEvent tableBackground;
    private File file = null;

    public RunLenghtEvent(XESanalyze analyze,File file) throws DocumentException, FileNotFoundException, IOException, SQLException {
        this.analyze = analyze;
        this.file = file;
        this.counte = this.analyze.getEvents().size();
        this.countr = this.analyze.getResources().size();
        this.maxe = analyze.getEvents().get(0).getCount();
        this.maxr = analyze.getResources().get(0).getCount();
        this.maxse = analyze.getEstartcount();
        this.maxee = analyze.getEendcount();
        this.maxsr = analyze.getRstart();
        this.maxer = analyze.getRend();
        this.allevent = analyze.getCountevents();
        this.allresources = analyze.getRcount();
        this.tableBackground = new TableBackground();
        createPdf();
    }
   
    class TableBackground implements PdfPTableEvent {
 
        public void tableLayout(PdfPTable table, float[][] width, float[] height,
                int headerRows, int rowStart, PdfContentByte[] canvas) {
            PdfContentByte background = canvas[PdfPTable.BASECANVAS];
            background.saveState();
            background.setRGBColorFill(214, 214, 214);
            background.roundRectangle(
                width[0][0] , height[height.length - 1]  ,
                width[0][1] - width[0][0] , height[0] - height[height.length - 1] , 0);
            background.fill();
            background.restoreState();
        }
 
    }
    
    class RunLength implements PdfPCellEvent {
 
        public int duration;
        public int max;
 
        public RunLength(int duration,int EorR) {
            this.duration = duration;
            this.max = EorR;
            
        }
 
        public void cellLayout(PdfPCell cell, Rectangle rect,
                PdfContentByte[] canvas) {
            PdfContentByte cb = canvas[PdfPTable.BACKGROUNDCANVAS];
            cb.saveState();
            
            cb.setRGBColorFill(0x8C, 0x8C, 0x8C);
            
            cb.rectangle(rect.getLeft(), rect.getBottom(),
                rect.getWidth() * duration / max, rect.getHeight());
            cb.fill();
            cb.restoreState();
        }
    }
    
     public void createPdf() throws DocumentException, FileNotFoundException, IOException, SQLException
    {
        Rectangle pageSize = new Rectangle(PageSize.A4);
        pageSize.setBackgroundColor(new BaseColor(184,184,184));
        Document document = new Document(pageSize);
        PdfWriter.getInstance(document, new FileOutputStream(file));      
        document.open();
        Phrase logsumary = new Phrase("Log Summary \n",  FontFactory.getFont(
        FontFactory.TIMES_BOLD, 24,
        Font.BOLD,new BaseColor(0, 0, 0)));
        document.add(logsumary);
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -8));
        Phrase numtrace = new Phrase("Total number of process instances: "+analyze.getCounttrace()+"\n",  FontFactory.getFont(
        FontFactory.TIMES, 14,
        Font.NORMAL,new BaseColor(0, 0, 0)));
        document.add(numtrace);
        Phrase numevents = new Phrase("Total number of events: "+analyze.getCountevents()+"\n",  FontFactory.getFont(
        FontFactory.TIMES, 14,
        Font.NORMAL,new BaseColor(0, 0, 0)));
        document.add(numevents);
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -8));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        Phrase eventname = new Phrase("Event Name \n",  FontFactory.getFont(
        FontFactory.TIMES_BOLD, 18,
        Font.BOLD,new BaseColor(0, 0, 0)));
        document.add(eventname);
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -8));
        document.add(new Phrase("Event classes defined by Event Name \n",  FontFactory.getFont(
        FontFactory.TIMES, 14,
        Font.NORMAL,new BaseColor(0, 0, 0))));
        document.add(Chunk.NEWLINE);
        document.add(new Phrase("All events \n",  FontFactory.getFont(
        FontFactory.TIMES_BOLD, 16,
        Font.BOLD,new BaseColor(0, 0, 0))));
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -6));
        document.add(Chunk.NEWLINE);
        document.add(getTableE(analyze.getEvents()));
        document.add(Chunk.NEWLINE);
        document.add(new Phrase("Start events \n",  FontFactory.getFont(
        FontFactory.TIMES_BOLD, 16,
        Font.BOLD,new BaseColor(0, 0, 0))));
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -6));
        document.add(Chunk.NEWLINE);
        document.add(getTableEs(analyze.getEstarts()));
        document.add(Chunk.NEWLINE);
        document.add(new Phrase("End events \n",  FontFactory.getFont(
        FontFactory.TIMES_BOLD, 16,
        Font.BOLD,new BaseColor(0, 0, 0))));
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -6));
        document.add(Chunk.NEWLINE);
        document.add(getTableEe(analyze.getEends()));
        document.newPage();
        Phrase resourcename = new Phrase("Resource \n",  FontFactory.getFont(
        FontFactory.TIMES_BOLD, 18,
        Font.BOLD,new BaseColor(0, 0, 0)));
        document.add(resourcename);
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -8));
        document.add(new Phrase("Event classes defined by Resource \n",  FontFactory.getFont(
        FontFactory.TIMES, 14,
        Font.NORMAL,new BaseColor(0, 0, 0))));
        document.add(Chunk.NEWLINE);
        document.add(new Phrase("All resources \n",  FontFactory.getFont(
        FontFactory.TIMES_BOLD, 16,
        Font.BOLD,new BaseColor(0, 0, 0))));
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -6));
        document.add(Chunk.NEWLINE);
        document.add(getTableR(analyze.getResources()));
        document.add(Chunk.NEWLINE);
        document.add(new Phrase("Start resources \n",  FontFactory.getFont(
        FontFactory.TIMES_BOLD, 16,
        Font.BOLD,new BaseColor(0, 0, 0))));
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -6));
        document.add(Chunk.NEWLINE);
        document.add(getTableRs(analyze.getRstarts()));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(new Phrase("End resources \n",  FontFactory.getFont(
        FontFactory.TIMES_BOLD, 16,
        Font.BOLD,new BaseColor(0, 0, 0))));
        document.add(new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -6));
        document.add(Chunk.NEWLINE);
        document.add(getTableRe(analyze.getRends()));
        document.close();
    
    }
    
        public PdfPTable getTableE(ArrayList<XESEvent> events)
        throws SQLException, DocumentException, IOException {
        PdfPTable table = new PdfPTable(new float[] { 5, 5, 5 });
        table.setTableEvent(tableBackground);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setColspan(5);
        table.getDefaultCell().setBackgroundColor(new BaseColor(214, 214, 214));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell("Total number of classes: "+counte);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
        table.addCell("Class");
        table.addCell("Occurrences (absolute)");
        table.addCell("Occurrences (relative)");    
        table.getDefaultCell().setBackgroundColor(null);
        PdfPCell runLength;
        DecimalFormat twoDigit = new DecimalFormat("#,###0.000");
        double relative = 0;
        for (XESEvent event : events) {
            table.addCell(event.getName());
            runLength = new PdfPCell(table.getDefaultCell());
            runLength.setPhrase(new Phrase(String.valueOf(event.getCount())));
            runLength.setCellEvent(new RunLength(event.getCount(),maxe));            
            table.addCell(runLength);
            relative=(event.getCount()/allevent)*100;
            table.addCell(twoDigit.format(relative)+"%");         
        }
        return table;
    }
        
    public PdfPTable getTableEs(ArrayList<XESEvent> events)
        throws SQLException, DocumentException, IOException {
        PdfPTable table = new PdfPTable(new float[] { 5, 5, 5 });
        table.setTableEvent(tableBackground);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setColspan(5);
        table.getDefaultCell().setBackgroundColor(new BaseColor(214, 214, 214));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell("Total number of classes: "+analyze.getEstarts().size());
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
        table.addCell("Class");
        table.addCell("Occurrences (absolute)");
        table.addCell("Occurrences (relative)");    
        table.getDefaultCell().setBackgroundColor(null);
        PdfPCell runLength;
        DecimalFormat twoDigit = new DecimalFormat("#,###0.000");
        double relative = 0;
        for (XESEvent event : events) {
            table.addCell(event.getName());
            runLength = new PdfPCell(table.getDefaultCell());
            runLength.setPhrase(new Phrase(String.valueOf(event.getCounts())));
            runLength.setCellEvent(new RunLength(event.getCounts(),analyze.getEstartcount()));            
            table.addCell(runLength);
            relative=(event.getCounts()/maxse)*100;
            table.addCell(twoDigit.format(relative)+"%");         
        }
        return table;
    }
    
    public PdfPTable getTableEe(ArrayList<XESEvent> events)
        throws SQLException, DocumentException, IOException {
        PdfPTable table = new PdfPTable(new float[] { 5, 5, 5 });
        table.setTableEvent(tableBackground);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.setWidthPercentage(100f);
        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setColspan(5);
        table.getDefaultCell().setBackgroundColor(new BaseColor(214, 214, 214));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell("Total number of classes: "+analyze.getEends().size());
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
        table.addCell("Class");
        table.addCell("Occurrences (absolute)");
        table.addCell("Occurrences (relative)");    
        table.getDefaultCell().setBackgroundColor(null);
        PdfPCell runLength;
        DecimalFormat twoDigit = new DecimalFormat("#,###0.000");
        double relative = 0;
        for (XESEvent event : events) {
            table.addCell(event.getName());
            runLength = new PdfPCell(table.getDefaultCell());
            runLength.setPhrase(new Phrase(String.valueOf(event.getCounte())));
            runLength.setCellEvent(new RunLength(event.getCounte(),analyze.getEendcount()));            
            table.addCell(runLength);
            relative=(event.getCounte()/maxee)*100;
            table.addCell(twoDigit.format(relative)+"%");         
        }
        return table;
    }
        
        
     public PdfPTable getTableR(ArrayList<XESResource> resources)
        throws SQLException, DocumentException, IOException {
        PdfPTable table = new PdfPTable(new float[] { 5, 5, 5 });
        table.setWidthPercentage(100f);
        table.setTableEvent(tableBackground);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setColspan(5);
        table.getDefaultCell().setBackgroundColor(new BaseColor(214, 214, 214));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell("Total number of classes: "+countr);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
        table.addCell("Class");
        table.addCell("Occurrences (absolute)");
        table.addCell("Occurrences (relative)");
        table.getDefaultCell().setBackgroundColor(null);
        PdfPCell runLength;
        DecimalFormat twoDigit = new DecimalFormat("#,###0.000");
        double relative = 0;
        double count = 0;
        for (XESResource resource : resources) {
            table.addCell(resource.getName());
            runLength = new PdfPCell(table.getDefaultCell());
            runLength.setPhrase(new Phrase(String.valueOf(resource.getCount())));
            runLength.setCellEvent(new RunLength(resource.getCount(),maxr));            
            table.addCell(runLength);
            count = resource.getCount();
            relative=(count/allresources)*100;
            table.addCell(twoDigit.format(relative)+"%");
            
        }
        return table;
    }
     
    public PdfPTable getTableRs(ArrayList<XESResource> resources)
        throws SQLException, DocumentException, IOException {
        PdfPTable table = new PdfPTable(new float[] { 5, 5, 5 });
        table.setWidthPercentage(100f);
        table.setTableEvent(tableBackground);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setColspan(5);
        table.getDefaultCell().setBackgroundColor(new BaseColor(214, 214, 214));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell("Total number of classes: "+analyze.getRstarts().size());
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
        table.addCell("Class");
        table.addCell("Occurrences (absolute)");
        table.addCell("Occurrences (relative)");
        table.getDefaultCell().setBackgroundColor(null);
        PdfPCell runLength;
        DecimalFormat twoDigit = new DecimalFormat("#,###0.000");
        double relative = 0;
        double count = 0;
        for (XESResource resource : resources) {
            table.addCell(resource.getName());
            runLength = new PdfPCell(table.getDefaultCell());
            runLength.setPhrase(new Phrase(String.valueOf(resource.getCounts())));
            runLength.setCellEvent(new RunLength(resource.getCounts(),maxr));            
            table.addCell(runLength);
            count = resource.getCounts();
            relative=(count/maxsr)*100;
            table.addCell(twoDigit.format(relative)+"%");
            
        }
        return table;
    } 
     
    public PdfPTable getTableRe(ArrayList<XESResource> resources)
        throws SQLException, DocumentException, IOException {
        PdfPTable table = new PdfPTable(new float[] { 5, 5, 5 });
        table.setWidthPercentage(100f);
        table.setTableEvent(tableBackground);
        table.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setUseAscender(true);
        table.getDefaultCell().setUseDescender(true);
        table.getDefaultCell().setColspan(5);
        table.getDefaultCell().setBackgroundColor(new BaseColor(214, 214, 214));
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell("Total number of classes: "+analyze.getRends().size());
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setBackgroundColor(new BaseColor(255, 255, 255));
        table.addCell("Class");
        table.addCell("Occurrences (absolute)");
        table.addCell("Occurrences (relative)");
        table.getDefaultCell().setBackgroundColor(null);
         table.getDefaultCell().setBackgroundColor(new BaseColor(214, 214, 214));
        PdfPCell runLength;
        DecimalFormat twoDigit = new DecimalFormat("#,###0.000");
        double relative = 0;
        double count = 0;
        for (XESResource resource : resources) {
            table.addCell(resource.getName());
            runLength = new PdfPCell(table.getDefaultCell());
            runLength.setPhrase(new Phrase(String.valueOf(resource.getCounte())));
            runLength.setCellEvent(new RunLength(resource.getCounte(),maxr));            
            table.addCell(runLength);
            count = resource.getCounte();
            relative=(count/maxse)*100;
            table.addCell(twoDigit.format(relative)+"%");
            
        }
        return table;
    }   
    
}