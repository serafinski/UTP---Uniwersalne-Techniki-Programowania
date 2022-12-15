package zad1;

/**
 * It's a class that represents a commodity
 */
public class Commodity {

    // A private variable that is used to store the ID of the commodity.
    private final int commodity_id;

    // A private variable that is used to store the weight of the commodity.
    private final double weight;

    // A constructor.
    public Commodity(int commodity_id, double weight) {
        this.commodity_id = commodity_id;
        this.weight = weight;
    }

    /**
     * This function returns the ID of the commodity
     *
     * @return The commodity_id is being returned.
     */
    public int getCommodity_id() {
        return commodity_id;
    }

    /**
     * This function returns the weight of the commodity.
     *
     * @return The weight of the commodity.
     */
    public double getWeight() {
        return weight;
    }
}
