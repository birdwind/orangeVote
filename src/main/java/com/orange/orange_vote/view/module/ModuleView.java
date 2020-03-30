package com.orange.orange_vote.view.module;

import com.orange.orange_vote.base.view.BaseView;
import com.orange.orange_vote.view.function.FunctionView;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleView implements BaseView {

    private static final long serialVersionUID = 1L;

    private Integer moduleId;

    private String moduleNo;

    private String moduleKey;

    private String moduleValue;

    private Boolean status;

    private String note;

    private List<FunctionView> function;

}
