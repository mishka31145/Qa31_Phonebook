package manager;

import models.Contact;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderContacts {

    @DataProvider
    public Iterator<Object[]> example() {
        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactSuccess() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("Tony")
                .lastName("Molly")
                .email("tony@gmail.com")
                .phone("343434343434")
                .address("Haifa")
                .description("all fields")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("Rita")
                .lastName("Dolly")
                .email("rita@gmail.com")
                .phone("569843265412")
                .address("Haifa")
                .build()});

        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> contactWrongPhone() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .email("john@gmail.com")
                .phone("343")
                .address("Haifa")
                .description("all fields")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .email("john@gmail.com")
                .phone("34345612321564565132135465463213")
                .address("Haifa")
                .description("all fields")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .email("john@gmail.com")
                .phone("wwwwwwwwwwww")
                .address("Haifa")
                .description("all fields")
                .build()});
        list.add(new Object[]{Contact.builder()
                .name("John")
                .lastName("Wick")
                .email("john@gmail.com")
                .phone("")
                .address("Haifa")
                .description("all fields")
                .build()});

        return list.iterator();
    }


    @DataProvider
    public Iterator<Object[]> contactCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contact.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] all = line.split(",");
            list.add(new Object[]{Contact.builder()
                    .name(all[0])
                    .lastName(all[1])
                    .email(all[2])
                    .phone(all[3])
                    .address(all[4])
                    .description(all[5])
                    .build()});
            line = reader.readLine();
        }

        return list.iterator();
    }

}
