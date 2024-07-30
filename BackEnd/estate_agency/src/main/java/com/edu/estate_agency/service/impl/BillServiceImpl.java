package com.edu.estate_agency.service.impl;

import com.edu.estate_agency.entity.Bill;
import com.edu.estate_agency.entity.Contract;
import com.edu.estate_agency.entity.Maintenance;
import com.edu.estate_agency.entity.Room;
import com.edu.estate_agency.model.request.CreateBillRequest;
import com.edu.estate_agency.repository.*;
import com.edu.estate_agency.service.BillService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;



import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

import static com.itextpdf.text.FontFactory.getFont;

@Service
@Slf4j
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;
@Autowired
private RoomRepository roomRepository;
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MaintenanceRepository maintenanceRepository;
    @Override
    public Bill save(CreateBillRequest createBillRequest) {
       Bill bill= new Bill();
       bill.setName(createBillRequest.getName());
       bill.setDate(new Date());
       bill.setContract(contractRepository.findById(createBillRequest.getIdContract()).get());
       bill.setStatus("Chưa thanh toán");
        Contract contract=contractRepository.findById(createBillRequest.getIdContract()).get();
        Room room= contract.getRoom();
        List<Maintenance> maintenances= room.getMaintenances();
        List<Maintenance> maintenances1= getByDay(maintenances, LocalDate.now());
        long total=0;
        total+=room.getPrice();
for(Maintenance maintenance: maintenances1)
{
    total+=maintenance.getPrice();
    }
bill.setTotal(total);
return billRepository.save(bill);
    }

    private List<Maintenance> getByDay(List<Maintenance> list, LocalDate currentDate) {
   LocalDate firstDayOfMonth=currentDate.withDayOfMonth(1);
   LocalDate lastDayOfMonth= currentDate.withDayOfMonth(currentDate.lengthOfMonth());
   List<Maintenance> maintenances1= new ArrayList<>();
   for(Maintenance maintenance: list)
   {
       if(convertDate(maintenance.getDate()).isEqual(firstDayOfMonth)
           || convertDate(maintenance.getDate()).isEqual(lastDayOfMonth)
           ||(convertDate(maintenance.getDate()).isAfter(firstDayOfMonth)
       && convertDate(maintenance.getDate()).isBefore(lastDayOfMonth))){
           maintenances1.add(maintenance);
       }

   }
   return  maintenances1;
    }

    private LocalDate convertDate(Date date) {
        LocalDateTime localDateTime= LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        // Chuyển đổi từ java.time.LocalDateTime sang java.time.LocalDate
        LocalDate localDate = localDateTime.toLocalDate();
   return localDate;
    }

    @Override
    public List<Bill> getByIdContract(Long id) {
        return billRepository.getByContractId(id);
    }

    @Override
    public byte[] generateReport(long id) {
       try{
           Optional<Bill> optionalBill= billRepository.findById(id);
           if(optionalBill.isPresent())
           {
               Bill bill= optionalBill.get();
               Contract contract= bill.getContract();
               Room room= contract.getRoom();
               String data= "Name:"+room.getName()+ "\n"+"Address: "+room.getAddress()+"\n" +"Price: "+room.getPrice()+"\n" +"Payment Method: Cart";
               Date date= new Date();
               String fileName= room.getName();
               Document document= new Document();
               ByteArrayOutputStream byteArr= new ByteArrayOutputStream();
               PdfWriter.getInstance(document, byteArr);
               document.open();
               setRactangleInPdf(document);
               Paragraph chunk= new Paragraph("Bill in month", getFont("Header"));
               chunk.setAlignment(Element.ALIGN_CENTER);
               document.add(chunk);
               Paragraph paragraph= new Paragraph(data+ "\n  \n", getFont("Data"));
               document.add(paragraph);
               PdfPTable table= new PdfPTable(3);
               table.setWidthPercentage(100);
               addTableHeader(table);
               List<Maintenance> maintenances= maintenanceRepository.getListByRoom(room.getId());
               if(maintenances.size()>0) {

                   List<Maintenance> maintenances1 = getByDay(maintenances, convertDate(bill.getDate()));
                   for (Maintenance maintenance : maintenances1) {
                       addRows(table, maintenance);
                   }
                   document.add(table);
               }

        Paragraph footer= new Paragraph("Total: "+getTotal(id).toString()+ "\n" + "Thank you for visiting. Please visit again!!", getFont("Data"));
       document.add(footer);
       document.close();
       byte[] pdfContent= byteArr.toByteArray();
       byteArr.close();
        return pdfContent;
        // return fileName;
    }
           else {
               throw new IllegalArgumentException("Bill not found for id: " + id);
           }
       } catch (Exception e) {
           log.info(e.getMessage());
           throw new IOException("Error generating report: " + e.getMessage());
       }
        // return "";

    }

    private Long getTotal(long id) {
        long total = 0;
        Contract contract = contractRepository.findById(id).get();
        Room room = contract.getRoom();
        total += room.getPrice();
        List<Maintenance> maintenances = room.getMaintenances();
        for (Maintenance maintenance : maintenances) {
            total += maintenance.getPrice();
        }
        return total;
    }

    private void addRows(PdfPTable table, Maintenance maintenance) {
table.addCell((String) maintenance.getName());
table.addCell((String) maintenance.getDate().toString());
        table.addCell((String) maintenance.getPrice().toString());
    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("Name", "Date", "Price").forEach(columTitle->{
            PdfPCell header= new PdfPCell();
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Phrase(columTitle));
            header.setBackgroundColor(BaseColor.YELLOW);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(header);
        });
    }
    private Font getFont(String type) {
        switch (type) {
            case "Header":
                Font heaFont = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE, 18, BaseColor.BLACK);
                heaFont.setStyle(Font.BOLD);
                return heaFont;
            // break;
            case "Data":
                Font heaFontDT = FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, BaseColor.BLACK);
                heaFontDT.setStyle(Font.BOLD);
                return heaFontDT;
            default:
                return new Font();
        }
    }
    private void setRactangleInPdf(Document document) throws  DocumentException{
    Rectangle rect= new Rectangle(577, 825, 18, 15);
    rect.enableBorderSide(1);
        rect.enableBorderSide(2);
        rect.enableBorderSide(4);
        rect.enableBorderSide(8);
        rect.setBorderColor(BaseColor.BLACK);
        rect.setBorderWidth(1);
        document.add(rect);
    }




    @Override
    public Bill detail(long id) {
        return billRepository.findById(id).get();
    }

    @Override
    public Bill updatePayment(long id, String user) {
        Bill bill= billRepository.findById(id).get();
        bill.setStatus("Đã thanh toán");
        bill.setUser(user);

        return billRepository.save(bill);
    }

    @Override
    public List<Object> getReport(Long idAgent) {
        List<Contract> contracts = contractRepository.getByAgent(idAgent);
        List<Long> list = new ArrayList<>();
        for (Contract contract : contracts) {
            list.add(contract.getId());
        }
        return billRepository.getTotalByMonthYear(list);
    }

    @Override
    public List<Integer> getReportRoomandMaintain(Long idAgent) {
       int totalBill=0;
       int totalmaintaince=0;
       List<Long> list= new ArrayList<>();
       List<Contract> contracts= contractRepository.getByAgent(idAgent);
       for(Contract contract: contracts)
       {
           list.add(contract.getId());
       }
       List<Bill> bills= billRepository.getPaymented(list);
       for(Bill bill: bills)
       {
           totalBill+=bill.getContract().getRoom().getPrice();
           for(Maintenance maintenance: bill.getContract().getRoom().getMaintenances())
           {
               totalmaintaince+=maintenance.getPrice();
           }
       }
       List<Integer> result= new ArrayList<>();
       result.add(totalBill);
       result.add(totalmaintaince);
       return result;
    }
    private Integer getTotalAll(long id) {
        int totalmaintaince = 0;

        List<Long> list = new ArrayList<>();
        List<Contract> contracts = contractRepository.getByAgent(id);
        for (Contract contract : contracts) {
            list.add(contract.getId());
        }
        List<Bill> bills = billRepository.getPaymented(list);
        for (Bill bill : bills) {
            totalmaintaince += bill.getContract().getRoom().getPrice();
            for (Maintenance maintenance : bill.getContract().getRoom().getMaintenances()) {
                totalmaintaince += maintenance.getPrice();
            }

        }
        return totalmaintaince;
    }
    @Override
    public Map<String, Integer> getReportAgent(Long id) {
        Map<String, Integer> map= new HashMap<>();
        map.put("room", roomRepository.getByAgent(id).size());
        map.put("roomrented", roomRepository.getByAgentAngented(id).size());
        map.put("notpayment", billRepository.getByContractIDandNotPayment(id).size());
        map.put("total",getTotalAll(id));
        return map;
    }

    @Override
    public Map<String, Integer> getReportAdmin() {
        Map<String, Integer> map = new HashMap<>();
        map.put("acount", userRepository.findAll().size());
        map.put("room", roomRepository.findAll().size());
        map.put("roomnot", roomRepository.getByRoomReportAdmin().size());
        map.put("contact", contractRepository.findAll().size());
        return map;
    }
}
