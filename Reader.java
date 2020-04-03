import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader {
    //All final attributes
    private final String fileName; // required
    private final int initialWaste; // optional
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();
    private final XMLStreamReader reader;

    private final int sum = 0;


    private Reader(UserBuilder builder) throws IOException, XMLStreamException {
        this.fileName = builder.fileName;
        this.initialWaste = builder.initialWaste;
        reader = FACTORY.createXMLStreamReader(Files.newInputStream(Paths.get(this.fileName)));
    }

    //All getter, and NO setter to provde immutability
    public String getFileName() {
        return fileName;
    }

    public int getInitialWaste() {
        return initialWaste;
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    @Override
    public String toString() {
        return "User: " + this.fileName + ", " + this.initialWaste;
    }

    public static class UserBuilder implements AutoCloseable {
        private final String fileName;
        private int initialWaste;
        private int sum = 0;
        private int amount = 0;
        XMLStreamReader reader;

        public UserBuilder(String fileName) throws IOException, XMLStreamException {
            this.fileName = fileName;
            this.reader = FACTORY.createXMLStreamReader(Files.newInputStream(Paths.get(this.fileName)));
        }

        public UserBuilder intialWaste(int initialWaste) {
            this.initialWaste = initialWaste;
            return this;
        }

        //Return the finally constructed User object
        public Reader build() throws IOException, XMLStreamException {
            Reader user = new Reader(this);
            validateUserObject(user);
            read();
            sum();
            return user;
        }

        private int sum() {
            System.out.println("Sum of " + amount + " tours cost = " + sum + " + intialWaste = " + initialWaste + " is " + (sum + initialWaste));
            return sum;
        }

        private void read() throws XMLStreamException {
            sum = 0;

            while (reader.hasNext()) {       // while not end of XML
                int event = reader.next();   // read next event
                if (event == XMLEvent.START_ELEMENT &&
                        "cost".equals(reader.getLocalName())) {
                    sum += Integer.parseInt(reader.getElementText());
                    amount++;
                }
            }
        }

        private void validateUserObject(Reader user) {

        }

        @Override
        public void close() {
            if (reader != null) {
                try {
                    reader.close();
                } catch (XMLStreamException e) { // empty
                }
            }
        }


    }

}