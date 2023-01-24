import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *	Population - <description goes here>
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Artem Savchenko
 *	@since	1/12/23
 */
public class Population {

    private String [] states;
    private ArrayList<City> cities;
    private final String DATA_FILE = "usPopData2017.txt";

    /**
     * initializes field variables, and helps create an object of the Population class
     */
    public Population()
    {
        states = new String[]{"Alaska", "Alabama", "Arkansas", "Arizona", "California", "Colorado",
                "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Iowa", "Idaho",
                "Illinois", "Indiana", "Kansas", "Kentucky", "Louisiana", "Massachusetts", "Maryland", "Maine", "Michigan",
                "Minnesota", "Missouri", "Mississippi", "Montana", "North Carolina", "North Dakota", "Nebraska",
                "New Hampshire", "New Jersey", "New Mexico", "Nevada", "New York", "Ohio", "Oklahoma", "Oregon",
                "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas",
                "Utah", "Virginia", "Vermont", "Washington", "Wisconsin", "West Virginia", "Wyoming"};
        cities = new ArrayList<>();
    }


    /**	Prints the introduction to Population */
    public void printIntroduction() {
        System.out.println("   ___                  _       _   _");
        System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
        System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
        System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
        System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
        System.out.println("           |_|");
        System.out.println();
    }

    /**	Print out the choices for population sorting */
    public void printMenu() {
        System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
        System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
        System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
        System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
        System.out.println("5. Fifty most populous cities in named state");
        System.out.println("6. All cities matching a name sorted by population");
        System.out.println("9. Quit");
    }

    /**
     * calls the runner method
     * @param args
     */
    public static void main(String[] args) {
        Population p = new Population();
        p.runner();
    }

    /**
     * runs the main part of the code, with the while loop that asks the user for input over and over again,
     * along with calling the methods to sort the appropriate data and printing out the sorted lists/ data.
     * Also calls different helper methods to format or print out the data.
     */
    public void runner()
    {
        printIntroduction();
        readTxt();
        printMenu();
        boolean gameOn = true;
        int c = 0; 
        while(gameOn){
			System.out.println(); 
			if(c != 0)printMenu(); 
            System.out.println();
            int choice = Prompt.getInt("Enter your selection ");
            System.out.println();
            if(choice == 9) {
                gameOn = false;
                continue;
            }
            long startMillisec = System.currentTimeMillis();

            if(choice == 1) SortMethods.selectionSort(cities);
            if(choice == 2) {SortMethods.mergeSort(cities,"population");  Collections.reverse(cities);}
            if(choice == 3) SortMethods.insertionSort(cities,"ASC","name");
            if(choice == 4) {SortMethods.mergeSort(cities,"name");   Collections.reverse(cities);}
            if(choice == 5)
            {
                SortMethods.insertionSort(cities, "DSC","population");
                String stateName = "";
                boolean f = false;
                while(!f) {
                    stateName = Prompt.getString("Enter state name (ie. Alabama) ");
                    if (!validState(stateName)) {
                        f = false;
                        System.out.println("ERROR: "+ stateName + "is not valid");
                    }
                    else f = true;
                }
                int counter=0;
                header();
                for (int i = 0; i < cities.size(); i++)
                {
                    if (cities.get(i).getState().equals(stateName))
                    {
                        System.out.printf("%3d: %-3s\n",counter+1,cities.get(i));
                        counter++;
                        if (counter==50) i = cities.size();
                    }
                }
                continue;
            }

            if(choice == 6)
            {
                SortMethods.insertionSort(cities, "DSC", "population");
                String cityName = Prompt.getString("Enter city name ");
                System.out.println("\nAll cities matching a name sorted by population");
                int counter=0;
                header();
                for (int i = 0; i < cities.size(); i++)
                {
                    if (cities.get(i).getName().equals(cityName))
                    {
                        System.out.printf("%3d: %-3s\n",counter+1,cities.get(i));
                        counter++;
                        if (counter==50) i = cities.size();
                    }
                }
                continue;
            }

            long endMillisec = System.currentTimeMillis();
            header();
            for (int i = 0; i<50; i++) System.out.printf("%3d: %-3s\n",i+1,cities.get(i));
            System.out.println(String.format("The time taken for the sort: %s milliseconds.", endMillisec-startMillisec));
			c++; 
        }
    }

    /**
     * Prints out a header for every time we need to print a table or data
     */
    public void header()
    {
        System.out.printf("     %-22s %-22s %-12s %12s\n", "State", "City", "Type", "Population");
    }

    /**
     * reads the txt and stores all its contents into the cities arraylist
     */
    public void readTxt()
    {
        FileUtils fu = new FileUtils();
        Scanner sc = fu.openToRead("usPopData2017.txt");
        while(sc.hasNext())
        {

            String line = sc.nextLine();
            String [] arr = line.split("\t");
            City newCity = new City();
            newCity.setState(arr[0]);
            newCity.setPopulation(Integer.parseInt(arr[3]));
            newCity.setDesignation(arr[2]);
            newCity.setName(arr[1]);

            cities.add(newCity);
        }
    }

    /**
     * @param state name of the state that the user inputted, and wants to see the cities of
     * @return if the state name is a valid and existing name of a state in the U.S.
     */
    public boolean validState(String state)
    {
        for(int i = 0; i < states.length; i++)
        {
            if(states[i].equals(state)) return true;
        }
        return false;
    }

}
