package commands;

import model.PaymentType;

public interface PaymentTypeCommand {
    PaymentType execute(String type);
}