package ink.qs.excp;

import ink.qs.utils.ResultCodeEnum;
import java.io.Serializable;

public class EduGlobalException extends Exception implements Serializable {

    //模版异常
    private ResultCodeEnum exceptionEnums;
    //自定义异常信息
    private String errorDetail;

    /**
     * 带自定义异常信息的构造方法
     * @param exceptionEnums
     * @param errorDetail
     */
    public EduGlobalException(ResultCodeEnum exceptionEnums,String errorDetail){
        this.exceptionEnums = exceptionEnums;
        this.errorDetail = errorDetail;
    }

    public EduGlobalException(ResultCodeEnum exceptionEnums) {
        this.exceptionEnums = exceptionEnums;
    }

    public EduGlobalException() {
        super();
    }

    public ResultCodeEnum getExceptionEnums() {
        return exceptionEnums;
    }

    public void setExceptionEnums(ResultCodeEnum exceptionEnums) {
        this.exceptionEnums = exceptionEnums;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
