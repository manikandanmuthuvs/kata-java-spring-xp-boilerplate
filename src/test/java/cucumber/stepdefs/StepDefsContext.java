package cucumber.stepdefs;

import io.restassured.response.Response;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

@Getter
public enum StepDefsContext {

    CONTEXT;

    private final ThreadLocal<Map<String, Object>> givenObjects = ThreadLocal.withInitial(HashMap::new);
    private final ThreadLocal<Map<String, Object>> thenObjects = ThreadLocal.withInitial(HashMap::new);

    public <T> T givenObject(Class<T> clazz) {
        return clazz.cast(givenObjects.get().get(clazz.getName()));
    }

    public <T> void givenObject(T object) {
        givenObjects.get().put(object.getClass().getName(), object);
    }

    public <T> T result(Class<T> clazz) {
        return clazz.cast(thenObjects.get().get(clazz.getName()));
    }

    public <T> void result(T object) {
        thenObjects.get().put(object.getClass().getName(), object);
    }

    public Throwable throwable() {
        return Throwable.class.cast(thenObjects.get().get(Throwable.class.getName()));
    }

    public void throwable(Throwable throwable) {
        thenObjects.get().put(Throwable.class.getName(), throwable);
    }

    public Response response() {
        return Response.class.cast(thenObjects.get().get(Response.class.getName()));
    }

    public void response(Response throwable) {
        thenObjects.get().put(Response.class.getName(), throwable);
    }
    public void response(ResponseEntity<String> response) {
        thenObjects.get().put(ResponseEntity.class.getName(), response);
    }
    public void reset() {
        givenObjects.get()
                .clear();
        thenObjects.get()
                .clear();
    }
}
