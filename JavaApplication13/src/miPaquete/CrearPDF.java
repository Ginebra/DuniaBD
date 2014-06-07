package miPaquete;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import static com.itextpdf.text.pdf.XfaXpathConstructor.XdpPackage.Pdf;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;

public class CrearPDF {
  public void imagePDF() {
    Document document = new Document();
    try {
        PdfWriter.getInstance(document,new FileOutputStream("C:/Users/neko/Desktop/factura.pdf"));
        document.open();
        Image image = Image.getInstance("C:/Users/neko/Desktop/factura.jpg");
        /*
        scaleAbsolute()
        scaleAbsoluteWidth()
        scaleAbsoluteHeight()
        scalePercentage()
        scaleToFit()
        */
        image.scaleAbsolute(525f, 300f);
        document.add(image);
        document.close();
        JOptionPane.showMessageDialog(null, "PDF CREADO..");
    } catch(Exception e){
      e.printStackTrace();
    }
  }
}