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

import java.util.List;

@TestPath("vaadin-checkbox-group-test-data-view")
public class DataViewIT extends AbstractComponentIT {

    @Test
    public void baseDataView_baseDataViewApiRequested_dataAvailable() {
        open();

        TestBenchElement checkboxGroup = $("vaadin-checkbox-group").id(DataViewPage.CHECKBOX_GROUP);
        List<TestBenchElement> checkboxes = checkboxGroup.$("vaadin-checkbox").all();

        // Checkbox items size
        Assert.assertEquals("Unexpected checkbox count", 3, checkboxes.size());

        // Data set size
        Assert.assertEquals("Unexpected item count", "3",
                $("span").id(DataViewPage.ITEMS_SIZE).getText());

        // Data set content
        Assert.assertEquals("Unexpected checkbox labels", "foo,bar,baz",
                $("span").id(DataViewPage.ALL_ITEMS).getText());

        // Item present
        Assert.assertEquals("Item 'foo' is expected to be present", "true",
                $("span").id(DataViewPage.ITEM_PRESENT).getText());

        // Item on index
        Assert.assertEquals("Item 'foo' is expected on index 0", "foo",
                $("span").id(DataViewPage.ITEM_ON_INDEX).getText());
    }
}
