package best.buy.api.playgroundInfo;

import best.buy.api.constants.EndPoint;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UtilitiesSteps {
    @Step("Getting Version")
    public ValidatableResponse gettingVersion(){

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoint.GET_VERSION)
                .then();
    }

    @Step("Getting HealthCheck")
    public ValidatableResponse gettingHealthCheck() {

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(EndPoint.GET_HEALTHCHECK)
                .then();
    }

}
