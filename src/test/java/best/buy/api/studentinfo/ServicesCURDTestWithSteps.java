package best.buy.api.studentinfo;


import best.buy.api.playgroundInfo.ProductSteps;
import best.buy.api.playgroundInfo.ServicesSteps;
import best.buy.api.testbase.TestBase;
import best.buy.api.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.annotation.Order;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class ServicesCURDTestWithSteps extends TestBase {

    static String name = "Car dealer" + TestUtils.getRandomValue();
    static int serviceID;

    @Steps
    ServicesSteps servicesSteps;

    @Title("This will create a New Service")
    @Test
    @Order(1)
    public void test001() {
        ValidatableResponse response = servicesSteps.createService(name);
        response.log().all().statusCode(201);
        serviceID = response.log().all().extract().path("id");
        System.out.println(serviceID);
    }

    @Title("Verify if the Services was added to the application")
    @Test
    @Order(2)
    public void test002() {
        HashMap<String, ?> storeMap = servicesSteps.getServiceInfoByName(serviceID);
        Assert.assertThat(storeMap, hasValue(name));
        System.out.println(storeMap);
    }

    @Title("Update the Services information")
    @Test
    @Order(3)
    public void test003() {
        name = name + "_updated";
        HashMap<Object, Object> servicesData = new HashMap<>();
        servicesSteps.updateService(serviceID, name);
        HashMap<String, ?> productList = servicesSteps.getServiceInfoByName(serviceID);
        Assert.assertThat(productList, hasValue(name));
        System.out.println(productList);
    }

    @Title("Delete the Services by ID")
    @Test
    @Order(4)
    public void test004() {
        servicesSteps.deleteService(serviceID).statusCode(200);
        servicesSteps.getServiceByID(serviceID).statusCode(404);
    }

}
