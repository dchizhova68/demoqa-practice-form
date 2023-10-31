import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class testStudentRegistrationForm {
    final String FIRST_NAME = "QA";
    final String LAST_NAME = "GURU";
    final String USER_EMAIL = "test@test.ru";
    final String GENDER = "Female";
    final String USER_NUMBER = "1234567890";
    final String DATE_OF_BIRTH = "28";
    final String MONTH_OF_BIRTH = "March";
    final String YEAR_OF_BIRTH = "1990";
    final String[]  SUBJECTS = {"Computer Science", "Chemistry"};
    final String[] HOBBIES = {"Sports","Music"};
    final String PICTURE = "test.png";
    final String CURRENT_ADDRES= "Russia";
    final String STATE= "Haryana";
    final String CITY= "Karnal";
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
    $x("//*[@id='dateOfBirthInput']").click();
    $x("(//*[@id='dateOfBirth']//select)[1]").selectOption(MONTH_OF_BIRTH);
    $x("(//*[@id='dateOfBirth']//select)[2]").selectOption(YEAR_OF_BIRTH);
    $x("//*[@id='dateOfBirth']//div[contains(@aria-label, '" +MONTH_OF_BIRTH+" " + DATE_OF_BIRTH+"')]").click();
    for (int i = 0; i < SUBJECTS.length; i++) {
        $x("//*[@id='subjectsInput']").val(SUBJECTS[i]).pressEnter();
    }
    for (int i = 0; i < HOBBIES.length; i++) {
        $x("//*[@id='hobbiesWrapper']//label[text() = '" + HOBBIES[i] + "']").click();
    }
    $x("//*[@id ='uploadPicture']").uploadFromClasspath(PICTURE);
    $x("//*[@id='currentAddress']").setValue(CURRENT_ADDRES);
    $x("//*[@id='state']").click();
    $x("//*[@id='stateCity-wrapper']//*[text()='"+STATE+"']").click();
    $x("//*[@id='city']").click();
    $x("//*[@id='stateCity-wrapper']//*[text()='"+CITY+"']").click();
    $x("//*[@id='submit']").click();

    $x("//*[@class='table-responsive']//td[contains(text(), 'Student Name')]/following-sibling::td").shouldHave(text(FIRST_NAME + " " + LAST_NAME));
    $x("//*[@class='table-responsive']//td[contains(text(), 'Student Email')]/following-sibling::td").shouldHave(text(USER_EMAIL));
    $x("//*[@class='table-responsive']//td[contains(text(), 'Gender')]/following-sibling::td").shouldHave(text(GENDER));
    $x("//*[@class='table-responsive']//td[contains(text(), 'Mobile')]/following-sibling::td").shouldHave(text(USER_NUMBER));
    $x("//*[@class='table-responsive']//td[contains(text(), 'Date of Birth')]/following-sibling::td").shouldHave(text(DATE_OF_BIRTH + " " + MONTH_OF_BIRTH + "," +YEAR_OF_BIRTH));
    $x("//*[@class='table-responsive']//td[contains(text(), 'Subject')]/following-sibling::td").shouldHave(text(String.join(", ", SUBJECTS)));
    $x("//*[@class='table-responsive']//td[contains(text(), 'Hobbies')]/following-sibling::td").shouldHave(text(String.join(", ", HOBBIES)));
    $x("//*[@class='table-responsive']//td[contains(text(), 'Picture')]/following-sibling::td").shouldHave(text(PICTURE));
    $x("//*[@class='table-responsive']//td[contains(text(), 'Address')]/following-sibling::td").shouldHave(text(CURRENT_ADDRES));
    $x("//*[@class='table-responsive']//td[contains(text(), 'State and City')]/following-sibling::td").shouldHave(text(STATE + " " + CITY));
    }

}
