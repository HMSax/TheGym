import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TheGymTest {
    Path fromCustomersTestFilePath = Paths.get("test/customerTest.txt");
    Path toPTinfoTestFilePath = Paths.get("test/ptinfoTest.txt");
List<Customer> testList = new ArrayList<>();
TheGym testGym = new TheGym();
    @Test

public void addToCustomerListTest(){
        try (Scanner fileScanner = new Scanner(fromCustomersTestFilePath)) {
                testList = testGym.addToCustomerList(fileScanner);
            assertTrue(testList.size() == 3);
            assertFalse(testList.size() ==2);

        }    catch (FileNotFoundException e){
            System.out.println("File not found.");
            e.printStackTrace();
            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("Unable to write to file.");
            e.printStackTrace();
            System.exit(0);
        }
        catch (Exception e){
            System.out.println("Something went wrong.");
            e.printStackTrace();
            System.exit(0);
        }

    }
}