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
     * Refactored version of quality method.
     */
    public void updateQuality() {
        for (Item item : items) {
            if (isAgedBrie(item)) {
                updateAgedBrie(item);

            } else if (isBackstagePasses(item)) {
                updateBackstagePasses(item);

            } else if (isConjuredMana(item)) {
                updatedConjuredMana(item);

            } else if (SulfurasHand(item)) {
                break; // This is dead code; why even check for sulfuras hand.

            } else {
                updateNormal(item);
            }
        }
    }

    /**
     * Determine if an item is aged brief.
     * @param item item to be checked.
     * @return return true if aged brie; false otherwise.
     */
    private boolean isAgedBrie(Item item) {
        return GildedRoseConstants.AGED_BRIE.equals(item.name);
    }

    /**
     * Determine if an item is sulfuras hand.
     * @param item item to be checked.
     * @return return true if sulfuras hand; false otherwise.
     */
    private boolean SulfurasHand(Item item) {
        return GildedRoseConstants.SULFURAS_HAND_OF_RAGNAROS.equals(item.name);
    }

    /**
     * Determine if an item is aged conjured mana.
     * @param item item to be checked.
     * @return return true if aged conjured mana; false otherwise.
     */
    private boolean isConjuredMana(Item item) {
        return GildedRoseConstants.CONJURED_MANA_CAKE.equals(item.name);
    }

    /**
     * Determine if an item is backstage passes.
     * @param item item to be checked.
     * @return return true if backstage passes; false otherwise.
     */
    private boolean isBackstagePasses(Item item) {
        return GildedRoseConstants.BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT.equals(item.name);
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
     * Update aged brie. That requires increasing the quality by AGED_BRIE_INCREASE, decreasing the sellIn by 1,
     * and then increasing the quality by AGED_BRIE_INCREASE  again if it is more than max quality.
     * @param item item to be updated.
     */
    private void updateAgedBrie(Item item) {
        if (item.quality < GildedRoseConstants.MAX_QUALITY) {
            item.quality += GildedRoseConstants.AGED_BRIE_INCREASE;
        }
        item.sellIn--;
        updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_NONE,item.quality < GildedRoseConstants.MAX_QUALITY,item.quality + GildedRoseConstants.AGED_BRIE_INCREASE);
    }

    /**
     * Update backstage passes. That requires increasing the quality by BACKSTAGE_INCREASE. If it is  10 days or less
     * then the quality is decreased by BACKSTAGE_INCREASE, and increased again if  it 5 days or less than it is the
     * quality is decreased by BACKSTAGE_INCREASE. It it is expired the quality is set to 0.
     * @param item item to be updated.
     */
    private void updateBackstagePasses(Item item) {
        if (item.quality < GildedRoseConstants.MAX_QUALITY) {
            item.quality += GildedRoseConstants.BACKSTAGE_INCREASE;
            updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_10, item.quality < GildedRoseConstants.MAX_QUALITY, item.quality + GildedRoseConstants.BACKSTAGE_INCREASE);
            updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_5, item.quality < GildedRoseConstants.MAX_QUALITY, item.quality + GildedRoseConstants.BACKSTAGE_INCREASE);
        }
        item.sellIn--;
        updateQualityIfLessThanRemainingDaysToSell(item, GildedRoseConstants.REMAINING_DAYS_NONE, true, GildedRoseConstants.MIN_QUALITY);
    }

    /**
     * Update conjured mana. That requires decreasing the quality by CONJURED_DECREASE, decreasing the sellIn by 1,
     * and then decreasing the quality by CONJURED_DECREASE again if it is more than zero.
     * @param item item to be updated.
     */
    private void updatedConjuredMana(Item item) {
        if (item.quality > GildedRoseConstants.MIN_QUALITY + GildedRoseConstants.CONJURED_DECREASE - 1) {
            item.quality -= GildedRoseConstants.CONJURED_DECREASE;
        } else {
            item.quality = GildedRoseConstants.MIN_QUALITY;
        }
        item.sellIn--;
        updateQualityIfLessThanRemainingDaysToSell(
                item,
                GildedRoseConstants.REMAINING_DAYS_NONE,
                item.quality > GildedRoseConstants.MIN_QUALITY,
                item.quality - GildedRoseConstants.CONJURED_DECREASE);
    }

    /**
     * Update normal items. That requires decreasing the quality by NORMAL_DECREASE, decreasing the sellIn by 1,
     * and then decreasing the quality by NORMAL_DECREASE again if it is more than zero.
     * @param item item to be updated.
     */
    private void updateNormal(Item item) {
        if (item.quality > GildedRoseConstants.MIN_QUALITY) {
            item.quality -= GildedRoseConstants.NORMAL_DECREASE;
        }
        item.sellIn--;
        updateQualityIfLessThanRemainingDaysToSell(item,
                GildedRoseConstants.REMAINING_DAYS_NONE,
                item.quality > GildedRoseConstants.MIN_QUALITY,
                item.quality - GildedRoseConstants.NORMAL_DECREASE);
    }

}