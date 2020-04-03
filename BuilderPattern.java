import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BuilderPattern {

    public static void main(String args[]) throws IOException, XMLStreamException {

        //Creating object using Builder pattern in java

        Reader reader1 = new Reader.UserBuilder("src/Tour.xml")
                .intialWaste(30)
                .build();

        System.out.println(reader1);
        System.out.println();
        Reader reader2 = new Reader.UserBuilder("src/Tour.xml")
                .intialWaste(40)
                .build();

        System.out.println(reader2);
        System.out.println();

        Reader reader3 = new Reader.UserBuilder("src/Tour.xml")
                //No initialWaste
                .build();

        System.out.println(reader3);
    }


    }
