/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca_1;

import java.util.Scanner;


/**
 *
 * @author jna90
 */
public class CA_1 {
    /**
     * @param args the command line arguments
     */
    //I created tha variable Scanner to call it in the main method.
    private static final Scanner scanner = new Scanner(System.in);
    
    //I created this variable capture to save space, so I just call it when I need the user insert some information. 
    private static String capture(String ask) {
        System.out.println(ask);
        return scanner.nextLine();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        //I had to do a boolen to keep asking user a valid file's name until the program got one.
        boolean loaded = false;
        
        while (!loaded) {
            String fileName = capture("Places enter the file with extension (e.g. .txt) name to read.");
            //I'm callin the class DataLibrary to boolean loaded.
            loaded = DataLibrary.load(fileName);
        }
        
        //I'm calling the method goToChoices.
        goToChoices("Do you wish to SORT or SEARCH", "SORT", "SEARCH");
    }
    
    //Creating a Menu
    private static void goToChoices(String ask, String choiceOneLabel, String choiceTwolabel) {
        //Variable element will capture what the user input and do the switch.
        String element = capture( ask + ":\n(1) " + choiceOneLabel + "\n(2) " + choiceTwolabel);
        
        switch (element) {
            case "1": {
                System.out.println(choiceOneLabel + " selected");
                goToChoice(choiceOneLabel);
                break;
            }
            case "2": {
                System.out.println(choiceTwolabel + "selected");
                goToChoice(choiceTwolabel);
                break;
            }
            default: {
                break;
            }
        }
    }
    
    //Now is user enter 1 or 2 it will show this menu.
    
    public static void goToChoice(String label) {
        switch (label) {
            //If the user press SORT or SEARCH, it will star the method doSort or doSearch.
            case "SEARCH": {
                doSearch();
                break;
            }
            case "SORT": {
                doSort();
                break;
            }
            //if the user presse Main Menu they will be back to first menu "goToChoices".
            case "MAIN MENU": {
                goToChoices("Do you wish to SORT or SEARCH", "SORT", "SEARCH");
                break;
            }
            //If the user press EXIT they will leave the program.
            case "EXIT": {
                System.exit(0);
            }
            default: {
                break;
            }
        }
    }
    
    //method sort
    private static void doSort(){
        //scann if user enter 1 or 2.
        String element = capture("Choose the element to sort:\n(1) BOOK TITLE\n(2) NUMBER OF PAGES");
        //checking if the variable element is 1, if it is, so print BOOk TITLE selected.
        System.out.println(element.equals("1")? "BOOK TITLE selected" : "NUMBER OF PAGES selected");
        
        //scann if user enter 1 or 2.
        String order = capture("Choose one:\n(1) ASCENDING\n(2) DESCENDING");
        //checking if the variable element is 1, if it is, so print ASCENDING selected.
        System.out.println(order.equals("1")? "ASCENDING selected" : "DESCENDING selected");
        //If the variable order is equals 1 the variable isAsc is 1.
        boolean isAsc = order.equals("1");
        
        //create to  build a string gradually using method append().
        StringBuilder sb = new StringBuilder();
        //long to check time to process of Bubble and slection algorithms.
        long bubbleTime;
        long selectionTime;
        
        if (element.equals("1")) {
            //take the clone form method getBookTitle.
            String [] resultBubble = DataLibrary.getBookTitle();
            
            //Cheking how long bubble and selection take to finish the sort..
            long startTime = System.nanoTime();
            //taking the sort from Sort class.
            Sort.bubble(resultBubble, isAsc);
            long endTime = System.nanoTime();
            bubbleTime = endTime - startTime;
            
            String [] resultSelection = DataLibrary.getBookTitle();
            startTime = System.nanoTime();
            //taking the search from Search classs.
            Sort.selection(resultSelection, isAsc);
            endTime = System.nanoTime();
            selectionTime = endTime - startTime;            
            
            //Iterate to check all of the elements are in the same order, so compare the result of each algorithms.
            //Also filter to show only 50 elements.
            int count = 0;
            for (int i = 0; i < resultBubble.length; i++) {
                if (resultBubble [i].equals(resultSelection[i])) {
                    if (count < 50) {
                        sb.append("Title: ").append(resultBubble[i]).append(" - Number of pages: ").append(DataLibrary.getNumOfPagesByTitle(resultBubble[i])).append("\n");
                        count++;
                    }
                }else {
                    System.exit(-1);
                }
            }            
        } else {
            //take the clone form method getBookTitle.
            int[] resultBubble = DataLibrary.getNumOfPages();
            
            //Cheking how long bubble and selection take to finish the sort..
            long startTime = System.nanoTime();
            //taking the sort from Sort class.
            Sort.bubble(resultBubble, isAsc);
            long endTime = System.nanoTime();
            bubbleTime = endTime - startTime;
            
            int [] resultSelection = DataLibrary.getNumOfPages();
            startTime = System.nanoTime();
            //taking the search from Search classs.
            Sort.selection(resultSelection, isAsc);
            endTime = System.nanoTime();
            selectionTime = endTime - startTime;          
            
            //Iterate to check all of the elements are in the same order, so compare the result of each algorithms.
            //Also filter to show only 50 elements.
            int count = 0;
            for (int i = 0; i < resultBubble.length; i++) {
                if (resultBubble [i] == resultSelection[i]) {
                    String [] titles = DataLibrary.getTitlesByNumOfPages(resultBubble[i]);
                    for(int j = 0; j <titles.length; j++){
                        if (count < 50) {
                            sb.append("Title: ").append(titles[j]).append(" - Number of pages: ").append(resultBubble[i]).append("\n");
                            count++;
                        }                        
                    }
                }else {
                    System.exit(-1);
                }
            }
        }
        //convert to String and print.
        System.out.println("\n" + sb.toString());
        //print the time.
        System.out.println("Bubble sort takes " + bubbleTime + " nanosecs.");
        System.out.println("Selection sort takes " + selectionTime + " nanosecs.");
        
        //Checking which algorithms is better.
        boolean isBubbleBetter = bubbleTime < selectionTime;
        String bestSort = isBubbleBetter ? "Bubble" : "Selection";
        System.out.println(bestSort + " sort performed better.");
        
        goToChoices("\nWould you like to finish the program?", "MAIN MENU", "EXIT");
    }
    
    private static void doSearch() {
        //Asking user to enter a a book's name that they want to find.
        String title = capture("Please enter the book's name you wish to find:");
        System.out.println(title + " informed");
        
        String resultLinear;
        //Cheking how long Lineaar and Binary take to finish the search.
        long startTime = System.nanoTime();
        int indexFound = Search.linear(DataLibrary.getBookTitle(), title);
        long endTime = System.nanoTime();
        long linearTime = endTime - startTime;

        //checking if the index found is a positive number.
        if (indexFound == -1) {
            resultLinear = "Not Found";
        } else {
            resultLinear = "Index position " + indexFound + " - Pages " + DataLibrary.getNumOfPages()[indexFound];
        }

        String resultBinary;
        //Creating a sorted books to be search using binary algorithms, because it asssume the list is already sorted.
        String[] sortedBook = DataLibrary.getSortedBookTitleNumOfPages();
        
        //Checking time.
        startTime = System.nanoTime();
        indexFound = Search.binary(sortedBook, title);
        endTime = System.nanoTime();
        long binaryTime = endTime - startTime;
        
        if (indexFound == -1) {
            resultBinary = "Not Found";
        } else {
            //Clone of array books using the separator #.
            String originalLine = sortedBook[indexFound];
            //calling the method extractOriginalindex and extractNumOfPages from Search class.
            resultBinary = "Index position " + Search.extractOriginalIndex(originalLine) + " - Pages " + Search.extractNumOfPages(originalLine);
        }
        
        //Erros check to guarantee there are no different on result between the algorithms.
        if (resultLinear.equals(resultBinary)) {
            System.out.println("\n" + resultLinear + "\n");
            System.out.println("Linear search takes " + linearTime + " nanosecs");
            System.out.println("Linear search takes " + binaryTime + " nanosecs");
            
            //Checking which algorithms is better.
            boolean isLinearBetter = linearTime < binaryTime;
            String bestSearch = isLinearBetter ? "Linear" : "Binary";
            System.out.println(bestSearch + " search performed better.");
        } else {
            System.exit(-1);
        }
        
        goToChoices("\nWould you like to finish the program?", "MAIN MENU", "EXIT");
    }
}