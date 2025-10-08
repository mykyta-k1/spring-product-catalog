package com.product.util;

import com.github.slugify.Slugify;
import java.util.UUID;

public final class SlugGenerator {

    private static final Slugify slugify = Slugify.builder().build();

    public static String generateSlug(String template, UUID id) {
        return slugify.slugify(template.concat(id.toString().split("-")[0]));
    }
}
