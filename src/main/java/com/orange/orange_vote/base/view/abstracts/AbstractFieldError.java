package com.orange.orange_vote.base.view.abstracts;

import com.orange.orange_vote.base.view.BaseFieldError;
import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public abstract class AbstractFieldError implements BaseFieldError {

    private static final long serialVersionUID = 1L;

    private Serializable rejectedValue;

    private String defaultMessage;

    private String code;

    private String field;

}
