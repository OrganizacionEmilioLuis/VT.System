package vt.system.Activity.api;



import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import vt.system.Activity.model.UploadResponse;

/**
 * Created by Miler Pacheco 3/31/17.
 */
public interface Service {
    @Multipart
    @POST("/WebService_VargasTecniFour/WSPrueba2/upload_image.php")
    Call<UploadResponse> uploadFile(@Part MultipartBody.Part file,
                                    @Part("file") RequestBody name);

    @Multipart
    @POST("/WebService_VargasTecniFour/WSPrueba2/upload_image.php")
    Call<UploadResponse> uploadMulFile(@Part MultipartBody.Part file1,
                                       @Part MultipartBody.Part file2);

    @GET("/WebService_VargasTecniFour/upload/getImages.php")
    Call<UploadResponse> getUpload();
}
