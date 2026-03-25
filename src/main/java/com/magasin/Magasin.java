package com.magasin;

class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (!item.name.equals("Comté") && !item.name.equals("Pass VIP Concert")) {
                if (item.quality > 0) {
                    if (!item.name.equals("Kryptonite")) {
                        item.quality = item.quality - 1;
                    }
                    if  (item.name.equals("Pouvoirs magiques")) {
                        item.quality = item.quality - 1;
                    }
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;

                    if (item.name.equals("Pass VIP Concert")) {
                        if (item.sellIn < 11) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }

                        if (item.sellIn < 6) {
                            if (item.quality < 50) {
                                item.quality = item.quality + 1;
                            }
                        }
                    }
                }
            }

            if (!item.name.equals("Kryptonite")) {
                item.sellIn = item.sellIn - 1;
            }

            if (item.sellIn < 0) {
                if (!item.name.equals("Comté")) {
                    if (!item.name.equals("Pass VIP Concert")) {
                        if (item.quality > 0) {
                            if (!item.name.equals("Kryptonite")) {
                                item.quality = item.quality - 1;
                            }
                            if (item.name.equals("Pouvoirs magiques")) {
                                item.quality = item.quality - 1;
                            }
                        }
                    } else {
                        item.quality = 0;
                    }
                } else {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }
}
