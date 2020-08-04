package com.Employee.EmployeeFront.ServiceImplementation;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee.EmployeeFront.Dao.GazorderRepository;
import com.Employee.EmployeeFront.Domain.Gazorder;
import com.Employee.EmployeeFront.Service.GazorderService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


@Service
public class GazorderImplementation implements GazorderService {

	@Autowired
	private GazorderRepository gazorderrepo;
	
	@Override
	public void creategazorder(Gazorder order) {
		gazorderrepo.save(order);
		
	}

	@Override
	public void updategazorder(Gazorder order) {
		gazorderrepo.save(order);
		
	}
	@Override
	public void delete(Gazorder order) {
		
		gazorderrepo.delete(order);
		
	}

	@Override
	public Optional<Gazorder> findById(int id) {
		
		return gazorderrepo.findById(id);
	}

	@Override
	public Optional<Gazorder> findByUuid(String uuid) {
		
		return gazorderrepo.findByUuid(uuid);
	}

	@Override
	public List<Gazorder> findAll(Class c) {
	
		return gazorderrepo.findAll();
	}

	/* (non-Javadoc)
	 * @see com.Employee.EmployeeFront.Service.GazorderService#OrderGazDetailsPDF(com.Employee.EmployeeFront.Domain.Gazorder)
	 */
	
	@Override
	public byte[] OrderGazDetailsPDF(Gazorder app) {
    
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		
		try {
			   Document document = new Document(PageSize.A4);
		        PdfWriter writer = PdfWriter.getInstance(document, b);
		        document.open();

		        // ----------------Table Header "Title"----------------
		        // font
		        
		       
		        
		        Font font = new Font(FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLACK);
		        Font font2 = new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);
		        document.add(new Paragraph(" "));
		        Chunk title = new Chunk("Gaz Order Request Summary", font);
		        Phrase phrase = new Phrase();
		        phrase.add(title);
		        Paragraph para = new Paragraph();
		        para.add(phrase);     
		        para.setAlignment(Element.ALIGN_CENTER);
		        document.add(para);
		        document.add(new Paragraph(" "));

		      /*  Image image = Image.getInstance("src\\main\\resources\\static\\image\\logobboxx.jpg");
		        image.scaleToFit(120f, 190f);
		        image.setAbsolutePosition(5, 730);
		        document.add(image);
*/

		        //Writing Printing Date Created
		        
		        Paragraph para2 = new Paragraph();
		        para2.setAlignment(Element.ALIGN_RIGHT);
		        Phrase datePhrase = new Phrase();
		       /* datePhrase.add(new Chunk("       Date: " ,new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK)));
		        datePhrase.add(new Chunk(new SimpleDateFormat("dd-MM-yyyy").format(app.getOrderdate()) ,new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK)));
		        para2.add(datePhrase);*/
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		        datePhrase.add(new Chunk("       Date: " ,new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK)));
		        datePhrase.add(new Chunk(new SimpleDateFormat("dd-MM-yyyy").format(new Date())+"" ,new Font(FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK)));
		        para2.add(datePhrase);
		        document.add(para2);
		         
		       
		        

		        //Writing Printing Date Created
		        Paragraph para3 = new Paragraph();
		        para3.setAlignment(Element.ALIGN_LEFT);
		        Phrase datePhrase1 = new Phrase();
		       
		        para3.add(datePhrase1);
		        document.add(para3);



		        document.add(new Phrase("\n"));

		        // create 4 column table
		        PdfPTable table0 = new PdfPTable(3);
		        PdfPTable tableSeller = new PdfPTable(1);
		        PdfPTable tableByer = new PdfPTable(1);
		        PdfPTable table = new PdfPTable(2);
		        PdfPTable table1 = new PdfPTable(2);

		        table.setWidthPercentage(100);
		        table1.setWidthPercentage(100);
		        table0.setWidthPercentage(92);

		        tableSeller.setWidthPercentage(15);
		        tableByer.setWidthPercentage(15);



		        // Defiles the relative width of the columns
		        float[] columnWidths = new float[] { 15f, 20f, 15f };
		        table0.setWidths(columnWidths);
		        tableByer.setWidths(new float[] {  20f });
		        tableSeller.setWidths(new float[] {  20f });


		        // Writting Buyer Basic Informationion

		        PdfPCell tTitle = new PdfPCell(new Phrase("BRANCH", new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK)));
		        tTitle.setBorderColor(BaseColor.WHITE);
//				tTitle.setColspan(1);
		        tableByer.addCell(tTitle);
		        Phrase bid = new Phrase();
		        Phrase bn = new Phrase();
		        Phrase ba= new Phrase();
		        Phrase bp= new Phrase();
		        Phrase bd=new Phrase();
		        Phrase bs=new Phrase();
		        bid.add(new Chunk("branchname: ",new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));
		        bid.add(new Chunk(app.getGaz().getBranchuser().getBranch().getBranchname(),new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		        Paragraph pId = new Paragraph();
		        pId.add(bid);
		        tableByer.addCell(praCell(pId)).setBorderColor(BaseColor.WHITE);
		        bn.add(new Chunk("PhoneNumber: ",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
		        bn.add(new Chunk(app.getGaz().getBranchuser().getBranch().getPhonenumber(),new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		        Paragraph pname = new Paragraph();
		        pname.add(bn);
		       /* tableByer.addCell(praCell(pname)).setBorderColor(BaseColor.WHITE);
		        ba.add(new Chunk("Address: ",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
		        ba.add(new Chunk(appointment.getSolarenergy().getBranch().getProvince().getName(),new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		     */
		        
		        bp.add(new Chunk("E-mail: ",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
		        bp.add(new Chunk(app.getGaz().getBranchuser().getBranch().getEmail(),new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		        Paragraph pPhone = new Paragraph();
		        pPhone.add(bp);
		        tableByer.addCell(praCell(pPhone)).setBorderColor(BaseColor.WHITE);

		        // Writting Seller Basic Informationion
		        PdfPCell tTitle1 = new PdfPCell(new Phrase("Seller", new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK)));
		        tTitle1.setBorderColor(BaseColor.WHITE);
		        tableSeller.addCell(tTitle1);
		    
		        Phrase sid = new Phrase();
		        Phrase sn = new Phrase();
		        Phrase sa= new Phrase();
		        Phrase sp= new Phrase();
		        Phrase sd=new Phrase();
		        Phrase ss=new Phrase();
		        /*sid.add(new Chunk("Identity: ",new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)));
		        sid.add(new Chunk(app.getGaz().getBranchuser().getIdnumber(),new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		        Paragraph spId = new Paragraph();
		        spId.add(sid);
		        tableSeller.addCell(praCell(spId)).setBorderColor(BaseColor.WHITE);*/
		        sn.add(new Chunk("Name: ",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
		        sn.add(new Chunk(app.getGaz().getBranchuser().getFirstname()+" "+ app.getGaz().getBranchuser().getLastname(),new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		        Paragraph spname = new Paragraph();
		        spname.add(sn);
		        tableSeller.addCell(praCell(spname)).setBorderColor(BaseColor.WHITE);
		        /*sa.add(new Chunk("Address: ",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
		        sa.add(new Chunk(appointment.getRegistrar().getDistrict().getProvince().getName()+ ","+appointment.getRegistrar().getDistrict().getName(),new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		        Paragraph spAddress = new Paragraph();
		        spAddress.add(sa);*/
		       
		        sp.add(new Chunk("Phone: ",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)));
		        sp.add(new Chunk(app.getGaz().getBranchuser().getPhonenumber(),new Font(FontFamily.TIMES_ROMAN, 9, Font.BOLD)));
		        Paragraph spPhone = new Paragraph();
		        spPhone.add(sp);
		        tableSeller.addCell(praCell(spPhone)).setBorderColor(BaseColor.WHITE);


		        // Nesting Buyer and Sellers information
		        PdfPCell cBuyer = new PdfPCell(tableByer);
		        PdfPCell cSeller = new PdfPCell(tableSeller);
		        PdfPCell cHold = new PdfPCell();

		        cHold.setBorderColor(BaseColor.WHITE);
		        cBuyer.setBorderColor(BaseColor.WHITE);
		        cSeller.setBorderColor(BaseColor.WHITE);

		        table0.addCell(cBuyer);
		        table0.addCell(cHold);
		        table0.addCell(cSeller);
		        
		        // Populating the Fisrt Table
		        // T-Header

		        Paragraph p1=new Paragraph("Order Details",new Font(FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLACK));
		        p1.setSpacingAfter(100);
		         
		        
//				/p1.setAlignment(Element.ALIGN_CENTER);
		        PdfPCell tcell0=new PdfPCell(p1);
		        tcell0.setBackgroundColor(new BaseColor(237, 240, 244));
		        tcell0.setColspan(2);
		        tcell0.setHorizontalAlignment(Element.ALIGN_CENTER);
		        tcell0.setPadding(6);
//				table1.addCell(tcell0);
		        table.addCell(tcell0);
//				table.addCell(praCell(new Paragraph("Requested Delivery Date",new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)))).setBorderColor(BaseColor.BLACK);
		        table.addCell(praCell(new Paragraph("Order Date",new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)))).setBorderColor(BaseColor.BLACK);
		        table.addCell(praCell(new Paragraph(app.getOrderdate()+"",new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK))));
		        table.addCell(praCell(new Paragraph("Status",new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)))).setBorderColor(BaseColor.BLACK);
		        table.addCell(praCell(new Paragraph(app.getOrderstatus()+"",new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK))));
		        /*table.addCell(praCell(new Paragraph("Quantity",new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK)))).setBorderColor(BaseColor.BLACK);
		        table.addCell(praCell(new Paragraph(app.getQuantity()+"",new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK))));
		       */
//				table.addCell(praCell(new Paragraph("Product",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)))).setBorderColor(BaseColor.BLACK);
//				table.addCell(praCell(new Paragraph("Quantity Ordered",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)))).setBorderColor(BaseColor.BLACK);
//				table.addCell(praCell(new Paragraph("Unit Price",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)))).setBorderColor(BaseColor.BLACK);
//				table.addCell(praCell(new Paragraph("Total",new Font(FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK)))).setBorderColor(BaseColor.BLACK);


		        //            Adding Comment
		        PdfPTable comment=new PdfPTable(2);
		        comment.setWidthPercentage(100);
		        comment.setWidths(new float[] {  3f,20f });
		        PdfPCell comCell = new PdfPCell(new Phrase("Comment: ", new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK)));
		        comCell.setPadding(2);
		        comCell.setBorderColor(BaseColor.WHITE);
		        comment.addCell(comCell);
		         document.add(new Paragraph(""));
		        document.add(table0);
		        document.add(new Paragraph(" "));
		        document.add(table);
		        document.add(new Paragraph(" "));
		        document.add(table1);
		        document.add(new Paragraph(" "));
		        document.add(comment);
		        document.add(para2);
		        document.close();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return b.toByteArray();
	}
	
	//create cells
	private static PdfPCell createLabelCellDetails(String text) {
	    // font
	    Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY);

	    // create cell
	    PdfPCell cell = new PdfPCell(new Phrase(text, font));

	    // set style
	    // Style.labelCellStyle(cell);
	// cell.setBorder(PdfPCell.NO_BORDER);rgb()
	cell.setBackgroundColor(new BaseColor(237, 240, 244));
	cell.setPadding(new Float(6));
	    return cell;
	}
	
	
	//create cells
	private static PdfPCell  praCell(Paragraph p) {
	PdfPCell cell = new PdfPCell(p);
	cell.setPadding(new Float(6));
	return cell;
	}
	
	
	//create cells
	private static PdfPCell createLabelCell(String text) {
	// font
	Font font = new Font(FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.DARK_GRAY);

	// create cell
	PdfPCell cell = new PdfPCell(new Phrase(text, font));

	// set style
	// Style.labelCellStyle(cell);
	//cell.setBorder(PdfPCell.NO_BORDER);rgb()

	//cell.setBackgroundColor(new BaseColor(237, 240, 244));
	cell.setPadding(new Float(6));
	return cell;

		
	}

	
}
