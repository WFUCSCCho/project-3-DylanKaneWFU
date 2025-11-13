/************************************************************************
 * @file DataObj.java
 * @brief This program implements java to create a Data Object Class to hold caffeine.csv records
 * @author Dylan Kane
 * @date November 13, 2025
 *************************************************************************/


public class DataObj implements Comparable<DataObj> {
    String name;
    Double volume;
    int calories;
    Integer caffeine;
    String type;

    public DataObj() {
        //default constructor
        name = "None";
        volume = -1.0;
        calories = -1;
        caffeine = -1;
        type = "None";
    }

    public DataObj(String name, double volume, int calories, int caffeine, String type) {
        //fully parameterized constructor
        this.name = name;
        this.volume = volume;
        this.calories = calories;
        this.caffeine = caffeine;
        this.type = type;
    }

    public DataObj(DataObj original) {
        //copy constructor
        this.name = original.name();
        this.volume = original.volume();
        this.calories = original.calories();
        this.caffeine = original.caffeine();
        this.type = original.type();
    }

    //get methods
    public int caffeine() { return this.caffeine; }

    public double volume() { return this.volume; }

    public int calories() { return this.calories; }

    public String type() { return this.type; }

    public String name() { return this.name; }

    @Override
    public boolean equals(Object o) {
        //returns true if identical to another data object
        //first checks if they are of the same class
        if (o.getClass() == this.getClass()) return checkValues((DataObj) o);
        else return false;
    }

    private boolean checkValues(DataObj o) {
        //helper method that checks the values before returning true or false
        return o.caffeine() == this.caffeine
                && Math.abs(o.volume() - this.volume) < 0.001
                && o.name().equals(this.name)
                && o.type().equals(this.type)
                && o.calories() == (this.calories);
    }

    @Override
    public int compareTo(DataObj o) {
        //if caffeine amount is equal, compare volume
        if (this.caffeine.compareTo(o.caffeine()) == 0)
            return this.volume.compareTo(o.volume());
        //else return caffeine
        return this.caffeine
                .compareTo(o.caffeine());
    }

    @Override
    public String toString() {
        //returns a string of its data
        /*return String.format(
                "\nName: %s\nVolume: %f\nCalories: %d\nCaffeine: %d\nType: %s\n",
                this.name,
                this.volume,
                this.calories,
                this.caffeine,
                this.type
        );*/
        return "" + caffeine;
    }
}
