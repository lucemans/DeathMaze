package com.georlegacy.general.deathmaze.objects.pagination;

import org.bukkit.Bukkit;

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
            page.setNumber(1);
            pages.add(page);
            return;
        }

        int position = 0;
        int pageNumber = 1;
        while (position <= items.size()) {
            PaginationPage page = new PaginationPage();
            if (position + pageLength - 1 > items.size()) {
                for (String item : items.subList(position, items.size())) {
                    page.addItems(item);
                }
                page.setNumber(pageNumber);
                pages.add(page);
                pageNumber++;
                position += pageLength;
                return;
            }
            for (String item : items.subList(position, position + pageLength)) {
                page.addItems(item);
            }
            page.setNumber(pageNumber);
            pages.add(page);
            pageNumber++;
            position += pageLength;
        }
    }

    public PaginationPage getNextPage() {
        if (currentPage + 1 != pages.size()) {
            currentPage++;
            return pages.get(currentPage);
        } else
            return new EmptyPaginationPage();
    }

    public PaginationPage getPreviousPage() {
        if (currentPage != 0) {
            currentPage--;
            return pages.get(currentPage);
        } else
            return new EmptyPaginationPage();
    }

    public PaginationPage getPage(int index) {
        return pages.get(index);
    }

    public List<PaginationPage> getPages() {
        return pages;
    }

}
