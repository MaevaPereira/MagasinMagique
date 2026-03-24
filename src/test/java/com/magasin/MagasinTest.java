package com.magasin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagasinTest {

    private static final String GOLDEN_FILE = "src/test/resources/golden_master.txt";

    @Test
    void foo() {
        Item[] items = new Item[] {
                new Item( "Chaise", 10,20),
                new Item("Comté", 10, 20),
                new Item( "Pass VIP Concert", 15, 20),
                new Item( "Pass VIP Concert", 10, 20),
                new Item( "Pass VIP Concert", 5, 20),
                new Item( "Pass VIP Concert", 0, 20),
                new Item( "Kryptonite", 10, 80),
                new Item("Comté", 10, 50),
                new Item( "Chaise", 0,20),
                new Item( "Chaise", 10,0),
        };

        Magasin app = new Magasin(items);
        app.updateQuality();

    //ITEM NORMAL
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

}
