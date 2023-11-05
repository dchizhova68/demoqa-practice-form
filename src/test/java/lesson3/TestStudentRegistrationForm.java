package lesson3;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class TestStudentRegistrationForm {
    final String FIRST_NAME = "QA";
    final String LAST_NAME = "GURU";
    final String USER_EMAIL = "test@test.ru";
    final String GENDER = "Female";
    final String USER_NUMBER = "1234567890";
    final String DATE_OF_BIRTH = "28";
    final String MONTH_OF_BIRTH = "March";
    final String YEAR_OF_BIRTH = "1990";
    final String SUBJECTS =  "Chemistry";
    final String HOBBIES = "Sports";
    final String PICTURE = "../test.png";
    final String CURRENT_ADDRESS = "Russia";
    final String STATE = "Haryana";
    final String CITY = "Karnal";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(USER_EMAIL);
        $("#genterWrapper input[value='"+ GENDER+ "'] + label").click();
        $("#userNumber").setValue(USER_NUMBER);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(MONTH_OF_BIRTH);
        $(".react-datepicker__year-select").selectOption(YEAR_OF_BIRTH);
        $("#dateOfBirth div[aria-label*= '" + MONTH_OF_BIRTH + " " + DATE_OF_BIRTH + "']").click();
        $("#subjectsInput").val(SUBJECTS).pressEnter();
        $("#hobbiesWrapper").$(byText(""+HOBBIES+"")).click();
        $("#uploadPicture").uploadFromClasspath(PICTURE);
        $("#currentAddress").setValue(CURRENT_ADDRESS);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(""+STATE+"")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(""+CITY+"")).click();
        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(FIRST_NAME + " " + LAST_NAME));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(USER_EMAIL));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(GENDER));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(USER_NUMBER));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(DATE_OF_BIRTH + " " + MONTH_OF_BIRTH + "," + YEAR_OF_BIRTH));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(SUBJECTS));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(HOBBIES));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(PICTURE));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(CURRENT_ADDRESS));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(STATE + " " + CITY));
    }

}
