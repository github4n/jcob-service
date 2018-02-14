package com.aicai.jcob.exception.common;

/**
 * Jcob 服务只有一个异常类
 * 不同的异常用ExceptionCode.code 分辨
 * @author fx
 *
 */
public class JcobServerException extends RuntimeException {

    private static final long serialVersionUID = -7673793242894704838L;

    protected ExceptionCode code;

    
   
    public JcobServerException(ExceptionCode code) {
        super(code.getMsg());
        this.code = code;
       
    }
    
    public JcobServerException(ExceptionCode code,String param) {
    	super(code.getMsg().replace("{}", param));
        this.code = code;
    }
    
    public JcobServerException(ExceptionCode exceptionCode,Throwable cause) {
        super(exceptionCode.getMsg(),cause);
        this.code = exceptionCode;
    
    }
    
    
    public JcobServerException(String code,String exceptionMsg,Throwable cause) {
    	super(exceptionMsg,cause);
        this.code = new ExceptionCode(code,exceptionMsg);
    }
    
    public JcobServerException(String code,String errorMsg) {
        super(errorMsg);    
        this.code = new ExceptionCode(code,errorMsg);
    }
    
   
    public ExceptionCode getExceptionCode(){
    	return code;
    }
    public String gerMsg(){
    	return code.getMsg();
    }
    
    public String getCode(){
    	return code.getCode();
    }
}
