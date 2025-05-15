/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca_1;

import java.io.File;
import java.util.Scanner;


/**
 *
 * @author jna90
 */
public class DataLibrary {
    //declaring the 2 arrays, one for titles and one for number of pages.    
    private static String[] bookTitle;
    private static int [] numberOfPages;
    //I had to create this variable, because I couldn't find any more efficient weys to do the Search.
    //When I search some title or page, It didn't come in the same order.
    private static String [] books;
    
    /**
     * *Tryload library data
     * @param fileName
     */
    
    public static boolean load(String fileName){
        
        //I used this File class to work with the MOCK_DATA.txt.
        File file = new File(fileName);
        
        //Variable to count how many lines there are in the file.
        int numOfLines = 0;
        
        //Check if the file exists.
        boolean validFile = file.exists();
        
        //If the provided file is valid, it will read the content to load library data.
        //eElse it'll do nothing, just ask the user again to insert a valid file's name.
        if(validFile){
            try{
                //Scanner to read the file.
                Scanner scanner = new Scanner (file);

                //Checking if there is a next line in the file.
                //Reading the nex line.
                //Counting how many lines.
                while(scanner.hasNextLine()){
                    scanner.nextLine();
                    numOfLines++;
                }

                //Created the instance for the three arrays.
                //I used the scanner again to reset the line position.
                bookTitle = new String[numOfLines];
                numberOfPages = new int[numOfLines];
                books = new String[numOfLines];
                scanner = new Scanner(file);

                //Variable index to interate the arrays.
                int index = 0;

                //Variable separator to separate line content when there is a comma. 
                //I am calling it when I need to populate a array.

                String separator = ",";

                //I am using a scanner to check if there are more lines.
                while(scanner.hasNextLine()){
                    //variable line to read the next line.
                    String line = scanner.nextLine();

                    //I used contain to verify if there is a " in the line.
                    if (line.contains("\"")){
                        //I used split, so that when it find a ", it will populate the array booTitles with the inex 1.
                        bookTitle[index] = line.split("\"")[1];
                        //I used parseInt to convert a String to integer.
                        //And I used split to find the " and populate the array numberOfPages with the index 2.
                        //Also I used a replaceAll to switch the variable separator tp empty
                        numberOfPages[index] = Integer.parseInt(line.split("\"")[2].replaceAll(separator, ""));
                    }else {
                        //If there isn't a " I just use the variable separator.
                        //I populate the bookTitle with the zero index and numOfPages with index 1.
                        bookTitle[index] = line.split(separator)[0];
                        numberOfPages[index] = Integer.parseInt(line.split(separator) [1]);
                    }
                    //I used a # to concatenate all the information by line.
                    books[index] = bookTitle[index] + "#" + numberOfPages[index] + "#" + index;
                    //Increase value to iterate the position.
                    index++;
                }
                //Stop reading
                scanner.close();

                //Print if thefile was read successfully or not.
                System.out.println("File read successfully");
                System.out.println("Number of lines in this file: " +numOfLines);
            }catch (Exception e){
                System.out.println("Occur some error readinh file: " + e.getMessage());
                return false;
            }

            return true;
        }else {
            //If it doesn't find some file, give this alert.
            System.out.println("File now found to provide fileName");
            
            return false;
        }   
    }
    
    //I'm creating the clones to use for search and sort.
    public static int[] getNumOfPages() {
        return numberOfPages.clone();
    }
    
    public static String[] getBookTitle() {
        return bookTitle.clone();
    }
    
    //I had to do it to consult the book array when I have to binary search.
    public static int getNumOfPagesByTitle(String title) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].split("#")[0].equals(title)) {
                return Integer.parseInt(books[i].split("#")[1]);
            }
        }
        return -1;
    }
    
    public static String[] getTitlesByNumOfPages(int numOfPages) {
        String titles = "";
        boolean first = true;
        for (int i = 0; i< books.length; i++) {
            if(Integer.parseInt(books[i].split("#")[1]) == numOfPages) {
                if(first){
                    titles = books[i].split("#")[0];
                    first = false;
                }else {
                    titles = titles + "," + books[i].split("#")[0];
                }
            }
        }
        return titles.split(",");
    }
    
    public static String[] getSortedBookTitleNumOfPages() {
        String[] clone = books.clone();
        Sort.selection(clone, true);
        
        return clone;
    }
}
