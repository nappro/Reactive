package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import play.libs.F;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Application extends Controller {

    private static final String ISO_8601_TIMEZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final DateTimeFormatter df = DateTimeFormat.forPattern(ISO_8601_TIMEZONE_FORMAT);
    private static ObjectMapper mapper = new ObjectMapper();

    public static Result index() {
        return ok(views.html.index.render());
    }

    /**
     * Entry point for Http Post /sortCustomers
     * @return
     */
    public static F.Promise<Result> sortCustomers() {
        response().setContentType("application/json");
        JsonNode json = request().body().asJson();
        if(json == null) {
            return F.Promise.promise(() -> badRequest("Expecting Json data"));
        } else {
            // Threads are not blocked on the getting and sorting of Customer objects
            F.Promise<List<Customer>> promise = F.Promise.promise(() -> getSortedCustomers(json));
            return promise.map((List<Customer> customers) -> ok(Json.toJson(customers)));
        }
    }

    /**
     * We create a list of Customer objects, initialise each customers 'duetime'.
     * We sort the list by Customer 'duetime' and return the sorted list.
     * @param json
     * @return
     */
    private static List<Customer> getSortedCustomers(JsonNode json) {
        List<Customer> customers = null;
        try {
            // Create Customer list by parsing Json Array
            TypeReference<List<Customer>> typeRef = new TypeReference<List<Customer>>(){};
            MappingIterator<List<Customer>> it = mapper.readValues(json.traverse(), typeRef);
            customers = it.next();
            // Initialise Customer DateTime
            for(Customer customer: customers) {
                customer.setDueDateTime(df.withOffsetParsed().parseDateTime(customer.getDuetime()));
            }
            Collections.sort(customers); // Sort Customer Collection
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
