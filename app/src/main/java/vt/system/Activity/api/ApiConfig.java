package vt.system.Activity.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import vt.system.Activity.model.ServerResponse;

public interface ApiConfig {
    @Multipart
    @POST("WebService_VargasTecniFour/WSPrueba2/upload_image.php")
    Call<ServerResponse> uploadFile(@Part MultipartBody.Part file,
                                    @Part("file") RequestBody name);

    @Multipart
    @POST("WebService_VargasTecniFour/WSPrueba2/upload_image.php")
    Call<ServerResponse> uploadMulFile(@Part MultipartBody.Part file1,
                                       @Part MultipartBody.Part file2);
}
