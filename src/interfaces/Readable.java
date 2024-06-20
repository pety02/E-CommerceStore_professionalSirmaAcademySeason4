package interfaces;


import java.io.IOException;

public interface Readable<T> {
    T read(String filename) throws IOException, ClassNotFoundException;
}
