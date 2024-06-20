package utils;

import commands.*;
import model.*;

import java.time.LocalDateTime;

/**
 *
 */
public class CommandLineParser {
    private String command;
    private ParentCommand parsedCommand;

    private InventoryItem item;

    /**
     *
     */
    public CommandLineParser() {
        this.command = null;
        this.parsedCommand = null;
    }

    /**
     *
     * @param commandLine
     * @throws IllegalArgumentException
     */
    public void parseCommand(String commandLine) throws IllegalArgumentException {
        if(!CommandValidator.validate(commandLine)) {
            throw new IllegalArgumentException("Invalid command line!");
        }

        String[] splitCommandLine = commandLine.split(" ");
        String parsedCommand = splitCommandLine[0];
        this.command = parsedCommand;
        int argumentsCount = splitCommandLine.length - 1;
        if(parsedCommand.equals("add_item")) {
            String category = splitCommandLine[1];
            int quantity = Integer.parseInt(splitCommandLine[2]);
            double price = Double.parseDouble(splitCommandLine[3]);
            if(argumentsCount == 3) {
                this.item = new InventoryItem(quantity, price);
                this.parsedCommand = new AddItemCommand(this.item, quantity);
            } else {
                switch (category) {
                    case "fragile" -> {
                        double weight = Double.parseDouble(splitCommandLine[4]);
                        FragileItem item = new FragileItem(quantity, price, weight);
                        this.parsedCommand = new AddItemCommand(item, quantity);
                    }
                    case "electronics" -> {
                        double weight = Double.parseDouble(splitCommandLine[4]);
                        ElectronicsType type = switch (splitCommandLine[5]) {
                            case "mouse" -> ElectronicsType.MOUSE;
                            case "pc" -> ElectronicsType.PC;
                            case "monitor" -> ElectronicsType.MONITOR;
                            case "laptop" -> ElectronicsType.LAPTOP;
                            case "keyboard" -> ElectronicsType.KEYBOARD;
                            case "phone" -> ElectronicsType.PHONE;
                            case "speakers" -> ElectronicsType.SPEAKERS;
                            case "tablet" -> ElectronicsType.TABLET;
                            default -> ElectronicsType.OTHER;
                        };
                        this.item = new ElectronicsItem(quantity, price, weight, type);
                        this.parsedCommand = new AddItemCommand(this.item, quantity);
                    }
                    case "grocery" -> {
                        LocalDateTime expireTill = LocalDateTime.parse(splitCommandLine[4]);
                        this.item = new GroceryItem(quantity, price, expireTill);
                        this.parsedCommand = new AddItemCommand(this.item, quantity);
                    }
                    default -> {
                        this.item = new InventoryItem(quantity, price);
                        this.parsedCommand = new AddItemCommand(this.item, quantity);
                    }
                }
            }
        } else if (parsedCommand.equals("display_items")) {
            this.parsedCommand = new DisplayItemsCommand();
            this.item = null;
        } else if (parsedCommand.equals("remove_item")) {
            int itemID = Integer.parseInt(splitCommandLine[1]);
            this.parsedCommand = new RemoveItemCommand(itemID,1);
            this.item = null;
        } else if (parsedCommand.equals("categorize_items")) {
            this.parsedCommand = new CategorizeItemsCommand();
            this.item = null;
        } else if (parsedCommand.equals("place_order")) {
            this.parsedCommand = new PlaceOrderCommand();
            this.item = null;
        } else if(parsedCommand.equals("choose_payment")) {
            this.parsedCommand = new ChoosePaymentTypeCommand();
            this.item = null;
        } else {
            this.parsedCommand = null;
            this.item = null;
        }
     }

    public String getCommand() {
        return command;
    }

    public ParentCommand getParsedCommand() {
        return parsedCommand;
    }
}