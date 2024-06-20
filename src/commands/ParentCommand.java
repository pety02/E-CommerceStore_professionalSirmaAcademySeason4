package commands;

import utils.CommandValidator;

/**
 *
 */
public class ParentCommand {
    private String command;
    private CommandType type;

    /**
     *
     * @param command
     */
    public ParentCommand(String command) {
        try {
            this.setCommand(command);
            this.type = switch (command) {
                case "add_item" -> CommandType.ADD_ITEM;
                case "remove_item" -> CommandType.REMOVE_ITEM;
                case "display_items" -> CommandType.DISPLAY_ITEMS;
                case "categorize_items" -> CommandType.CATEGORIZE_ITEMS;
                case "place_order" -> CommandType.PLACE_ORDER;
                case "choose_payment_type" -> CommandType.CHOOSE_PAYMENT_TYPE;
                default -> CommandType.UNKNOWN;
            };
        } catch (IllegalArgumentException ex) {
            ex.fillInStackTrace();
            this.command = null;
        }
    }

    /**
     *
     * @return
     */
    public String getCommand() {
        return command;
    }

    /**
     *
     * @param command
     * @throws IllegalArgumentException
     */
    public void setCommand(String command) throws IllegalArgumentException {
        if(CommandValidator.validate(command)) {
            this.command = command;
        } else {
            throw new IllegalArgumentException("Invalid command!");
        }
    }

    public CommandType getType() {
        return type;
    }
}