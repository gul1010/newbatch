package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AddEmployeePage;
import pages.DashBoardPage;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReading;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        DashBoardPage dash = new DashBoardPage();
        click(dash.pimOPtion);
    }

    @When("user clicks on Add employee button")
    public void user_clicks_on_add_employee_button() {
        DashBoardPage dash = new DashBoardPage();
        click(dash.employeeListOption);
    }

    @When("user enters firstname, middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        AddEmployeePage add = new AddEmployeePage();
        sendText(add.firstName, "Nelson123");
        sendText(add.middleName, "MS");
        sendText(add.lastName, "MS123");
    }


    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname(String firstname, String middlename, String lastname) {
        AddEmployeePage add = new AddEmployeePage();
        sendText(add.firstName,firstname);
        sendText(add.middleName, middlename);
        sendText(add.lastName, lastname);
    }

    @When("user enters first name {string} middle name {string} and last name {string}")
    public void user_enters_first_name_middle_name_and_last_name(String FirstName, String MiddleName, String LastName) {
        AddEmployeePage add = new AddEmployeePage();
        sendText(add.firstName, FirstName);
        sendText(add.middleName, MiddleName);
        sendText(add.lastName, LastName);
    }
    @When("user enters {string} {string} and {string} in the application")
    public void user_enters_and_in_the_application(String Firstname, String Middlename, String Lastname) {

    }

    @When("user clicks on save button option")
    public void user_clicks_on_save_button_option() {
        AddEmployeePage add = new AddEmployeePage();
        click(add.saveBtn);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added successfully");
    }

    @When("add multiple employees and verify they are added successfully")
    public void add_multiple_employees_and_verify_they_are_added_successfully(DataTable empployees) throws InterruptedException {
        List<Map<String, String>> employeenames = empployees.asMaps();
        for (Map<String, String> employee : employeenames) {
            String firstnamevalue = employee.get("Firstname");
            String middlenamevalue = employee.get("Middlename");
            String lastnamevalue = employee.get("Lastname");
            System.out.println(firstnamevalue + " " + middlenamevalue + " " + lastnamevalue);

            AddEmployeePage addEmployeePage=new AddEmployeePage();
            sendText(addEmployeePage.firstName, firstnamevalue);
            sendText(addEmployeePage.middleName, middlenamevalue);
            sendText(addEmployeePage.lastName, lastnamevalue);
            click(addEmployeePage.saveBtn);
            //assertion

            Thread.sleep(1000);
            DashBoardPage dash=new DashBoardPage();
            click(dash.addEmployeeButton);
            Thread.sleep(1000);
        }
    }
    @When("user adds multiple employees from excel file from {string} sheet and verify they are added")
    public void user_adds_multiple_employees_from_excel_file_from_sheet_and_verify_they_are_added(String sheetname) {
        List<Map<String, String>> newemployees= ExcelReading.excelIntoListMap(Constants.TESTDATA_FILEPATH, sheetname);
        DashBoardPage dash=new DashBoardPage();
        AddEmployeePage add=new AddEmployeePage();

        Iterator<Map<String, String>> it=newemployees.iterator();
        while(it.hasNext()){
            Map<String, String> mapnewEmp=it.next();
            sendText(add.firstName, mapnewEmp.get("Firstname"));
            sendText(add.middleName, mapnewEmp.get("Middlename"));
            sendText(add.lastName, mapnewEmp.get("Lastname"));
            click(add.saveBtn);
            //assertion
        }
    }
}

