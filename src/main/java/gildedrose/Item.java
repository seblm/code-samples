package gildedrose;

class Item {
    private final String name;
    private Integer sellIn;
    private Integer quality;

    Item(String name, Integer sellIn, Integer quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    String getName() {
        return name;
    }

    public Integer getSellIn() {
        return sellIn;
    }

    public void setSellIn(Integer sellIn) {
        this.sellIn = sellIn;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}
