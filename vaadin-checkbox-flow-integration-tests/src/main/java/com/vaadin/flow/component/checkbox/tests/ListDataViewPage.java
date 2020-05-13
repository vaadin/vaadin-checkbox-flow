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

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.dataview.CheckboxGroupListDataView;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

import java.util.concurrent.atomic.AtomicReference;

@Route("vaadin-checkbox-group-test-list-data-view")
public class ListDataViewPage extends Div {

    public ListDataViewPage() {
        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        CheckboxGroupListDataView<String> dataView =
                checkboxGroup.setDataProvider("foo", "bar", "baz");

        AtomicReference<String> currentItem = new AtomicReference<>("bar");

        Span currentItemSpan = new Span(currentItem.get());

        Span hasNextItemSpan = new Span(String.valueOf(dataView.hasNextItem("foo")));
        Span hasPrevItemSpan = new Span(String.valueOf(dataView.hasPreviousItem("bar")));

        Button nextItemButton = new Button("Next Item", event -> {
            String nextItem = dataView.getNextItem(currentItem.get());
            currentItem.set(nextItem);
            currentItemSpan.setText(currentItem.get());
        });
        Button prevItemButton = new Button("Previous Item", event -> {
            String prevItem = dataView.getPreviousItem(currentItem.get());
            currentItem.set(prevItem);
            currentItemSpan.setText(currentItem.get());
        });
        Button filterButton = new Button("Filter Items",
                event -> dataView.withFilter(item -> item.equals("bar")));
        Button sortButton = new Button("Sort Items",
                event -> dataView.withSortComparator(String::compareTo));

        checkboxGroup.setId("checkbox-group-list-data-view");
        currentItemSpan.setId("current-item-span-list-data-view");
        hasNextItemSpan.setId("has-next-item-span-list-data-view");
        hasPrevItemSpan.setId("has-prev-item-span-list-data-view");
        nextItemButton.setId("next-item-button-list-data-view");
        prevItemButton.setId("prev-item-button-list-data-view");
        filterButton.setId("filter-button-list-data-view");
        sortButton.setId("sort-button-list-data-view");

        add(checkboxGroup, currentItemSpan, hasNextItemSpan, hasPrevItemSpan, filterButton,
                sortButton, nextItemButton, prevItemButton);
    }
}
