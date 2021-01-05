package ISS.functionality.isspasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ISSPassesResponse {

    private String message;
    private ISSPassesRequest request;
    @JsonProperty("response")
    private ISSPass[] ISSPasses;

    public ISSPassesResponse() {
    }

    public ISSPassesResponse(String message, ISSPassesRequest request, ISSPass[] ISSPasses) {
        this.message = message;
        this.request = request;
        this.ISSPasses = ISSPasses;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ISSPassesRequest getRequest() {
        return request;
    }

    public void setRequest(ISSPassesRequest request) {
        this.request = request;
    }

    public ISSPass[] getPasses() {
        return ISSPasses;
    }

    public void setPasses(ISSPass[] ISSPasses) {
        this.ISSPasses = ISSPasses;
    }
}
