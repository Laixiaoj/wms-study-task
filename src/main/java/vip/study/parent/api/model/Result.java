package vip.study.parent.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private int code;
    private String message;
    private T response;

    public Result(T response) {
        this.code = 200;
        this.message = "success";
        this.response = response;
    }

    public Result(T data, boolean success, String message) {
        if (success) {
            this.code = 200;
            this.message = "success";
        } else {
            this.code = 500;
            this.message = message;
        }
        this.response = response;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.response = null;
    }

    public static <T> Result<T> success(T response) {
        return new Result<>(response);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(500, message);
    }

    public static <T> Result<T> fail(int code, String message) {
        return new Result<>(code, message);
    }
}

