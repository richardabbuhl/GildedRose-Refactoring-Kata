package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

        app.updateQuality();
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

        app.updateQuality();
        assertEquals(-3, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void testAgedBrieHighQuality() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.AGED_BRIE, 10, 48) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.AGED_BRIE, app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);

        app.updateQuality();
        assertEquals(8, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        app.updateQuality();
        assertEquals(7, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void testAgedBrieExpired() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.AGED_BRIE, 2, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.AGED_BRIE, app.items[0].name);
        assertEquals(1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);

        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);

        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }

    @Test
    public void testDexterityNormal() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.PLUS_5_DEXTERITY_VEST, 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.PLUS_5_DEXTERITY_VEST, app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);

        app.updateQuality();
        assertEquals(8, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);

        app.updateQuality();
        assertEquals(7, app.items[0].sellIn);
        assertEquals(17, app.items[0].quality);
    }

    @Test
    public void testDexterityExpired() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.PLUS_5_DEXTERITY_VEST, 2, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.PLUS_5_DEXTERITY_VEST, app.items[0].name);
        assertEquals(1, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);

        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);

        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(16, app.items[0].quality);
    }

    @Test
    public void testElixir() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.ELIXIR_OF_THE_MONGOOSE, 5, 7) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.ELIXIR_OF_THE_MONGOOSE, app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);

        app.updateQuality();
        assertEquals(3, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);

        app.updateQuality();
        assertEquals(2, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }

    @Test
    public void testSulfurasNormal() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.SULFURAS_HAND_OF_RAGNAROS, 0, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.SULFURAS_HAND_OF_RAGNAROS, app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);

        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    public void testSulfurasExpired() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.SULFURAS_HAND_OF_RAGNAROS, -1, 80) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.SULFURAS_HAND_OF_RAGNAROS, app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);

        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    public void testBackstageNormal() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 15, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, app.items[0].name);
        assertEquals(14, app.items[0].sellIn);
        assertEquals(21, app.items[0].quality);

        app.updateQuality();
        assertEquals(13, app.items[0].sellIn);
        assertEquals(22, app.items[0].quality);
    }

    @Test
    public void testBackstage10DaysOrLess() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 11, 22) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, app.items[0].name);
        assertEquals(10, app.items[0].sellIn);
        assertEquals(23, app.items[0].quality);

        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(25, app.items[0].quality);
    }

    @Test
    public void testBackstage10DaysOrLessHighQuality() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 11, 48) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, app.items[0].name);
        assertEquals(10, app.items[0].sellIn);
        assertEquals(49, app.items[0].quality);

        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        app.updateQuality();
        assertEquals(8, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void testBackstage5DaysOrLess() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 6, 22) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, app.items[0].name);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(24, app.items[0].quality);

        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(27, app.items[0].quality);
    }

    @Test
    public void testBackstage5DaysOrLessHighQuality() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, 6, 48) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT, app.items[0].name);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    public void testConjuredNormal() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.CONJURED_MANA_CAKE, 10, 20) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.CONJURED_MANA_CAKE, app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(18, app.items[0].quality);

        app.updateQuality();
        assertEquals(8, app.items[0].sellIn);
        assertEquals(16, app.items[0].quality);

        app.updateQuality();
        assertEquals(7, app.items[0].sellIn);
        assertEquals(14, app.items[0].quality);

        app.updateQuality();
        assertEquals(6, app.items[0].sellIn);
        assertEquals(12, app.items[0].quality);
    }

    @Test
    public void testConjuredEdgeCase() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.CONJURED_MANA_CAKE, 10, 1) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.CONJURED_MANA_CAKE, app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);

        app.updateQuality();
        assertEquals(8, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void testConjuredExpired() {
        Item[] items = new Item[] { new Item(GildedRoseConstants.CONJURED_MANA_CAKE, 3, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();
        assertEquals(GildedRoseConstants.CONJURED_MANA_CAKE, app.items[0].name);
        assertEquals(2, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);

        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);

        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);

        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }
}
