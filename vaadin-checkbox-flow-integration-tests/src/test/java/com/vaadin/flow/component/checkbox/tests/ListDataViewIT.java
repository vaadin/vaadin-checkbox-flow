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

import com.vaadin.flow.testutil.AbstractComponentIT;
import com.vaadin.flow.testutil.TestPath;
import com.vaadin.testbench.TestBenchElement;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

@TestPath("vaadin-checkbox-group-test-list-data-view")
public class ListDataViewIT extends AbstractComponentIT {

    @Test
    public void listDataView_listDataViewApiRequested_dataAvailable() {
        open();

        TestBenchElement checkboxGroup = $("vaadin-checkbox-group").id(ListDataViewPage.CHECKBOX_GROUP);

        // Has next item
        Assert.assertEquals("Next item is expected", "true",
                $("span").id(ListDataViewPage.HAS_NEXT_ITEM).getText());

        // Has previous item
        Assert.assertEquals("Previous item is expected", "true",
                $("span").id(ListDataViewPage.HAS_PREVIOUS_ITEM).getText());

        findElement(By.id(ListDataViewPage.NEXT_ITEM)).click();

        // Next item
        Assert.assertEquals("Unexpected next item", "baz",
                $("span").id(ListDataViewPage.CURRENT_ITEM).getText());

        findElement(By.id(ListDataViewPage.PREVIOUS_ITEM)).click();

        // Previous item
        Assert.assertEquals("Unexpected previous item", "bar",
                $("span").id(ListDataViewPage.CURRENT_ITEM).getText());

        findElement(By.id(ListDataViewPage.SORT_BUTTON)).click();

        // Sort order
        Assert.assertEquals("Unexpected sort order", "bar,baz,foo",
                checkboxGroup.$("vaadin-checkbox").all().stream()
                        .map(TestBenchElement::getText).collect(Collectors.joining(",")));

        findElement(By.id(ListDataViewPage.FILTER_BUTTON)).click();

        // Filtering
        List<TestBenchElement> checkboxes = checkboxGroup.$("vaadin-checkbox").all();
        Assert.assertEquals("Unexpected filtered checkbox count", 1, checkboxes.size());
        Assert.assertEquals("Unexpected filtered checkbox item", "bar", checkboxes.get(0).getText());
    }
}
