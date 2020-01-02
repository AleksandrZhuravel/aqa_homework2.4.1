import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardTester {

    @BeforeEach
    public void initEach() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @DisplayName(value = "01.Should transfer valid amount to first card from second card")
    void shouldTransferMoneyToFirstCardFromSecondCard() {
        int StartFirstCardBalance = DashboardPage.CurrentBalance.GetFirstCardBalance();
        int StartSecondCardBalance = DashboardPage.CurrentBalance.GetSecondCardBalance();
        String amount = "100";
        int amountInt = Integer.parseInt(amount);
        int ExpectedFirstCardBalance = StartFirstCardBalance + amountInt;
        int ExpectedSecondCardBalance = StartSecondCardBalance - amountInt;
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.choiceFirstCard();
        DashboardPage.validAmountInput(amount);
        dashboardPage.cardNumberInput(DataHelper.getSecondCardNumber());
        dashboardPage.clickTransferActionButton();
        int NextFirstCardBalance = DashboardPage.CurrentBalance.GetFirstCardBalance();
        int NextSecondCardBalance = DashboardPage.CurrentBalance.GetSecondCardBalance();
        assertEquals(ExpectedFirstCardBalance, NextFirstCardBalance);
        assertEquals(ExpectedSecondCardBalance, NextSecondCardBalance);
    }

    @Test
    @DisplayName(value = "02.Should transfer valid amount to second card from first card")
    void shouldTransferMoneyFromFirstCardToSecondCard() {
        int StartFirstCardBalance = DashboardPage.CurrentBalance.GetFirstCardBalance();
        int StartSecondCardBalance = DashboardPage.CurrentBalance.GetSecondCardBalance();
        String amount = "100";
        int amountInt = Integer.parseInt(amount);
        int ExpectedFirstCardBalance = StartFirstCardBalance - amountInt;
        int ExpectedSecondCardBalance = StartSecondCardBalance + amountInt;
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.choiceSecondCard();
        DashboardPage.validAmountInput(amount);
        dashboardPage.cardNumberInput(DataHelper.getFirstCardNumber());
        dashboardPage.clickTransferActionButton();
        int NextFirstCardBalance = DashboardPage.CurrentBalance.GetFirstCardBalance();
        int NextSecondCardBalance = DashboardPage.CurrentBalance.GetSecondCardBalance();
        assertEquals(ExpectedFirstCardBalance, NextFirstCardBalance);
        assertEquals(ExpectedSecondCardBalance, NextSecondCardBalance);
    }

    @Test
    @DisplayName(value = "03.Should show error message if amount be more than first balance")
    void shouldShowErrorMessageIfAmountBeMoreTenThousands() {
        int StartFirstCardBalance = DashboardPage.CurrentBalance.GetFirstCardBalance();
        int amountInt = StartFirstCardBalance + 1;
        String amount = Integer.toString(amountInt);
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.choiceFirstCard();
        dashboardPage.validAmountInput(amount);
        dashboardPage.cardNumberInput(DataHelper.getSecondCardNumber());
        dashboardPage.clickTransferActionButton();
        dashboardPage.errorMessageCheck();
    }
}
