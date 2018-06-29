package com.georlegacy.general.deathmaze.objects.pagination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaginationPage {

    private List<String> items;

    public PaginationPage() {
        items  = new ArrayList<String>();
    }

    public PaginationPage(String... items) {
        this.items  = new ArrayList<String>();

        this.items.addAll(Arrays.asList(items));
    }

    public void addItems(String... items) {
        this.items.addAll(Arrays.asList(items));
    }

    public List<String> getItems() {
        return items;
    }

    public int countItems() {
        return items.size();
    }

}
