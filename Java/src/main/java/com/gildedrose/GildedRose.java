package com.gildedrose;

/**
 * Refactored version of GildedRose. Ideally, the item names and how to calc would be stored in a DB.
 */
class GildedRose {
    final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    /**
     * Update the quality if the remaining days to see is less than the sellIn date.
     * @param item item to be updated.
     * @param remainingDaysToSell remaining days to sell.
     * @param qualityCheck whether the quality check is true or false.
     * @param quality new quality value to be updated if the quality check is true.
     */
    private void updateQualityIfLessThanRemainingDaysToSell(Item item, int remainingDaysToSell, boolean qualityCheck, int quality) {
        if (item.sellIn <= remainingDaysToSell) {
            if (qualityCheck) {
                item.quality = quality;
            }
        }
    }

    /**
     * Update the sellIn minus a day.
     * @param item item to be updated.
     */
    private void updateSellInByMinusOneDay(Item item) {
        item.sellIn--;
    }

    /**
     * Refactored version of quality method.
     */
    public void updateQuality() {
        for (Item item : items) {

            if (GildedRoseConstants.AGED_BRIE.equals(item.name)) {
                if (item.quality < GildedRoseConstants.MAX_QUALITY) {
                    item.quality += GildedRoseConstants.AGED_BRIE_INCREASE;
                }
                updateSellInByMinusOneDay(item);
                updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_NONE,item.quality < GildedRoseConstants.MAX_QUALITY,item.quality + GildedRoseConstants.AGED_BRIE_INCREASE);

            } else if (GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT.equals(item.name)) {
                if (item.quality < GildedRoseConstants.MAX_QUALITY) {
                    item.quality += GildedRoseConstants.BACKSTAGE_INCREASE;
                    updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_10, item.quality < GildedRoseConstants.MAX_QUALITY, item.quality + GildedRoseConstants.BACKSTAGE_INCREASE);
                    updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_5, item.quality < GildedRoseConstants.MAX_QUALITY, item.quality + GildedRoseConstants.BACKSTAGE_INCREASE);
                }
                updateSellInByMinusOneDay(item);
                updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_NONE, true, GildedRoseConstants.MIN_QUALITY);

            } else if (GildedRoseConstants.CONJURED_MANA_CAKE.equals(item.name)) {
                if (item.quality > GildedRoseConstants.MIN_QUALITY + GildedRoseConstants.CONJURED_DECREASE - 1) {
                    item.quality -= GildedRoseConstants.CONJURED_DECREASE;
                } else {
                    item.quality = GildedRoseConstants.MIN_QUALITY;
                }
                updateSellInByMinusOneDay(item);
                updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_NONE, item.quality > GildedRoseConstants.MIN_QUALITY, item.quality - GildedRoseConstants.CONJURED_DECREASE);

            } else if (!GildedRoseConstants.SULFURAS_HAND_OF_RAGNAROS.equals(item.name)) {
                if (item.quality > GildedRoseConstants.MIN_QUALITY) {
                    item.quality -= GildedRoseConstants.NORMAL_DECREASE;
                }
                updateSellInByMinusOneDay(item);
                updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_NONE, item.quality > GildedRoseConstants.MIN_QUALITY, item.quality - GildedRoseConstants.NORMAL_DECREASE);
            }
        }
    }
}