package commands.response;

public class CommandResponse {

    private Object object;
    private ResponseType responseType;
    private Class typeOfObject;
    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Class getTypeOfObject() {
        return typeOfObject;
    }

    public void setTypeOfObject(Class typeOfObject) {
        this.typeOfObject = typeOfObject;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }
}