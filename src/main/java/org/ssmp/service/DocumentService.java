package org.ssmp.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.ssmp.Utils.HibernateUtil;
import org.ssmp.dtos.documents.*;
import org.ssmp.model.Document;
import org.ssmp.repository.DocumentRepository;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.ssmp.Utils.DocumentUtil.fillTemplate;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final TypeDocumentService typeDocumentService;
    private final StatusService statusService;
    private final StationService stationService;
    private final CarService carService;
    private final StaffService staffService;

    private static final String TEMPLATE_PATH_ACCEPT = "D:\\JavaProjects\\DIPLOM\\backendKSSASMP\\example_accept.docx";
    private static final String TEMPLATE_PATH_GET = "D:\\JavaProjects\\DIPLOM\\backendKSSASMP\\example_peredacha.docx";
    private static final String TEMPLATE_PATH_DROP = "D:\\JavaProjects\\DIPLOM\\backendKSSASMP\\example_spisanie.docx";

    public void saveDocumentBcast(InfoForSaveDocDTO infoForSaveDocDTO) throws IOException {
        SaveDocDTO saveDocDTO = getSaveDocDTO(infoForSaveDocDTO);
        byte[] documentBytes = fillTemplate(fillMapBcast(saveDocDTO), TEMPLATE_PATH_GET);
        Document document = new Document();
        document.setCarPlates(saveDocDTO.getCarPlates());
        document.setDataType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        document.setStatus(statusService.getByStatusName(saveDocDTO.getStatus()));
        document.setTypeDocument(typeDocumentService.getTypeByName("Передача"));
        document.setDocumentData(documentBytes);
        documentRepository.save(document);
    }
    public void saveDocumentAccept(InfoDocumentAcceptDTO infoDocumentAcceptDTO) throws IOException {
        SaveDocAcceptDTO saveDocAcceptDTO =  getSaveDocAcceptDTO(infoDocumentAcceptDTO);

        byte[] documentBytes = fillTemplate(fillMapAcceptDoc(saveDocAcceptDTO), TEMPLATE_PATH_ACCEPT);

        Document document = new Document();
        document.setCarPlates(saveDocAcceptDTO.getCarPlates());
        document.setDataType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        document.setStatus(statusService.getByStatusName(infoDocumentAcceptDTO.getStatus()));
        document.setTypeDocument(typeDocumentService.getTypeByName("Прием"));
        document.setDocumentData(documentBytes);
//        documentRepository.save(document);
    }
    public void saveDocumentWriteDown(InfoForWriteDownDocDTO infoForWriteDownDocDTO) throws IOException {
        SaveDocWriteDownDTO saveDocWriteDownDTO = getSaveDocWriteDownDTO(infoForWriteDownDocDTO);
        byte[] documentBytes = fillTemplate(fillMapWriteDown(saveDocWriteDownDTO), TEMPLATE_PATH_DROP);
        Document document = new Document();
        document.setCarPlates(infoForWriteDownDocDTO.getCarPlates());
        document.setDataType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        document.setStatus(statusService.getByStatusName(infoForWriteDownDocDTO.getStatus()));
        document.setTypeDocument(typeDocumentService.getTypeByName("Списание"));
        document.setDocumentData(documentBytes);
        documentRepository.save(document);
    }
    private SaveDocDTO getSaveDocDTO(InfoForSaveDocDTO infoForSaveDocDTO) {
        Date calendar =new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SaveDocDTO saveDocDTO = new SaveDocDTO();
        saveDocDTO.setSellerCompanyName(infoForSaveDocDTO.getSellerCompanyName());
        saveDocDTO.setFioGlavVrach(infoForSaveDocDTO.getInfoMyCompany().substring(9));
        saveDocDTO.setFioSeller(infoForSaveDocDTO.getFioSeller());
        saveDocDTO.setDate(sdf.format(calendar));
        saveDocDTO.setBrand(carService.findCarByPlates(infoForSaveDocDTO.getCarPlates()).getBrand());
        saveDocDTO.setVinNumber(carService.findCarByPlates(infoForSaveDocDTO.getCarPlates()).getVinNumber());
        saveDocDTO.setCarPlates(infoForSaveDocDTO.getCarPlates());
        saveDocDTO.setYearRelease(carService.findCarByPlates(infoForSaveDocDTO.getCarPlates()).getYearRelease());
        saveDocDTO.setTypeDocument(typeDocumentService.getTypeByName(infoForSaveDocDTO.getTypeDocument()).getTypeName());
        saveDocDTO.setStatus(statusService.getByStatusName(infoForSaveDocDTO.getStatus()).getStatusName());
        return saveDocDTO;
    }
    private SaveDocWriteDownDTO getSaveDocWriteDownDTO(InfoForWriteDownDocDTO infoForWriteDownDocDTO){
        SaveDocWriteDownDTO saveDocWriteDownDTO = new SaveDocWriteDownDTO();
        saveDocWriteDownDTO.setStationName(stationService.getStationsList().getFirst().getStationName());
        saveDocWriteDownDTO.setCarPlates(infoForWriteDownDocDTO.getCarPlates());
        saveDocWriteDownDTO.setCarID(String.valueOf(carService.findCarByPlates(infoForWriteDownDocDTO.getCarPlates()).getCarID()));
        saveDocWriteDownDTO.setBrand(carService.findCarByPlates(infoForWriteDownDocDTO.getCarPlates()).getBrand());
        saveDocWriteDownDTO.setReason(infoForWriteDownDocDTO.getReason());
        saveDocWriteDownDTO.setFioGlavVrach(infoForWriteDownDocDTO.getInfoMyCompany().substring(9));
        saveDocWriteDownDTO.setYearRelease(carService.findCarByPlates(infoForWriteDownDocDTO.getCarPlates()).getYearRelease());
        saveDocWriteDownDTO.setDateStart(carService.findCarByPlates(infoForWriteDownDocDTO.getCarPlates()).getDateStart());
        saveDocWriteDownDTO.setMileage(String.valueOf(carService.findCarByPlates(infoForWriteDownDocDTO.getCarPlates()).getMileage()));
        saveDocWriteDownDTO.setVinNumber(carService.findCarByPlates(infoForWriteDownDocDTO.getCarPlates()).getVinNumber());
        saveDocWriteDownDTO.setFuel(carService.findCarByPlates(infoForWriteDownDocDTO.getCarPlates()).getFuelType().getFuelName());
        saveDocWriteDownDTO.setFioMech(staffService.getFIOMech(Long.valueOf(infoForWriteDownDocDTO.getMechID())));
        return saveDocWriteDownDTO;
    }
    private SaveDocAcceptDTO getSaveDocAcceptDTO(InfoDocumentAcceptDTO infoDocumentAcceptDTO){
        SaveDocAcceptDTO saveDocAcceptDTO = new SaveDocAcceptDTO();
        saveDocAcceptDTO.setSellerCompanyName(infoDocumentAcceptDTO.getSellerCompanyName());
        saveDocAcceptDTO.setCarPlates(infoDocumentAcceptDTO.getCarPlates());
        saveDocAcceptDTO.setMyStationName(infoDocumentAcceptDTO.getStationName());
        saveDocAcceptDTO.setFioGlavVrach(staffService.getFIO(staffService.getStaffList().getFirst()));
        saveDocAcceptDTO.setBrand(infoDocumentAcceptDTO.getBrand());
        saveDocAcceptDTO.setVinNumber(infoDocumentAcceptDTO.getVinNumber());
        saveDocAcceptDTO.setRelease(infoDocumentAcceptDTO.getRelease());
        saveDocAcceptDTO.setMileage(infoDocumentAcceptDTO.getMileage());
        saveDocAcceptDTO.setFioSeller(infoDocumentAcceptDTO.getFioSeller());
        return saveDocAcceptDTO;
    }
    private Map<String, String> fillMapBcast(SaveDocDTO saveDocDTO){
        Map<String, String> placeholderMap = new HashMap<>();
        placeholderMap.put("sellerCompanyName", saveDocDTO.getSellerCompanyName());
        placeholderMap.put("myStationName", stationService.getStationsList().getFirst().getStationName());
        placeholderMap.put("fioGlavVrach", saveDocDTO.getFioGlavVrach());
        placeholderMap.put("brand", saveDocDTO.getBrand());
        placeholderMap.put("vinNumber", saveDocDTO.getVinNumber());
        placeholderMap.put("carPlates", saveDocDTO.getCarPlates());
        placeholderMap.put("Release", saveDocDTO.getYearRelease());
        placeholderMap.put("fioSeller", saveDocDTO.getFioSeller());
        placeholderMap.put("day", saveDocDTO.getDate().substring(8,10));
        placeholderMap.put("month", saveDocDTO.getDate().substring(5,7));
        placeholderMap.put("year", saveDocDTO.getDate().substring(0,4));
        return placeholderMap;
    }
    private Map<String, String> fillMapWriteDown(SaveDocWriteDownDTO saveDocWriteDownDTO){
        Date calendar =new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, String> placeholder = new HashMap<>();
        placeholder.put("stationName", stationService.getStationsList().getFirst().getStationName());
        placeholder.put("day", sdf.format(calendar).substring(8,10));
        placeholder.put("month", sdf.format(calendar).substring(5,7));
        placeholder.put("year", sdf.format(calendar).substring(0,4));
        placeholder.put("carPlates", saveDocWriteDownDTO.getCarPlates());
        placeholder.put("id", saveDocWriteDownDTO.getCarID());
        placeholder.put("brand", saveDocWriteDownDTO.getBrand());
        placeholder.put("reason", saveDocWriteDownDTO.getReason());
        placeholder.put("fioGlavVrach",saveDocWriteDownDTO.getFioGlavVrach());
        placeholder.put("Release", saveDocWriteDownDTO.getYearRelease());
        placeholder.put("dateStart", saveDocWriteDownDTO.getDateStart());
        placeholder.put("mileage", saveDocWriteDownDTO.getMileage());
        placeholder.put("vinNumber", saveDocWriteDownDTO.getVinNumber());
        placeholder.put("fuel", saveDocWriteDownDTO.getFuel());
        placeholder.put("fioMech", saveDocWriteDownDTO.getFioMech());

        return placeholder;
    }
    private Map<String, String> fillMapAcceptDoc(SaveDocAcceptDTO saveDocAcceptDTO){
        Date calendar =new Date();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, String> placeholder=new HashMap<>();
        placeholder.put("sellerCompanyName", saveDocAcceptDTO.getSellerCompanyName());
        placeholder.put("myStationName", saveDocAcceptDTO.getMyStationName());
        placeholder.put("fioGlavVrach", saveDocAcceptDTO.getFioGlavVrach());
        placeholder.put("day", sdf.format(calendar).substring(8,10));
        placeholder.put("month", sdf.format(calendar).substring(5,7));
        placeholder.put("year", sdf.format(calendar).substring(0,4));
        placeholder.put("brand", saveDocAcceptDTO.getBrand());
        placeholder.put("vinNumber", saveDocAcceptDTO.getVinNumber());
        placeholder.put("carPlates", saveDocAcceptDTO.getCarPlates());
        placeholder.put("Release", saveDocAcceptDTO.getRelease());
        placeholder.put("mileage", saveDocAcceptDTO.getMileage());
        placeholder.put("fioSeller", saveDocAcceptDTO.getFioSeller());

        return placeholder;
    }


    public Document getDocByPlateAndType(String carPlates, String typeName){
        List<Document> documentList = new ArrayList<>();

        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            documentList = session.createQuery(
                            "from Document " +
                                    "where carPlates= :carPlates and typeDocument=:typeName", Document.class)
                    .setParameter("carPlates", carPlates)
                    .setParameter("typeName", typeDocumentService.getTypeByName(typeName)).list();
        }catch (Exception e){
            System.out.println(e);
        }
        if(documentList.isEmpty()){
            return null;
        }
        return documentList.getFirst();
    }

}
