package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Car;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import store.CarStore;
import utils.Util;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class DriverController extends Controller {
    private HttpExecutionContext ec;
    private CarStore CarStore;

    @Inject
    public DriverController(HttpExecutionContext ec, CarStore carStore) {
        this.carStore = carStore;
        this.ec = ec;
    }

    public CompletionStage<Result> create(Http.Request request) {
        JsonNode json = request.body().asJson();
        return supplyAsync(() -> {
            if (json == null) {
                return badRequest(Util.createResponse("Expecting Json data", false));
            }

            Optional<Car> carOptional = CarStore.addcar(Json.fromJson(json, Car.class));
            return carOptional.map(car -> {
                JsonNode jsonObject = Json.toJson(car);
                return created(Util.createResponse(jsonObject, true));
            }).orElse(internalServerError(Util.createResponse("Could not create data.", false)));
        }, ec.current());
    }

    public CompletionStage<Result> listCars() {
        return supplyAsync(() -> {
            Set<Car> result = CarStore.getAllCars();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonData = mapper.convertValue(result, JsonNode.class);
            return ok(Util.createResponse(jsonData, true));
        }, ec.current());
    }

    public CompletionStage<Result> retrieve(int id) {
        return supplyAsync(() -> {
            final Optional<Car> carOptional = CarStore.getCar(id);
            return carOptional.map(car -> {
                JsonNode jsonObjects = Json.toJson(student);
                return ok(Util.createResponse(jsonObjects, true));
            }).orElse(notFound(Util.createResponse("Car with Plate Number:" + id + " not found", false)));
        }, ec.current());
    }

    public CompletionStage<Result> update(Http.Request request) {
        JsonNode json = request.body().asJson();
        return supplyAsync(() -> {
            if (json == null) {
                return badRequest(Util.createResponse("Expecting Json data", false));
            }
            Optional<Car> CarOptional = carStore.updateCar(Json.fromJson(json, Car.class));
            return CarOptional.map(car -> {
                if (car == null) {
                    return notFound(Util.createResponse("No Car found", false));
                }
                JsonNode jsonObject = Json.toJson(car);
                return ok(Util.createResponse(jsonObject, true));
            }).orElse(internalServerError(Util.createResponse("Could not create data.", false)));
        }, ec.current());
    }

    public CompletionStage<Result> delete(String license_plate) {
        return supplyAsync(() -> {
            boolean status = CarStore.deleteCar(license_plate);
            if (!status) {
                return notFound(Util.createResponse("Car with licenseplate:" + license_plate + " not found", false));
            }
            return ok(Util.createResponse("Car with licenseplate:" + license_plate + " deleted", true));
        }, ec.current());
    }
}