/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author Artem Savchenko
 *	@since 1/17/23
 */
public class City implements Comparable<City> {
    private String state, name, designation;
    private int population;

    /**
     * Initializes the fields
     */
    public City()
    {
        state = name = designation = "";
        population = 0;
    }

    /**	Compare two cities populations
     *	@param other		the other City to compare
     *	@return				the following value:
     *		If populations are different, then returns (this.population - other.population)
     *		else if states are different, then returns (this.state - other.state)
     *		else returns (this.name - other.name)
     */

    public int compareTo(City other)
    {
        if(this.population != other.population) return this.population - other.population;
        else if(!this.state.equals(other)) return this.state.compareTo(other.getState());
            else return this.name.compareTo(other.getName());
    }

    /**
     * @param other City object that we need to compare to 'this' City objects
     * @return difference in the name of the city of the two objects (this.name - other.name)
     */
    public int compareToByName(City other)
    {
        return this.name.compareTo(other.getName());
    }

    /**	Equal city name and state name
     *	@param other		the other City to compare
     *	@return				true if city name and state name equal; false otherwise
     */

    public boolean equals(City other)
    {
        return this.state.equals(other.getState());
    }

    /**
     * Sets the population of current City object
     * @param pop int that we want to set the population of this City object to
     */
    public void setPopulation(int pop)
    {
        population = pop;
    }

    /**
     * Sets the city name in the current City object
     * @param st String with the name that we want to assign to the current City object
     */
    public void setState(String st)
    {
        state = st;
    }

    /**
     * Sets the state name in the current City object
     * @param n String with the name of the state that we want to assign to the current City object
     */
    public void setName(String n)
    {
        name = n;
    }

    /**
     * Sets the designation/type of the city of the current City object
     * @param d String with the type of city that we want to assign to the current City object
     */
    public void setDesignation(String d)
    {
        designation = d;
    }

    /**
     * returns the name of the City
     * @return name of the city
     */
    public String getName() { return name;}

    /**
     * returns the population of the city
     * @return population of the city
     */
    public int getPopulation() { return population; }

    /**
     * returns the state name of the City
     * @return state name
     */
    public String getState() { return state; }

    /**
     * allows the City object to be printed out
     * @return formatted String to print out
     */
    @Override
    public String toString() {
        return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
                population);
    }

}

