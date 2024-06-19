import java.io.*;

public class InventoryReaderWriter implements Writeable, Readable {

    public InventoryReaderWriter() {

    }

    @Override
    public AbstractItem read(String filename) throws IOException, ClassNotFoundException {
        return (AbstractItem) new ObjectInputStream(new FileInputStream(filename)).readObject();
    }

    @Override
    public void write(AbstractItem item, String filename) throws IOException {
        new ObjectOutputStream(new FileOutputStream(filename)).writeObject(item);
    }
}