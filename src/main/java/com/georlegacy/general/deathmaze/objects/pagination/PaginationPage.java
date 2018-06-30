package com.georlegacy.general.deathmaze.objects.pagination;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaginationPage {

    private List<String> items;

    @Getter @Setter
    private int number;

    public PaginationPage() {
        items  = new ArrayList<String>();
    }

    public PaginationPage(int number, String... items) {
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
