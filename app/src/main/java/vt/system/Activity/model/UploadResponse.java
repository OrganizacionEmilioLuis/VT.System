package vt.system.Activity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by delaroy on 3/31/17.
 */
public class UploadResponse {

    @SerializedName("registro")
    @Expose
    private List<registro> upload;

    public List<registro> getUpload(){
        return upload;
    }
    public void setUpload(List<registro>upload){
        this.upload = upload;
    }




    // variable name should be same as in the json response from php
    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;

    public String getMessage() {
        return message;
    }

    public boolean getSuccess() {
        return success;
    }
}
