package com.app.recall.impl.RetrofitImpl;

import com.app.recall.entity.item.TItem;
import com.app.recall.entity.retrofit.BaseEntity;
import com.app.recall.entity.retrofit.MeEntity;
import com.app.recall.entity.retrofit.PhoneSignUpSuccessEntity;
import com.app.recall.entity.retrofit.PlatformsEntity;
import com.app.recall.entity.retrofit.UserEntity;
import com.app.recall.entity.retrofit.ReceiverOtherUserEntity;
import com.app.recall.presenter.ChangePasswordPresenterImpl;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by KenChan on 16/12/19.
 */

public interface RecallImpl {
    @GET("users/phone/{phone}/code")
    Call<BaseEntity> callVerificationCode(@Path("phone") String phone);

    @POST("users/phone/{phone}")
    Call<PhoneSignUpSuccessEntity> callPhoneSignUp(@Path("phone") String phone, @Body RequestBody
            body);

    @POST("sessions")
    Call<UserEntity> callLogin(@Body RequestBody body);

    @GET("users/{userId}/description")
    Call<ReceiverOtherUserEntity> callUserBaseInfo(@Path("userId") String user);

    @PUT("users/me/password")
    Call<BaseEntity> callPassword(@Body ChangePasswordPresenterImpl.Pass pass, @Header
            ("Authorization") String auth);

    @Multipart
    @PUT("users/me/profilePhoto")
    Call<MeEntity> callUploadAvatar(@Part MultipartBody.Part part, @Header("Authorization")
            String auth);

    @PUT("users/me/username/{username}")
    Call<MeEntity> callUserName(@Path("username") String userName, @Header("Authorization")
            String auth);

    @GET("users/me/contacts/{uid}/handshake")
    Call<BaseEntity> callHandshake(@Path("uid") String uid, @Header("Authorization") String auth);

    @GET("users/me")
    Call<MeEntity> callMe(@Header("Authorization") String auth);

    @POST("users/me/platforms")
    Call<List<PlatformsEntity>> callUploadPlatforms(@Body RequestBody body, @Header("Authorization")
            String auth);

    @GET("978-2")
    Call<TItem> callCommic(@QueryMap Map<String, String> map);
}
