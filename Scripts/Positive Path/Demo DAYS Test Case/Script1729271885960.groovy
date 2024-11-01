import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('')

WebUI.navigateToUrl('https://automationteststore.com/')

WebUI.click(findTestObject('Object Repository/asdf/Page_A place to practice your automation skills/input_Checkout_filter_keyword'))

WebUI.click(findTestObject('Object Repository/asdf/Page_A place to practice your automation skills/input_Checkout_filter_keyword'))

WebUI.setText(findTestObject('Object Repository/asdf/Page_A place to practice your automation skills/input_Checkout_filter_keyword'), 
    'moisture')

WebUI.click(findTestObject('Object Repository/asdf/Page_A place to practice your automation skills/i_Checkout_fa fa-search'))

WebUI.verifyElementText(findTestObject('Object Repository/asdf/Page_Total Moisture Facial Cream/span_38.00'), '$38.00')

WebUI.click(findTestObject('Object Repository/asdf/Page_Total Moisture Facial Cream/a_Add to Cart'))

WebUI.closeBrowser()

