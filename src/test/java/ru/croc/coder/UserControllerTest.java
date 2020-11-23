package ru.croc.coder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.croc.coder.controller.UserController;
import ru.croc.coder.controller.dto.UserDto;
import ru.croc.coder.school.pearsons.SchoolRank;
import ru.croc.coder.service.exceptions.NotFoundException;
import ru.croc.coder.service.exceptions.PermissionException;
import ru.croc.coder.service.exceptions.UserConstrainException;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    private UserController userController;

    @Autowired
    private WebApplicationContext applicationContext;
    private MockMvc mockMvc;


    @BeforeEach
    public void init () {
        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    //Задание 1 из 10
    @Test
    public void testAuth() {
        final Long userId = 1l;
        final String email = "episarenko@croc.ru";
        final String pass = "123";

        assertEquals(userController.auth(userId, pass).getSchoolRank(), SchoolRank.TEACHER);
        assertEquals(userController.auth(email, pass).getSchoolRank(), SchoolRank.TEACHER);




        /*mockMvc.perform(MockMvcRequestBuilders.get("/auth/1/123")).
                andExpect(MockMvcResultMatchers.view().)*/
    }

 /*       mockMvc.perform(MockMvcRequestBuilders.get("/auth/1")).
    andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
*/
    @Test
    public void testFalseAuth() {

        final Long wrongUserId = 0l;
        final String pass = "123";
        NotFoundException thrown = assertThrows(NotFoundException.class, ()->userController.auth(wrongUserId, pass));
        assertNotNull(thrown);

    }

    //Задание 2 из 10
    @Test
    public void testReg(){
        final String firstName = "Alexey";
        final String lastName = "Pugovkin";
        final String email = "123456@abraNevo.zm";
        final String pass = "123asag_@#$%";


        UserDto userDto = userController.reg(firstName, lastName, email, pass);
        assertEquals(userDto.getFirstName(), firstName);
        userController.auth(1l, "123");
        assertEquals(userController.teach(userDto.getId()).getSchoolRank(), SchoolRank.TEACHER);
    }



    @Test
    public void testFalseReg(){
        final String firstName = "Alexey";
        final String lastName = "Pugovkin";
        final String email = "123456@abraNevo.zm";
        final String pass = "123asag_@#$%";

        final String wrongEmail = "12345";

        Throwable thrown = assertThrows(PermissionException.class, () -> userController.teach(2l));
        assertNotNull(thrown);
        assertTrue(thrown instanceof PermissionException);

        thrown = assertThrows(UserConstrainException.class, () -> userController.reg(firstName, lastName, wrongEmail, pass));
        assertNotNull(thrown);
        assertTrue(thrown.getMessage().contains("Invalidate email"));

    }


}
