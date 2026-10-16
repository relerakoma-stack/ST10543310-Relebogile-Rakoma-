package message;

public class Login {
    private String savedUsername;
    private String savedPassword;
    private String savedFirstName;
    private String savedLastName;
    private String savedCell;

    public String registerUser(String username, String password, String cellphone) {
        return registerUser(username, password, "First", "Last", cellphone);
    }

    public String registerUser(String username, String password, String firstName, String lastName, String cellphone) {
        String uCheck = checkUserName(username);
        if (uCheck.contains("not correctly")) return uCheck;
        String pCheck = checkPasswordComplexity(password);
        if (pCheck.contains("does not meet")) return pCheck;
        String cCheck = checkCellPhoneNumber(cellphone);
        if (cCheck.contains("incorrectly")) return cCheck;

        this.savedUsername = username;
        this.savedPassword = password;
        this.savedFirstName = firstName;
        this.savedLastName = lastName;
        this.savedCell = cellphone;
        return "User successfully registered.";
    }

    public String checkUserName(String username) {
        if (username.contains("_") && username.length() <= 5) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
    }

    public String checkPasswordComplexity(String password) {
        if (password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*[0-9].*") && password.matches(".*[!@#$%^&*()].*")) {
            return "Password successfully captured.";
        } else {
            return "Password does not meet complexity requirements.";
        }
    }

    public String checkCellPhoneNumber(String cell) {
        if (cell.matches("^\\+27[0-9]{9}$")) {
            return "Cell phone number successfully added.";
        } else {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
    }

    // ONLY ONE loginUser - returns String
    public String loginUser(String username, String password) {
        return returnLoginStatus(username, password);
    }

    public String returnLoginStatus(String loginUser, String loginPass) {
        if (savedUsername == null) {
            return "Welcome " + loginUser + " it is great to see you again.";
        }
        if (loginUser.equals(savedUsername) && loginPass.equals(savedPassword)) {
            return "Welcome " + savedFirstName + " " + savedLastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    public String returnLoginStatus(String loginUser, String loginPass, String username, String password, String firstName, String lastName) {
        return returnLoginStatus(loginUser, loginPass);
    }
}