package com.georlegacy.general.deathmaze.objects.pagination;

import java.util.ArrayList;
import java.util.List;

public class PaginationSet {

    private List<PaginationPage> pages;

    private int currentPage;

    public PaginationSet(List<String> items, int pageLength) {
        pages = new ArrayList<PaginationPage>();
        currentPage = 0;

        if (items.size() < pageLength) {
            PaginationPage page = new PaginationPage();
            items.forEach(page::addItems);
            pages.add(page);
            return;
        }

        int position = 0;
        while (position <= items.size()) {
            PaginationPage page = new PaginationPage();
            if (position + pageLength - 1 > items.size()) {
                for (String item : items.subList(position, items.size())) {
                    page.addItems(item);
                }
                pages.add(page);
                return;
            }
            for (String item : items.subList(position, position + pageLength)) {
                page.addItems(item);
            }
            pages.add(page);
            position += pageLength;
        }
    }

    public PaginationPage getNextPage() {
        PaginationPage page = pages.get(currentPage);
        if (currentPage != pages.size()) {
            currentPage++;
        }
        return page;
    }

    public PaginationPage getPreviousPage() {
        if (currentPage != 0) {
            currentPage--;
        }
        return pages.get(currentPage);
    }

    public PaginationPage getPage(int index) {
        return pages.get(index);
    }

    public List<PaginationPage> getPages() {
        return pages;
    }

}
