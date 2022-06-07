package best.buy.api.studentinfo;


import best.buy.api.playgroundInfo.ProductSteps;
import best.buy.api.testbase.TestBase;
import best.buy.api.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class
ProductCURDTestWithSteps extends TestBase {

    static String name = "LPatel" + TestUtils.getRandomValue();
    static String type = "cars" + TestUtils.getRandomValue();
    static Integer price = 8;
    static Integer shipping = 25;
    static String upc = "0123456789";
    static String description = "Red color";
    static String manufacturer = "AUDI";
    static String model = "A3";
    static String url = "www.AUDI.com";
    static String image = "www.AUDI.com/images/car2541.com";
    static int productID;

    @Steps
    ProductSteps productsSteps;

    @Title("This will create a New product")
    @Test
    public void test001() {


        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productID = response.log().all().extract().path("id");
        System.out.println(productID);
    }

    @Title("Verify if the Product was added to the application")
    @Test
    public void test002() {
        HashMap<String, ?> productMap = productsSteps.getProductInfoByName(productID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productMap);
    }

    @Title("Update the product information")
    @Test
    public void test003() {
        name = name + "_updated";
        productsSteps.updatingProduct(productID, name, type, price, shipping, upc, description, manufacturer, model, url, image);
        HashMap<String, ?> productMap = productsSteps.getProductInfoByName(productID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productMap);
    }

    @Title("Delete the product by ID")
    @Test
    public void test004() {
        productsSteps.deleteProduct(productID).statusCode(200);
        productsSteps.getProductByID(productID).statusCode(404);
    }
}
