package best.buy.api.studentinfo;


import best.buy.api.playgroundInfo.StoreSteps;
import best.buy.api.playgroundInfo.UtilitiesSteps;
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

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class UtilitiesCURDTestWithSteps extends TestBase {
    @Steps
    UtilitiesSteps utilitiesSteps;

    @Title("Get the version of Application")
    @Test
    @Order(1)
    public void getVersion() {
        ValidatableResponse response = utilitiesSteps.gettingVersion();
        response.log().all().statusCode(200);
    }

    @Title("Get the Health check of Application")
    @Test
    @Order(2)
    public void getHealthCheck() {
        ValidatableResponse response = utilitiesSteps.gettingHealthCheck();
        response.log().all().statusCode(200);
        HashMap<String, ?> healthMap = response.extract().path("");
        Assert.assertThat(healthMap, hasKey("uptime"));
        Assert.assertThat(healthMap, hasKey("readonly"));
        Assert.assertThat(healthMap, hasKey("documents"));
    }

}
