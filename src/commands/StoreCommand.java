package commands;

import model.Store;

public interface StoreCommand {
    void execute(Store store);
}