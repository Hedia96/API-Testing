package trainigxyz;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Challenge {
    @Test
    public void createProduct(){
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        Product product = new Product(
                "sweatband",
                "bydafy ",
                3,
                3
        );
        var response = given().body(product).when().post(endpoint).then();
        response.log().body();
        System.out.println("the product id id "+ product.getId());
    }
    @Test
    public void getProduct(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";

        var response = given().queryParam("id",1002).when().get(endpoint).then().assertThat().statusCode(200).
                body("name",equalTo("sweatband"));
        response.log().body();
        response.log().headers();
    }
    @Test
    public void deserializeGetProduct(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";

        Product product=given().queryParam("id",1002).when().get(endpoint).as(Product.class);
        System.out.println(product.getName());
    }
    @Test
    public void getProducts(){
        String endpoint = "http://localhost:80/api_testing/product/read.php";

        var response = given().when().get(endpoint).then().
               // assertThat().statusCode(200).body("records.size()",equalTo(21));

        assertThat().statusCode(200).body("records.size()",greaterThan(3));
        response.log().body();
    }
@Test
    public void updateProduct()
{
String endpoint="http://localhost:80/api_testing/product/update.php";
Product product = new Product(
        1002,"sweatband","bydafy",6,3
);
var response = given().body(product).when().put(endpoint).then();
response.log().body();
}
}
