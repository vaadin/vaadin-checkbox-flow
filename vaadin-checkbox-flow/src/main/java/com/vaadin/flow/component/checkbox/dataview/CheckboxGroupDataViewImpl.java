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
import com.vaadin.flow.data.provider.AbstractDataView;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.function.SerializableSupplier;
import com.vaadin.flow.function.ValueProvider;

/**
 * Implementation of the base DataView applicable for use with any
 * DataProvider.
 * Note that this has a minimal API and using {@link CheckboxGroupListDataView}
 * is advised.
 *
 * @param <T>
 *         item type
 * @since
 */
public class CheckboxGroupDataViewImpl<T> extends AbstractDataView<T>
        implements CheckboxGroupDataView<T> {

    /**
     * Constructs a new DataView.
     *
     * @param dataProviderSupplier
     *         data provider supplier
     * @param checkboxGroup
     *         checkbox instance for this DataView
     */
    public CheckboxGroupDataViewImpl(
            SerializableSupplier<DataProvider<T, ?>> dataProviderSupplier,
            CheckboxGroup<T> checkboxGroup) {
        super(dataProviderSupplier, checkboxGroup);
    }

    @Override
    public T getItemOnIndex(int index) {
        final int dataSize = getSize();
        if (index < 0 || index >= dataSize) {
            throw new IndexOutOfBoundsException(String.format(
                    "Given index %d is outside of the accepted range '0 - %d'",
                    index, dataSize - 1));
        }
        return getItems().skip(index).findFirst().orElse(null);
    }

    @Override
    public boolean contains(T item) {
        final DataProvider<T, ?> dataProvider = dataProviderSupplier.get();
        final Object itemIdentifier = dataProvider.getId(item);
        return getItems()
                .anyMatch(i -> itemIdentifier.equals(dataProvider.getId(i)));
    }

    @Override
    protected Class<?> getSupportedDataProviderType() {
        return DataProvider.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setIdentityProvider(ValueProvider<T, ?> identityProvider) {
        super.setIdentityProvider(identityProvider);
        if (!(component instanceof CheckboxGroup)) {
            throw new IllegalStateException(String.format(
                    "CheckboxGroupDataView cannot be used with component %s. " +
                    "Use CheckboxGroup component instead.",
                    component.getClass().getSimpleName()));
        }
        CheckboxGroup<T> checkboxGroup = (CheckboxGroup<T>) component;
        checkboxGroup.setIdentityProvider(identityProvider);
    }
}