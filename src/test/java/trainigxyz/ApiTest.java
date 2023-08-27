package trainigxyz;

import models.Product;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

public class ApiTest {
    @Test
    public void getCategories()
    {
        String endpoint="http://localhost:80/api_testing/category/read.php";
       var response= given().when().get(endpoint).then();
        response.log().body();

    }

    @Test
    public void getProducts()
    {
        String endpoint="http://localhost:80/api_testing/product/read.php";
        var response= given().queryParam("id",1000)
                .when()
                .get(endpoint)
                .then();
response.log().body();

    }
    @Test
    public void createProduct()
    {
        String endpoint="http://localhost:80/api_testing/product/create.php";
        String body= """
                {
                "name":"Laptop",
                "description":"this laptop is fantastic ",
                "price":"3000",
                "category_id":19           
                  }
                """;
        var response= given().body(body).when().post(endpoint).then();
        response.log().body();
    }
    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = """
                {
                "id": 19
                }
                """;
        var response = given().body(body).when().delete(endpoint).then();
        response.log().body();
    }
@Test
public void createSerializedProduct(){
    String endpoint = "http://localhost:80/api_testing/product/create.php";
    Product product = new Product(
            "Water Bottle",
            "Blue water bottle. Holds 64 ounces",
            12,
            3
    );
    var response = given().body(product).when().post(endpoint).then();
    response.log().body();
}

}
