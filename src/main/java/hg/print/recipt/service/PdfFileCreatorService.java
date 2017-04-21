/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hg.print.recipt.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;

/**
 *
 * @author terseer
 */
@Service
public class PdfFileCreatorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfFileCreatorService.class);
    private static final Gson GSON = new Gson();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static <T> T fromJson(String data, Class<T> tClass) {
        return GSON.fromJson(data, tClass);
    }

    static final String AB = "0123456789876543210";
    static Random rnd = new Random();
    String dt = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    DecimalFormat df = new DecimalFormat("#.##");

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    public static String formatTimestamp(Timestamp timestamp) {
        String time = sdf.format(timestamp);
        return time;//dsdsds
    }
    
    
    
    
    
            //cell1.setColumn();
            //   document.add(table);
//Document document = new Document();
//     try
//    {
//        File file = new File("/home/terseer/printReceipt.pdf");
//            OutputStream out = new FileOutputStream(file);
//            PdfWriter writer = PdfWriter.getInstance(document, out);
//
//            document.open();
// 
//        PdfPTable table = new PdfPTable(3); // 3 columns.
//        table.setWidthPercentage(100); //Width 100%
//        table.setSpacingBefore(10f); //Space before table
//        table.setSpacingAfter(10f); //Space after table
// 
//        //Set Column widths
//        float[] columnWidths = {1f, 1f, 1f};
//        table.setWidths(columnWidths);
// 
//        PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
//        cell1.setBorderColor(BaseColor.BLUE);
//        cell1.setPaddingLeft(10);
//        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
// 
//        PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
//        cell2.setBorderColor(BaseColor.GREEN);
//        cell2.setPaddingLeft(10);
//        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
// 
//        PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
//        cell3.setBorderColor(BaseColor.RED);
//        cell3.setPaddingLeft(10);
//        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
//        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
// 
//        //To avoid having the cell border and the content overlap, if you are having thick cell borders
//        //cell1.setUserBorderPadding(true);
//        //cell2.setUserBorderPadding(true);
//        //cell3.setUserBorderPadding(true);
// 
//        table.addCell(cell1);
//        table.addCell(cell2);
//        table.addCell(cell3);
// 
//        try {
//            document.add(table);
//        } catch (DocumentException ex) {
//            java.util.logging.Logger.getLogger(PdfFileCreatorService.class.getName()).log(Level.SEVERE, null, ex);
//        }
// 
//        document.close();
//        writer.close();
//    } catch (Exception e)
//    {
//        e.printStackTrace();
//    }
//    
//    }
    

//    public void writePdfToResponse(PdfFileRequest fileRequest, HttpServletResponse response) {
//        // get pdf file name
//        String pdfFileName = "";
//        requireNotNull(pdfFileName, "The file name of the created PDF must be set");
//        requireNotEmpty(pdfFileName, "File name of the created PDF cannot be empty");
//
//        // and source url
//        String sourceHtmlUrl = "";
//        requireNotNull(sourceHtmlUrl, "Source HTML url must be set");
//        requireNotEmpty(sourceHtmlUrl, "Source HTML url cannot be empty");
//
//        List<String> pdfCommand = Arrays.asList("wkhtmltopdf", sourceHtmlUrl, "-");
//
//        ProcessBuilder pb = new ProcessBuilder(pdfCommand);
//
//        Process pdfProcess;
//
//        try {
//
//            pdfProcess = pb.start();
//
//            try (InputStream in = pdfProcess.getInputStream()) {
//
//                writeCreatedPdfFileToResponse(in, response);
//
//                waitForProcessBeforeContinueCurrentThread(pdfProcess);
//
//                requireSuccessfulExitStatus(pdfProcess);
//
//                File file = new File("/home/terseer/myfile.ext");
//                OutputStream out = new FileOutputStream(file);
//// Write your data
//                out.close();
//
//                setResponseHeaders(response, fileRequest);
//
//            } catch (Exception ex) {
//                writeErrorMessageToLog(ex, pdfProcess);
//                throw new RuntimeException("PDF generation failed");
//            } finally {
//                pdfProcess.destroy();
//            }
//
//        } catch (IOException ex) {
//            throw new RuntimeException("PDF generation failed");
//
//        }
//
//    }
//
//    private void requireNotNull(String value, String message) {
//        if (value == null) {
//            throw new IllegalArgumentException(message);
//        }
//    }
//
//    private void requireNotEmpty(String value, String message) {
//        if (value.isEmpty()) {
//            throw new IllegalArgumentException(message);
//        }
//
//    }
//
//    private void writeCreatedPdfFileToResponse(InputStream in, HttpServletResponse response) throws IOException {
//        OutputStream out = response.getOutputStream();
//        IOUtils.copy(in, out);
//
//        //out.flush();
//        File file = new File("/home/terseer/myfile.pdf");
//        out = new FileOutputStream(file);
//        // Write your data
//        out.close();
//    }
//
//    private void waitForProcessBeforeContinueCurrentThread(Process process) {
//        try {
//            process.waitFor(5, TimeUnit.SECONDS);
//        } catch (InterruptedException ex) {
//            Thread.currentThread().interrupt();
//        }
//    }
//
//    private void requireSuccessfulExitStatus(Process process) {
//        if (process.exitValue() != 0) {
//            throw new RuntimeException("PDF generation failed");
//        }
//    }
//
//    private void setResponseHeaders(HttpServletResponse response, PdfFileRequest fileRequest) {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=\"" + "name" + "\"");
//    }
//
//    private void writeErrorMessageToLog(Exception ex, Process pdfProcess) throws IOException {
//        LOGGER.error("Could not create PDF because an exception was thrown: ", ex);
//        LOGGER.error("The exit value of PDF process is: {}", pdfProcess.exitValue());
//
//        String errorMessage = getErrorMessageFromProcess(pdfProcess);
//        LOGGER.error("PDF process ended with error message: {}", errorMessage);
//    }
//
//    private String getErrorMessageFromProcess(Process pdfProcess) {
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(pdfProcess.getErrorStream()));
//            StringWriter writer = new StringWriter();
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                writer.append(line);
//            }
//
//            return writer.toString();
//        } catch (IOException ex) {
//            LOGGER.error("Could not extract error message from process because an exception was thrown", ex);
//            return "";
//        }
//    }

}
