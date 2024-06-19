import java.io.IOException;

public interface Writeable {
    void write(AbstractItem item, String filename) throws IOException;
}