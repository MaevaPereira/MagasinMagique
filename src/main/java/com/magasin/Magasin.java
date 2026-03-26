package com.magasin;

class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        switch (item.name) {
            case "Kryptonite":
                break;
            case "Comté":
                updateComte(item);
                break;
            case "Pass VIP Concert":
                updatePass(item);
                break;
            case "Pouvoirs magiques":
                updatePouvoirsMagiques(item);
                break;
            default:
                updateStandardItem(item);
                break;
        }
        if (!item.name.equals("Kryptonite")) {
            item.sellIn--;
        }
    }

    private void updateComte(Item item) {
        if (item.quality < 50) item.quality++;
        if (item.sellIn <= 0 && item.quality < 50) item.quality++;
    }

    private void updatePass(Item item) {
        if (item.sellIn <= 0) {
            item.quality = 0;
        } else {
            item.quality++;
            if (item.sellIn <= 10 && item.quality < 50) item.quality++;
            if (item.sellIn <= 5 && item.quality < 50) item.quality++;
            item.quality = Math.min(item.quality, 50);
        }
    }

    private void updatePouvoirsMagiques(Item item) {
        if (item.quality > 0) item.quality -= 2;
        if (item.sellIn <= 0 && item.quality > 0) item.quality -= 2;
    }

    private void updateStandardItem(Item item) {
        if (item.quality > 0) item.quality--;
        if (item.sellIn <= 0 && item.quality > 0) item.quality--;
    }
}