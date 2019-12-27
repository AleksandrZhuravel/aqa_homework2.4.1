import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;


public class CardTester {

    @Test
    void shouldTransferMoneyBetweenOwnCards()
    {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        DashboardPage dashboardPage = new DashboardPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        dashboardPage.choiceFirstCard();
        DashboardPage.validAmountInput();
        dashboardPage.cardNumberInput(DataHelper.getSecondCardNumber());
        dashboardPage.clickTransferActionButton();
        dashboardPage.checkCardBalance();
    }

}
