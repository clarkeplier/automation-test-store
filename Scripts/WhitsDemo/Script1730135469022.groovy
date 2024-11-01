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

WebUI.click(findTestObject('Object Repository/asdf/Page_A place to practice your automation skills/a_Apparel  accessories'))

WebUI.click(findTestObject('Object Repository/asdf/Page_Apparel  accessories/a_Womens high heel point toe stiletto sanda_024c99'))

WebUI.click(findTestObject('Object Repository/asdf/Page_Womens high heel point toe stiletto sa_38c633/input_green_option347'))

WebUI.click(findTestObject('Object Repository/asdf/Page_Womens high heel point toe stiletto sa_38c633/a_Add to Cart'))

WebUI.click(findTestObject('Object Repository/asdf/Page_Shopping Cart/a_Login or register'))

WebUI.setText(findTestObject('Object Repository/asdf/Page_Account Login/input_Login Name_loginname'), 'guest_account_420_69')

WebUI.setEncryptedText(findTestObject('Object Repository/asdf/Page_Account Login/input_Password_password'), 'LC3zoSP415LSi2us0NEv6w==')

WebUI.click(findTestObject('Object Repository/asdf/Page_Account Login/button_Login'))

WebUI.click(findTestObject('Object Repository/asdf/Page_My Account/a_1Items-26.00'))

WebUI.click(findTestObject('Object Repository/asdf/Page_Shopping Cart/a_Checkout'))

WebUI.click(findTestObject('Object Repository/asdf/Page_Checkout Confirmation/button_Confirm Order'))

WebUI.doubleClick(findTestObject('Object Repository/asdf/Page_Your Order Has Been Processed/span_Your Order Has Been Processed'))

WebUI.verifyElementText(findTestObject('Object Repository/asdf/Page_Your Order Has Been Processed/span_Your Order Has Been Processed'), 
    'Your Order Has Been Processed!')

WebUI.closeBrowser()

