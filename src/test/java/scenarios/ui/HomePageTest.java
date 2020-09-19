package scenarios.ui;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

import com.qa.ui.Base.BasePage;
import com.qa.ui.pages.HomePage;

public class HomePageTest {
	public HomePage homepage;
	public BasePage basepage;
	public Properties prop;
	public WebDriver driver;


	@BeforeMethod  //These methods need to be executed before every @ test method
	public void setup() {
		basepage = new BasePage();
		prop = 

	}
}