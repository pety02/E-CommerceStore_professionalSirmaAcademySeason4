package commands;

import utils.CommandValidator;

/**
 * Parent command class
 */
public class ParentCommand {
    private String command;
    private CommandType type;

    /**
     * Constructs parent command object as the default command - display_items.
     */
    public ParentCommand() {
        this.setCommand("display_items");
        this.setType(CommandType.DISPLAY_ITEMS);
    }

    /**
     * Constructs parent command object by given command name.
     * @param command the given command name
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
     * Command Getter
     * @return the command name as string.
     */
    public String getCommand() {
        return command;
    }

    /**
     * Sets the given command name as name of the current command.
     * @param command the given command name
     * @throws IllegalArgumentException when the command name is invalid.
     */
    public void setCommand(String command) throws IllegalArgumentException {
        if(command.equals("add_item") || command.equals("place_order") || command.equals("display_items")
        || command.equals("remove_item") || command.equals("categorize_items") || command.equals("choose_payment_type")) {
            this.command = command;
        } else {
            throw new IllegalArgumentException("Invalid command!");
        }
    }

    /**
     * Command type Getter
     * @return the command type.
     */
    public CommandType getType() {
        return type;
    }

    /**
     * Sets the given command type as type of the current command.
     * @param type the given command type
     */
    public void setType(CommandType type) {
        this.type = type;
    }
}