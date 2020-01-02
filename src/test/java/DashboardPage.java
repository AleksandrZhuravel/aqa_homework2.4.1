import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement form = $(By.className("App_appContainer__3jRx1"));
    private SelenideElement blockFirstCard = form.$("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement firstCardButton = blockFirstCard.$("[data-test-id = action-deposit]");
    private SelenideElement blockSecondCard = form.$("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private SelenideElement secondCardButton = blockSecondCard.$("[data-test-id = action-deposit]");
    private SelenideElement errorNotification = form.$("[data-test-id = error-notification]");
    private static SelenideElement fieldTransactionAmount = $("[data-test-id = amount] input");
    private SelenideElement fieldCardNumberFrom = form.$("[data-test-id = from] input");
    private SelenideElement transferActionButton = form.$("[data-test-id = action-transfer]");

    public DashboardPage() {
    }

    public static void validAmountInput(String amount) {
        fieldTransactionAmount.setValue(DataHelper.Amount.getAmount(amount));
    }


    public void cardNumberInput(DataHelper.NumberCard number) {
        fieldCardNumberFrom.setValue(number.getCardNumber());
    }

    public void choiceFirstCard() {
        firstCardButton.click();
    }

    public void choiceSecondCard() {
        secondCardButton.click();
    }

    public void clickTransferActionButton() {
        transferActionButton.click();
    }

    public void errorMessageCheck() {
        errorNotification.shouldBe(visible);
    }


    public static class CurrentBalance {
        private static SelenideElement form = $(By.className("App_appContainer__3jRx1"));
        private static SelenideElement blockFirstCard = form.$("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
        private static SelenideElement blockSecondCard = form.$("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

        public static int GetFirstCardBalance() {
            String firstCardBalanceStr = blockFirstCard.text();
            int firstCardBalance = Integer.parseInt(firstCardBalanceStr.substring(29, firstCardBalanceStr.length() - 13).trim());
            return firstCardBalance;
        }

        public static int GetSecondCardBalance() {
            String secondCardBalanceStr = blockSecondCard.text();
            int secondCardBalance = Integer.parseInt(secondCardBalanceStr.substring(29, secondCardBalanceStr.length() - 13).trim());
            return secondCardBalance;
        }
    }
}