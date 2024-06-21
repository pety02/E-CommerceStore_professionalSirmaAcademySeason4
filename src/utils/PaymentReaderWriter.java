package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.PaymentProcessor;

import java.io.*;

/**
 * Payment reader writer class
 */
public class PaymentReaderWriter implements Readable<PaymentProcessor>, Writeable<PaymentProcessor> {

    /**
     * Constructs the payment reader writer object by default.
     */
    public PaymentReaderWriter() {
    }

    /**
     * Reads from a file with a definite name.
     * @param filename the given filename.
     * @return the read object.
     * @throws IOException when cannot read from the file.
     * @throws ClassNotFoundException  when cannot parse to object of this class.
     */
    @Override
    public PaymentProcessor read(String filename) throws IOException, ClassNotFoundException {
        return (PaymentProcessor) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     * Writes the item in a file with a definite name.
     * @param item the given item
     * @param filename the given filename
     * @throws IOException when cannot write in a file.
     */
    @Override
    public void write(PaymentProcessor item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}