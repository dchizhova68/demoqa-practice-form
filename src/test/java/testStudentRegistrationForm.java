package tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import java.beans.PropertyEditor;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$x;
public class testStudentRegistrationForm {
    final String FIRST_NAME = "QA";
    final String LAST_NAME = "GURU";
    final String USER_EMAIL = "test@test.ru";
    final String GENDER = "Female";
    final String USER_NUMBER = "+71234567890";
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }
    @Test
    void fillFormTest(){
    open("/automation-practice-form");

    // input Name id =firstName
    $x("//*[@id='firstName']").setValue(FIRST_NAME);
    $x("//*[@id='lastName']").setValue(LAST_NAME);
    $x("//*[@id='userEmail']").setValue(USER_EMAIL);
    $x("//input[@type='radio'][@value='" + GENDER + "']/parent::div").click();
    $x("//*[@id='userNumber']").setValue(USER_NUMBER);

    // input mobail id="userNumber"
    // input Date of Birth id="dateOfBirthInput"
    // div subject
    // div hobbies
    // div pictures
    //textarea id="currentAddress"
    //div State and City
    }

}
