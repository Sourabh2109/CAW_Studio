package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;



public class Practice1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.drive", "C:\\Users\\sourabhojha\\Documents\\Project1\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
//Step1::open the url
        driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
//2- Click on Table Data button , a new input text box will be displayed:
        WebElement element = driver.findElement(By.xpath("//*[contains(text(),'Table Data')]"));
        element.click();
//3 - Insert the following data in input text box :

        WebElement dataInput = driver.findElement(By.id("jsondata"));
        String jsonData = "[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, " +
                "{\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, " +
                "{\"name\": \"Sara\", \"age\" : 42, \"gender\": \"female\"}, " +
                "{\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, " +
                "{\"name\": \"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]";
        dataInput.clear();
        dataInput.sendKeys(jsonData); // inserting the data using sendkeys() method
        //And click on Refresh Table button.
        WebElement refreshButton = driver.findElement(By.id("refreshtable"));
        refreshButton.click();
        // Convert the JSON data to a list of strings for comparison
        String[] jsonDataArray = jsonData.split(",");

        for (int i = 0; i < jsonDataArray.length; i++) {
            jsonDataArray[i] = jsonDataArray[i].trim();
        }
        // Step 5: Assert data in the table with the input data
        List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='dynamictable']//tr"));

        for (WebElement row : tableRows) {
            String rowData = row.getText();

            for (String item : jsonDataArray)
                // Assert that each item in the table data is present in the input JSON data
                assert rowData.contains(item) : "Data in the table does not match the input data.";
        }

        // Close the browser
        driver.quit();
    }
}
