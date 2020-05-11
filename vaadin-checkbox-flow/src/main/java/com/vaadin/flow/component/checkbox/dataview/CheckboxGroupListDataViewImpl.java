package com.vaadin.flow.component.checkbox.dataview;

import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.data.provider.*;
import com.vaadin.flow.function.SerializableComparator;
import com.vaadin.flow.function.SerializableConsumer;
import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.function.SerializablePredicate;
import com.vaadin.flow.shared.Registration;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckboxGroupListDataViewImpl<T> implements CheckboxGroupListDataView<T> {

    private int itemsSize = 0;
    private Set<SizeChangeListener> sizeChangeListeners;
    private CheckboxGroup<T> component;

    public CheckboxGroupListDataViewImpl(CheckboxGroup<T> checkboxGroup) {
        this.component = checkboxGroup;
    }

    @Override
    public T getNextItem(T item) {
        return getNextOrPreviousItem(item, itemsList -> {
            int itemIndex = itemsList.indexOf(item);
            if (itemIndex != -1 && itemIndex < itemsList.size() - 1) {
                return itemsList.get(itemIndex + 1);
            }
            return null;
        });
    }

    @Override
    public boolean hasNextItem(T item) {
        return getNextItem(item) != null;
    }

    @Override
    public T getPreviousItem(T item) {
        return getNextOrPreviousItem(item, itemsList -> {
            int itemIndex = itemsList.indexOf(item);
            if (itemIndex > 0) {
                return itemsList.get(itemIndex - 1);
            }
            return null;
        });
    }

    @Override
    public boolean hasPreviousItem(T item) {
        return getPreviousItem(item) != null;
    }

    @Override
    public CheckboxGroupListDataView<T> withFilter(SerializablePredicate<T> filter) {
        CheckboxGroupListDataView<T> dataView = withFilterOrOrder(
                dataProvider -> dataProvider.setFilter(filter));
        // fire size change event if size has changed
        getDataSize();
        return dataView;
    }

    @Override
    public CheckboxGroupListDataView<T> withSortComparator(SerializableComparator<T> sortComparator) {
        return withFilterOrOrder(dataProvider -> dataProvider.setSortComparator(sortComparator));
    }

    @Override
    public Stream<T> getAllItems() {
        DataProvider<T, ?> dataProvider = getDataProvider();
        return dataProvider.fetch(new Query<>());
    }

    @Override
    public int getDataSize() {
        int size = getDataProvider().size(new Query<>());
        if (size != itemsSize && sizeChangeListeners != null) {
            sizeChangeListeners.forEach(listener -> listener.sizeChanged(
                    new SizeChangeEvent<>(this.component, size)));
            itemsSize = size;
        }
        return size;
    }

    @Override
    public boolean dataContainsItem(T item) {
        // InMemoryDataProviders do not override getId(), so rely on equals() method
        return getAllItems().collect(Collectors.toList()).contains(item);
    }

    @Override
    public T getItemOnRow(int row) {
        if (row < 0) {
            throw new IndexOutOfBoundsException("Row number should be zero on greater");
        }

        List<T> itemsList = getAllItems().collect(Collectors.toList());

        if (itemsList.isEmpty()) {
            throw new IndexOutOfBoundsException("Item requested on an empty data set");
        }

        final int itemsSize = itemsList.size();
        if (row >= itemsSize) {
            throw new IndexOutOfBoundsException(
                    String.format("Row number is outside of data set size, row=%d, data set size=%d", row, itemsSize));
        }

        return itemsList.get(row);
    }

    // This could be a common method on top of List- and Lazy- DataViews
    @Override
    public Registration addSizeChangeListener(SizeChangeListener listener) {
        if (sizeChangeListeners == null) {
            sizeChangeListeners = new HashSet<>();
        }
        sizeChangeListeners.add(listener);
        return () -> sizeChangeListeners.remove(listener);
    }

    // This is specific for components
    private DataProvider<T, ?> getDataProvider() {
        return this.component.getDataProvider();
    }

    @SuppressWarnings("unchecked")
    private CheckboxGroupListDataView<T> withFilterOrOrder(
            SerializableConsumer<ListDataProvider<T>> filterOrOrderConsumer) {
        DataProvider<T, ?> dataProvider = getDataProvider();
        filterOrOrderConsumer.accept((ListDataProvider<T>) dataProvider);
        return this;
    }

    private T getNextOrPreviousItem(T item, SerializableFunction<List<T>, T> itemProvider) {
        Objects.requireNonNull(item, "Item cannot be null");
        Objects.requireNonNull(itemProvider, "Item Provider cannot be null");
        List<T> allItemsList = getAllItems().collect(Collectors.toList());

        if (!allItemsList.isEmpty()) {
            return itemProvider.apply(allItemsList);
        }

        return null;
    }
}
