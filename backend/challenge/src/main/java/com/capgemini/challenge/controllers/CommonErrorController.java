package com.capgemini.challenge.controllers;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
public class CommonErrorController implements ErrorController {

    @Autowired 
    private ErrorAttributes errorAttributes;

    public void setErrorAttributes(ErrorAttributes errorAttributes) {

        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    @ResponseBody
    public ExceptionResponse error(WebRequest webRequest, HttpServletResponse response) {

        return new ExceptionResponse(response.getStatus(), getErrorAttributes(webRequest));
    }

    @Override
    public String getErrorPath() {

        return "/error";
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest) {
        
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.putAll(errorAttributes.getErrorAttributes(webRequest, false));
        return errorMap;
    }
  
    private class ExceptionResponse {

        private Integer status;
        private String path;
        private String errorMessage;
        private String timeStamp;
        private String trace;

        public ExceptionResponse(int status, Map<String, Object> errorAttributes) {

            this.setStatus(status);
            this.setPath((String) errorAttributes.get("path"));
            this.setErrorMessage((String) errorAttributes.get("message"));
            this.setTimeStamp(errorAttributes.get("timestamp").toString());
            this.setTrace((String) errorAttributes.get("trace"));
        }

        public Integer getStatus() {
          return status;
        }

        private void setStatus(Integer status) {
          this.status = status;
        }

        public String getErrorMessage() {
          return errorMessage;
        }

        private void setErrorMessage(String errorMessage) {
          this.errorMessage = errorMessage;
        }

        public String getTimeStamp() {
          return timeStamp;
        }

        private void setTimeStamp(String timeStamp) {
          this.timeStamp = timeStamp;
        }

        public String getTrace() {
          return trace;
        }

        private void setTrace(String trace) {
          this.trace = trace;
        }

        public String getPath() {
          return path;
        }

        private void setPath(String path) {
          this.path = path;
        }
    }
}