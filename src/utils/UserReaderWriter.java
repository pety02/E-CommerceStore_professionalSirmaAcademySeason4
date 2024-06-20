package utils;

import interfaces.Readable;
import interfaces.Writeable;
import model.User;

import java.io.*;

/**
 *
 */
public class UserReaderWriter implements Readable<User>, Writeable<User> {

    /**
     *
     */
    public UserReaderWriter() {
    }

    /**
     *
     * @param filename
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public User read(String filename) throws IOException, ClassNotFoundException {
        return (User) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    /**
     *
     * @param item
     * @param filename
     * @throws IOException
     */
    @Override
    public void write(User item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}