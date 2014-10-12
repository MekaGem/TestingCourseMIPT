package com.github.mekagem.testing.wikiregistration.data;

public final class RegistrationTestData extends AbstractTestData {
    private String login;
    private String password;
    private String passwordConfirmation;
    private String email;
    private String expectedResult;
    // There is no captcha. Because I can not guarantee the correctness of test result.

    public RegistrationTestData(String login, String password, String passwordConfirmation, String email, String expectedResult) {
        this.login = login;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.email = email;
        this.expectedResult = expectedResult;
    }

    @Override
    public String toString() {
        return new StringBuilder("RegistrationTestData: {")
        .append("login: ").append(login)
        .append(", password: ").append(password)
        .append(", passwordConfirmation: ").append(passwordConfirmation)
        .append(", email: ").append(email)
        .append(", expectedResult: ").append(expectedResult)
        .append("}").toString();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }
}
