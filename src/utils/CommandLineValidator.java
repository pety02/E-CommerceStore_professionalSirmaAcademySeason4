package utils;

import java.time.LocalDateTime;

/**
 * Command line validator class
 */
public class CommandLineValidator {
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
     * Validates the command line.
     * @param commandLine the given command line
     * @return true if the command line is fully valid and false in all other cases.
     */
    public static boolean validate(String commandLine) {
        // Splits the command line to command and its arguments.
        // First String always is the command name.
        String[] splitCommandLine = commandLine.split(" ");
        if(splitCommandLine.length == 0) {
            return false;
        }
        String command = splitCommandLine[0];
        // Arguments count always id equal to splitCommandLine.length - 1
        int argumentsCount = splitCommandLine.length - 1;

        // Continue with validating the command names and the arguments count in the different cases.
        switch (command) {
            case "add_item" -> {
                String category = splitCommandLine[1];
                int quantity; double price;
                try {
                    quantity = Integer.parseInt(splitCommandLine[2]);
                    price = Double.parseDouble(splitCommandLine[3]);
                } catch (NumberFormatException ex) {
                    ex.fillInStackTrace();
                    return false;
                }
                if (argumentsCount == 3) {
                    return validateCategory(category) && category.equals("inventory")
                            && validateItemQuantity(quantity) && validatePrice(price);
                } else if (argumentsCount == 4) {
                    if (validateCategory(category) && validateItemQuantity(quantity) && validatePrice(price)) {
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
                    } else {
                        return false;
                    }
                } else if (argumentsCount == 5) {
                    double weight;
                    try {
                        weight = Double.parseDouble(splitCommandLine[4]);
                    } catch (NumberFormatException ex) {
                        ex.fillInStackTrace();
                        return false;
                    }
                    return validateWeight(weight) && validateElectronicType(splitCommandLine[5]);
                } else {
                    return false;
                }
            }
            case "remove_item" -> {
                if (argumentsCount != 1) {
                    return false;
                }
                try {
                    int itemID = Integer.parseInt(splitCommandLine[1]);
                    return validateItemID(itemID);
                } catch (NumberFormatException ex) {
                    ex.fillInStackTrace();
                    return false;
                }
            }
            case "categorize_items", "display_items", "place_order" -> {
                return true;
            }
            case "choose_payment_type" -> {
                if (argumentsCount != 1) {
                    return false;
                }
                String paymentType = splitCommandLine[1];
                return paymentType.equals("debit_card") || paymentType.equals("paypal");
            }
            default -> {
                return false;
            }
        }
    }
}