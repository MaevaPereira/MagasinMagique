package com.magasin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MagasinTest {

    private static final String GOLDEN_FILE = "src/test/resources/golden_master.txt";

    // Appelle cette méthode UNE SEULE FOIS pour créer le fichier de référence
    void generateGoldenMaster() throws IOException {
        Item[] items = buildItems();
        Magasin app = new Magasin(items);
        app.updateQuality();

        StringBuilder sb = new StringBuilder();
        for (Item item : app.items) {
            sb.append(item.name)
                    .append(" | sellIn=").append(item.sellIn)
                    .append(" | quality=").append(item.quality)
                    .append("\n");
        }

        Files.createDirectories(Paths.get("src/test/resources"));
        Files.writeString(Paths.get(GOLDEN_FILE), sb.toString());
        System.out.println("Golden master généré !");
    }

    // Le vrai test : compare la sortie actuelle au fichier de référence
    @Test
    void goldenMasterTest() throws IOException {
        Item[] items = buildItems();
        Magasin app = new Magasin(items);
        app.updateQuality();

        // Construire la sortie actuelle
        StringBuilder actual = new StringBuilder();
        for (Item item : app.items) {
            actual.append(item.name)
                    .append(" | sellIn=").append(item.sellIn)
                    .append(" | quality=").append(item.quality)
                    .append("\n");
        }

        // Lire le golden master
        String expected = Files.readString(Paths.get(GOLDEN_FILE));

        assertEquals(expected, actual.toString(),
                "Le comportement a changé par rapport au golden master !");
    }


    private Item[] buildItems() {
        return new Item[] {
                new Item("Chaise", 10, 20),
                new Item("Comté", 10, 20),
                new Item("Pass VIP Concert", 15, 20),
                new Item("Pass VIP Concert", 10, 20),
                new Item("Pass VIP Concert", 5, 20),
                new Item("Pass VIP Concert", 0, 20),
                new Item("Kryptonite", 10, 80),
                new Item("Comté", 10, 50),
                new Item("Chaise", 0, 20),
                new Item("Chaise", 10, 0),
        };

    }

    @Test
    void updateQualityTest() {
        Item[] items = buildItems();
        Magasin app = new Magasin(items);
        app.updateQuality();

    // ITEM NORMAL
        assertEquals(19,app.items[0].quality);
        assertEquals(18,app.items[8].quality);
        assertEquals(0,app.items[9].quality);
    //ITEM COMTÉ
        assertEquals(21,app.items[1].quality);
        assertEquals(50,app.items[7].quality);
    //ITEM PASS VIP
        assertEquals(21,app.items[2].quality);
        assertEquals(22,app.items[3].quality);
        assertEquals(23,app.items[4].quality);
        assertEquals(0,app.items[5].quality);
    //ITEM KRYPTONITE
        assertEquals(80,app.items[6].quality);
    }

    @Test
    void testPouvoirsMagiques() {
        Item[] items = new Item[] {
                new Item( "Pouvoirs magiques", 0,20),
                new Item( "Pouvoirs magiques", 10,20),
        };

        Magasin app = new Magasin(items);
        app.updateQuality();

        assertEquals(16,app.items[0].quality);
        assertEquals(18,app.items[1].quality);
    }

}
