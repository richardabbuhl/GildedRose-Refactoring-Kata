package com.gildedrose;



import static org.junit.Assert.*;

import org.junit.Test;

public class ItemTest {

    @Test
    public void testToString() {
        Item item = new Item("foo", 0, 0);
        assertEquals("foo, 0, 0", item.toString());
    }

}
