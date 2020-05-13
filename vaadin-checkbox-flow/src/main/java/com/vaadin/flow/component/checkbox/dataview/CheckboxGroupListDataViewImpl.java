/*
 * Copyright 2000-2020 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.component.checkbox.dataview;

import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.SizeChangeAware;

/**
 * Implementation of {@link CheckboxGroupListDataView} interface for {@link CheckboxGroup} component.
 *
 * @param <T>
 *         data type
 */
public class CheckboxGroupListDataViewImpl<T> extends CheckboxGroupListDataView<T> {

    public CheckboxGroupListDataViewImpl(CheckboxGroup<T> checkboxGroup, DataProvider<T, ?> dataProvider) {
        super(checkboxGroup);
        if (dataProvider instanceof SizeChangeAware) {
            ((SizeChangeAware) dataProvider).setSizeChangeHandler(this);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ListDataProvider<T> getDataProvider() {
        // CheckboxGroup data view is always backed by ListDataProvider
        return (ListDataProvider<T>) this.component.getDataProvider();
    }
}
