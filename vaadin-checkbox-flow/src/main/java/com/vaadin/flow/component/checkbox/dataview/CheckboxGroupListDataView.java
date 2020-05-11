package com.vaadin.flow.component.checkbox.dataview;

import com.vaadin.flow.data.provider.ListDataView;

public interface CheckboxGroupListDataView<T>
        extends ListDataView<T, CheckboxGroupListDataView<T>>, CheckboxGroupDataView<T> {
}
