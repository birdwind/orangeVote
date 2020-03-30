package com.orange.orange_vote.view.system;

import com.orange.orange_vote.base.view.BaseView;
import com.orange.orange_vote.view.module.ModuleView;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleAuthView implements BaseView {

    private static final long serialVersionUID = 1L;

    private List<ModuleView> modules;

}
