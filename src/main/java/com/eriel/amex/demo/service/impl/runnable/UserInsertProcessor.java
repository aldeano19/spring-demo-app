package com.eriel.amex.demo.service.impl.runnable;

import com.eriel.amex.demo.builder.PostalAddressBuilder;
import com.eriel.amex.demo.constants.EyeColorEnum;
import com.eriel.amex.demo.dto.CreateUserDto;
import com.eriel.amex.demo.dto.PostalAddress;
import com.eriel.amex.demo.service.UserService;
import org.apache.poi.ss.usermodel.Row;

public class UserInsertProcessor implements Runnable {
    private CreateUserDto createUserDto;
    private Row row;
    private int rowNumber;
    private UserService userService;


    public UserInsertProcessor(CreateUserDto createUserDto, Row row, int rowNumber, UserService userService){
        this.createUserDto = createUserDto;
        this.userService = userService;
        this.row = row;
        this.rowNumber = rowNumber;
    }

    @Override
    public void run() {


        String firstName = row.getCell(0).getStringCellValue();
        String lastName = row.getCell(1).getStringCellValue();
        String email = row.getCell(2).getStringCellValue();
        PostalAddress address = new PostalAddressBuilder().build();
        EyeColorEnum eyeColorEnum = EyeColorEnum.getByVal(rowNumber%5);

        CreateUserDto createUserDto = new CreateUserDto(firstName, lastName, email, address, eyeColorEnum);

        userService.createUser(createUserDto);
    }
}
