import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private SelenideElement form = $(By.className("App_appContainer__3jRx1"));
    private SelenideElement heading = form.$("[data-test-id = dashboard]");
    private SelenideElement blockFirstCard = form.$("[data-test-id = 92df3f1c-a033-48e6-8390-206f6b1f56c0]");
    private SelenideElement firstCardButton = blockFirstCard.$("[data-test-id = action-deposit]");
    private SelenideElement blockSecondCard = form.$("[data-test-id = 0f3f5c2a-249e-4c3d-8287-09f7a039391d]");
    private SelenideElement secondCardButton = blockSecondCard.$("[data-test-id = action-deposit]");
    private SelenideElement reloadButton = form.$("[data-test-id = action-reload]");
    private SelenideElement errorNotification = form.$("[data-test-id = error-notification]");
    private static SelenideElement fieldTransactionAmount = $("[data-test-id = amount]");
    private SelenideElement fieldCardNumberFrom = form.$("[data-test-id = from]");
    private SelenideElement fieldCardNumberTo = form.$("[data-test-id = to]");
    private SelenideElement transferActionButton = form.$("[data-test-id = action-transfer]");
    private SelenideElement transferCancelButton = form.$("[data-test-id = action-cancel]");


    public DashboardPage() {
    }

    public static void validAmountInput() {
        fieldTransactionAmount.setValue(DataHelper.Amount.getAmount());

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


    public static class BalanceCalculator {
        int firstBalance = DataHelper.StartBalance.getStartBalance();
        int amount = Integer.parseInt(DataHelper.Amount.getAmount());
        int balanceFrom = firstBalance - amount;
        String stringBalanceFrom = Integer.toString(balanceFrom);
        int balanceTo = firstBalance - amount;
        String stringBalanceTo = Integer.toString(balanceTo);

        public String getBalanceFrom() {
            return stringBalanceFrom;
        }

        public String getBalanceTo() {
            return stringBalanceTo;
        }

    }

    public void checkCardBalance() {
        BalanceCalculator balanceCalculator = new BalanceCalculator();
        blockFirstCard.shouldHave(exactText("**** **** **** 0001" + ", баланс: " + balanceCalculator.getBalanceTo() + " р. "));
        blockFirstCard.shouldHave(exactText("**** **** **** 0002" + ", баланс: " + balanceCalculator.getBalanceFrom() + " р. "));
    }

}
