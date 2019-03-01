/*
 * Copyright 2000-2018 Vaadin Ltd.
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

import java.util.Set;
import java.util.stream.Collectors;

import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("selection-listener")
public class SelectionListenerPage extends Div {

    public SelectionListenerPage() {
        Div selectedInfo = new Div();
        selectedInfo.setId("current-selection");

        CheckboxGroup<String> checkboxGroup = new CheckboxGroup<>();
        checkboxGroup.setItems("foo", "bar");

        // event handler is not invoked
        checkboxGroup.addSelectionListener(event -> {
            Set<String> addedSelection = event.getNewSelection();
            String selected = addedSelection.stream().sorted()
                    .collect(Collectors.joining(", "));
            selectedInfo.setText(selected);
        });

        add(checkboxGroup, selectedInfo);
    }
}
