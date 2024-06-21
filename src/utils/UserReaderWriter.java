package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.User;

import java.io.*;

/**
 * User reader writer class
 */
public class UserReaderWriter implements Readable<User>, Writeable<User> {

    /**
     * Constructs the user reader writer object by default.
     */
    public UserReaderWriter() {
    }

    /**
     * Reads from a file with a definite name.
     * @param filename the given filename.
     * @return the read object.
     * @throws IOException when cannot read from the file.
     * @throws ClassNotFoundException  when cannot parse to object of this class.
     */
    @Override
    public User read(String filename) throws IOException, ClassNotFoundException {
        return (User) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     * Writes the item in a file with a definite name.
     * @param item the given item
     * @param filename the given filename
     * @throws IOException when cannot write in a file.
     */
    @Override
    public void write(User item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}