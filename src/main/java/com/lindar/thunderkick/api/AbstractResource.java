package com.lindar.thunderkick.api;

import com.lindar.thunderkick.vo.api.ErrorResponse;
import com.lindar.thunderkick.vo.internal.AccessCredentials;
import com.lindar.wellrested.WellRestedRequest;
import com.lindar.wellrested.util.StringDateSerializer;
import com.lindar.wellrested.vo.ResponseVO;
import com.lindar.wellrested.vo.Result;
import com.lindar.wellrested.vo.ResultFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class AbstractResource {
    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZZ";

    private static final String QUESTION = "?";
    private static final String AND = "&";
    private static final String EQUAL = "=";

    @Getter
    private final AccessCredentials accessCredentials;

    public AbstractResource(AccessCredentials accessCredentials) {
        this.accessCredentials = accessCredentials;
    }

    protected WellRestedRequest buildRequestFromResourcePath(String resourcePath) {
        String url = validatePath(accessCredentials.getApiUrl() + resourcePath);
        return WellRestedRequest.build(url, accessCredentials.getUsername(), accessCredentials.getPassword())
                .setDateSerializer(new StringDateSerializer(ISO_DATE_FORMAT));
    }

    protected String validatePath(String path) {
        String newPath = path.replaceAll("//", "/");
        return newPath.replaceFirst("/", "//");
    }

    private String validatePathWithAttributes(String path) {
        String newPath = validatePath(path);
        if (newPath.endsWith("/")) {
            newPath = newPath.substring(0, newPath.length() - 1);
            newPath += QUESTION;
        } else if (newPath.contains(QUESTION)) {
            newPath += AND;
        } else {
            newPath += QUESTION;
        }
        return newPath.replaceAll(AND + AND, AND);
    }

    protected <T extends ErrorResponse> Result<T> sendAndGet(String resourcePath, Class<T> clazz) {
        WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
        ResponseVO response = request.get();
        if (response.isValid()) {
            return ResultFactory.getSuccessResult(response.castJsonResponse(clazz));
        }
        ErrorResponse errorResponse = response.castJsonResponse(ErrorResponse.class);
        return ResultFactory.getFailResult(errorResponse.getErrorMessage(), errorResponse.getErrorCode());
    }

    protected Result<Void> post(String resourcePath) {
        return post(resourcePath, null);
    }

    protected <T> Result<Void> post(String resourcePath, T objectToPost) {
        try {
            WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
            ResponseVO response;
            if (objectToPost != null) {
                response = request.post(objectToPost);
            } else {
                response = request.post();
            }

            if (response.getStatusCode() < 300) {
                return ResultFactory.getSuccessResultMsg(response.getServerResponse());
            }
            ErrorResponse errorResponse = response.castJsonResponse(ErrorResponse.class);
            return ResultFactory.getFailResult(errorResponse.getErrorMessage(), errorResponse.getErrorCode());
        } catch (Exception ex) {
            log.error("Error occurred: ", ex);
            return ResultFactory.getFailResult("Error occurred: " + ex.getMessage());
        }
    }

    protected <U, T extends ErrorResponse> Result<T> postAndGet(String resourcePath, U objectToPost, Class<T> responseClass) {
        WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
        ResponseVO response = request.post(objectToPost);
        if (response.isValid()) {
            return ResultFactory.getSuccessResult(response.castJsonResponse(responseClass));
        }
        ErrorResponse errorResponse = response.castJsonResponse(ErrorResponse.class);
        return ResultFactory.getFailResult(errorResponse.getErrorMessage(), errorResponse.getErrorCode());
    }

    protected Result<Void> put(String resourcePath) {
        return put(resourcePath, null);
    }

    protected <T> Result<Void> put(String resourcePath, T objectToPut) {
        try {
            WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
            ResponseVO response;
            if (objectToPut != null) {
                response = request.put(objectToPut);
            } else {
                response = request.put();
            }

            if (response.getStatusCode() < 300) {
                return ResultFactory.getSuccessResultMsg(response.getServerResponse());
            }
            ErrorResponse errorResponse = response.castJsonResponse(ErrorResponse.class);
            return ResultFactory.getFailResult(errorResponse.getErrorMessage(), errorResponse.getErrorCode());
        } catch (Exception ex) {
            log.error("Error occurred: ", ex);
            return ResultFactory.getFailResult("Error occurred: " + ex.getMessage());
        }
    }

    protected <U, T extends ErrorResponse> Result<T> putAndGet(String resourcePath, U objectToPost, Class<T> responseClass) {
        WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
        ResponseVO response = request.put(objectToPost);
        if (response.isValid()) {
            return ResultFactory.getSuccessResult(response.castJsonResponse(responseClass));
        }
        ErrorResponse errorResponse = response.castJsonResponse(ErrorResponse.class);
        return ResultFactory.getFailResult(errorResponse.getErrorMessage(), errorResponse.getErrorCode());
    }

    protected Result<Void> delete(String resourcePath) {
        try {
            WellRestedRequest request = buildRequestFromResourcePath(resourcePath);
            ResponseVO response;
            response = request.delete();

            if (response.getStatusCode() < 300) {
                return ResultFactory.getSuccessResultMsg(response.getServerResponse());
            }
            ErrorResponse errorResponse = response.castJsonResponse(ErrorResponse.class);
            return ResultFactory.getFailResult(errorResponse.getErrorMessage(), errorResponse.getErrorCode());
        } catch (Exception ex) {
            log.error("Error occurred: ", ex);
            return ResultFactory.getFailResult("Error occurred: " + ex.getMessage());
        }
    }

    protected String buildPath(String path) {
        return buildPath(path, null, null);
    }

    protected String buildPathWithSessionToken(String path, String playerSessionToken) {
        return buildPath(path, playerSessionToken, null);
    }

    protected String buildPathWithTemplateRef(String path, String templateReference) {
        return buildPath(path, null, templateReference);
    }

    protected String buildPathWithUser(String path, String user) {
        Map<String, String> values = new HashMap<>(2);
        values.put("operatorId", this.accessCredentials.getOperatorId());
        values.put("user", user);
        StrSubstitutor substitutor = new StrSubstitutor(values);
        return substitutor.replace(path);
    }

    protected String buildPathWithUserAndPlayerFreeRoundsRef(String path, String user, String playerFreeRoundsRef) {
        Map<String, String> values = new HashMap<>(3);
        values.put("operatorId", this.accessCredentials.getOperatorId());
        values.put("user", user);
        values.put("playerFreeRoundsReference", playerFreeRoundsRef);
        StrSubstitutor substitutor = new StrSubstitutor(values);
        return substitutor.replace(path);
    }

    private String buildPath(String path, String playerSessionToken, String templateReference) {
        Map<String, String> values = new HashMap<>(3);
        values.put("operatorId", this.accessCredentials.getOperatorId());
        if (StringUtils.isNotBlank(playerSessionToken)) {
            values.put("playerSessionToken", playerSessionToken);
        }
        if (StringUtils.isNotBlank(templateReference)) {
            values.put("templateReference", templateReference);
        }
        StrSubstitutor substitutor = new StrSubstitutor(values);
        return substitutor.replace(path);
    }

    protected String addAttrAndValueToPath(String path, String attrName, String value) {
        String newPath = validatePathWithAttributes(path);
        return newPath + attrName + EQUAL + value;
    }

}
