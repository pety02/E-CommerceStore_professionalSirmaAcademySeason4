import java.io.IOException;

public interface Readable {
    AbstractItem read(String filename) throws IOException, ClassNotFoundException;
}
