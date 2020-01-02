public class DataHelper {
    private DataHelper() {
    }

    public static class AuthInfo {
        public String login;
        public String password;

        public AuthInfo(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static class VerificationCode {
        public String code;

        public VerificationCode(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static class NumberCard {
        public String cardNumber;

        public NumberCard(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getCardNumber() {
            return cardNumber;
        }
    }

    public static NumberCard getFirstCardNumber() {
        return new NumberCard("5559 0000 0000 0001");
    }

    public static NumberCard getSecondCardNumber() {
        return new NumberCard("5559 0000 0000 0002");
    }

    public static class Amount {
        public static String amount;

        public Amount(String amount) {
            this.amount = amount;
        }

        public static String getAmount(String amount) {
            return amount;
        }
    }
}


    



