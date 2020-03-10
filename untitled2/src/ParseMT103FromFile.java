import com.prowidesoftware.swift.io.RJEWriter;
import com.prowidesoftware.swift.model.field.*;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;
import createBlocks.Block1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;

/**
 *
 */
public class ParseMT103FromFile {

    public static void main(String[] args) throws IOException {

        Block1 block1 = new Block1();

        final MT103 m = new MT103();

        /*
         * Set sender and receiver BIC codes
         */
            m.setSender(block1.getGoodSender());
            m.setReceiver(block1.getGoodSender());



        /*
            SET BLOCK 4
         */
            /*
            * Start adding the message's fields in correct order
            */
            m.addField(new Field20("REFERENCE"));
            m.addField(new Field23B("CRED"));

        /*
         * Add a field using comprehensive setters API
         */
        Field32A f32A = new Field32A()
                .setDate(Calendar.getInstance())
                .setCurrency("EUR")
                .setAmount("1234567,89");
        m.addField(f32A);

        /*
         * Add the orderer field
         */
        Field50A f50A = new Field50A()
                .setAccount("12345678901234567890")
                .setBIC("FOOBANKXXXXX");
        m.addField(f50A);

        /*
         * Add the beneficiary field
         */
        Field59 f59 = new Field59()
                .setAccount("12345678901234567890")
                .setNameAndAddress("JOE DOE");
        m.addField(f59);

        /*
         * Add the commission indication
         */
        m.addField(new Field71A("OUR"));

        /*
         * Create and print out the SWIFT FIN message string
         */
        RJEWriter writer = new RJEWriter("/tmp/test.rje");
        System.out.println(m.message());
    }
}