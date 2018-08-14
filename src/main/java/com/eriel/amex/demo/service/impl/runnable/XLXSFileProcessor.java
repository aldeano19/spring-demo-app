package com.eriel.amex.demo.service.impl.runnable;

import com.eriel.amex.demo.builder.PostalAddressBuilder;
import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.PostalAddress;
import com.eriel.amex.demo.helper.LoggingHelper;
import com.eriel.amex.demo.service.UserService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class XLXSFileProcessor implements Runnable{
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Iterator<Row> rowIterator;
    private UserService userService;

    private LoggingHelper loggingHelper = LoggingHelper.getInstance();

    public XLXSFileProcessor(File file, UserService userService) throws IOException {
        workbook = new XSSFWorkbook(new FileInputStream(file));
        sheet = workbook.getSheetAt(0);
        rowIterator = sheet.iterator();
        this.userService = userService;
    }

    @Override
    public void run() {
        int i = 0;
        while (rowIterator.hasNext()){
            i++;
            Row row = rowIterator.next();

            String firstName = row.getCell(0).getStringCellValue();
            String lastName = row.getCell(1).getStringCellValue();
            String email = row.getCell(2).getStringCellValue();
            PostalAddress address = new PostalAddressBuilder().build();
            EyeColorEnum eyeColorEnum = EyeColorEnum.getByVal(i%5);

            CreateUserDto createUserDto = new CreateUserDto(firstName, lastName, email, address, eyeColorEnum);

            Thread insertThread = new Thread(new UserInsertProcessor(createUserDto, row, i, userService));

            String message = String.format("Starting user insert thread with name = %s", insertThread.getName());
            loggingHelper.logLine(message);

            insertThread.start();
        }
    }
}
