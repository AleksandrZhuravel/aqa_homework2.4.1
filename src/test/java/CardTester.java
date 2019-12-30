import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardTester {

    @BeforeEach
    public void initEach(){
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }



    @Test
    @Order(1)
    @DisplayName(value = "01.Should transfer valid amount to first card to second card")
    void shouldAtransferMoneyToFirstCardFromSecondCard()
    {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.choiceFirstCard();
        DashboardPage.validAmountInput();
        dashboardPage.cardNumberInput(DataHelper.getSecondCardNumber());
        dashboardPage.clickTransferActionButton();
        dashboardPage.FirstCheckCardBalance();

    }

    @Test
    @Order(2)
    @DisplayName(value = "02.Should return valid amount to second card to first card")
    void shouldBreturnMoneyFromFirstCardToSecondCard()
    {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.choiceSecondCard();
        DashboardPage.validAmountInput();
        dashboardPage.cardNumberInput(DataHelper.getFirstCardNumber());
        dashboardPage.clickTransferActionButton();
        dashboardPage.ReturnCheckCardBalance();
    }

    @Test
    @Order(3)
    @DisplayName(value = "03.Should show error message if amount be more than first balance")
    void shouldCshowErrorMessageIfAmountBeMoreTenThousands()
    {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.choiceFirstCard();
        dashboardPage.toBigAmountInput();
        dashboardPage.cardNumberInput(DataHelper.getSecondCardNumber());
        dashboardPage.clickTransferActionButton();
        dashboardPage.errorMessageCheck();
    }


}
