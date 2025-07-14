package com.example.crud.exceptions.base;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import com.example.crud.utils.ExceptionUtils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString(callSuper = true)
public class ErrorMessage {

	public static final String ERROR_TEXT = "ERROR|พบข้อผิดพลาด|กรุณาติดต่อผู้ดูแลระบบ";

	private String code;
	private String summary;
	private String detail;
	private String cause;

	public ErrorMessage(final Exception e) {
		this.errorMessage(e, null);
	}

	public ErrorMessage(final Exception e, final Locale locale) {
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
	        ErrorMessage.log.error("Real exception is MissingResourceException:", e);
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
	        this.code = (String) stk.nextElement();
	    }
	    if (stk.hasMoreElements()) {
	        this.summary = (String) stk.nextElement();
	        if (params != null && params.length > 0) {
	            this.summary = MessageFormat.format(this.summary, params);
	        }
	    }
	    if (stk.hasMoreElements()) {
	        this.detail = (String) stk.nextElement();
	        if (params != null && params.length > 0) {
	            this.detail = MessageFormat.format(this.detail, params);
	        }
	    } else {
	        this.detail = "";
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
