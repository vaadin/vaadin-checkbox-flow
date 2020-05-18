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
package com.vaadin.flow.component.checkbox.tests;

import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.dataview.CheckboxGroupListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

import java.util.stream.Collectors;

@Route("vaadin-checkbox-group-test-data-view")
public class DataViewPage extends Div {

    static final String CHECKBOX_GROUP = "checkbox-group-data-view";
    static final String ITEMS_SIZE = "size-span-data-view";
    static final String ITEM_PRESENT = "item-present-span-data-view";
    static final String ALL_ITEMS = "all-items-span-data-view";
    static final String ITEM_ON_INDEX = "item-on-index-data-view";

    public DataViewPage() {
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        CheckboxGroupListDataView<String> dataView =
                checkboxGroup.setDataProvider("foo", "bar", "baz");

        Span sizeSpan = new Span(String.valueOf(dataView.getDataSize()));
        Span containsItemSpan = new Span(String.valueOf(dataView.isItemPresent("foo")));
        Span allItemsSpan = new Span(dataView.getAllItems().collect(Collectors.joining(",")));
        Span itemOnIndexSpan = new Span(dataView.getItemOnIndex(0));

        checkboxGroup.setId(CHECKBOX_GROUP);
        sizeSpan.setId(ITEMS_SIZE);
        containsItemSpan.setId(ITEM_PRESENT);
        allItemsSpan.setId(ALL_ITEMS);
        itemOnIndexSpan.setId(ITEM_ON_INDEX);

        add(checkboxGroup, sizeSpan, containsItemSpan, allItemsSpan, itemOnIndexSpan);
    }
}
