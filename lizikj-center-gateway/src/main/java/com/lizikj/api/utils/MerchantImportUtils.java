package com.lizikj.api.utils;

import com.lizikj.common.util.DateUtils;
import com.lizikj.member.dto.MerchantMemberImportDTO;
import com.lizikj.member.enums.GenderEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author lxl
 * @Date 2018/5/3 16:31
 */
public class MerchantImportUtils {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    private final static Logger log = LoggerFactory.getLogger(MerchantImportUtils.class);


    public static List<MerchantMemberImportDTO> readFromExcel(InputStream inputStream,String fileName){
        Workbook workbook = getWorkBook(inputStream,fileName);
        List<MerchantMemberImportDTO> dtos = new ArrayList<>();
        if(workbook == null){
            log.info("file {} read null ",fileName);
            return dtos;
        }
        int numberOfSheets = workbook.getNumberOfSheets();
        for(int i=0;i < numberOfSheets;i++){
            Sheet sheet = workbook.getSheetAt(i);
            int lastRowNum = sheet.getLastRowNum();
            int firstRowNum = sheet.getFirstRowNum();
            Map<Integer,String> headMap = getHeaderMap(sheet,firstRowNum);
            for(int j=firstRowNum+1;j < lastRowNum+1;j++){
                MerchantMemberImportDTO dto = readFromRow(headMap,sheet.getRow(j));
                if(dto == null){
                    continue;
                }
                dtos.add(dto);
            }
        }

        return dtos;
    }


    public static List<MerchantMemberImportDTO> readFromExcel(String fileName){
        Workbook workbook = getWorkBook(fileName);
        List<MerchantMemberImportDTO> dtos = new ArrayList<>();
        if(workbook == null){
            log.info("file {} read null ",fileName);
            return dtos;
        }
        int numberOfSheets = workbook.getNumberOfSheets();
        for(int i=0;i < numberOfSheets;i++){
            Sheet sheet = workbook.getSheetAt(i);
            int lastRowNum = sheet.getLastRowNum();
            int firstRowNum = sheet.getFirstRowNum();
            Map<Integer,String> headMap = getHeaderMap(sheet,firstRowNum);
            for(int j=firstRowNum+1;j < lastRowNum+1;j++){
                MerchantMemberImportDTO dto = readFromRow(headMap,sheet.getRow(j));
                if(dto == null){
                    continue;
                }
                dtos.add(dto);
            }
        }

        return dtos;
    }


    private static MerchantMemberImportDTO readFromRow(Map<Integer,String> headerMap,Row row){
        short firstCellNum = row.getFirstCellNum();
        short lastCellNum = row.getLastCellNum();
        Map<String,String> valMap = new HashMap<>();

        for(int i = firstCellNum;i < lastCellNum;i++){
            Cell cell = row.getCell(i);
            if(cell == null){
                continue;
            }
            String val = null;
            switch (cell.getCellTypeEnum()){
                case NUMERIC:
                    if(HSSFDateUtil.isCellDateFormatted(cell)){
                        val = DateUtils.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()),"yyyy/MM/dd");
                    }else{
                        cell.setCellType(CellType.STRING);
                        val = cell.getStringCellValue();
                    }
                    break;
                default:
                    cell.setCellType(CellType.STRING);
                    val = cell.getStringCellValue().trim();
                    break;
            }

            valMap.put(headerMap.get(i),val);
        }

        MerchantMemberImportDTO dto = new MerchantMemberImportDTO();

        String mobile = valMap.get("手机号");
        if(StringUtils.isBlank(mobile)){
            return null;
        }
        dto.setMobile(mobile);
        String gender = valMap.get("性别");
        dto.setGenderEnum(GenderEnum.MALE);
        try {
            if("女".equalsIgnoreCase(gender)){
                dto.setGenderEnum(GenderEnum.FEMALE);
            }
        } catch (Exception e) {
            //可以忽略
        }
        String realName = valMap.get("姓名");
        dto.setRealName(realName);
        String birth = valMap.get("生日");
        String registerDate = valMap.get("注册日期");
        if(!StringUtils.isBlank(registerDate)){
            try {
                dto.setRegisterDate(DateUtils.getStartOfDay(DateUtils.parse(registerDate,"yyyy/MM/dd")));
            } catch (Exception e) {
                //可以忽略
            }
        }

        if(StringUtils.isNotBlank(birth)){
            try {
                Date date = DateUtils.parse(birth, "yyyy/MM/dd");
                dto.setBirthYear(DateUtils.format(date,"yyyy"));
                dto.setBirthDate(DateUtils.format(date,"MM-dd"));
            } catch (Exception e) {
                //可以忽略
            }
        }

        try {
            String balance = valMap.get("账户余额");
            if(balance != null){
                dto.setAmount(Double.valueOf(Double.valueOf(balance)*100).longValue());
            }
        } catch (NumberFormatException e) {
            //可以忽略
        }

        try {
            String rebate = valMap.get("返利");
            if(rebate != null){
                dto.setRebateAmount(Double.valueOf(Double.valueOf(rebate)*100).longValue());
            }
        } catch (NumberFormatException e) {
            //可以忽略
        }

        try {
            String credit = valMap.get("积分");
            if(credit != null){
                dto.setCredit(Long.valueOf(credit));
            }
        } catch (NumberFormatException e) {
            //可以忽略
        }


        return dto;
    }

    public static Map<Integer,String> getHeaderMap(Sheet sheet,int rowNum){
        Row row = sheet.getRow(rowNum);
        short firstCellNum = row.getFirstCellNum();
        short lastCellNum = row.getLastCellNum();
        Map<Integer,String> headerMap = new HashMap<>();

        for(int i = firstCellNum;i < lastCellNum;i++){
            Cell cell = row.getCell(i);
            if(cell == null){
                continue;
            }
            headerMap.put(i,cell.getStringCellValue().trim());
        }

        return headerMap;
    }

    public static Workbook getWorkBook(InputStream inputStream,String fileName){
        // 创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            // 获取excel文件的io流
            // 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                // 2003
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileName.endsWith(xlsx)) {
                // 2007
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            log.info("read excel file exception {}",e);
        }

        return workbook;
    }

    public static Workbook getWorkBook(String fileName) {
        // 创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            // 获取excel文件的io流
            InputStream is = new FileInputStream(fileName);
            // 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) {
                // 2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith(xlsx)) {
                // 2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            log.info("read excel file exception {}",e);
        }

        return workbook;
    }
}
