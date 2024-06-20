package utils;

import java.time.LocalDateTime;

/**
 *
 */
public class CommandValidator {
    private static boolean validateItemID(int id) {
        return 0 < id;
    }
    private static boolean validateCategory(String category) {
        return category.equals("inventory") || category.equals("fragile")
                || category.equals("electronics") || category.equals("grocery");
    }

    private static  boolean validateItemQuantity(int quantity) {
        return 0 <= quantity;
    }

    private static boolean validatePrice(double price) {
        return 0.00 <= price;
    }

    private static boolean validateWeight(double weight) {
        return 0.00 <= weight;
    }

    private static boolean validateElectronicType(String type) {
        return type.equals("mouse") || type.equals("monitor") || type.equals("keyboard")
                || type.equals("laptop") || type.equals("pc") || type.equals("phone")
                || type.equals("tablet") || type.equals("speakers") || type.equals("other");
    }

    private static boolean validateExpirationDate(LocalDateTime expirationDate) {
        return expirationDate.isAfter(LocalDateTime.now());
    }


    /**
     *
     * @param commandLine
     * @return
     */
    public static boolean validate(String commandLine) {
        String[] splitCommandLine = commandLine.split(" ");
        if(splitCommandLine.length == 0) {
            return false;
        }
        String command = splitCommandLine[0];
        int argumentsCount = splitCommandLine.length - 1;
        if(command.equals("add_item")) {
            String category = splitCommandLine[1];
            int quantity = Integer.parseInt(splitCommandLine[2]);
            double price = Double.parseDouble(splitCommandLine[3]);
            if(argumentsCount == 3) {
                return validateCategory(category) && category.equals("inventory")
                        && validateItemQuantity(quantity) && validatePrice(price);
            } else if(argumentsCount == 4) {
                if(validateCategory(category) && validateItemQuantity(quantity) && validatePrice(price)) {
                    switch (category) {
                        case "fragile" -> {
                            double weight = Double.parseDouble(splitCommandLine[4]);
                            return validateWeight(weight);
                        }
                        case "grocery" -> {
                            LocalDateTime expireTill = LocalDateTime.parse(splitCommandLine[4]);
                            return validateExpirationDate(expireTill);
                        }
                        default -> {
                            return false;
                        }
                    }
                }
            } else if(argumentsCount == 5){
                double weight = Double.parseDouble(splitCommandLine[4]);
                return validateWeight(weight) && validateElectronicType(splitCommandLine[5]);
            } else {
                return false;
            }
        } else if (command.equals("remove_item")) {
            if (argumentsCount != 1) {
                return false;
            }

            int itemID = Integer.parseInt(splitCommandLine[1]);
            return validateItemID(itemID);
        } else if (command.equals("categorize_items")) {
            return true;
        } else if (command.equals("choose_payment_type")) {
            if(argumentsCount != 1) {
                return false;
            }

            String paymentType = splitCommandLine[1];
            return paymentType.equals("debit_card") || paymentType.equals("paypal_wallet");
        } else if (command.equals("display_items")) {
            return true;
        } else if (command.equals("place_order")) {
            return true;
        } else {
            return false;
        }

        return false;
    }
}