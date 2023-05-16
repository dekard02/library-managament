package com.dekard02.librarymanagement.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.dekard02.librarymanagement.entity.Category;
import com.github.slugify.Slugify;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryListener {

    private final Slugify slugify;

    @PrePersist
    @PreUpdate
    public void generateSlug(Category category) {
        var name = category.getName();
        category.setSlug(slugify.slugify(name));
    }

}
