package chat;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class XMLMessageSerializer implements MessageSerializer {
    private final XmlMapper readXmlMapper = new XmlMapper();

    private final XmlMapper writeXmlMapper = new XmlMapper();
    private final Object readLock = new Object();
    private final Object writeLock = new Object();

    public Message readMessage(InputStream inputStream) throws IOException {
        synchronized (readLock) {
            return readXmlMapper.readValue(new NonCloseableInputStream(inputStream), Message.class);
        }
    }

    public void writeMessage(OutputStream outputStream, Message message) throws IOException {
        synchronized (writeLock) {
            writeXmlMapper.writeValue(new NonCloseableOutputStream(outputStream), message);
        }
    }
}
