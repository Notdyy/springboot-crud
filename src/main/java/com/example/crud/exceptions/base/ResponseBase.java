package com.example.crud.exceptions.base;

import java.io.Serial;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import com.example.crud.utils.ExceptionUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
	public static final String ERROR_TEXT = "ERROR|พบข้อผิดพลาด|กรุณาติดต่อผู้ดูแลระบบ";
    
    @JsonProperty("message_code")
    private String messageCode;

    @JsonProperty("message_summary")
    private String messageSummary;

    @JsonProperty("message_description")
    private String messageDesc;
    
    @JsonProperty("message_cause")
    private String cause;
    
    public ResponseBase(final Exception e) {
		this.errorMessage(e, null);
	}

	public ResponseBase(final Exception e, final Locale locale) {
		this.errorMessage(e, locale);
	}
	
	
	private void errorMessage(final Exception e, Locale locale) {
	    String text = ERROR_TEXT;
	    String name = null;
	    try {
	        if (locale == null) {
	            locale = Locale.getDefault();
	        }
	        this.cause = getCause(e);
	        name = e.getClass().getName();
	        final ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
	        text = bundle.getString(name);
	    } catch (final MissingResourceException mre) {
	        log.error("Real exception is MissingResourceException:", e);
	        text = text + " (" + name + ")";
	    }

	    if (e instanceof final ApiException ex) {
	        this.init(text, this.cause, ex.getParams());
	    } else {
	    	Object[] empty = new Object[0];
	    	this.init(text, this.cause, empty);
	    }
	}

	private void init(final String text, final String cause, final Object... params) {
	    final StringTokenizer stk = new StringTokenizer(text, "|");
	    if (stk.hasMoreElements()) {
	        this.messageCode = (String) stk.nextElement();
	    }
	    if (stk.hasMoreElements()) {
	        this.messageSummary = (String) stk.nextElement();
	        if (params != null && params.length > 0) {
	            this.messageSummary = MessageFormat.format(this.messageSummary, params);
	        }
	    }
	    if (stk.hasMoreElements()) {
	        this.messageDesc = (String) stk.nextElement();
	        if (params != null && params.length > 0) {
	            this.messageDesc = MessageFormat.format(this.messageDesc, params);
	        }
	    } else {
	        this.messageDesc = "";
	    }
	    this.cause = cause;
	}

	private static String getCause(final Exception e) {
		String cause;
		if (e.getCause() != null) {
			cause = e.getCause().getMessage();
		} else if (e instanceof ApiException) {
			cause = null;
		} else {
			cause = ExceptionUtils.exceptionToString(e);
			if (cause.length() > 500) {
				cause = cause.substring(0, 500).concat("...");
			}
		}
		return cause;
	}
}
