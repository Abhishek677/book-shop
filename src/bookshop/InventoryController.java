
package bookshop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Programmer: Abhishek Jassal
 * Program: PROG24178 OOP JAVA-2
 * @version 2.0
 * @author Abhishek
 */
public class InventoryController implements Initializable {

    static int record=0;
    PrintWriter output;
    Scanner input;
    File newfile;
    
   
    @FXML private TextField txtBkname,txtauthname,txtprcname,txtnumbk,txtsrchname;
    @FXML private Button btnsearch,btnadd,btnsave,btnexit,btnwrite,btnnext,btnprevious,btnedit,btndisplay;
    @FXML private ListView lstview;
    
    ArrayList<BooksInventory> books = new ArrayList<BooksInventory>();
    ArrayList<String> options = new ArrayList<>();
    ObservableList<String> items;
    
    @FXML
    private void add(ActionEvent event)  //adding the new record
    {
        record =books.size();
        System.out.println("In Add, Record = "+record+" Size = "+books.size());
        txtBkname.clear();
        txtauthname.clear();
        txtprcname.clear();
        txtnumbk.clear();     
    }
   
    @FXML
    private void save(ActionEvent event)
    {
        BooksInventory bks = new BooksInventory();
        bks.setBookname(txtBkname.getText());
        bks.setAuthor(txtauthname.getText());
        bks.setPrice(Double.parseDouble(txtprcname.getText()));
        bks.setQuantity(Integer.parseInt(txtnumbk.getText()));
        if(record==books.size())
        {
            books.add(bks);
            System.out.println("In Save, if Record = "+record+" Size = "+books.size());
        }
        else
        {
            books.set(record, bks);
            System.out.println("In Save, if Record = "+record+" Size = "+books.size());
        }
        
        
    }
    
    @FXML
    private void exit(ActionEvent event)
    {
        {
      Alert box=new Alert(Alert.AlertType.CONFIRMATION);
      box.setTitle("Exit message");
      box.setHeaderText("Confirmation");
      box.setContentText("Do you want to Exit?");
      box.showAndWait().ifPresent(response -> { 
        if(response == ButtonType.OK)
            {
                System.exit(0);
            }
         });      
    }
    }
        
    @FXML
    private void write(ActionEvent event)  
    {
             try 
             {
              PrintWriter pwr = new PrintWriter(newfile);
              System.out.println("In PrintWriter, Your File is Empty");
              pwr.print("");
              pwr.close();
              
              FileWriter fwr = new FileWriter(newfile,true);
              output = new PrintWriter(fwr);
              System.out.println("PrintWriter Output Append Mode");
             
            for(int i=0;i<books.size();i++)
            {
                output.println(books.get(i).getBookname()+","+books.get(i).getAuthor()+","+books.get(i).getPrice()+","+books.get(i).getQuantity());
                
            }
            
            output.close();
              
              
             }
             catch(Exception e)
             {
               System.out.println("In Writer, There is Error in creation of File") ; 
             }
    }
        
    @FXML
    private void next(ActionEvent event)
    {
        try
        {
            record++;
            txtBkname.setText(books.get(record).getBookname());
            txtauthname.setText(books.get(record).getAuthor());
            txtprcname.setText(String.valueOf(books.get(record).getPrice()));
            txtnumbk.setText(String.valueOf(books.get(record).getQuantity()));
            
            if(record==0)
            btnprevious.setDisable(true);
            else
            btnprevious.setDisable(false);
            if(record == books.size()-1)
                btnnext.setDisable(true);
            else
                btnnext.setDisable(false);
            }
            catch(IndexOutOfBoundsException e)
            {
               System.out.println("Next"+e.getMessage());
            } 
    }
        
    @FXML
    private void previous(ActionEvent event)
    {
          try
        {
        record--;
        txtBkname.setText(books.get(record).getBookname());
        txtauthname.setText(books.get(record).getAuthor());
        txtprcname.setText(String.valueOf(books.get(record).getPrice()));
        txtnumbk.setText(String.valueOf(books.get(record).getQuantity()));
        if(record==0)
            btnprevious.setDisable(true);
        else
            btnprevious.setDisable(false);
        
        if(record==books.size()-1)
                btnnext.setDisable(true);
        else
                btnnext.setDisable(false);
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Previous"+e.getMessage());
        }
        
    }
        
    @FXML
    private void edit(ActionEvent event)
    {
        btnsave.setDisable(false);
        btnadd.setDisable(true);
        if(record==0)
            btnprevious.setDisable(true);
        else
            btnprevious.setDisable(false);
        
        if(record==books.size()-1)
                btnnext.setDisable(true);
        else
                btnnext.setDisable(false);
    }
        
    @FXML
    private void display(ActionEvent event)
    {   
       
        try
        {
            Scanner input=new Scanner(newfile); //open read mode
            if(newfile.exists())
            {
            options.add("Name"+"                "+"Author"+"                "+"Price"+"             "+"Quantity"+"              "+"Total Price");
            while(input.hasNext())
            {
                String s=input.next();
                System.out.println(s);
                Scanner in = new Scanner(s);
                in.useDelimiter(",");

                
                  String name = in.next(); 
                  String author = in.next();
                  double price = in.nextDouble(); 
                  int quantity= in.nextInt();  
                  double totalPrice;
                  totalPrice=price*quantity;
                  
                  
                  options.add(name+"                      "+author+"                        "+price+"                 "+quantity+"                           "+totalPrice);
                  lstview.getItems().addAll();

      
            }
            }
            items = FXCollections.observableArrayList(options);
            lstview.setItems(items);  
            options.removeAll(items);   
            System.out.println("listview size="+items.size());  
            input.close();
            }
        catch(IOException e)
        {
            System.out.println("Error in File Display");
        }
        catch (InputMismatchException ex) 
        {
            System.out.println("Invalid Input");
        }
      
    }
    
    @FXML
    private void search(ActionEvent event)
    {lstview.getItems().clear();

try
        {
            Scanner input=new Scanner(newfile); //open read mode
            if(newfile.exists())
            {
            options.add("Name"+"                "+"Author"+"                "+"Price"+"             "+"Quantity"+"              "+"Total Price");
            while(input.hasNext())
            {
                String s=input.next();
                System.out.println(s);
                Scanner in = new Scanner(s);
                in.useDelimiter(",");

                
                  String name = in.next(); 
                  String author = in.next();
                  double price = in.nextDouble(); 
                  int quantity= in.nextInt();  
                  double totalPrice;
                  totalPrice=price*quantity;
                  
                  if(name.equalsIgnoreCase(txtsrchname.getText()) ){
                 
                 
                  
                  options.add(name+"                      "+author+"                        "+price+"                 "+quantity+"                           "+totalPrice);
                  lstview.getItems().addAll();
                  }
      
            }
            }
        
          
            items = FXCollections.observableArrayList(options);
            lstview.setItems(items);  
            options.removeAll(items);   
            System.out.println("listview size="+items.size());  
            input.close();
            }
        catch(IOException e)
        {
            System.out.println("Error in File Display");
        }
        catch (InputMismatchException ex) 
        {
            System.out.println("Invalid Input");
        }
          
    }  
    
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstview.setPrefHeight(700);
      newfile=new File("Books.txt");
        
        try
        {
          if(newfile.exists())
          {
           input=new Scanner(newfile);
              while(input.hasNext())
              {
                  String s=input.next();
                  Scanner in=new Scanner(s);
                  in.useDelimiter(",");
                  BooksInventory bki=new BooksInventory();
                  bki.setBookname(in.next());
                  bki.setAuthor(in.next());
                  bki.setQuantity(in.nextInt());
                  bki.setPrice(in.nextDouble());
                  books.add(bki);

                  txtBkname.setText(books.get(record).getBookname());
                  txtauthname.setText(books.get(record).getAuthor());
                  txtprcname.setText(String.valueOf(books.get(record).getQuantity()));
                  txtnumbk.setText(String.valueOf(books.get(record).getPrice()));
              }
          }
          else
          {
              System.err.println("File Not Exists Add Record First");
          }
          input.close();
      }
      catch(Exception e)
      {
          System.out.println("Error in Initialize");
      }
        }
        
        
        
        
        
    }    
