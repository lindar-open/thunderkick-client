package com.lindar.thunderkick.api;

import com.lindar.thunderkick.vo.api.ErrorResponse;
import com.lindar.thunderkick.vo.internal.AccessCredentials;
import com.lindar.wellrested.WellRestedRequest;
import com.lindar.wellrested.util.StringDateSerializer;
import com.lindar.wellrested.vo.Result;
import com.lindar.wellrested.vo.ResultBuilder;
import com.lindar.wellrested.vo.WellRestedResponse;
import lindar.acolyte.util.UrlAcolyte;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Slf4j
public abstract class AbstractResource {
    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

    @Getter
    private final AccessCredentials accessCredentials;

    AbstractResource(AccessCredentials accessCredentials) {
        this.accessCredentials = accessCredentials;
    }

    private WellRestedRequest buildRequestFromResourcePath(String resourcePath) {
        String url = UrlAcolyte.safeConcat(accessCredentials.getApiUrl(), resourcePath);
        return WellRestedRequest.builder().url(url).credentials(accessCredentials.getUsername(), accessCredentials.getPassword())
                .dateSerializer(new StringDateSerializer(ISO_DATE_FORMAT))
                .excludeFields(Collections.singletonList("ref"))
                .build();
    }

    <T extends ErrorResponse> Result<T> sendAndGet(String resourcePath, Class<T> clazz) {
        WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
        WellRestedResponse response = request.get().submit();
        if (response.isValid()) {
            return ResultBuilder.successful(response.fromJson().castTo(clazz));
        }
        ErrorResponse errorResponse = response.fromJson().castTo(ErrorResponse.class);
        return ResultBuilder.failed().msg(errorResponse.getErrorMessage()).code(errorResponse.getErrorCode()).buildAndIgnoreData();
    }

    protected Result<Void> post(String resourcePath) {
        return post(resourcePath, null);
    }

    <T> Result<Void> post(String resourcePath, T objectToPost) {
        try {
            WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
            WellRestedResponse response;
            if (objectToPost != null) {
                response = request.post().jsonContent(objectToPost).submit();
            } else {
                response = request.post().submit();
            }

            if (response.getStatusCode() < 300) {
                return ResultBuilder.successfulWithoutData(response.getServerResponse());
            }
            ErrorResponse errorResponse = response.fromJson().castTo(ErrorResponse.class);
            return ResultBuilder.failed().msg(errorResponse.getErrorMessage()).code(errorResponse.getErrorCode()).buildAndIgnoreData();
        } catch (Exception ex) {
            log.error("Error occurred: ", ex);
            return ResultBuilder.failed("Error occurred: " + ex.getMessage());
        }
    }

    <U, T extends ErrorResponse> Result<T> postAndGet(String resourcePath, U objectToPost, Class<T> responseClass) {
        WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
        WellRestedResponse response = request.post().jsonContent(objectToPost).submit();
        log.debug("postAndGet: {} | request object: {} | response: {}", resourcePath, objectToPost, response);
        if (response.isValid()) {
            return ResultBuilder.successful(response.fromJson().castTo(responseClass));
        }
        ErrorResponse errorResponse = response.fromJson().castTo(ErrorResponse.class);
        return ResultBuilder.failed().msg(errorResponse.getErrorMessage()).code(errorResponse.getErrorCode()).buildAndIgnoreData();
    }

    Result<Void> put(String resourcePath) {
        return put(resourcePath, null);
    }

    <T> Result<Void> put(String resourcePath, T objectToPut) {
        try {
            WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
            WellRestedResponse response;
            if (objectToPut != null) {
                response = request.put().jsonContent(objectToPut).submit();
            } else {
                response = request.put().submit();
            }

            if (response.getStatusCode() < 300) {
                return ResultBuilder.successfulWithoutData(response.getServerResponse());
            }
            ErrorResponse errorResponse = response.fromJson().castTo(ErrorResponse.class);
            return ResultBuilder.failed().msg(errorResponse.getErrorMessage()).code(errorResponse.getErrorCode()).buildAndIgnoreData();
        } catch (Exception ex) {
            log.error("Error occurred: ", ex);
            return ResultBuilder.failed("Error occurred: " + ex.getMessage());
        }
    }

    protected <U, T extends ErrorResponse> Result<T> putAndGet(String resourcePath, U objectToPost, Class<T> responseClass) {
        WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
        WellRestedResponse response = request.put().jsonContent(objectToPost).submit();
        if (response.isValid()) {
            return ResultBuilder.successful(response.fromJson().castTo(responseClass));
        }
        ErrorResponse errorResponse = response.fromJson().castTo(ErrorResponse.class);
        return ResultBuilder.failed().msg(errorResponse.getErrorMessage()).code(errorResponse.getErrorCode()).buildAndIgnoreData();
    }

    Result<Void> delete(String resourcePath) {
        try {
            WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
            WellRestedResponse response;
            response = request.delete().submit();

            if (response.getStatusCode() < 300) {
                return ResultBuilder.successfulWithoutData(response.getServerResponse());
            }
            ErrorResponse errorResponse = response.fromJson().castTo(ErrorResponse.class);
            return ResultBuilder.failed().msg(errorResponse.getErrorMessage()).code(errorResponse.getErrorCode()).buildAndIgnoreData();
        } catch (Exception ex) {
            log.error("Error occurred: ", ex);
            return ResultBuilder.failed("Error occurred: " + ex.getMessage());
        }
    }
}
